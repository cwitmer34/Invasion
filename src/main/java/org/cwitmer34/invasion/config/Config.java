package org.cwitmer34.invasion.config;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.util.ChatUtil;

public class Config {
	private static final ConfigFile config = new ConfigFile(Invasion.getPlugin().getDataFolder(), "config.yml");

	public static final String PREFIX = config.getString("prefix");
	public static final boolean ENABLED = config.getBoolean("enabled");
	public static final long INTERVAL = config.getLong("interval");
	public static final boolean SHOULD_ANNOUNCE = config.getBoolean("announce.enabled");
	public static final Component START_ANNOUNCEMENT = ChatUtil.fromMiniMessage(config.getString("announce.start"));
	public static final Component SUCCESS_ANNOUNCEMENT = ChatUtil.fromMiniMessage(config.getString("announce.success"));
	public static final Component FAILURE_ANNOUNCEMENT = ChatUtil.fromMiniMessage(config.getString("announce.failure"));
	public static final int START_ANNOUNCEMENT_DELAY = config.getInt("announce.start-delay") * 1200;
	public static final int START_ANNOUNCEMENT_INTERVAL = config.getInt("announce.start-interval") * 1200;
}
