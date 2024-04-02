package net.ddns.vcccd;

import java.util.HashMap;

import org.bukkit.Material;

public class LevelData {
	
	HashMap<Material, Integer> FoodData = new HashMap<Material, Integer>();
	   Material[] Foods = {
	            Material.COOKED_BEEF,
	            Material.COOKED_CHICKEN,
	            Material.COOKED_COD,
	            Material.COOKED_MUTTON,
	            Material.COOKED_PORKCHOP,
	            Material.COOKED_RABBIT,
	            Material.BAKED_POTATO,
	            Material.BEETROOT_SOUP,
	            Material.MUSHROOM_STEW,
	            Material.RABBIT_STEW,
	            Material.SUSPICIOUS_STEW,
	            // Add more cookable food items here as needed
	    };

	    // Corresponding values for cookable food items
	    int[] Values = {
	            5, // Cooked Beef value
	            3, // Cooked Chicken value
	            3, // Cooked Cod value
	            2, // Cooked Mutton value
	            4, // Cooked Porkchop value
	            2, // Cooked Rabbit value
	            3, // Baked Potato value
	            2, // Beetroot Soup value
	            2, // Mushroom Stew value
	            2, // Rabbit Stew value
	            1, // Suspicious Stew value
	            // Add more corresponding values here as needed
	    };
	
	public LevelData() {
		for(int i = 0; i < Foods.length; i++) {
			FoodData.put(Foods[i], Values[i]);
		}
	}
	
	public int getFoodExp(Material material) {
		try {
			return(FoodData.get(material));
		} catch (Exception e){
			return(0);
		}
	}

}
