package org.cwitmer34.invasion.npcs.traits;

import java.util.Random;
import net.citizensnpcs.api.ai.event.NavigationStuckEvent;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDamageEvent;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.cwitmer34.invasion.Invasion;

@TraitName("alientrait")
public class AlienTrait extends Trait {

  public AlienTrait() {
    super("alientrait");
    plugin = JavaPlugin.getPlugin(Invasion.class);
  }

  Invasion plugin;

  int jumpHeight = 1;
  boolean hasAbility = true;

  public void load(DataKey key) {
    jumpHeight = key.getInt("jumpHeight");
    hasAbility = key.getBoolean("canJump");
  }

  @EventHandler
  public void onStuck(NavigationStuckEvent event) {
    if (npc.isSpawned()) {
      jump(npc.getNavigator().getTargetAsLocation());
    }
  }

  @Override
  public void run() {
    if (!npc.isSpawned()) return;
    if (!npc.getEntity().isOnGround()) {
      npc.getNavigator().setPaused(true);
    } else {
      npc.getNavigator().setPaused(false);
    }

    Location target = npc.getNavigator().getTargetAsLocation();
    if (target == null) return;
    if (npc.getEntity().getLocation().distance(target) < 15) return;
    if (!hasAbility) return;

    useRandomAbility(target);
    cooldown();
  }

  private void useRandomAbility(Location target) {
    int random = new Random().nextInt(3);
    switch (random) {
      case 0, 1, 2 -> jump(target);
    }
  }

  private void teleport(Location target) {
    npc.teleport(
      npc.getEntity().getLocation().clone().subtract(target.toVector().divide(new Vector(2, 0, 2))),
      PlayerTeleportEvent.TeleportCause.PLUGIN
    );
  }

  private void fireball(Location target) {
    if (npc.getNavigator().isNavigating() && npc.isSpawned() && npc.getEntity() instanceof LivingEntity entity) {
      entity.launchProjectile(Fireball.class, target.toVector());
    }
  }

  private void cooldown() {
    hasAbility = false;
    new BukkitRunnable() {
      @Override
      public void run() {
        hasAbility = true;
      }
    }
      .runTaskLater(plugin, 100L);
  }

  public void jump(Location target) {
    if (npc.getNavigator().isNavigating() && npc.isSpawned() && npc.getEntity() instanceof LivingEntity entity) {
      Location currentLocation = entity.getLocation();
      Vector direction = target
        .setDirection(currentLocation.getDirection())
        .toVector()
        .subtract(currentLocation.toVector())
        .normalize();
      npc.faceLocation(target);
      entity.setVelocity(direction.multiply(1.75).setY(1.25));
      npc.faceLocation(target);
    }
  }

  @EventHandler
  public void onDamage(NPCDamageByEntityEvent event) {
    if (!(event.getDamager() instanceof Player player)) return;
    Invasion.getActiveInvasion().addDamage(player, (int) event.getDamage());
  }

  @EventHandler
  public void onFall(NPCDamageEvent event) {
    if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
      event.setCancelled(true);
    }
  }

  @Override
  public void onAttach() {}

  @Override
  public void onSpawn() {}
}
