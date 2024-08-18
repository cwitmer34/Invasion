package org.cwitmer34.invasion.events.listeners;

import com.sk89q.worldedit.EditSession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.events.InvasionEndEvent;
import org.cwitmer34.invasion.util.ConsoleUtil;

public class InvasionEnd implements Listener {

  public InvasionEnd() {
    Invasion.getPlugin().getServer().getPluginManager().registerEvents(this, Invasion.getPlugin());
  }

  @EventHandler
  public void onInvasionEnd(InvasionEndEvent event) {
    ConsoleUtil.debug("invasion end event");
    Invasion.setActiveInvasion(null);
    EditSession ufo = event.getInvasion().getEditSession();
    ufo.undo(ufo);
  }
}
