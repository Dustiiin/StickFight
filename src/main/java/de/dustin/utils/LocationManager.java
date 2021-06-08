package de.dustin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationManager {
    public static File folder = new File("plugins/StickRush/");

    public static File file = new File("plugins/StickRush/loc.yml");

    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void saveFile() {
        try {
            cfg.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void setupFiles() {
        if (!folder.exists())
            folder.mkdir();
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        saveFile();
    }

    public static void setHeight(Player p, String name) {
        cfg.set("Locations." + name + ".Y", String.valueOf(p.getLocation().getBlockY()));
        saveFile();
    }

    public static void setLocation(Player p, String name) {
        double x = p.getLocation().getBlockX() + 0.5D;
        double y = p.getLocation().getBlockY();
        double z = p.getLocation().getBlockZ() + 0.5D;
        double yaw = Math.round(p.getLocation().getYaw() * 22.5D) / 22.5D;
        double pitch = Math.round(p.getLocation().getPitch() * 22.5D) / 22.5D;
        String worldName = p.getWorld().getName();
        cfg.set("Locations." + name + ".X", Double.valueOf(x));
        cfg.set("Locations." + name + ".Y", Double.valueOf(y));
        cfg.set("Locations." + name + ".Z", Double.valueOf(z));
        cfg.set("Locations." + name + ".Yaw", Double.valueOf(yaw));
        cfg.set("Locations." + name + ".Pitch", Double.valueOf(pitch));
        cfg.set("Locations." + name + ".worldName", worldName);
        saveFile();
    }

    public static int getHeight(String name) {
        if (cfg.get("Locations." + name + ".Y") != null)
            return cfg.getInt("Locations." + name + ".Y");
        return 0;
    }

    public static Location getLocation(String name) {
        if (cfg.get("Locations." + name + ".worldName") != null) {
            double x = cfg.getDouble("Locations." + name + ".X");
            double y = cfg.getInt("Locations." + name + ".Y");
            double z = cfg.getDouble("Locations." + name + ".Z");
            double yaw = cfg.getDouble("Locations." + name + ".Yaw");
            double pitch = cfg.getDouble("Locations." + name + ".Pitch");
            String worldName = cfg.getString("Locations." + name + ".worldName");
            return new Location(Bukkit.getWorld(worldName), x, y, z, (float)yaw, (float)pitch);
        }
        return null;
    }
}
