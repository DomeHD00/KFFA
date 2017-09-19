package durchrasten.kffa.evnets;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import durchrasten.kffa.main.Main;

public class Block implements Listener{

	public Block(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public static void BlockBreak(BlockBreakEvent e){
		if(Main.builder.contains(e.getPlayer())){
			e.setCancelled(false);
		}else{
			e.setCancelled(true);
		}
		
		
	}

	@EventHandler
	public static void BlockPlace(BlockPlaceEvent e){
		if(Main.builder.contains(e.getPlayer())){
			e.setCancelled(false);
		}else{
			e.setCancelled(true);
		}
		
		
	}
	
	@EventHandler
	public void on(BlockDamageEvent e) {
        if(e.getBlock().getType().equals(Material.WHEAT)){
            e.setCancelled(true);
        }
    }
	
	
}
