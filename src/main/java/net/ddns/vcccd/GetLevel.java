package net.ddns.vcccd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

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
	        String Level = ChatColor.GREEN + Integer.toString(playerData.getCookingEXP());
	        player.sendMessage("Your Cooking Expierence Amounts To: " + Level);
	        player.sendMessage(playerData.getCookQuality());
	        }
	    catch (Exception e) {
	        sender.sendMessage("An error occurred while setting cooking experience. Please try again later.");
	        e.printStackTrace();
	    }

	    return true;
	}

}
