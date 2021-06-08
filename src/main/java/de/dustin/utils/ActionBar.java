package de.dustin.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void sendActionBar(Player player, String message) {
        String s = ChatColor.translateAlternateColorCodes('&', message);
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet) bar);
    }

    public static void sendAllActionbar(String message) {
        if (Bukkit.getOnlinePlayers().size() == 0)
            return;
        String s = ChatColor.translateAlternateColorCodes('&', message);
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        for (Player player : Bukkit.getOnlinePlayers())
            (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)bar);
    }


}
