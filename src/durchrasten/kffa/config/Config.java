package durchrasten.kffa.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import durchrasten.kffa.main.Main;


public class Config {

	public static Location loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
	
	public static void setConfig () {
	
		//spawn sachen! Sachen !
		Main.getInstance().getConfig().addDefault("FFA.system.spawn", loc);
		Main.getInstance().getConfig().addDefault("FFA.system.pot1", loc);
		Main.getInstance().getConfig().addDefault("FFA.system.pot2", loc);
		
		//save and reload
		Main.getInstance().getConfig().options().copyDefaults(true);
		Main.getInstance().saveConfig();
		Main.getInstance().reloadConfig();
	}
	
}
