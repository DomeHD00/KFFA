package durchrasten.kffa.evnets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import durchrasten.kffa.main.Main;


public class EntityDamage implements Listener {
	
	public EntityDamage(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	 public void onEntityDamageEvent(EntityDamageEvent e) {
	        if (e.getEntity() instanceof Player) {
		        if (e.getCause() == DamageCause.FALL) {
		        	e.setCancelled(true);
		        } 
	        }

	 }

}
