package org.cwitmer34.invasion.scheduler;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;

public class RepeatingTask implements Listener {

  @Getter
  private int id = -1;

  public RepeatingTask(Runnable runnable, long delay, long interval) {
    if (Invasion.getPlugin().isEnabled()) {
      id = Bukkit.getScheduler()
        .scheduleSyncRepeatingTask(Invasion.getPlugin(), runnable, delay, interval);
    } else {
      runnable.run();
    }
  }
}
