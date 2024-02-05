package net.ddns.vcccd;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;


import java.io.File;
import java.io.IOException;

public class CookedFood implements Listener{
	private final Main main;
	private LevelData EXPData = new LevelData();
	String filePath = "plugins/ExpandedCooking/PlayerData/";
	
	public CookedFood(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void OnPlayerCook(FurnaceExtractEvent event) {
		
		File rootDirectory = new File(filePath);
		if(!rootDirectory.exists()) {
			if(rootDirectory.mkdirs()) {
				main.getConsole().sendMessage("File path created, no errors!");
			} else {
				main.getConsole().sendMessage("An error occured when creating directory");
			}
		}
		
		Player player = event.getPlayer();
		String UpdatedPath = filePath + player.getUniqueId().toString() + ".yml";
		File file = new File(UpdatedPath);
		if(!file.exists()) {
			YamlConfiguration yamlFile = new YamlConfiguration();
			yamlFile.createSection("CookingExp");
			yamlFile.set("CookingExp", 0);
			try {
				yamlFile.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(file);
			int playerLevel = yamlFile.getInt("CookingExp");
			player.sendMessage(ChatColor.WHITE + "You have earned " + ChatColor.GREEN + Integer.toString(EXPData.getFoodExp(event.getItemType()) * event.getItemAmount()) + ChatColor.WHITE + " Cooking Exp...");
			yamlFile.set("CookingExp", (playerLevel + (EXPData.getFoodExp(event.getItemType()) * event.getItemAmount())));
			
		}
		
	}

}
