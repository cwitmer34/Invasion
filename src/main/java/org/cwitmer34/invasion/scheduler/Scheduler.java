package org.cwitmer34.invasion.scheduler;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.events.InvasionStartEvent;
import org.cwitmer34.invasion.util.CleanupUtil;
import org.cwitmer34.invasion.util.LocationUtil;
import org.cwitmer34.invasion.util.MessageUtil;

import java.time.Duration;

public class Scheduler {
	@Getter
	@Setter
	private static int eventTaskId = -1;
	@Getter
	@Setter
	private static int announcementTaskId = -1;
	@Getter
	@Setter
	private static Duration timeUntilNextInvasion;

	public static void start() {
		Duration duration = Duration.ofMinutes(Config.EVENT_DURATION);
		timeUntilNextInvasion = Duration.ofMillis(Config.EVENT_INTERVAL);

		RepeatingTask task = new RepeatingTask(() -> {
			if (timeUntilNextInvasion.isZero() || timeUntilNextInvasion.isNegative()) {
				InvasionStartEvent event = new InvasionStartEvent(InvasionTier.getRandom(), LocationUtil.getRandom(), duration);
				event.callEvent();
				timeUntilNextInvasion = Duration.ofMillis(Config.EVENT_INTERVAL);
			} else timeUntilNextInvasion = timeUntilNextInvasion.minusSeconds(5);

			if (timeUntilNextInvasion.toMinutes() - Config.START_ANNOUNCE_DELAY == 0 && announcementTaskId != -1)
				startAnnouncements();

		}, 20L, 100L);
		setEventTaskId(task.getId());
	}

	public static void stop() {
		Invasion.getPlugin().getServer().getScheduler().cancelTask(eventTaskId);
		eventTaskId = -1;
		Invasion.getPlugin().getServer().getScheduler().cancelTask(announcementTaskId);
		announcementTaskId = -1;
		CleanupUtil.start();
	}

	private static void startAnnouncements() {

		new Countdown(Config.START_ANNOUNCE_INTERVAL, 1200, Invasion.getPlugin()) {
			@Override
			public void count(int current) {
				if (current == Config.START_ANNOUNCE_INTERVAL) {
					announcementTaskId = this.task.getTaskId();
				} else if (current == 1) {
					this.cancel();
					new Countdown(60, 20, Invasion.getPlugin()) {
						@Override
						public void count(int current) {
							if (current % 10 == 0 && current <= Config.PER_SECOND_COUNTDOWN) {
								MessageUtil.announce(Component.empty()
												.append(Component.text("The next alien invasion is starting soon. You have ").color(NamedTextColor.YELLOW))
												.append(Component.text(current).color(NamedTextColor.WHITE))
												.append(Component.text(" seconds to prepare.").color(NamedTextColor.YELLOW)
												)
								);
							} else if (current <= Config.PER_SECOND_COUNTDOWN) {
								MessageUtil.announce(Component.empty()
												.append(Component.text("The next alien invasion is starting soon. You have ").color(NamedTextColor.YELLOW))
												.append(Component.text(current).color(NamedTextColor.WHITE))
												.append(Component.text(" seconds").color(NamedTextColor.YELLOW)
												));

							}
						}
					};
				}

				);
			}
		}.start();

		MessageUtil.announce(Component.empty()
						.append(Component.text("The next alien invasion is starting in ").color(NamedTextColor.YELLOW))
						.append(Component.text(timeUntilNextInvasion.getSeconds()))
		);
		timeUntilNextInvasion = timeUntilNextInvasion.minusSeconds(5);
		MessageUtil.announce(Component.empty()
						.append(Component.text("The next invasion is starting soon. You have ").color(NamedTextColor.YELLOW))
						.append(Component.text(timeUntilNextInvasion.getSeconds()).color(NamedTextColor.WHITE))
						.append(Component.text(" seconds to prepare.").color(NamedTextColor.YELLOW)
						));
	}

}
