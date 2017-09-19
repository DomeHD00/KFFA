package durchrasten.kffa.evnets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import durchrasten.kffa.main.Main;

public class PlayerMove implements Listener{

	public PlayerMove(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public static void Player(PlayerMoveEvent e){
		Player p = e.getPlayer();
		p.setFoodLevel(20);
		
		if(p.getLocation().getY() == -4){
			p.setHealth(0);
		}
		
		
	}

	
}
