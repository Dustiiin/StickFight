package de.dustin.utils;

import de.dustin.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Messages {

    public static void sendMessage(Player player, String message) {
        player.sendMessage(Main.variablen.prefix + message);
    }

    public void sendMessage2(Player player, String message) {
        player.sendMessage(message);
    }

    public static void sendToAllMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(Main.variablen.prefix + message);
    }

    public static void sendToAllWithOutPrefixMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(message);
    }

}
