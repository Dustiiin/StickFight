package de.dustin.timer;

import de.dustin.Main;
import de.dustin.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class LobbyTimer {

    public static int task;
    public static int time = 21;
    public static void start() {
        GameState.setState(GameState.LOBBY);

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {


            @Override
            public void run() {



                if (time == 0) {
                    Bukkit.getScheduler().cancelTask(task);
                    GameState.setState(GameState.INGAME);
                    ActionBar.sendAllActionbar("Das Spiel startet jetzt!");

                    GameTimer.start();

                    if (Main.one != null) {
                        Main.one.getInventory().setItem(0, ALLManager.createEnchantment(Material.STICK, 1, (short) 0, Main.variablen.prefix, Enchantment.KNOCKBACK, 1));
                        Main.one.getInventory().setItem(1, ALLManager.createItem(Material.SANDSTONE, 5, (short) 0, ChatColor.AQUA + "SandStone"));
                        Main.one.setPlayerListName(ChatColor.BLUE + "BLAU : " + Main.one.getDisplayName());
                        Main.one.setGameMode(GameMode.SURVIVAL);
                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Main.one.teleport(LocationManager.getLocation("Pos1"));
                            }
                        },40L);


                    }
                    if (Main.two != null){
                        Main.two.getInventory().setItem(0, ALLManager.createEnchantment(Material.STICK, 1, (short) 0, Main.variablen.prefix, Enchantment.KNOCKBACK, 1));
                        Main.two.getInventory().setItem(1, ALLManager.createItem(Material.SANDSTONE, 5, (short) 0, ChatColor.AQUA + "SandStone"));
                        Main.two.setPlayerListName(ChatColor.RED + "ROT : " + Main.two.getDisplayName());
                        Main.two.setGameMode(GameMode.SURVIVAL);

                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Main.two.teleport(LocationManager.getLocation("Pos2"));
                            }
                        }, 40L);
                    }


                    return;

                }



                if (time == 20) {
                    ActionBar.sendAllActionbar("Time left: " + String.valueOf(time));
                    Messages.sendToAllMessage("Time left: " + String.valueOf(time));



                }



                if (time == 10) {
                    ActionBar.sendAllActionbar("Time left: " + String.valueOf(time));
                    Messages.sendToAllMessage("Time left: " + String.valueOf(time));
                }



                if (time == 5) {
                    ActionBar.sendAllActionbar("Time left: " + String.valueOf(time));
                    Messages.sendToAllMessage("Time left: " + String.valueOf(time));
                }



                if (time == 4) {
                    ActionBar.sendAllActionbar("Time left: " + String.valueOf(time));
                    Messages.sendToAllMessage("Time left: " + String.valueOf(time));
                }



                if (time == 3) {
                    ActionBar.sendAllActionbar("Time left: " + String.valueOf(time));
                    Messages.sendToAllMessage("Time left: " + String.valueOf(time));
                }



                if (time == 2) {
                    ActionBar.sendAllActionbar("Time left: " + String.valueOf(time));
                    Messages.sendToAllMessage("Time left: " + String.valueOf(time));
                }



                if (time == 1) {
                    ActionBar.sendAllActionbar("Time left: " + String.valueOf(time));
                    Messages.sendToAllMessage("Time left: " + String.valueOf(time));
                }



                time--;



            }
        }, 0L, 20L);


    }

}
