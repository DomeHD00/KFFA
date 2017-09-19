package durchrasten.kffa.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import durchrasten.kffa.main.Main;



public class Spectator implements CommandExecutor {

	private Main plugin;

	public Spectator(Main Instance) {
		this.plugin = Main.getInstance();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("spectator")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(Main.prefix + "§cNur Spieler Dürfen /spectator nutzen");
				return true;
			}
			Player p = (Player) sender;

			if (p.hasPermission("kffa.commands.spec")) {
				
				if(Main.spectator.contains(p)){
					Main.spectator.remove(p);
					for(Player all : Bukkit.getOnlinePlayers()){
						all.showPlayer(p);
					} 

					
					
					p.setAllowFlight(false);
					p.sendMessage(Main.prefix + "§aDu bist nun nicht mehr Spectatormodus!");	
					p.teleport((Location) Main.getInstance().getConfig().get("kffa.system.spawn"));
				}else {
					Main.spectator.add(p);
					for(Player all : Bukkit.getOnlinePlayers()){
					if(!all.hasPermission("kffa.spec.see")){
						all.hidePlayer(p);
					}
					}
					p.setAllowFlight(true);
					p.sendMessage(Main.prefix + "§aDu bist nun im Spectatormodus!");
				
				}
				}else {
				p.sendMessage(Main.noperm);
				
			}
			}
			
		
	
		return true;
	}

}
