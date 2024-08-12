package org.cwitmer34.invasion.enums;

import org.cwitmer34.invasion.Invasion;

import java.util.Random;

public enum InvasionTier {
	BASIC,
	ADVANCED,
	ELITE;

	public static InvasionTier fromString(String string) {
		for (InvasionTier tier : values()) {
			if (tier.name().equalsIgnoreCase(string)) {
				return tier;
			}
		}
		return null;
	}

	public static InvasionTier getRandom() {
		int random = new Random().nextInt(values().length);
		return values()[random];
	}

	public static boolean isValid(String string) {
		return fromString(string) != null;
	}

}
