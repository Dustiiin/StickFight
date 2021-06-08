package de.dustin.listener;

import de.dustin.Main;
import de.dustin.timer.LobbyTimer;
import de.dustin.utils.GameState;
import de.dustin.utils.LocationManager;
import de.dustin.utils.SideBar.Sidebar;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(null);



        if (GameState.getState().equals(GameState.WAITING)) {
            if(Main.one == null) {
                Main.one = player;

                Main.one.teleport(LocationManager.getLocation("spawn"));
                Main.one.setGameMode(GameMode.ADVENTURE);
                Main.one.getInventory().clear();
                Main.one.setHealth(20);
                Main.one.setFoodLevel(20);

                for (PotionEffect potion : Main.one.getActivePotionEffects()) {
                    Main.one.removePotionEffect(potion.getType());
                }



            } else if(Main.two == null) {
                Main.two = player;

                Main.two.teleport(LocationManager.getLocation("spawn"));
                Main.two.setGameMode(GameMode.ADVENTURE);
                Main.two.getInventory().clear();
                Main.two.setHealth(20);
                Main.two.setFoodLevel(20);

                for (PotionEffect potion : Main.two.getActivePotionEffects()) {
                    Main.two.removePotionEffect(potion.getType());
                }

            }

            Main.variablen.sendJoinmessage(player);



            Main.variablen.players.add(player);

            if (Main.variablen.players.size() == 2) {
                LobbyTimer.start();
                new Sidebar(Main.one);
                new Sidebar(Main.two);

            }

        }
        if (GameState.getState().equals(GameState.INGAME)) {
            e.setJoinMessage(null);
            e.getPlayer().kickPlayer("Server bereits voll!");
        }
        if (GameState.getState().equals(GameState.ENDING)) {
            e.setJoinMessage(null);
            e.getPlayer().kickPlayer("Server wird gerade gestoppt");
        }





    }

}
