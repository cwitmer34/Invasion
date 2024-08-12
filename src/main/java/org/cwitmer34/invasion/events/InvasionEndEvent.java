package org.cwitmer34.invasion.events;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public class InvasionEndEvent extends Event implements Cancellable {
	private static final HandlerList HANDLERS = new HandlerList();

	@Getter private final boolean isVictory;
	@Getter	private final Map<UUID, Integer> damagers;
	@Getter private final InvasionTier tier;
	@Getter private final Location location;
	@Getter private final int waveReached;
	@Getter	private final int totalDamage;

	private boolean isCancelled;

	public InvasionEndEvent(boolean isVictory, Map<UUID, Integer> damagers, InvasionTier tier, Location location, int waveReached) {
		this.isVictory = isVictory;
		this.damagers = damagers;
		this.totalDamage = damagers.values().stream().mapToInt(Integer::intValue).sum();
		this.waveReached = waveReached;
		this.tier = tier;
		this.location = location;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		this.isCancelled = b;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLERS;
	}
}
