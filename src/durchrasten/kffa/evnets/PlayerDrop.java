package durchrasten.kffa.evnets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.Plugin;

import durchrasten.kffa.main.Main;

public class PlayerDrop implements Listener{
	
	public static Plugin plugin;
	

	public PlayerDrop(Main plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void PlayerPickup(PlayerPickupItemEvent e){
		
		Player p = e.getPlayer();
		
		if(Main.builder.contains(p)){
			e.setCancelled(false);
		}else{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Player(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		
		if(Main.builder.contains(p)){
			e.setCancelled(false);
		}else{
			e.setCancelled(true);
		}
	}

}
