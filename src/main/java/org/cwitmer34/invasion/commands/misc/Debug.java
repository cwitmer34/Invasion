package org.cwitmer34.invasion.commands.misc;

import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.framework.Command;

public class Debug {
	@Command(names = {"debug", "d"}, permission = "invasion.admin", description = "config debug command")
	@SneakyThrows
	public boolean execute(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		Config.settings.forEach((thing) -> player.sendMessage(thing.toString()));
		return true;
	}
}
