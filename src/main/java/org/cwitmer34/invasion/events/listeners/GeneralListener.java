package org.cwitmer34.invasion.events.listeners;

import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.events.InvasionNewWaveEvent;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.util.ConsoleUtil;

public class GeneralListener implements Listener {

  public GeneralListener() {
    Invasion.getPlugin().getServer().getPluginManager().registerEvents(this, Invasion.getPlugin());
  }

  @EventHandler
  public void onNPCDeath(NPCDeathEvent event) {
    ActiveInvasion invasion = Invasion.getActiveInvasion();
    if (invasion == null) return;
    NPC npc = event.getNPC();
    if (!invasion.getAliens().contains(npc)) return;
    ConsoleUtil.debug("alien was in the list, removing!");
    invasion.getAliens().remove(npc);
    npc.destroy();

    if (invasion.getAliens().isEmpty()) {
      ConsoleUtil.debug("no more aliens, starting next wave");
      new InvasionNewWaveEvent(invasion).callEvent();
    }
  }
}
