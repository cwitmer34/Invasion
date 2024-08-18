package org.cwitmer34.invasion.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.enums.InvasionTier;

public class AlienUtil {

  public static int[] getAttributes(InvasionTier tier) {
    switch (tier) {
      case BASIC -> {
        return new int[] { Config.BASIC_HEALTH, Config.BASIC_DAMAGE, Config.BASIC_SPEED };
      }
      case ADVANCED -> {
        return new int[] { Config.ADVANCED_HEALTH, Config.ADVANCED_DAMAGE, Config.ADVANCED_SPEED };
      }
      case ELITE -> {
        return new int[] { Config.ELITE_HEALTH, Config.ELITE_DAMAGE, Config.ELITE_SPEED };
      }
      default -> {
        return new int[] { 20, 10, 2 };
      }
    }
  }

  public static String getName(InvasionTier tier) {
    switch (tier) {
      case BASIC -> {
        return "&aBasic Alien";
      }
      case ADVANCED -> {
        return "&eAdvanced Alien";
      }
      case ELITE -> {
        return "&cElite Alien";
      }
      default -> {
        return "&7Alien";
      }
    }
  }

  public static ItemStack[] getGear(InvasionTier tier) {
    switch (tier) {
      case BASIC -> {
        return new ItemStack[] {
          new ItemStack(Material.GOLDEN_HELMET),
          new ItemStack(Material.GOLDEN_CHESTPLATE),
          new ItemStack(Material.GOLDEN_LEGGINGS),
          new ItemStack(Material.GOLDEN_BOOTS),
          new ItemStack(Material.GOLDEN_SWORD),
        };
      }
      case ADVANCED -> {
        {
          return new ItemStack[] {
            new ItemStack(Material.IRON_HELMET),
            new ItemStack(Material.IRON_CHESTPLATE),
            new ItemStack(Material.IRON_LEGGINGS),
            new ItemStack(Material.IRON_BOOTS),
            new ItemStack(Material.IRON_SWORD),
          };
        }
      }
      case ELITE -> {
        return new ItemStack[] {
          new ItemStack(Material.DIAMOND_HELMET),
          new ItemStack(Material.DIAMOND_CHESTPLATE),
          new ItemStack(Material.DIAMOND_LEGGINGS),
          new ItemStack(Material.DIAMOND_BOOTS),
          new ItemStack(Material.DIAMOND_SWORD),
        };
      }
      default -> {
        return new ItemStack[] {
          new ItemStack(Material.LEATHER_HELMET),
          new ItemStack(Material.LEATHER_CHESTPLATE),
          new ItemStack(Material.LEATHER_LEGGINGS),
          new ItemStack(Material.LEATHER_BOOTS),
          new ItemStack(Material.WOODEN_SWORD),
        };
      }
    }
  }
}
