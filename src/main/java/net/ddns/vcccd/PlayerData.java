package net.ddns.vcccd;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;



public class PlayerData {
	
	private final Main main;
	private String filePath = "plugins/ExpandedCooking/PlayerData/";
	private int CookingEXP;
	private String CookQuality;
	private YamlConfiguration RawData;
	private Player player;
	
	public PlayerData(Main main, Player player) {
		this.main = main;
		this.player = player;
		File rootDirectory = new File(filePath);
		if(!rootDirectory.exists()) {
			if(rootDirectory.mkdirs()) {
				main.getConsole().sendMessage("File path created, no errors!");
			} else {
				main.getConsole().sendMessage("An error occured when creating directory");
			}
		}
		String UpdatedPath = filePath + player.getUniqueId().toString() + ".yml";
		File file = new File(UpdatedPath);
		if(!file.exists()) {
			YamlConfiguration yamlFile = new YamlConfiguration();
			yamlFile.createSection("CookingExp");
			yamlFile.set("CookingExp", 0);
			this.CookingEXP = 0;
			this.CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &lBasically Cardboard");
			try {
				yamlFile.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.RawData = yamlFile;
		} else {
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(file);
			int cookingExp = yamlFile.getInt("CookingExp");
			int homemadeExp = main.getConfig().getInt("HomemadeEXP");
			int tastyExp = main.getConfig().getInt("TastyEXP");
			int delusciousExp = main.getConfig().getInt("DelusciousEXP");
			int gourmetExp = main.getConfig().getInt("GourmetEXP");
			int restaurantQualityExp = main.getConfig().getInt("RestaurantQualityEXP");
			int oneStarExp = main.getConfig().getInt("OneStarEXP");
			int twoStarExp = main.getConfig().getInt("TwoStarEXP");
			int threeStarExp = main.getConfig().getInt("ThreeStarEXP");
			int legendaryExp = main.getConfig().getInt("LegendaryEXP");

			if (cookingExp < homemadeExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &lBasically Cardboard");
			} else if (cookingExp >= homemadeExp && cookingExp < tastyExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &2Home-Made");
			} else if (cookingExp >= tastyExp && cookingExp < delusciousExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &6Tasty");
			} else if (cookingExp >= delusciousExp && cookingExp < gourmetExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &eDeluscious");
			} else if (cookingExp >= gourmetExp && cookingExp < restaurantQualityExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &bGourmet");
			} else if (cookingExp >= restaurantQualityExp && cookingExp < oneStarExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &9Restaurant Quality");
			} else if (cookingExp >= oneStarExp && cookingExp < twoStarExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &3One Star");
			} else if (cookingExp >= twoStarExp && cookingExp < threeStarExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &3Two Star");
			} else if (cookingExp >= threeStarExp && cookingExp < legendaryExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &5Three Star");
			} else if (cookingExp >= legendaryExp) {
			    CookQuality = ChatColor.translateAlternateColorCodes('&', "&8Quality: &6Ledgendary");
			}
			this.RawData = yamlFile;
			this.CookingEXP = this.RawData.getInt("CookingExp");
		}
	}
	
	public int getCookingEXP() {
		return(this.CookingEXP);
	}
	
	public void setCookingExp(int newLevel) {
		String UpdatedPath = filePath + player.getUniqueId().toString() + ".yml";
		File file = new File(UpdatedPath);
		this.CookingEXP = newLevel;
		this.RawData.set("CookingExp", newLevel);
		try {
			this.RawData.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCookQuality() {
		return(this.CookQuality);
	}
	
	public String getChef() {
		return(ChatColor.translateAlternateColorCodes('&', "&8Cooked By: ") + ChatColor.YELLOW + this.player.getName());
	}
	
}
