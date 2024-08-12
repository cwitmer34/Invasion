package org.cwitmer34.invasion.commands.admin;

import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cwitmer34.invasion.framework.Command;

public class StartInvasion {
	@SneakyThrows
	public boolean execute(CommandSender sender, String[] args) {
		Player player = (Player) sender;
			return true;
	}
}
