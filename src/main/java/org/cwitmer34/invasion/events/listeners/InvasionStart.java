package org.cwitmer34.invasion.events.listeners;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.cwitmer34.invasion.Invasion;
import org.cwitmer34.invasion.config.Config;
import org.cwitmer34.invasion.events.InvasionStartEvent;
import org.cwitmer34.invasion.models.ActiveInvasion;
import org.cwitmer34.invasion.util.ConsoleUtil;
import org.cwitmer34.invasion.util.MessageUtil;
import org.cwitmer34.invasion.util.WorldEditUtil;

public class InvasionStart implements Listener {

  public InvasionStart() {
    Invasion.getPlugin().getServer().getPluginManager().registerEvents(this, Invasion.getPlugin());
  }

  @EventHandler
  public void onInvasionStart(InvasionStartEvent event) {
    Location loc = event.getLocation();
    ConsoleUtil.debug("invasion start event");
    MessageUtil.announce(Config.START_MESSAGE);
    UUID id = UUID.randomUUID();
    if (Invasion.getActiveInvasion() != null) {
      Invasion.getActiveInvasion().getEditSession().undo(Invasion.getActiveInvasion().getEditSession());
    }
    EditSession editSession = WorldEditUtil.loadAndPasteUFO(loc);
    Invasion.setActiveInvasion(new ActiveInvasion(id, event.getDuration(), loc, event.getTier(), editSession));
  }
}
