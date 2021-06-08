package de.dustin.listener;

import de.dustin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        final List<Location> locs = new ArrayList<>();
        final List<Material> materials = new ArrayList<>();
        final List<Byte> bytes = new ArrayList<>();
        Block b = e.getBlock();
        if (p.getItemInHand().getType() == Material.SANDSTONE) {
            locs.add(b.getLocation());
            materials.add(b.getType());
            bytes.add(Byte.valueOf(b.getData()));
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getInstance(), new Runnable() {
                public void run() {
                    for (Location loc : locs) {
                        for (Material material : materials) {
                            for (Byte by : bytes) {
                                Block block = loc.getBlock();
                                block.setType(Material.RED_SANDSTONE);
                                block.setData(by.byteValue());
                                block.getWorld().playEffect(block.getLocation(), Effect.FLAME, 3);
                                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), new Runnable() {
                                    public void run() {
                                        for (Location loc : locs) {
                                            for (Material material : materials) {
                                                for (Byte by : bytes) {
                                                    Block block = loc.getBlock();
                                                    block.setType(Material.AIR);
                                                    block.setData(by.byteValue());
                                                    block.getWorld().playEffect(block.getLocation(), Effect.FLAME, 3);
                                                }
                                            }
                                        }
                                    }
                                },60L);
                            }
                        }
                    }
                }
            },40L);
        }
    }

}
