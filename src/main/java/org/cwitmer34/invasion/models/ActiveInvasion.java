package org.cwitmer34.invasion.models;

import com.sk89q.worldedit.EditSession;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.events.InvasionEndEvent;
import org.cwitmer34.invasion.scheduler.RepeatingTask;
import org.cwitmer34.invasion.util.ConsoleUtil;

@Getter
@Setter
public class ActiveInvasion {

  private UUID id;
  private Duration duration;
  private InvasionTier tier;
  private int totalDamage = 0;
  private Map<Player, Integer> playersInvolved = new HashMap<>();
  private int waveReached = 0;
  private Location location;
  private EditSession editSession;
  private RepeatingTask countdown;

  public ActiveInvasion(UUID id, Duration duration, Location loc, InvasionTier tier, EditSession editSession) {
    this.id = id;
    this.duration = duration;
    this.tier = tier;
    this.location = loc;
    this.editSession = editSession;
    this.countdown = new RepeatingTask(
      () -> {
        if (this.duration.isZero() || this.duration.isNegative()) {
          new InvasionEndEvent(this).callEvent();
          this.countdown.cancel();
        } else {
          ConsoleUtil.debug(getDuration().toSeconds() + " seconds remaining in invasion");
          this.duration = this.duration.minusSeconds(1);
        }
      },
      0,
      20
    );
  }

  public void addDamage(Player player, int damage) {
    if (playersInvolved.containsKey(player)) {
      playersInvolved.put(player, playersInvolved.get(player) + damage);
    } else {
      playersInvolved.put(player, damage);
    }
    totalDamage += damage;
  }
}
