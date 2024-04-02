package net.ddns.vcccd;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.CampfireStartEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class CookedFood implements Listener{
	private final Main main;
	private LevelData EXPData = new LevelData();
	
	public CookedFood(Main main) {
		this.main = main;
	}
	
	private Map<Location, Player> furnaceInteractions = new HashMap<>();

	@EventHandler
	public void onCampFireCook(CampfireStartEvent event) {
		ItemStack finalCook = event.getRecipe().getResult();
		ItemMeta finalCookMeta = finalCook.getItemMeta();
		List<String> Lore = new ArrayList<String>();
		Lore.add(ChatColor.translateAlternateColorCodes('&', "&8Quality: &lBasically Cardboard"));
		finalCook.setItemMeta(finalCookMeta);

	}
	
	@EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
    	try {
    	Block furnaceBlock = event.getBlock();
        Location furnaceLocation = furnaceBlock.getLocation();
    	Player player = furnaceInteractions.get(furnaceLocation);
        ItemStack resultItem = event.getResult();
        ItemMeta resultItemMeta = resultItem.getItemMeta();
        List<String> Lore = new ArrayList<String>();
        PlayerData ourPlayer = new PlayerData(main, player);
        Lore.add(ourPlayer.getCookQuality());
        Lore.add(ourPlayer.getChef());
        resultItemMeta.setLore(Lore);
        resultItem.setItemMeta(resultItemMeta); }
    	catch (Exception e) {
    		event.setCancelled(true);
    	}
    
    }

	
	@EventHandler
	public void OnPlayerCook(FurnaceExtractEvent event) {
		Player player = event.getPlayer();
		PlayerData ourPlayerData = new PlayerData(main, player);
		int Calculation = ourPlayerData.getCookingEXP() + (EXPData.getFoodExp(event.getItemType()) * event.getItemAmount());
		ourPlayerData.setCookingExp(Calculation);
		player.sendMessage("You have " + ChatColor.GREEN + Integer.toString(Calculation) + ChatColor.WHITE + " Cooking EXP!");
		player.playSound(player, Sound.BLOCK_FIRE_EXTINGUISH, 100, 100);
	}
	
	@EventHandler
	public void FurnaceInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block furnaceBlock = event.getClickedBlock();
		if (furnaceBlock.getState() instanceof Furnace) {
            Furnace furnace = (Furnace) furnaceBlock.getState();
            furnaceInteractions.put(furnace.getLocation(), player);
	}
	}
		
		@EventHandler
		public void FurnacePlace(BlockPlaceEvent event) {
			Player player = event.getPlayer();
			Block furnaceBlock = event.getBlock();
			if (furnaceBlock.getState() instanceof Furnace) {
	            Furnace furnace = (Furnace) furnaceBlock.getState();
	            furnaceInteractions.put(furnace.getLocation(), player);
		}

}
}
