package org.cwitmer34.invasion.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.config.Config;

public class LocationUtil {

  public static Location getRandom() {
    return Config.INVASION_LOCATIONS.get(new Random().nextInt(Config.INVASION_LOCATIONS.size()));
  }

  public static List<Location> parse(ConfigurationSection section) {
    List<Location> locations = new ArrayList<>();

    if (section == null) {
      ConsoleUtil.severe("Could not find configuration section " + s);
      ConsoleUtil.severe("Disabling plugin...");
      Bukkit.getPluginManager().disablePlugin(Invasion.getPlugin());
      return locations;
    }

    String worldString = section.getString("world");
    ConsoleUtil.debug("World: " + worldString);
    World world = Bukkit.getWorld(worldString == null ? "world" : worldString);
    if (world == null) {
      ConsoleUtil.severe("World " + worldString + " does not exist.");
      ConsoleUtil.severe("Disabling plugin...");
      Bukkit.getPluginManager().disablePlugin(Invasion.getPlugin());
      return locations;
    }
    for (String key : section.getKeys(false)) {
      if (!key.equals("world")) {
        List<List<Integer>> locs = (List<List<Integer>>) section.get(key);

        if (locs == null) {
          ConsoleUtil.severe("Could not find configuration section " + key + " in " + s);
          ConsoleUtil.severe("Disabling plugin...");
          Bukkit.getPluginManager().disablePlugin(Invasion.getPlugin());
          return locations;
        }

        locs.forEach(loc -> {
          try {
            locations.add(new Location(world, loc.get(0), loc.get(1), loc.get(2)));
          } catch (Exception e) {
            ConsoleUtil.severe("Error parsing location: " + loc.toString() + " please make sure it is valid.");
          }
        });
      }
    }
    return locations;
  }
}
