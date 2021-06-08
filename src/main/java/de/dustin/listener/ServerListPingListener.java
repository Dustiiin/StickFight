package de.dustin.listener;

import de.dustin.utils.GameState;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {

    @EventHandler
    public void on(ServerListPingEvent e) {
        if (GameState.getState().equals(GameState.WAITING)) {
            e.setMotd(ChatColor.GRAY + "WAITING");
            e.setMaxPlayers(2);
        }

        if (GameState.getState().equals(GameState.INGAME)) {
            e.setMotd(ChatColor.GREEN + "INGAME");
            e.setMaxPlayers(0);
        }

        if (GameState.getState().equals(GameState.ENDING)) {
            e.setMotd(ChatColor.RED + "RESTART");
            e.setMaxPlayers(0);
        }
    }

}
