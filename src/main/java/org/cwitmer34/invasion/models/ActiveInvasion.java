package org.cwitmer34.invasion.models;

import com.sk89q.worldedit.EditSession;
import java.time.Duration;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.cwitmer34.invasion.enums.EndReasons;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.events.InvasionEndEvent;
import org.cwitmer34.invasion.npcs.Alien;
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
  private int currentWave = 0;
  private int maxWave;
  private List<NPC> aliens = new ArrayList<>();
  private Location location;
  private EditSession editSession;
  private RepeatingTask countdown;

  public ActiveInvasion(
    UUID id,
    Duration duration,
    int maxWave,
    Location loc,
    InvasionTier tier,
    EditSession editSession
  ) {
    this.id = id;
    this.duration = duration;
    this.maxWave = maxWave;
    this.tier = tier;
    this.location = loc;
    this.editSession = editSession;
    this.countdown = new RepeatingTask(
      () -> {
        if (this.duration.isZero() || this.duration.isNegative()) {
          new InvasionEndEvent(this, EndReasons.OUT_OF_TIME).callEvent();
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
