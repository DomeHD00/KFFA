package durchrasten.kffa.commands;

import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import durchrasten.kffa.MYQSL.Mysql;
import durchrasten.kffa.main.Main;


public class Stats implements CommandExecutor {

	private Main plugin;

	public Stats(Main Instance) {
		this.plugin = Main.getInstance();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("stats")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(Main.prefix + "§cNur Spieler Dürfen /stats nutzen");
				return true;
			}
			Player p = (Player) sender;
	
			Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
				try {
					ResultSet pos =  Mysql.getResult("SELECT * FROM stats WHERE UUID = '"+p.getUniqueId()+"'");
					if(pos.next()){
			
						p.sendMessage(Main.prefix + "Deine Kills:§e " + pos.getInt("kills"));
						p.sendMessage(Main.prefix + "Deine Tode:§e " + pos.getInt("Deaths"));
						 if(pos.getInt("kills") != 0 && pos.getInt("Deaths") != 0){	 
			    				double kd =  (double) pos.getInt("kills") / (double) pos.getInt("Deaths");
			    				kd = kd * 100;
			    				kd = Math.round(kd);
			    				kd = kd / 100;
							 p.sendMessage(Main.prefix + "Deine KD:§e " + kd);
						 }else{
							 p.sendMessage(Main.prefix + "Deine KD:§e 0"); 
						 }
						
						
						
					}
				}catch (Exception e) {
	
					}
				});
		}
	return true;
	}
	
}
