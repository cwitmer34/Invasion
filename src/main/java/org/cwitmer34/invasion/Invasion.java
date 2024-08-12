package org.cwitmer34.invasion;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.cwitmer34.invasion.framework.CommandFramework;

public final class Invasion extends JavaPlugin {

	@Getter
	private static Invasion plugin;

	@Override
	public void onEnable() {
		plugin = this;
		new CommandFramework(this);
		saveDefaultConfig();
	}

	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);
	}
}
