package org.cwitmer34.invasion.commands.misc;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cwitmer34.invasion.framework.Command;

public class Fly {

  @Command(names = { "fly", "f" }, description = "Toggle flight mode", permission = "invasion.admin")
  public void execute(CommandSender sender, String[] args) {
    Player player = (Player) sender;
    if (player.getAllowFlight()) {
      player.setAllowFlight(false);
      player.sendMessage("Flight mode disabled");
    } else {
      player.setAllowFlight(true);
      player.sendMessage("Flight mode enabled");
    }
  }
}
