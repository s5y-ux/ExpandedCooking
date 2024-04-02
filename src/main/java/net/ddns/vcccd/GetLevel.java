package net.ddns.vcccd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetLevel implements CommandExecutor {

private final Main main;
	
	public GetLevel(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!(sender instanceof Player)) {
	        sender.sendMessage("This command can only be executed by a player.");
	        return true;
	    }

	    Player player = (Player) sender;

	    try {
	        PlayerData playerData = new PlayerData(main, player);
	        player.sendMessage(Integer.toString(playerData.getCookingEXP()));
	        }
	    catch (Exception e) {
	        sender.sendMessage("An error occurred while setting cooking experience. Please try again later.");
	        e.printStackTrace();
	    }

	    return true;
	}

}
