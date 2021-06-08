package de.dustin.utils.SideBar;

import de.dustin.Main;
import de.dustin.MySQL.Stats;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Sidebar extends ScoreboardBuilder{

    public Sidebar(Player player) {
        super(player, ChatColor.BOLD + "" + ChatColor.AQUA + "StickFight");
        run();
    }

    @Override
    public void createScoreboard() {
        setScore(ChatColor.UNDERLINE.toString(), 10);
        setScore(ChatColor.AQUA + "Alltime Stats", 9);
        setScore(ChatColor.BLACK.toString(), 8);
        setScore(ChatColor.AQUA + "Kills", 7);
        setScore(ChatColor.GOLD + String.valueOf(Stats.getKills(player.getUniqueId().toString())), 6);
        setScore(ChatColor.DARK_RED.toString(), 5);
        setScore(ChatColor.AQUA + "Deaths", 4);
        setScore(ChatColor.GOLD + String.valueOf(Stats.getDeaths(player.getUniqueId().toString())), 3);

    }

    @Override
    public void update() {

    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                setScore(ChatColor.GOLD + String.valueOf(Stats.getKills(player.getUniqueId().toString())), 6);
                setScore(ChatColor.GOLD + String.valueOf(Stats.getDeaths(player.getUniqueId().toString())), 3);
            }
        }.runTaskTimer(Main.getInstance(), 20L, 20L);
    }
}
