package de.dustin.listener;

import de.dustin.Main;
import de.dustin.timer.EndingTimer;
import de.dustin.timer.GameTimer;
import de.dustin.timer.LobbyTimer;
import de.dustin.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Main.variablen.players.remove(e.getPlayer());

        if (GameState.getState() == GameState.WAITING) {
            Main.one.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

            Main.one = null;
        }


        if (GameState.getState() == GameState.LOBBY) {
            Bukkit.getScheduler().cancelTask(LobbyTimer.task);
            GameState.setState(GameState.WAITING);
            LobbyTimer.time = 21;

            Main.one.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
            Main.two.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

            Main.one = null;
            Main.two = null;



            for (Player all : Main.variablen.players) {
                ALLManager.sendActionbar(all, "Es wird auf weitere Spieler gewartet!");
                Messages.sendMessage(all, "Es wird auf weitere Spieler gewartet");
                Main.one = all;
                all.sendMessage(Main.one + "");
                all.sendMessage(Main.two + "");

            }
        }

        if (GameState.getState() == GameState.INGAME) {
            Bukkit.getScheduler().cancelTask(GameTimer.task);
            GameState.setState(GameState.ENDING);
            EndingTimer.start();
            for (Player all : Main.variablen.players) {
                all.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                all.teleport(LocationManager.getLocation("ending"));
            }

        }


    }

}
