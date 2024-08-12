package org.cwitmer34.invasion.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.events.InvasionStartEvent;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.util.ConsoleUtil;
import org.cwitmer34.invasion.util.MessageUtil;

import java.util.HashMap;
import java.util.UUID;

public class InvasionStart implements Listener {

	@EventHandler
	public void onInvasionStart(InvasionStartEvent event) {
		ConsoleUtil.debug("invasion start event");
		MessageUtil.announce(Config.START_MESSAGE);
		UUID id = UUID.randomUUID();
		Invasion.getActiveInvasions()
						.put(id, new ActiveInvasion(id, event.getDuration(), event.getLocation(), event.getTier()));
	}

}
