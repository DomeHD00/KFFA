package durchrasten.kffa.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import durchrasten.kffa.main.Main;

public class Setspawn implements CommandExecutor {

	private Main plugin;

	public Setspawn(Main Instance) {
		this.plugin = Main.getInstance();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("setspawn")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("§cNur Spieler dürfen /setspawn nutzen");
				return true;
			}

			Player p = (Player) sender;

			if (p.hasPermission("kffa.system.administration")) {
				if(args.length == 1){


					if(args[0].equals("1")){
						Main.getInstance().getConfig().set("FFA.system.pot1", p.getLocation());
						Main.getInstance().saveConfig();
						Main.getInstance().reloadConfig();
						p.sendMessage(Main.prefix + "§cSpawn Schutze Stelle 1 gesetzt");
					}else if(args[0].equals("2")){
						Main.getInstance().getConfig().set("FFA.system.pot2", p.getLocation());
						Main.getInstance().saveConfig();
						Main.getInstance().reloadConfig();
						p.sendMessage(Main.prefix + "§cSpawn Schutze Stelle 2 gesetzt");
					}else {
						p.sendMessage(Main.prefix + "§c/setspawn <1/2>");
					}
					
					
				}else {
					p.sendMessage(Main.prefix + "§c/setspawn <1/2>");
				}
				
				
			}else {
				p.sendMessage(Main.noperm);
			}
			
		}
		return true;
	}

}
