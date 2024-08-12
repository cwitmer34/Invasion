package org.cwitmer34.invasion.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.events.InvasionEndEvent;
import org.cwitmer34.invasion.util.ConsoleUtil;

public class InvasionEnd implements Listener {

	@EventHandler
	public void onInvasionEnd(InvasionEndEvent event) {
		ConsoleUtil.debug("invasion end event");
		Invasion.getActiveInvasions().remove(event.getInvasion().getId());
	}

}
