package org.cwitmer34.invasion.events.listeners;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.enums.EndReason;
import org.cwitmer34.invasion.events.InvasionEndEvent;
import org.cwitmer34.invasion.events.InvasionNewWaveEvent;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.npcs.Alien;
import org.cwitmer34.invasion.scheduler.DelayedTask;
import org.cwitmer34.invasion.util.ConsoleUtil;

public class InvasionNewWave implements Listener {

  public InvasionNewWave() {
    Invasion.getPlugin().getServer().getPluginManager().registerEvents(this, Invasion.getPlugin());
  }

  @EventHandler
  public void newInvasionWave(InvasionNewWaveEvent event) {
    ActiveInvasion invasion = event.getInvasion();
    int currentWave = invasion.getCurrentWave();
    int maxWave = invasion.getMaxWave();
    invasion.setCurrentWave(currentWave + 1);
    if (currentWave > maxWave) {
      new InvasionEndEvent(invasion, EndReason.SUCCESSFUL).callEvent();
      return;
    } else if (currentWave != 0) {
      Audience.audience(invasion.getPlayersInvolved().keySet()).showTitle(
        Title.title(
          Component.empty()
            .append(Component.text("Wave ").color(NamedTextColor.GREEN))
            .append(Component.text(currentWave).color(NamedTextColor.YELLOW))
            .append(Component.text(" completed!").color(NamedTextColor.GREEN)),
          Component.text(
            currentWave == maxWave ? "This is the last wave, be careful!" : "Prepare for the next wave"
          ).color(NamedTextColor.GRAY)
        )
      );
    }

    ConsoleUtil.debug("invasion new wave event");

    int aliens = currentWave <= 1
      ? Config.BASE_ALIENS
      : Config.BASE_ALIENS + (invasion.getCurrentWave() * Config.INCREASE_PER_WAVE);

    for (int i = 0; i < aliens; i++) {
      new DelayedTask(
        () -> {
          Alien alien = new Alien(invasion.getTier(), invasion.getLocation());
          invasion.getAliens().add(alien.getNpc());
        },
        100L
      );
    }
  }
}
