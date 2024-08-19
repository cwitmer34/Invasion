package org.cwitmer34.invasion.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Server;
import org.cwitmer34.invasion.Invasion;

public class MessageUtil {

  private static final Server server = Invasion.getPlugin().getServer();

  public static void announce(Component c) {
    server.sendMessage(c);
  }

  public static Component fromMiniMessage(String message) {
    MiniMessage parser = MiniMessage.miniMessage();
    return parser.deserialize(message);
  }

  public static void titleAll(Component title, Component subtitle) {
    server.showTitle(Title.title(title, subtitle));
  }
}
