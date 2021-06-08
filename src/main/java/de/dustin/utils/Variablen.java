package de.dustin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Variablen {

    public String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "StickFight" + ChatColor.GRAY + "] ";

    public ArrayList<Player> players = new ArrayList<>();

    public int killsone = 0;
    public int killstwo = 0;


    public void sendJoinmessage(Player player) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(prefix + player.getDisplayName() + ChatColor.AQUA + " hat das Spiel betreten!");
        }
    }

    public int getKillsone() {
        return killsone;
    }

    public int getKillstwo() {
        return killstwo;
    }
}
