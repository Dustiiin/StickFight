package de.dustin.timer;

import de.dustin.Main;
import de.dustin.utils.ALLManager;
import de.dustin.utils.LocationManager;
import de.dustin.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EndingTimer {

    public static int task;
    public static int time = 11;

    public static void start() {


        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {


            @Override
            public void run() {


                if (time == 0) {
                    Bukkit.getScheduler().cancelTask(task);


                    for (Player all : Bukkit.getOnlinePlayers())  {
                            ALLManager.sendToServer(all, "Lobby-1");
                    }
                    Bukkit.shutdown();


                }

                if (time == 10) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.teleport(LocationManager.getLocation("spawn"));
                    }
                }

                if (time == 5 || time == 4 || time == 3 || time == 2 || time ==1) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        ALLManager.sendActionbar(all, ChatColor.RED + "Der Server stoppt in " + time);
                        Messages.sendMessage(all, ChatColor.RED + "Der Server stoppt in " + time);
                }
                }


                time--;


            }
        }, 0L, 20L);

    }
}
