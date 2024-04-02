package net.ddns.vcccd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeLevel implements CommandExecutor {
	
	private final Main main;
	
	public ChangeLevel(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if (!(sender instanceof Player)) {
	        sender.sendMessage("This command can only be executed by a player.");
	        return true;
	    }

	    if (args.length < 2) {
	        sender.sendMessage("Usage: /setcookingexp <playerName> <cookingExp>");
	        return true;
	    }

	    Player player = Bukkit.getPlayerExact(args[0]);
	    if (player == null) {
	        sender.sendMessage("Player not found.");
	        return true;
	    }

	    try {
	        int cookingExp = Integer.parseInt(args[1]);
	        PlayerData playerData = new PlayerData(main, player);
	        playerData.setCookingExp(cookingExp);
	        sender.sendMessage("Cooking experience set successfully.");
	    } catch (NumberFormatException e) {
	        sender.sendMessage("Invalid cooking experience. Please provide a valid number.");
	    } catch (Exception e) {
	        sender.sendMessage("An error occurred while setting cooking experience. Please try again later.");
	        e.printStackTrace();
	    }

	    return true;
	}
}
