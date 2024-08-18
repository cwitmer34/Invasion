package org.cwitmer34.invasion.util;

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
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.cwitmer34.invasion.Invasion;

public class WorldEditUtil {

  public static EditSession loadAndPasteUFO(Location loc) {
    File file = new File(Invasion.getPlugin().getDataFolder(), "schematic/ufo.schem");
    Clipboard clipboard;
    ClipboardFormat format = ClipboardFormats.findByFile(file);
    try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
      clipboard = reader.read();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(loc.getWorld()))) {
      Operation operation = new ClipboardHolder(clipboard)
        .createPaste(editSession)
        .to(BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()))
        .build();
      Operations.complete(operation);
      return editSession;
    } catch (WorldEditException e) {
      ConsoleUtil.debug(e.getMessage());
      return null;
    }
  }

  public static boolean deleteUFO(EditSession editSession) {
    try {
      editSession.undo(editSession);
      editSession.close();
      return true;
    } catch (Exception e) {
      ConsoleUtil.severe(e.getMessage());
      return false;
    }
  }
}
