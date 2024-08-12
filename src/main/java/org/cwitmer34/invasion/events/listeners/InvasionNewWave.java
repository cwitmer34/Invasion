package org.cwitmer34.invasion.events.listeners;

import org.bukkit.event.Listener;
import org.cwitmer34.invasion.events.InvasionNewWaveEvent;
import org.cwitmer34.invasion.util.ConsoleUtil;

public class InvasionNewWave implements Listener {

	public void newInvasionWave(InvasionNewWaveEvent event) {
		ConsoleUtil.debug("invasion new wave event");
	}

}
