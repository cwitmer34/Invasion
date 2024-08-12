package org.cwitmer34.invasion;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Invasion extends JavaPlugin {

	@Getter
	private static Invasion plugin;

	@Override
	public void onEnable() {
		// Plugin startup logic
		plugin = this;
	}

	@Override
	public void onDisable() {
			// Plugin shutdown logic
	}
}
