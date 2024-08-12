package org.cwitmer34.invasion;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.cwitmer34.invasion.events.listeners.InvasionListeners;
import org.cwitmer34.invasion.framework.CommandFramework;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.scheduler.Scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Invasion extends JavaPlugin {

	@Getter
	private static Map<UUID, ActiveInvasion> activeInvasions = new HashMap<>();

	@Getter
	private static Invasion plugin;

	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		Scheduler.start();
		new CommandFramework(this);
		new InvasionListeners();
	}

	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);
	}
}
