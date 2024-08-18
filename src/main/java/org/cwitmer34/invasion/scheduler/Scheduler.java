package org.cwitmer34.invasion.scheduler;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.events.InvasionStartEvent;
import org.cwitmer34.invasion.util.CleanupUtil;
import org.cwitmer34.invasion.util.ConsoleUtil;
import org.cwitmer34.invasion.util.LocationUtil;
import org.cwitmer34.invasion.util.MessageUtil;

public class Scheduler {

  @Getter
  @Setter
  private static int eventTaskId = -1;

  @Getter
  @Setter
  private static Duration timeUntilNextInvasion;

  @Getter
  @Setter
  private static Duration timeUntilNextAnnouncement;

  public static void start() {
    if (!Config.IS_ENABLED) {
      ConsoleUtil.severe("Invasions are set to be disabled. If this is a mistake, please enable them in the config.");
      return;
    }

    Duration duration = Duration.ofMinutes(Config.EVENT_INTERVAL);
    Duration delay = Duration.ofMinutes(Config.START_ANNOUNCE_DELAY);
    Duration announceInterval = Duration.ofMinutes(Config.START_ANNOUNCE_INTERVAL);
    timeUntilNextAnnouncement = delay;
    timeUntilNextInvasion = duration;

    RepeatingTask task = new RepeatingTask(
      () -> {
        Bukkit.broadcast(Component.text(timeUntilNextInvasion.toSeconds()));
        if (timeUntilNextInvasion.isZero() || timeUntilNextInvasion.isNegative()) {
          InvasionStartEvent event = new InvasionStartEvent(
            InvasionTier.getRandom(),
            LocationUtil.getRandom(),
            duration
          );
          event.callEvent();
          timeUntilNextInvasion = duration;
        } else {
          timeUntilNextInvasion = timeUntilNextInvasion.minusSeconds(5);
          timeUntilNextAnnouncement = timeUntilNextAnnouncement.minusSeconds(5);
          if (timeUntilNextAnnouncement.toSeconds() <= 0 && timeUntilNextInvasion.toSeconds() > 60) {
            timeUntilNextAnnouncement = announceInterval;
            Bukkit.broadcast(
              Component.text("Announcement: " + timeUntilNextInvasion.toMinutes() + " minutes until the next invasion.")
            );
          } else if (timeUntilNextInvasion.toSeconds() == 60) {
            countdown();
          }
        }
      },
      0,
      100L
    );
    setEventTaskId(task.getId());
  }

  private static void countdown() {
    new Countdown(60, 20, Invasion.getPlugin()) {
      @Override
      public void count(int current) {
        if (current % Config.WARNING_OCCURANCE == 0 && current > Config.PER_SECOND_COUNTDOWN) {
          MessageUtil.announce(Component.text("Invasion in " + current + " seconds", NamedTextColor.RED));
        } else if (current <= Config.PER_SECOND_COUNTDOWN) {
          MessageUtil.announce(Component.text(current, NamedTextColor.RED));
        }
      }
    };
  }

  public static void stop() {
    Invasion.getPlugin().getServer().getScheduler().cancelTask(eventTaskId);
    eventTaskId = -1;
    CleanupUtil.start();
  }
}
