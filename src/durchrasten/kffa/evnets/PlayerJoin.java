package durchrasten.kffa.evnets;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import durchrasten.kffa.MYQSL.Mysql;
import durchrasten.kffa.config.Config;
import durchrasten.kffa.main.Main;
import durchrasten.kffa.soreboard.ScoreboardAPI;


public class PlayerJoin implements Listener{

	public PlayerJoin(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public static void Player(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		Main.setIventar(p);
		
		if(!((Location) Main.getInstance().getConfig().get("kffa.system.spawn")).equals(Config.loc)){
			p.teleport((Location) Main.getInstance().getConfig().get("kffa.system.spawn"));
		}else {
			p.sendMessage(Main.prefix + "§cBitte spawn setzten mit §b/setloc");
		}
		
        for(Player all : Bukkit.getOnlinePlayers()){
        	if(Main.spectator.contains(all)){
        		p.hidePlayer(all);
        	}
        }
		
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
         	try {
     			ResultSet pos =  Mysql.getResult("SELECT * FROM stats WHERE UUID = '"+ p.getUniqueId() +"'");
     			if(pos.next()){
     				double kd =  (double) pos.getInt("kills") / (double) pos.getInt("Deaths");
     				kd = kd * 100;
     				kd = Math.round(kd);
     				kd = kd / 100;
     			    p.sendMessage(Main.prefix + "Deine KD ist: §e" + kd);
     			}else{
     				Mysql.update("INSERT INTO stats VALUES ('"+ p.getUniqueId() +"','"+ p.getName() +"','1','1')");
     				p.sendMessage(Main.prefix + "Willkommen auf dem FFA Server");
     			}
     		} catch (SQLException e1) {
     			e1.printStackTrace();
     		}
    	
    	
    	
    	 });
		
		
        e.setJoinMessage("§a>> §7" + p.getName());
        new ScoreboardAPI().createScoreboard(p);
	}
	
	
	
}
