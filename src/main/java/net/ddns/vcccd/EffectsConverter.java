package net.ddns.vcccd;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.potion.PotionEffectType;

public class EffectsConverter {
	private String[] effectValues = {"poison", "fireResistance", "protection", "regeneration", "luck"};
	private PotionEffectType[] effects = {PotionEffectType.POISON, PotionEffectType.FIRE_RESISTANCE, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.REGENERATION, PotionEffectType.LUCK};
	private Map<String, PotionEffectType> effectDict = new HashMap<>();
	
	public EffectsConverter() {
		for(int i = 0; i < effectValues.length; i++) {
			effectDict.put(effectValues[i], effects[i]);
		}
	}
	
	public PotionEffectType getEffect(String parameter) {
		return(effectDict.get(parameter));
	}
}
