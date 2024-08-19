package org.cwitmer34.invasion.events.listeners;

import com.sk89q.worldedit.EditSession;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.enums.EndReason;
import org.cwitmer34.invasion.events.InvasionEndEvent;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.util.ConsoleUtil;
import org.cwitmer34.invasion.util.MessageUtil;

public class InvasionEnd implements Listener {

  public InvasionEnd() {
    Invasion.getPlugin().getServer().getPluginManager().registerEvents(this, Invasion.getPlugin());
  }

  @EventHandler
  public void onInvasionEnd(InvasionEndEvent event) {
    ActiveInvasion invasion = event.getInvasion();
    EndReason endReason = event.getEndReason();
    Map<Player, Integer> playerDamageValue = getPlayerWithMostDamage(invasion.getPlayersInvolved());
    MessageUtil.announce(Component.text("the total damage done was " + invasion.getTotalDamage()));
    MessageUtil.announce(
      Component.text(
        "the player with the most damage was " +
        playerDamageValue.keySet().iterator().next().getName() +
        " with " +
        playerDamageValue.values().iterator().next() +
        " damage"
      )
    );
    invasion.getCountdown().cancel();

    ConsoleUtil.debug("invasion end event");
    Invasion.setActiveInvasion(null);

    EditSession ufo = invasion.getEditSession();
    ufo.undo(ufo);
  }

  private Map<Player, Integer> getPlayerWithMostDamage(Map<Player, Integer> playersInvolved) {
    Map<Player, Integer> playerWithMostDamage = new HashMap<>();
    for (Map.Entry<Player, Integer> entry : playersInvolved.entrySet()) {
      if (playerWithMostDamage.isEmpty() || entry.getValue() > playerWithMostDamage.values().iterator().next()) {
        playerWithMostDamage = Map.of(entry.getKey(), entry.getValue());
      }
    }
    return playerWithMostDamage;
  }
}
