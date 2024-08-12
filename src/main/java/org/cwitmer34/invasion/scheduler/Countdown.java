package org.cwitmer34.invasion.scheduler;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class Countdown {

  protected final Plugin plugin;
  protected BukkitTask task;
  private int time;
  private final long ticks;

  protected Countdown(int time, long ticks, Plugin plugin) {
    this.plugin = plugin;
    this.ticks = ticks;
    this.time = time;
  }

  public abstract void count(int current);

  public final void start() {
    task = new BukkitRunnable() {
      @Override
      public void run() {
        count(time);
        if (time-- <= 0) {
          cancel();
        }
      }
    }
      .runTaskTimer(plugin, 0L, ticks);
  }

  public void cancel() {
    if (task != null) {
      task.cancel();
      task = null;
    }
  }
}
