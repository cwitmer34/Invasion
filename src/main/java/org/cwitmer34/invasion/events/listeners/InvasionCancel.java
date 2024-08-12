package org.cwitmer34.invasion.events.listeners;

import org.bukkit.event.Listener;
import org.cwitmer34.invasion.events.InvasionCancelEvent;
import org.cwitmer34.invasion.util.ConsoleUtil;

public class InvasionCancel implements Listener {

	public void onInvasionCancel(InvasionCancelEvent event) {
		ConsoleUtil.debug("invasion cancel event");
	}
}
