package org.cwitmer34.invasion.enums;

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

	public static boolean isValid(String string) {
		return fromString(string) != null;
	}

}
