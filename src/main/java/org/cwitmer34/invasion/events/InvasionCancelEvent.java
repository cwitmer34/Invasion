package org.cwitmer34.invasion.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cwitmer34.invasion.enums.CancelReasons;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InvasionCancelEvent extends Event {
	private static final HandlerList HANDLERS = new HandlerList();

	@Getter private final CancelReasons reason;
	@Getter
	private final List<Player> playersInvolved;

	public InvasionCancelEvent(CancelReasons reason, List<Player> playersInvolved) {
		this.reason = reason;
		this.playersInvolved = playersInvolved;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLERS;
	}
}
