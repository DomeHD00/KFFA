package durchrasten.kffa.MYQSL;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import durchrasten.kffa.main.Main;

public class MysqlFile {

	public void setStandert(){
		FileConfiguration cfg = getFileConfiguration();
		
		cfg.options().copyDefaults(true);
		
		cfg.addDefault("host", "localhost");
		cfg.addDefault("port", "3306");
		cfg.addDefault("database", "Bedwars");
		cfg.addDefault("username", "test");
		cfg.addDefault("password", "test123");
		
		
		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getFile() {
		return new File(Main.getInstance().getDataFolder(), "mysql.yml");
	}
	
	private FileConfiguration getFileConfiguration(){
		return YamlConfiguration.loadConfiguration(getFile());
	}
	
	public void readData(){
		FileConfiguration cfg = getFileConfiguration();
		
		Mysql.host = cfg.getString("host");
		Mysql.port = cfg.getString("port");
		Mysql.database = cfg.getString("database");
		Mysql.username = cfg.getString("username");
		Mysql.password = cfg.getString("password");
	}
}
