package org.cwitmer34.invasion.config;

import net.kyori.adventure.text.Component;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.util.ChatUtil;

import java.util.List;

public class Config {
	private static final ConfigFile config = new ConfigFile(Invasion.getPlugin().getDataFolder(), "config.yml");

	public static final Component PREFIX = ChatUtil.fromMiniMessage(config.getString("prefix"));
	public static final boolean IS_ENABLED = config.getBoolean("enabled");
	public static final boolean SHOULD_ANNOUNCE_START = config.getBoolean("start.announce");
	public static final int START_ANNOUNCE_INTERVAL = config.getInt("settings.announce-interval") * 1200;
	public static final int START_ANNOUNCE_DELAY = config.getInt("settings.announce-delay") * 1200;
	public static final Component START_MESSAGE = ChatUtil.fromMiniMessage(config.getString("start.message"));
	public static final int EVENT_INTERVAL = config.getInt("settings.interval") * 1200;
	public static final int TIME_LIMIT = config.getInt("settings.time-limit") * 1200;
	public static final int EVENT_DURATION = config.getInt("settings.duration") * 1200;
	public static final boolean WARNING = config.getBoolean("settings.ten-second-warning");
	public static final boolean FIVE_SECOND_COUNTDOWN = config.getBoolean("settings.five-second-countdown");

	public static List<Object> settings = List.of(
		PREFIX,
		IS_ENABLED,
		SHOULD_ANNOUNCE_START,
		START_ANNOUNCE_INTERVAL,
		START_ANNOUNCE_DELAY,
		START_MESSAGE,
		EVENT_INTERVAL,
		TIME_LIMIT,
		EVENT_DURATION,
		WARNING,
		FIVE_SECOND_COUNTDOWN
	);

}
