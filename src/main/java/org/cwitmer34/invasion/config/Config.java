package org.cwitmer34.invasion.config;

import java.util.List;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.util.MessageUtil;

public class Config {

  private static final ConfigFile config = new ConfigFile(Invasion.getPlugin().getDataFolder(), "config.yml");

  public static final Component PREFIX = MessageUtil.fromMiniMessage(config.getString("prefix"));
  public static final boolean IS_ENABLED = config.getBoolean("enabled");
  public static final boolean SHOULD_ANNOUNCE_START = config.getBoolean("start.announce");
  public static final int START_ANNOUNCE_INTERVAL = config.getInt("settings.announce-interval");
  public static final int START_ANNOUNCE_DELAY = config.getInt("settings.announce-delay");
  public static final Component MESSAGE_CONTAINER = MessageUtil.fromMiniMessage(config.getString("message-container"));
  public static final Component START_MESSAGE = MessageUtil.fromMiniMessage(config.getString("start.message"));
  public static final int EVENT_INTERVAL = config.getInt("settings.event-interval");
  public static final List<Location> INVASION_LOCATIONS = config.getLocations("locations");
  public static final int TIME_LIMIT = config.getInt("settings.time-limit");
  public static final int MAX_WAVE = config.getInt("settings.max-wave");
  public static final int BASE_ALIENS = config.getInt("settings.base-amount");
  public static final int INCREASE_PER_WAVE = config.getInt("settings.increase-per-wave");
  public static final int EVENT_DURATION = config.getInt("settings.duration");
  public static final boolean SHOULD_WARN = config.getBoolean("settings.warning");
  public static final int WARNING_OCCURRENCE = config.getInt("settings.warning-occurrence");
  public static final int PER_SECOND_COUNTDOWN = config.getInt("settings.per-second-countdown");

  public static final int ELITE_HEALTH = config.getInt("aliens.elite.health");
  public static final int ELITE_DAMAGE = config.getInt("aliens.elite.damage");
  public static final int ELITE_SPEED = config.getInt("aliens.elite.speed");
  public static final int ADVANCED_HEALTH = config.getInt("aliens.advanced.health");
  public static final int ADVANCED_DAMAGE = config.getInt("aliens.advanced.damage");
  public static final int ADVANCED_SPEED = config.getInt("aliens.advanced.speed");
  public static final int BASIC_HEALTH = config.getInt("aliens.basic.health");
  public static final int BASIC_DAMAGE = config.getInt("aliens.basic.damage");
  public static final int BASIC_SPEED = config.getInt("aliens.basic.speed");

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
    SHOULD_WARN,
    PER_SECOND_COUNTDOWN
  );
}
