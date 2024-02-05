package net.ddns.vcccd;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	ConsoleCommandSender console = getServer().getConsoleSender();
	
	@Override
	public void onEnable() {
		FileConfiguration config = this.getConfig();
		config.addDefault("XpPerCook", 0);
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new CookedFood(this), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public ConsoleCommandSender getConsole() {
		return(console);
	}

}
