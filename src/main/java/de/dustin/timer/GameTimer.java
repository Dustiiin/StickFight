package de.dustin.timer;

import de.dustin.Main;
import de.dustin.utils.GameState;
import de.dustin.utils.ALLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class GameTimer {

    public static int task;
    public static int time = 30*60 + 1;
    public static void start() {


        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {


            @Override
            public void run() {

                ALLManager.sendActionbar(Main.one,  ChatColor.GREEN + String.valueOf(Main.variablen.getKillsone()) + ChatColor.GRAY + " | " + ChatColor.RED + String.valueOf(Main.variablen.getKillstwo()));
                ALLManager.sendActionbar(Main.two,  ChatColor.GREEN + String.valueOf(Main.variablen.getKillstwo()) + ChatColor.GRAY + " | " + ChatColor.RED + String.valueOf(Main.variablen.getKillsone()));
                if (time == 0) {
                    Bukkit.getScheduler().cancelTask(task);
                    GameState.setState(GameState.ENDING);
                    EndingTimer.start();
                    for (Player all : Main.variablen.players) {
                        all.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                    }
                    return;

                }
                time--;


            }
        }, 0L, 20L);


    }

}
