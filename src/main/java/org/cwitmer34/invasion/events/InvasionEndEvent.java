package org.cwitmer34.invasion.events;

import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cwitmer34.invasion.enums.EndReasons;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.jetbrains.annotations.NotNull;

public class InvasionEndEvent extends Event implements Cancellable {

  private static final HandlerList HANDLERS = new HandlerList();

  @Getter
  private final ActiveInvasion invasion;

  private boolean isCancelled;

  @Getter
  private final EndReasons endReason;

  public InvasionEndEvent(ActiveInvasion invasion, EndReasons endReason) {
    this.endReason = endReason;
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
