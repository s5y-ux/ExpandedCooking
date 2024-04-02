package net.ddns.vcccd;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	ConsoleCommandSender console = getServer().getConsoleSender();
	
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
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public ConsoleCommandSender getConsole() {
		return(console);
	}

}
