package org.cwitmer34.invasion.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Server;
import org.cwitmer34.invasion.Invasion;

public class MessageUtil {
	private final static Server server = Invasion.getPlugin().getServer();

	public static void announce(Component c) {
		server.sendMessage(c);
	}

	public static Component fromMiniMessage(String message) {
		MiniMessage parser = MiniMessage.miniMessage();
		return parser.deserialize(message);
	}
}
