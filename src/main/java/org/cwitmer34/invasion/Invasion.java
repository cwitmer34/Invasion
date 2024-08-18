package org.cwitmer34.invasion;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import org.cwitmer34.invasion.events.listeners.InvasionListeners;
import org.cwitmer34.invasion.framework.CommandFramework;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.scheduler.Scheduler;

public final class Invasion extends JavaPlugin {

  @Getter
  @Setter
  private static ActiveInvasion activeInvasion;

  @Getter
  private static Invasion plugin;

  @Override
  public void onEnable() {
    plugin = this;
    saveDefaultConfig();
    Scheduler.start();
    new CommandFramework(this);
    new InvasionListeners();
  }

  @Override
  public void onDisable() {
    getServer().getScheduler().cancelTasks(this);
  }
}
