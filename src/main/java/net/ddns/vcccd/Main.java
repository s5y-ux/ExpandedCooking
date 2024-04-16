package net.ddns.vcccd;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private Connection publicConnection;
	private ConsoleCommandSender console = getServer().getConsoleSender();
	
	public Connection getPublicConnection() {
		return(this.publicConnection);
	}
	
	@Override
	public void onEnable() {
		FileConfiguration config = this.getConfig();
		config.addDefault("HomemadeEXP", 960);
		config.addDefault("TastyEXP", 1920);
		config.addDefault("DelusciousEXP", 2880);
		config.addDefault("GourmetEXP", 4800);
		config.addDefault("RestaurantQualityEXP", 6720);
		config.addDefault("OneStarEXP", 9600);
		config.addDefault("TwoStarEXP", 12480);
		config.addDefault("ThreeStarEXP", 16320);
		config.addDefault("LedgendaryEXP", 30000);
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new CookedFood(this), this);
		getServer().getPluginManager().registerEvents(new EatFood(this), this);
		this.getCommand("setcookingexp").setExecutor(new ChangeLevel(this));
		this.getCommand("getcookingexp").setExecutor(new GetLevel(this));
		this.getCommand("admindatabase").setExecutor(new Admin(this));
		
		File rootDirectory = new File("plugins/ExpandedCooking/PlayerData.db");
		if(!rootDirectory.exists()) {
			try {
				if(rootDirectory.createNewFile()) {
					console.sendMessage("File path created, no errors!");
				} else {
					console.sendMessage("An error occured when creating directory");
				}
			} catch (IOException e) {
				console.sendMessage(ChatColor.RED + "FATAL ERROR! Can't even check for database file existance!");
			}
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + "plugins/ExpandedCooking/PlayerData.db");
			this.publicConnection = conn;
			console.sendMessage(ChatColor.YELLOW + "Connected to Database successfully!");
		} catch (SQLException e) {
			console.sendMessage(ChatColor.RED + "Database machine broke...");
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
			console.sendMessage(ChatColor.RED + "Problem disabling database in onDisable");
		}
	}
	
	public ConsoleCommandSender getConsole() {
		return(console);
	}
    
    
}

