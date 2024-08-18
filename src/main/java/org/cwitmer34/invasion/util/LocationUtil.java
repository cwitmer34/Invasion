package org.cwitmer34.invasion.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

  public static Location getRandom() {
    return new Location(Bukkit.getWorld("world"), 20, 115, 885);
  }
}
