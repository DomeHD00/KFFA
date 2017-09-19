package durchrasten.kffa.main;

import java.sql.Connection;
import java.util.ArrayList;

import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import durchrasten.kffa.MYQSL.Mysql;
import durchrasten.kffa.MYQSL.MysqlFile;
import durchrasten.kffa.commands.Setloc;
import durchrasten.kffa.commands.Setspawn;
import durchrasten.kffa.commands.Stats;
import durchrasten.kffa.config.Config;
import durchrasten.kffa.evnets.*;



public class Main extends JavaPlugin {

	public static String prefix = "§b[WarsUP]: §7";
	public static String noperm = prefix + "§cDu hast nicht die nötingen Rechte dafür!"; 
	private static Main instance;
	public static Connection connection;
	public static Cuboid spawn;
	
	
	public static ArrayList<Player> builder = new ArrayList<Player>();
	public static ArrayList<Player> spectator = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
        super.onEnable();
        instance = this;
        
        new PlayerJoin(this);
        new PlayerQuit(this);
        new PlayerDeath(this);
        new PlayerMove(this);
        new Block(this);
        new EntityDamage(this);
        new PlayerDrop(this);
        new EntityDamageByEntity(this);
        new PlayerLogin(this);
        
        //commands
        commads();
        //config
        Config.setConfig();
        
		//
     	Location l = (Location) getConfig().get("FFA.system.spawn");
     	org.bukkit.World w = l.getWorld();
     	
     	w.setDifficulty(Difficulty.NORMAL);
     	w.setPVP(true);
     	w.setMonsterSpawnLimit(0);
     	w.setAnimalSpawnLimit(0);
     	
        //mysql
        mysql();
        
        Location loc1 = (Location)instance.getConfig().get("FFA.system.pot1");
        Location loc2 = (Location)instance.getConfig().get("FFA.system.pot2");
        
        if(!loc1.equals(Config.loc) || !loc2.equals(Config.loc)){
        	spawn = new Cuboid(loc1, loc2);
        }
        	
        
       
	}
	
	public static void setIventar(Player p){
		p.getInventory().clear();
		
		ItemStack i = createIteam(Material.STICK);
		
		ItemMeta im = i.getItemMeta();
		Enchantment en = Enchantment.KNOCKBACK;
		
		im.addEnchant(en, 1, true);
		im.setDisplayName("§6KNOCKBACK Stab");
		im.spigot().setUnbreakable(true);
		
		i.setItemMeta(im);
		
		p.getInventory().addItem(i);
	}
	
	private static void mysql(){
		//mysql connect
		MysqlFile file = new MysqlFile();
		file.setStandert();
		file.readData();
		Mysql.connect(prefix);
		
		//mysql create databases
		Mysql.update("CREATE TABLE IF NOT EXISTS stats(UUID VARCHAR(64),Name VARCHAR(16),kills INT(100),Deaths INT(100))");
	}
	
	public void commads(){
		  Setloc exe = new Setloc(this);
		  getCommand("setloc").setExecutor(exe);
		  
		  durchrasten.kffa.commands.Builder exe1 = new durchrasten.kffa.commands.Builder(this);
		  getCommand("builder").setExecutor(exe1);
		  
		  Stats exe2 = new Stats(this);
		  getCommand("stats").setExecutor(exe2);
		  
		  durchrasten.kffa.commands.Spectator exe3 = new durchrasten.kffa.commands.Spectator(this);
		  getCommand("spectator").setExecutor(exe3);
		  
		  Setspawn exe4 = new Setspawn(this);
		  getCommand("setspawn").setExecutor(exe4);
	}
	
	public static Main getInstance() {
	     return instance;
	}
	
	private static ItemStack createIteam(Material m) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		item.setItemMeta(meta);
		
		return item;
	}
	
}
