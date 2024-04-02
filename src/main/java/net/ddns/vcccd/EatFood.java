package net.ddns.vcccd;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class EatFood implements Listener {
	
	private final Main main;
	
	public EatFood(Main main) {
		this.main = main;
	}
	
	
	
	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent event) {
		ItemStack eatenItem = event.getItem();
		ItemMeta eatenItemMeta = eatenItem.getItemMeta();
		List<String> Lore = eatenItemMeta.getLore();
		Player player = event.getPlayer();
		String name = Lore.get(1).split(" ")[2].substring(2, Lore.get(1).split(" ")[2].length());
		player.sendMessage(Boolean.toString(name.equals("s5y")));
		player.sendMessage(name);
		PlayerData chefInfo = new PlayerData(main, Bukkit.getPlayerExact(name));
		
		int homemadeExp = main.getConfig().getInt("HomemadeEXP");
		int tastyExp = main.getConfig().getInt("TastyEXP");
		int delusciousExp = main.getConfig().getInt("DelusciousEXP");
		int gourmetExp = main.getConfig().getInt("GourmetEXP");
		int restaurantQualityExp = main.getConfig().getInt("RestaurantQualityEXP");
		int oneStarExp = main.getConfig().getInt("OneStarEXP");
		int twoStarExp = main.getConfig().getInt("TwoStarEXP");
		int threeStarExp = main.getConfig().getInt("ThreeStarEXP");
		int legendaryExp = main.getConfig().getInt("LegendaryEXP");
		if (chefInfo.getCookingEXP() >= homemadeExp && chefInfo.getCookingEXP() < tastyExp) {
		    // Code for the condition where chef's cooking experience is between Homemade and Tasty
			Vector direction = new Vector(0, 1, 0);
			player.setVelocity(direction.multiply(100));
		} else if (chefInfo.getCookingEXP() >= tastyExp && chefInfo.getCookingEXP() < delusciousExp) {
		    // Code for the condition where chef's cooking experience is between Tasty and Deluscious
		} else if (chefInfo.getCookingEXP() >= delusciousExp && chefInfo.getCookingEXP() < gourmetExp) {
		    // Code for the condition where chef's cooking experience is between Deluscious and Gourmet
		} else if (chefInfo.getCookingEXP() >= gourmetExp && chefInfo.getCookingEXP() < restaurantQualityExp) {
		    // Code for the condition where chef's cooking experience is between Gourmet and RestaurantQuality
		} else if (chefInfo.getCookingEXP() >= restaurantQualityExp && chefInfo.getCookingEXP() < oneStarExp) {
		    // Code for the condition where chef's cooking experience is between RestaurantQuality and OneStar
		} else if (chefInfo.getCookingEXP() >= oneStarExp && chefInfo.getCookingEXP() < twoStarExp) {
		    // Code for the condition where chef's cooking experience is between OneStar and TwoStar
		} else if (chefInfo.getCookingEXP() >= twoStarExp && chefInfo.getCookingEXP() < threeStarExp) {
		    // Code for the condition where chef's cooking experience is between TwoStar and ThreeStar
		} else if (chefInfo.getCookingEXP() >= threeStarExp && chefInfo.getCookingEXP() < legendaryExp) {
		    // Code for the condition where chef's cooking experience is between ThreeStar and Legendary
		} else if (chefInfo.getCookingEXP() >= legendaryExp) {
		    // Code for the condition where chef's cooking experience is Legendary or higher
		}

		
		
	}
}
