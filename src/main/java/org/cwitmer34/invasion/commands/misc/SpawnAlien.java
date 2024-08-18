package org.cwitmer34.invasion.commands.misc;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.framework.Command;
import org.cwitmer34.invasion.npcs.Alien;

public class SpawnAlien {

  @Command(names = "spawnalien", description = "Spawn an alien")
  public void execute(CommandSender sender, String[] args) {
    Player player = (Player) sender;
    try {
      new Alien(InvasionTier.BASIC, player.getLocation());
      player.sendMessage("Spawned alien");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
