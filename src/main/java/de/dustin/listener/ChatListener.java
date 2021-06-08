package de.dustin.listener;

import de.dustin.Main;
import de.dustin.utils.GameState;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (GameState.getState() == GameState.INGAME) {

            if (e.getPlayer().equals(Main.one)) {
                e.setFormat(ChatColor.BLUE + e.getPlayer().getDisplayName() + ChatColor.GRAY + " : " + e.getMessage());
            }
            if (e.getPlayer().equals(Main.two)) {
                e.setFormat(ChatColor.RED + e.getPlayer().getDisplayName() + ChatColor.GRAY + " : " + e.getMessage());
            }
            }


    }

}
