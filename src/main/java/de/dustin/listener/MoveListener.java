package de.dustin.listener;

import de.dustin.Main;
import de.dustin.MySQL.Stats;
import de.dustin.timer.EndingTimer;
import de.dustin.timer.GameTimer;
import de.dustin.utils.GameState;
import de.dustin.utils.ALLManager;
import de.dustin.utils.LocationManager;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (GameState.getState() == GameState.INGAME) {
            Player playerone = Main.one;
            Player playertwo = Main.two;


            if (e.getPlayer() == playerone) {

                if (playerone.getLocation().getY() < 4) {


                if (playerone.getLocation().distance(LocationManager.getLocation("Pos1")) > playerone.getLocation().distance(LocationManager.getLocation("Pos2"))) {
                    playerone.teleport(LocationManager.getLocation("Pos1"));
                } else {
                    playerone.teleport(LocationManager.getLocation("Pos2"));
                }


                Main.variablen.killstwo = Main.variablen.getKillstwo() + 1;
                Stats.addDeaths(playerone.getUniqueId().toString(), 1);
                if (Main.variablen.getKillstwo() == 10) {
                    playertwo.addPotionEffect(PotionEffectType.SPEED.createEffect(99999, 1));
                }
                if (Main.variablen.getKillstwo() == 20) {
                    playertwo.addPotionEffect(PotionEffectType.JUMP.createEffect(99999, 1));
                }
                if (Main.variablen.getKillstwo() == 30) {
                    playertwo.getInventory().getItem(1).setAmount(15);
                }
                if (Main.variablen.getKillstwo() == 40) {
                    playertwo.getInventory().setItem(2, ALLManager.createItem(Material.BOW, 1, (short) 0, ChatColor.GOLD + "BOW"));
                    playertwo.getInventory().setItem(9, ALLManager.createItem(Material.ARROW, 5, (short) 0, ChatColor.AQUA + "Pfeile"));
                }
                if (Main.variablen.getKillstwo() == 49) {
                    playertwo.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(99999, 1));
                }

                if (Main.variablen.getKillstwo() == 50) {
                    Bukkit.getScheduler().cancelTask(GameTimer.task);
                    EndingTimer.start();
                    for (final PotionEffect potion : playerone.getActivePotionEffects()) {
                        playerone.removePotionEffect(potion.getType());
                    }
                    for (final PotionEffect potion : playertwo.getActivePotionEffects()) {
                        playertwo.removePotionEffect(potion.getType());
                    }
                }



                    playerone.getInventory().clear();

                    for (PotionEffect potion : playerone.getActivePotionEffects()) {
                        playerone.removePotionEffect(potion.getType());
                    }

                    Main.one.getInventory().setItem(0, ALLManager.createEnchantment(Material.STICK, 1, (short) 0, Main.variablen.prefix, Enchantment.KNOCKBACK, 1));
                    Main.one.getInventory().setItem(1, ALLManager.createItem(Material.SANDSTONE, 5, (short) 0, ChatColor.AQUA + "SandStone"));




                }
            }
            if (e.getPlayer() == playertwo) {

                if (playertwo.getLocation().getY() < 4) {


                if (playertwo.getLocation().distance(LocationManager.getLocation("Pos1")) > playertwo.getLocation().distance(LocationManager.getLocation("Pos2"))) {
                    playertwo.teleport(LocationManager.getLocation("Pos1"));
                } else {
                    playertwo.teleport(LocationManager.getLocation("Pos2"));
                }
                Main.variablen.killsone = Main.variablen.getKillsone() + 1;
                Stats.addDeaths(playertwo.getUniqueId().toString(), 1);


                    if (Main.variablen.getKillsone() == 10) {
                        playerone.addPotionEffect(PotionEffectType.SPEED.createEffect(99999, (short) 0.5));

                    }
                    if (Main.variablen.getKillsone() == 20) {
                        playerone.addPotionEffect(PotionEffectType.JUMP.createEffect(99999, 1));

                    }
                    if (Main.variablen.getKillsone() == 30) {
                        playerone.getInventory().getItem(1).setAmount(15);

                    }
                    if (Main.variablen.getKillsone() == 40) {
                        playerone.getInventory().setItem(2, ALLManager.createItem(Material.BOW, 1, (short) 0, ChatColor.GOLD + "BOW"));
                        playerone.getInventory().setItem(9, ALLManager.createItem(Material.ARROW, 5, (short) 0, ChatColor.AQUA + "Pfeile"));

                    }
                    if (Main.variablen.getKillsone() == 49) {
                        playerone.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(99999, 1));

                    }

                    if (Main.variablen.getKillsone() == 50) {
                        Bukkit.getScheduler().cancelTask(GameTimer.task);
                        EndingTimer.start();
                        for (final PotionEffect potion : playerone.getActivePotionEffects()) {
                            playerone.removePotionEffect(potion.getType());
                        }
                        for (final PotionEffect potion : playertwo.getActivePotionEffects()) {
                            playertwo.removePotionEffect(potion.getType());
                        }
                    }





                    for (PotionEffect potion : playertwo.getActivePotionEffects()) {
                        playertwo.removePotionEffect(potion.getType());
                    }
                playertwo.getInventory().clear();

                Main.two.getInventory().setItem(0, ALLManager.createEnchantment(Material.STICK, 1, (short) 0, Main.variablen.prefix, Enchantment.KNOCKBACK, 1));
                Main.two.getInventory().setItem(1, ALLManager.createItem(Material.SANDSTONE, 5, (short) 0, ChatColor.AQUA + "SandStone"));


                }


            }
        }
    }

}
