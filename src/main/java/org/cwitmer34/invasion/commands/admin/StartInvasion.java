package org.cwitmer34.invasion.commands.admin;

import java.time.Duration;
import java.util.UUID;
import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.enums.InvasionTier;
import org.cwitmer34.invasion.events.InvasionStartEvent;
import org.cwitmer34.invasion.framework.Command;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.scheduler.Scheduler;
import org.cwitmer34.invasion.util.LocationUtil;
import org.cwitmer34.invasion.util.WorldEditUtil;

public class StartInvasion {

  @Command(
    names = { "invasionstart", "instart", "startinvasion" },
    permission = "invasion.admin",
    description = "Start an invasion manually"
  )
  @SneakyThrows
  public boolean execute(CommandSender sender, String[] args) {
    Player player = (Player) sender;
    if (Invasion.getActiveInvasion() != null) {
      player.sendMessage("An invasion is already active!");
      return true;
    }

    Duration duration = Duration.ofMinutes(Config.EVENT_DURATION);

    if (args.length > 0) {
      try {
        duration = Duration.ofMinutes(Integer.parseInt(args[0]));
      } catch (NumberFormatException e) {
        player.sendMessage("Invalid duration");
        return true;
      }
    }

    InvasionStartEvent event = new InvasionStartEvent(InvasionTier.getRandom(), LocationUtil.getRandom(), duration);
    event.callEvent();
    Scheduler.stop();
    Scheduler.start();
    return true;
  }
}
