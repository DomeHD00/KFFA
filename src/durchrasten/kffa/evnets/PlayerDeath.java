package durchrasten.kffa.evnets;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import durchrasten.kffa.MYQSL.Mysql;
import durchrasten.kffa.main.Main;
import durchrasten.kffa.soreboard.ScoreboardAPI;

public class PlayerDeath implements Listener {

	public PlayerDeath(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public static void PlayerDeathEvent(PlayerDeathEvent e) {
		Player p = e.getEntity().getKiller();
		Player taget = e.getEntity().getPlayer();
		

		Main.setIventar(taget);
		
		
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
			try {
				ResultSet pos = Mysql.getResult("SELECT * FROM stats WHERE UUID = '" + taget.getUniqueId() + "'");
				if (pos.next()) {
					int Deaths = pos.getInt("Deaths") + 1;
					Mysql.update("update stats set Deaths = '" + Deaths + "' where UUID = '" + taget.getUniqueId()	+ "'");
				}
	
				new ScoreboardAPI().updateScoreboard(taget);
		if(p != null){
			ResultSet pos1 = Mysql.getResult("SELECT * FROM stats WHERE UUID = '" + p.getUniqueId() + "'");
			if (pos1.next()) {
				int kills = pos1.getInt("kills") + 1;

				Mysql.update("update stats set kills = '" + kills + "' where UUID = '" + p.getUniqueId() + "'");
			}
			new ScoreboardAPI().updateScoreboard(p);
			}
			


		} catch (SQLException ee) {
		}
		});
	

	}
	
	@EventHandler
	public static void PlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Main.setIventar(p);
		e.setRespawnLocation((Location) Main.getInstance().getConfig().get("FFA.system.spawn"));
		
	}
	
}
