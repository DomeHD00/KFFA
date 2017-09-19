package durchrasten.kffa.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import durchrasten.kffa.main.Main;

public class Setloc implements CommandExecutor {

	private Main plugin;

	public Setloc(Main Instance) {
		this.plugin = Main.getInstance();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("setloc")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(Main.prefix + "§cNur Spieler Dürfen /setloc nutzen");
				return true;
			}
			Player p = (Player) sender;
			
			if(p.hasPermission("kffa.commands.setloc")){
			Main.getInstance().getConfig().set("FFA.system.spawn", p.getLocation());
			
			Main.getInstance().saveConfig();
			Main.getInstance().reloadConfig();
			p.sendMessage(Main.prefix + "§aDu hast den Lobby gesetzt");
			}else {
				p.sendMessage(Main.noperm);
			}
			
		}
		return true;
}
	
}
