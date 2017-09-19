package durchrasten.kffa.evnets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import durchrasten.kffa.main.Main;

public class EntityDamageByEntity implements Listener{

	public EntityDamageByEntity(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	

	@EventHandler
	public static void EntityDamage(EntityDamageByEntityEvent e){		
		Player p = (Player) e.getDamager();
		
		if(Main.spawn.contains(p.getLocation())){
			e.setCancelled(true);
		}
        
		
	
	}
	
	
}
