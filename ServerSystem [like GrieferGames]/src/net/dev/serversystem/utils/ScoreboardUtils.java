package net.dev.serversystem.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardUtils {

	public static HashMap<String, Scoreboard> scoreboards = new HashMap<>();
	
	public static void setScoreboard(Player p) {
		if(!(scoreboards.containsKey(p.getName()))) {
			Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective o = sb.registerNewObjective("System", "Scoreboard");
			
			o.setDisplaySlot(DisplaySlot.SIDEBAR);
			o.setDisplayName("§6§lDeinServer.net");
			
			registerNewTeam(sb, "§0", "§0", "§0", "§0");
			o.getScore("§0").setScore(13);
			
			registerNewTeam(sb, "§1", "§7▻ ", "§3§lServer", "§1");
			o.getScore("§1").setScore(12);
			
			registerNewTeam(sb, "§2", "§r" + Bukkit.getServerName(), "§2", "§2");
			o.getScore("§2").setScore(11);
			
			registerNewTeam(sb, "§3", "§2", "§2", "§3");
			o.getScore("§3").setScore(10);
			
			registerNewTeam(sb, "§4", "§7▻ ", "§3§lOnline", "§4");
			o.getScore("§4").setScore(9);
			
			registerNewTeam(sb, "§5", "§r" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers(), "§5", "§5");
			o.getScore("§5").setScore(8);
			
			registerNewTeam(sb, "§6", "§6", "§6", "§6");
			o.getScore("§6").setScore(7);
			
			registerNewTeam(sb, "§7", "§7▻ ", "§3§lKontostand", "§7");
			o.getScore("§7").setScore(6);
			
			registerNewTeam(sb, "§8", "§r-1", "§8", "§8");
			o.getScore("§8").setScore(5);
			
			registerNewTeam(sb, "§9", "§9", "§9", "§9");
			o.getScore("§9").setScore(4);
			
			registerNewTeam(sb, "§a", "§7▻ ", "§3§lShop", "§a");
			o.getScore("§a").setScore(3);
			
			registerNewTeam(sb, "§b", "§rDeinServer.net", "§b", "§b");
			o.getScore("§b").setScore(2);
			
			registerNewTeam(sb, "§c", "§c", "§c", "§c");
			o.getScore("§c").setScore(1);

			registerNewRankedTeam(sb, "000Owner", "§4Owner §7| §4", "", "system.owner");
			registerNewRankedTeam(sb, "001Admin", "§4Admin §7| §4", "", "system.admin");
			registerNewRankedTeam(sb, "002Dev", "§bDev §7| §b", "", "system.dev");
			registerNewRankedTeam(sb, "003SrBuild", "§2SrBuild §7| §2", "", "system.srbuild");
			registerNewRankedTeam(sb, "004Build", "§2Build §7| §2", "", "system.build");
			registerNewRankedTeam(sb, "005SrMod", "§cSrMod §7| §c", "", "system.srmod");
			registerNewRankedTeam(sb, "006Mod", "§cMod §7| §c", "", "system.mod");
			registerNewRankedTeam(sb, "007Sup", "§9Sup §7| §9", "", "system.sup");
			registerNewRankedTeam(sb, "008YT", "§5YT §7| §5", "", "system.youtube");
			registerNewRankedTeam(sb, "009PrimeP", "§3P§6+ §7| §3", "", "system.primeplus");
			registerNewRankedTeam(sb, "010Prime", "§3P §7| §3", "", "system.prime");
			registerNewRankedTeam(sb, "999Default", "§7Spieler §7| §7", "", "NONE");
			
			p.setScoreboard(sb);
			scoreboards.put(p.getName(), sb);
		} else {
			Scoreboard sb = scoreboards.get(p.getName());
			
			sb.getTeam("§2").setPrefix("§r" + Bukkit.getServerName());
			sb.getTeam("§5").setPrefix("§r" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			sb.getTeam("§8").setPrefix("§r-1");
			sb.getTeam("§b").setPrefix("§rDeinServer.net");
		}
	}

	private static void registerNewTeam(Scoreboard sb, String teamName, String teamPrefix, String teamSuffix, String playerNameToAdd) {
		Team t = sb.registerNewTeam(teamName);
		t.setPrefix(teamPrefix);
		t.setSuffix(teamSuffix);
		
		t.addEntry(playerNameToAdd);
	}
	
	private static void registerNewRankedTeam(Scoreboard sb, String teamName, String teamPrefix, String teamSuffix, String permission) {
		Team t = sb.registerNewTeam(teamName);
		t.setPrefix(teamPrefix);
		t.setSuffix(teamSuffix);
		
		if(!(permission.equalsIgnoreCase("NONE"))) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				if((sb.getEntryTeam(all.getName()) == null) && all.hasPermission(permission)) {
					t.addEntry(all.getName());
					all.setDisplayName(teamPrefix + all.getName() + teamSuffix);
				}
			}
		}
	}

}