package org.cwitmer34.invasion.events.listeners;

import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;

public class InvasionListeners implements Listener {


	public InvasionListeners() {
		new InvasionEnd();
		new InvasionNewWave();
		new InvasionCancel();
		new InvasionStart();
	}
}
