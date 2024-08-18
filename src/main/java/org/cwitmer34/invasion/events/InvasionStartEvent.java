package org.cwitmer34.invasion.events;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class InvasionStartEvent extends Event implements Cancellable {
	private static final HandlerList HANDLER_LIST = new HandlerList();

	@Getter private final InvasionTier tier;
	@Getter	private final Location location;
	@Getter	private final Duration duration;

	private boolean isCancelled;

	public InvasionStartEvent(InvasionTier tier, Location location, Duration duration) {
		this.tier = tier;
		this.location = location;
		this.duration = duration;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		this.isCancelled = b;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLER_LIST;
	}
}
