package org.cwitmer34.invasion.events.listeners;

import com.sk89q.worldedit.EditSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.citizensnpcs.api.npc.NPC;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
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
    MessageUtil.announce(
      Component.empty()
        .append(Component.text(" !  ").decorate(TextDecoration.BOLD).color(NamedTextColor.RED))
        .append(Component.text("Total damage dealt ").color(NamedTextColor.YELLOW))
        .append(Component.text(invasion.getTotalDamage()).color(NamedTextColor.WHITE))
    );
    MessageUtil.announce(
      Component.empty()
        .append(Component.text("Congratulations to").color(NamedTextColor.WHITE))
        .append(Component.text(playerDamageValue.keySet().iterator().next().getName()).color(NamedTextColor.AQUA))
        .append(Component.text(" for dealing the most damage ").color(NamedTextColor.WHITE))
        .append(Component.text("(" + playerDamageValue.values().iterator().next() + ")").color(NamedTextColor.GRAY))
    );
    invasion.getCountdown().cancel();

    if (endReason.equals(EndReason.SUCCESSFUL)) {
      Audience audience = Audience.audience(invasion.getPlayersInvolved().keySet());
      audience.showTitle(
        Title.title(
          Component.text("Invasion Successful!").color(NamedTextColor.GREEN),
          Component.text("You have won!").color(NamedTextColor.GRAY)
        )
      );
      generateLoot(invasion.getLocation());
    }

    invasion.getAliens().forEach(NPC::destroy);

    ConsoleUtil.debug("invasion end event");
    Invasion.setActiveInvasion(null);

    EditSession ufo = invasion.getEditSession();
    ufo.undo(ufo);
  }

  public static void generateLoot(Location location) {
    ArmorStand armorStand = location
      .getWorld()
      .spawn(location, ArmorStand.class, stand -> {
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setMarker(true);
      });
    new BukkitRunnable() {
      @Override
      public void run() {
        Location currentLocation = armorStand.getLocation();
        currentLocation.subtract(0, 0.01, 0);
        if (currentLocation.getBlock().getType().isSolid()) {
          armorStand.remove();
          currentLocation.getBlock().setType(Material.CHEST);
          Chest chest = (Chest) currentLocation.getBlock().getState();
          Random random = new Random();
          ItemStack[] items = {
            new ItemStack(Material.DIAMOND, random.nextInt(3) + 1),
            new ItemStack(Material.GOLD_INGOT, random.nextInt(5) + 1),
            new ItemStack(Material.IRON_INGOT, random.nextInt(5) + 1),
            new ItemStack(Material.EMERALD, random.nextInt(2) + 1),
            new ItemStack(Material.ENCHANTED_BOOK),
          };
          for (ItemStack item : items) {
            chest.getInventory().addItem(item);
          }
          this.cancel();
        } else {
          armorStand.teleport(currentLocation);
        }
      }
    }
      .runTaskTimer(Invasion.getPlugin(), 0L, 2L);
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
