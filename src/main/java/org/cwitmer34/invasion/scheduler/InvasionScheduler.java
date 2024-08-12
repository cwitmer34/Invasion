package org.cwitmer34.invasion.scheduler;

import org.cwitmer34.invasion.config.Config;

public class InvasionScheduler {

	public static void start() {
		new RepeatingTask(() -> {

		}, Config.INTERVAL, Config.INTERVAL);

	}

}
