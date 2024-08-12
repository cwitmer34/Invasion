package org.cwitmer34.invasion.scheduler;

import org.cwitmer34.invasion.config.Config;

public class Scheduler {

	public static void start() {
		new RepeatingTask(() -> {
			// TODO
		}, Config.EVENT_INTERVAL, Config.EVENT_INTERVAL);

	}

}
