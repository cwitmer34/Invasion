package org.cwitmer34.invasion.framework;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.util.ClassScanner;
import org.cwitmer34.invasion.util.ConsoleUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class CommandFramework implements CommandExecutor {
	private final Map<String, Method> commandMap = new HashMap<>();
	private final Map<String, Object> instanceMap = new HashMap<>();
	private final Map<UUID, Long> cooldowns = new HashMap<>();
	private final Invasion invasion;

	public CommandFramework(final Invasion invasion) {
		this.invasion = invasion;
		registerCommands();
	}

	private void registerCommands() {
		final Set<Class<?>> commandClasses = ClassScanner.findClassesWithAnnotation(Command.class);
		for (final Class<?> commandClass : commandClasses) {
			registerCommandClass(commandClass);
		}
	}

	private void registerCommandClass(final Class<?> commandClass) {
		try {
			final Object commandInstance = commandClass.getDeclaredConstructor().newInstance();
			for (final Method method : commandClass.getDeclaredMethods()) {
				if (method.isAnnotationPresent(Command.class)) {
					final Command command = method.getAnnotation(Command.class);
					final String primaryName = command.names()[0];
					final String[] aliases = Arrays.copyOfRange(command.names(), 1, command.names().length);
					final String description = command.description();

					PluginCommand pluginCommand = createPluginCommand(primaryName, invasion);
					if (pluginCommand != null) {
						pluginCommand.setExecutor(this);
						pluginCommand.setAliases(Arrays.asList(aliases));
						pluginCommand.setDescription(description);
						registerCommand(pluginCommand);

						commandMap.put(primaryName.toLowerCase(), method);
						instanceMap.put(primaryName.toLowerCase(), commandInstance);
						invasion.getLogger().info("Registered command: " + primaryName + " with aliases: " + Arrays.toString(aliases));

						for (String alias : aliases) {
							commandMap.put(alias.toLowerCase(), method);
							instanceMap.put(alias.toLowerCase(), commandInstance);
							invasion.getLogger().info("Registered alias: " + alias + " for command: " + primaryName);
						}
					} else {
						invasion.getLogger().severe("Command '" + primaryName + "' could not be created.");
					}
				}
			}
		} catch (Exception e) {
			ConsoleUtil.severe("Error registering command class: " + e.getMessage());
		}
	}

	private PluginCommand createPluginCommand(final String name, final Invasion plugin) {
		try {
			final Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			constructor.setAccessible(true);
			return constructor.newInstance(name, plugin);
		} catch (Exception e) {
			ConsoleUtil.severe(e.getMessage());
			return null;
		}
	}

	private void registerCommand(final PluginCommand command) {
		try {
			final Field commandMapField = invasion.getServer().getClass().getDeclaredField("commandMap");
			commandMapField.setAccessible(true);
			final CommandMap commandMap = (CommandMap) commandMapField.get(invasion.getServer());

			commandMap.register(invasion.getPluginMeta().getName(), command);
		} catch (final Exception e) {
			ConsoleUtil.severe(e.getMessage());
		}
	}

	@Override
	public boolean onCommand(final CommandSender sender, final org.bukkit.command.Command command, final String label, final String[] args) {
		final Method method = commandMap.get(label.toLowerCase());
		final Object instance = instanceMap.get(label.toLowerCase());

		if (method != null && instance != null) {
			try {
				final Command cmd = method.getAnnotation(Command.class);

				if (!(sender instanceof final Player player)) {
					return true;
				}

				if (!cmd.permission().isEmpty() && !sender.hasPermission(cmd.permission())) {
					sender.sendMessage(
									Component.empty()
													.append(Component.text("You do not have permission to use").color(NamedTextColor.RED))
													.append(Component.text(" /" + label).color(NamedTextColor.GRAY))
					);
					return true;
				}

				final UUID playerId = player.getUniqueId();

				if (cmd.cooldown() > 0 && !player.hasPermission("prisons.staff")) {
					long currentTime = System.currentTimeMillis();
					long cooldownTime = cooldowns.getOrDefault(playerId, 0L);

					if (currentTime < cooldownTime) {
						long timeLeft = (cooldownTime - currentTime) / 1000;
						player.sendMessage(Component.text("You must wait " + timeLeft + " seconds before using this command again.").color(NamedTextColor.RED));
						return true;
					}

					cooldowns.put(playerId, currentTime + cmd.cooldown() * 1000L);
				}

				method.invoke(instance, sender, args);
			} catch (final Exception e) {
				ConsoleUtil.severe(e.getMessage());
			}
			return true;
		}

		return false;
	}
}
