package net.ddns.vcccd;

import java.util.HashMap;

import org.bukkit.Material;

public class LevelData {
	
	HashMap<Material, Integer> FoodData = new HashMap<Material, Integer>();
	Material[] Foods = {Material.COOKED_BEEF, Material.COOKED_CHICKEN};
	int[] Values = {5, 3};
	
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
