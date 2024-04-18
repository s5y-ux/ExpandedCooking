package net.ddns.vcccd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EatFood implements Listener {
	
	private final Main main;
	private EffectsConverter convert = new EffectsConverter();
	
	private void applyEffects(Player player, List<String> effects) {
		int time = main.getConfig().getInt("EffectsTime") * 20;
		for(String effectValue: effects) {
			player.addPotionEffect(new PotionEffect(convert.getEffect(effectValue), time, 0));
		}
	}
	
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
			PlayerData chefInfo = new PlayerData(main, Bukkit.getPlayerExact(name));
			
			Map<String, List<String>> effectsMap = new HashMap<>();
	        
	        String[] categories = {"HomemadeEffects", "TastyEffects", "DelusciousEffects", "GourmetEffects", "RestaurantQualityEffects", "OneStarEffects", "TwoStarEffects", "ThreeStarEffects", "LegendaryEffects"};
	        
	        for (String category : categories) {
	            effectsMap.put(category, main.getConfig().getStringList(category));
	        }
			
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
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
				} else {
				applyEffects(player, effectsMap.get("HomemadeEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= tastyExp && chefInfo.getCookingEXP() < delusciousExp) {
			    // Code for the condition where chef's cooking experience is between Tasty and Deluscious
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 0));
				} else {
					applyEffects(player, effectsMap.get("TastyEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= delusciousExp && chefInfo.getCookingEXP() < gourmetExp) {
			    // Code for the condition where chef's cooking experience is between Deluscious and Gourmet
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 100, 0));
				} else {
					applyEffects(player, effectsMap.get("DelusciousEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= gourmetExp && chefInfo.getCookingEXP() < restaurantQualityExp) {
			    // Code for the condition where chef's cooking experience is between Gourmet and RestaurantQuality
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 100, 0));
				} else {
					applyEffects(player, effectsMap.get("GourmetEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= restaurantQualityExp && chefInfo.getCookingEXP() < oneStarExp) {
			    // Code for the condition where chef's cooking experience is between RestaurantQuality and OneStar
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 250, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 150, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION , 100, 0));
				} else {
					applyEffects(player, effectsMap.get("RestaurantQualityEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= oneStarExp && chefInfo.getCookingEXP() < twoStarExp) {
			    // Code for the condition where chef's cooking experience is between OneStar and TwoStar
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 200, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION , 100, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK , 100, 0));
				} else {
					applyEffects(player, effectsMap.get("OneStarEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= twoStarExp && chefInfo.getCookingEXP() < threeStarExp) {
			    // Code for the condition where chef's cooking experience is between TwoStar and ThreeStar
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 350, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 200, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION , 100, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK , 100, 0));
				} else {
					applyEffects(player, effectsMap.get("TwoStarEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= threeStarExp && chefInfo.getCookingEXP() < legendaryExp) {
			    // Code for the condition where chef's cooking experience is between ThreeStar and Legendary
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 250, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION , 100, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK , 150, 0));
				} else {
					applyEffects(player, effectsMap.get("ThreeStarEffects"));
				}
			} else if (chefInfo.getCookingEXP() >= legendaryExp) {
			    // Code for the condition where chef's cooking experience is Legendary or higher
				if(!main.getConfig().getBoolean("UseCustomEffects")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 500, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 300, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION , 100, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK , 200, 1));
				} else {
					applyEffects(player, effectsMap.get("LegendaryEffects"));
				}
			} else {
				assert true;
			}

			
			
		}
}
