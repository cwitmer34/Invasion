package org.cwitmer34.invasion.events;

import java.time.Duration;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.jetbrains.annotations.NotNull;

public class InvasionNewWaveEvent extends Event implements Cancellable {

  private static final HandlerList HANDLERS = new HandlerList();

  @Getter
  private final ActiveInvasion invasion;

  private boolean isCancelled;

  public InvasionNewWaveEvent(ActiveInvasion invasion) {
    this.invasion = invasion;
  }

  @Override
  public boolean isCancelled() {
    return isCancelled;
  }

  @Override
  public void setCancelled(boolean b) {
    this.isCancelled = b;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
}
