package durchrasten.kffa.soreboard;

import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import durchrasten.kffa.MYQSL.Mysql;


public class ScoreboardAPI {

	public void createScoreboard(Player p){
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective score = board.registerNewObjective("aaa", "bbb");
		
		score.setDisplayName("§6KFFA");
		score.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		
		int d = 0;
		int k= 0;
		
			try {
				ResultSet pos =  Mysql.getResult("SELECT * FROM stats WHERE UUID = '"+p.getUniqueId()+"'");
				if(pos.next()){
					k =  pos.getInt("kills");
					d = pos.getInt("Deaths");
				}
			}catch (Exception e) {
				
			}
		
		score.getScore("§5Kills").setScore(d);
		score.getScore("§5Tode").setScore(k);
		
		p.setScoreboard(score.getScoreboard());
	}
	
	public void updateScoreboard(Player p){
		Objective score = p.getScoreboard().getObjective("aaa");
		
		int d = 0;
		int k= 0;
		
			try {
				ResultSet pos =  Mysql.getResult("SELECT * FROM stats WHERE UUID = '"+p.getUniqueId()+"'");
				if(pos.next()){
					k =  pos.getInt("kills");
					d = pos.getInt("Deaths");
				}
			}catch (Exception e) {
				
			}
		
			score.getScore("§5Kills").setScore(d);
			score.getScore("§5Tode").setScore(k);
		
		
	}
	
}
