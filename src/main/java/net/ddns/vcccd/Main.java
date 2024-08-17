package net.ddns.vcccd;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private Connection publicConnection;
	private ConsoleCommandSender console = getServer().getConsoleSender();
	private String prefix = ChatColor.translateAlternateColorCodes('&', "&f[&eExpanded Cooking&f] - ");
	
	public Connection getPublicConnection() {
		return(this.publicConnection);
	}
	
	@Override
	public void onEnable() {
		FileConfiguration config = this.getConfig();
		
        Map<String, List<String>> effectsMap = new HashMap<>();
        
        String[] categories = {"Homemade", "Tasty", "Deluscious", "Gourmet", "RestaurantQuality", "OneStar", "TwoStar", "ThreeStar", "Ledgendary"};
        
        for (String category : categories) {
            effectsMap.put(category, new ArrayList<>());
        }
        
        String[] effectValues = {"poison", "fireResistance", "protection", "regeneration", "luck"};
        
        for (String category : categories) {
            List<String> effectsList = effectsMap.get(category);
            for (String value : effectValues) {
                effectsList.add(value);
            }
        }
		
        config.addDefault("UseCustomEffects", false);
		config.addDefault("EffectsTime", 5);
		config.addDefault("HomemadeEXP", 960);
		config.addDefault("HomemadeEffects", effectsMap.get("Homemade"));
		config.addDefault("TastyEXP", 1920);
		config.addDefault("TastyEffects", effectsMap.get("Tasty"));
		config.addDefault("DelusciousEXP", 2880);
		config.addDefault("DelusciousEffects", effectsMap.get("Deluscious"));
		config.addDefault("GourmetEXP", 4800);
		config.addDefault("GourmetEffects", effectsMap.get("Gourmet"));
		config.addDefault("RestaurantQualityEXP", 6720);
		config.addDefault("RestaurantQualityEffects", effectsMap.get("RestaurantQuality"));
		config.addDefault("OneStarEXP", 9600);
		config.addDefault("OneStarEffects", effectsMap.get("OneStar"));
		config.addDefault("TwoStarEXP", 12480);
		config.addDefault("TwoStarEffects", effectsMap.get("TwoStar"));
		config.addDefault("ThreeStarEXP", 16320);
		config.addDefault("ThreeStarEffects", effectsMap.get("ThreeStar"));
		config.addDefault("LegendaryEXP", 30000);
		config.addDefault("LegendaryEffects", effectsMap.get("Ledgendary"));
		config.addDefault("HomemadeQualityName", "&aHome-Made");
		config.addDefault("CardboardQuality", "&lBasically Cardboard");
		config.addDefault("TastyQualityName", "&6Tasty");
		config.addDefault("DelusciousQualityName", "&eDeluscious");
		config.addDefault("GourmetQualityName", "&bGourmet");
		config.addDefault("RestaurantQualityName", "&9Restaurant Quality");
		config.addDefault("OneStarQualityName", "&3One Star");
		config.addDefault("TwoStarQualityName", "&3Two Star");
		config.addDefault("ThreeStarQualityName", "&5Three Star");
		config.addDefault("LegendaryQualityName", "&6Ledgendary");
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new CookedFood(this), this);
		getServer().getPluginManager().registerEvents(new EatFood(this), this);
		getServer().getPluginManager().registerEvents(new UpdateChecker(), this);
		this.getCommand("setcookingexp").setExecutor(new ChangeLevel(this));
		this.getCommand("getcookingexp").setExecutor(new GetLevel(this));
		this.getCommand("admindatabase").setExecutor(new Admin(this));
		
		File rootDirectory = new File("plugins/ExpandedCooking/PlayerData.db");
		if(!rootDirectory.exists()) {
			try {
				if(rootDirectory.createNewFile()) {
					console.sendMessage(prefix + "File path created, no errors!");
				} else {
					console.sendMessage(prefix + ChatColor.RED + "An error occured when creating directory, message the developer.");
				}
			} catch (IOException e) {
				console.sendMessage(prefix + ChatColor.RED + "FATAL ERROR! Can't even check for database file existance! Jesus...");
			}
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + "plugins/ExpandedCooking/PlayerData.db");
			this.publicConnection = conn;
			console.sendMessage(prefix + ChatColor.YELLOW + "Connected to Database successfully!");
		} catch (SQLException e) {
			console.sendMessage(prefix + ChatColor.RED + "Database machine broke... (onEnable) nag the developer!");
		}
		
		Statement statement;
		try {
			statement = this.publicConnection.createStatement();
			String createTableSQL = "CREATE TABLE IF NOT EXISTS PlayerData (\n"
	        		+ "uuid VARCHAR(50),\n"
	        		+ "cookingexp INT"
	        		+ ");";
	        
	        statement.execute(createTableSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {
		try {
			this.publicConnection.close();
		} catch (SQLException e) {
			console.sendMessage(prefix + ChatColor.RED + "Problem disabling database in onDisable");
		}
	}
	
	public ConsoleCommandSender getConsole() {
		return(console);
	}
    
    
}

