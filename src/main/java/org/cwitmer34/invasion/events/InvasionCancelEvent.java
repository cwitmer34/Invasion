package org.cwitmer34.invasion.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cwitmer34.invasion.enums.EndReasons;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.jetbrains.annotations.NotNull;

public class InvasionCancelEvent extends Event {

  private static final HandlerList HANDLERS = new HandlerList();

  @Getter
  private final EndReasons reason;

  @Getter
  private final ActiveInvasion invasion;

  public InvasionCancelEvent(EndReasons reason, ActiveInvasion invasion) {
    this.reason = reason;
    this.invasion = invasion;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLERS;
  }
}
