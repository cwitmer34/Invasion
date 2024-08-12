package org.cwitmer34.invasion.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ChatUtil {

	public static Component fromMiniMessage(String message) {
		MiniMessage parser = MiniMessage.miniMessage();
		return parser.deserialize(message);
	}

}
