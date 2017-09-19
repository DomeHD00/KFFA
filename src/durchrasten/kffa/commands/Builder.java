package durchrasten.kffa.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import durchrasten.kffa.main.Main;

public class Builder implements CommandExecutor {

	private Main plugin;

	public Builder(Main Instance) {
		this.plugin = Main.getInstance();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("builder")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("§cNur Spieler dürfen /Builder nutzen");
				return true;
			}

			Player p = (Player) sender;

			if (p.hasPermission("kffa.system.administration")) {

				if(Main.builder.contains(p)){
				Main.builder.remove(p);
				Main.setIventar(p);
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(Main.prefix + "§aDu hast den Buildermodus verlassen!");
				}else {
					Main.builder.add(p);
					p.setGameMode(GameMode.CREATIVE);
					p.getInventory().clear();
					p.sendMessage(Main.prefix + "§aDu hast den Buildermodus eingeschaltet!");
				}
				
			}

		}
		return true;

	}

}
