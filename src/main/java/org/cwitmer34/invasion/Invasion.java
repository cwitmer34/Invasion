package org.cwitmer34.invasion;

import lombok.Getter;
import lombok.Setter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.trait.TraitInfo;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.cwitmer34.invasion.events.listeners.InvasionListeners;
import org.cwitmer34.invasion.framework.CommandFramework;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.npcs.traits.AlienTrait;
import org.cwitmer34.invasion.scheduler.Scheduler;
import org.cwitmer34.invasion.util.ConsoleUtil;
import org.cwitmer34.invasion.util.WorldEditUtil;

public final class Invasion extends JavaPlugin implements Listener {

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

    if (getServer().getPluginManager().getPlugin("Citizens") == null) {
      ConsoleUtil.severe(NamedTextColor.RED + "Citizens not found! Disabling plugin...");
      getServer().getPluginManager().disablePlugin(this);
    } else {
      CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(AlienTrait.class));
    }
  }

  @EventHandler
  public void onCitizensEnable(CitizensEnableEvent event) {
    ConsoleUtil.info(NamedTextColor.GREEN + "Citizens was enabled!");
  }

  @Override
  public void onDisable() {
    getServer().getScheduler().cancelTasks(this);
    if (getActiveInvasion() == null) return;
    if (WorldEditUtil.deleteUFO(getActiveInvasion().getEditSession())) {
      ConsoleUtil.info(NamedTextColor.GREEN + "UFO has been deleted and plugin is now disabled.");
    } else {
      ConsoleUtil.severe(NamedTextColor.RED + "UFO could not be deleted when disabling plugin");
    }
  }
}
