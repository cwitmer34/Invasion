package org.cwitmer34.invasion.scheduler;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;

public class DelayedTask implements Listener {

  @Getter
  private int id = -1;

  public DelayedTask(Runnable runnable, long delay) {
    if (Invasion.getPlugin().isEnabled()) {
      id = Bukkit.getScheduler()
        .scheduleSyncDelayedTask(Invasion.getPlugin(), runnable, delay);
    } else {
      runnable.run();
    }
  }
}
