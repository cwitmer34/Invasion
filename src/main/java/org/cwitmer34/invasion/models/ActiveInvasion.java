package org.cwitmer34.invasion.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.cwitmer34.invasion.enums.InvasionTier;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class ActiveInvasion {
	private UUID id;
	private Duration duration;
	private InvasionTier tier;
	private int totalDamage = 0;
	private Map<UUID, Integer> playersInvolved = new HashMap<>();
	private int waveReached = 0;
	private Location location;

	public ActiveInvasion(UUID id, Duration duration, Location loc, InvasionTier tier) {
		this.id = id;
		this.duration = duration;
		this.tier = tier;
		this.location = loc;
	}

}
