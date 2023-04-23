package at.cracki.ggfeaturesrel;

import at.cracki.ggfeaturesrel.files.FileManager;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class Scoreboard {
    public static YamlDocument sb = FileManager.getScoreboardConfig();

    public static void sendScoreboard(Player player) {
        String title = sb.getString("Scoreboard.Messages.Title").replaceAll("&", "§");
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("gg", "f");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(title);

        for(int i = 1; i <= 13; i++) {
            Team team = scoreboard.registerNewTeam("Line" + i);
            objective.getScore(getPr(i) + " ").setScore(i);
            team.addEntry(getPr(i) + " ");
            team.setPrefix(getLine(player, i));
        }
        player.setScoreboard(scoreboard);
    }

    public static void updateScoreboard(Player player) {
        try {
            org.bukkit.scoreboard.Scoreboard scoreboard = player.getScoreboard();
            for(int i = 1; i <= 13; i++) {
                Team team = scoreboard.getTeam("Line" + i);
                team.setPrefix(getLine(player, i));
            }
        } catch(NullPointerException exception) {
            String title = sb.getString("Scoreboard.Messages.Title").replaceAll("&", "§");
            org.bukkit.scoreboard.Scoreboard scoreboard = player.getScoreboard();
            Objective objective = scoreboard.registerNewObjective("gg", "f");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(title);

            for(int i = 1; i <= 13; i++) {
                Team team = scoreboard.registerNewTeam("Line" + i);
                objective.getScore(getPr(i) + " ").setScore(i);
                team.addEntry(getPr(i) + " ");
                team.setPrefix(getLine(player, i));
            }
            player.setScoreboard(scoreboard);
        }
    }

    public static String getPr(Integer i) {
        String pr = "§1";
        if (i == 2) { pr = "§2";
        } else if (i == 3) { pr = "§3";
        } else if (i == 4) { pr = "§4";
        } else if (i == 5) { pr = "§5";
        } else if (i == 6) { pr = "§6";
        } else if (i == 7) { pr = "§7";
        } else if (i == 8) { pr = "§8";
        } else if (i == 9) { pr = "§9";
        } else if (i == 10) { pr = "§a";
        } else if (i == 11) { pr = "§b";
        } else if (i == 12) { pr = "§c";
        } else if (i == 13) { pr = "§d";
        }
        return pr;
    }

    public static boolean showLine(Player player, Integer i) {
        return !getLine(player, i).equalsIgnoreCase("%NULL%");
    }

    public static String getLine(Player player, Integer i) {
        return replace(player, sb.getString("Scoreboard.Messages.Line" + i));
    }
    /*

    d
     */
    public static String replace(Player player, String string) {
        String OnPlayer = String.valueOf(Bukkit.getOnlinePlayers().size());
        String MaxPlayer = String.valueOf(Bukkit.getMaxPlayers());
        PluginManager manager = Bukkit.getServer().getPluginManager();
        Plugin ess = manager.getPlugin("Essentials");
        String EssentialsMoney;
        if (ess == null || !ess.isEnabled()) {
            EssentialsMoney = "?Essentials?";
        } /*else {
            Essentials essp = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
            Double balance = essp.getUser(player).getMoney().doubleValue();
            EssentialsMoney = String.valueOf(balance);
        }*/
        return string.replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%BALANCE%", "NaN").replaceAll("%WORLD%", player.getWorld().getName());
    }
}