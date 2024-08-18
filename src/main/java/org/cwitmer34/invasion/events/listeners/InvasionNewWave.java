package org.cwitmer34.invasion.events.listeners;

import java.util.Collections;
import java.util.List;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.events.InvasionNewWaveEvent;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.util.ConsoleUtil;

public class InvasionNewWave implements Listener {

  @EventHandler
  public void newInvasionWave(InvasionNewWaveEvent event) {
    ActiveInvasion invasion = event.getInvasion();
    Audience audience = Audience.audience(invasion.getPlayersInvolved().keySet());
    audience.showTitle(
      Title.title(Component.text("Wave " + invasion.getWaveReached() + " completed!"), Component.empty())
    );
    ConsoleUtil.debug("invasion new wave event");
  }
}
