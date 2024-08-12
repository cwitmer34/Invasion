package org.cwitmer34.invasion.util;

import org.cwitmer34.invasion.Invasion;

public class ConsoleUtil {

	public static void info(String s) {
		Invasion.getPlugin().getLogger().info(s);
	}

	public static void warning(String s) {
		Invasion.getPlugin().getLogger().warning(s);
	}

	public static void severe(String s) {
		Invasion.getPlugin().getLogger().severe(s);
	}

	public static void debug(String s) {
		ConsoleUtil.warning("---------- DEBUG ----------");
		ConsoleUtil.info(s);
		ConsoleUtil.warning("---------- DEBUG ----------");
	}

}
