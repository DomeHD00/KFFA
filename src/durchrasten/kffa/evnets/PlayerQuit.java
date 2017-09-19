package durchrasten.kffa.evnets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import durchrasten.kffa.main.Main;

public class PlayerQuit implements Listener{

	public PlayerQuit(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public static void Player(PlayerQuitEvent e){
		Player p = e.getPlayer();

		e.setQuitMessage("§c<< §7" + p.getName());
		if(Main.builder.contains(p)){
			Main.builder.remove(p);
		}
	}
	
	@EventHandler
	public static void WeatherChange(WeatherChangeEvent e){
		e.setCancelled(true);
	}
	

}
