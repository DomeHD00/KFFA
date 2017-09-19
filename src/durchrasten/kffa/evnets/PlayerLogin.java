package durchrasten.kffa.evnets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import durchrasten.kffa.main.Main;



public class PlayerLogin implements Listener{
	
	public static Plugin plugin;
	

	public PlayerLogin(Main plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void Login(PlayerLoginEvent e){
		
		Player p = e.getPlayer();
		
		if(15 == Main.getInstance().getServer().getOnlinePlayers().size()){
			if(p.hasPermission("kffa.joinforce")){
				for(Player all : Main.getInstance().getServer().getOnlinePlayers()){
					if(!all.hasPermission("kffa.joinforce")){
						all.kickPlayer(Main.prefix + "§cDu wurdes gekickt um einem VIP/Youtuber/Teammitgild Platz zu machen!");
						e.allow();
						break;
					}
				}
			}else {
				e.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_OTHER, Main.prefix + "§6Kaufe dir VIP um zu joinen");
			}
		}
	}
}
