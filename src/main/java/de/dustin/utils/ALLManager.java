package de.dustin.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.dustin.Main;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import sun.security.jca.GetInstance;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ALLManager {

    public static void sendTitle1(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        PlayerConnection connection = (((CraftPlayer)player).getHandle()).playerConnection;
        PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
        connection.sendPacket((Packet)packetPlayOutTimes);
        if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket((Packet)packetPlayOutSubTitle);
        }
        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('&', title);
            IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket((Packet)packetPlayOutTitle);
        }
    }

    public static void sendTitle(Player p, int fadeIn, int stay, int fadeOut, String subtitle) {
        sendTitle1(p, Integer.valueOf(fadeIn), Integer.valueOf(stay), Integer.valueOf(fadeOut), subtitle, null);
    }

    public static void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)bar);
    }

    public static ItemStack createItem(Material material, int anzahl, int subid, String displayname) {
        short newsubid = (short)subid;
        ItemStack i = new ItemStack(material, anzahl, newsubid);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack createItem(int id, int anzahl, String displayname) {
        ItemStack i = new ItemStack(id, anzahl);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack createDurability(Material material, int anzahl, int subid, String displayname, int durabilty) {
        short neuesubid = (short)subid;
        ItemStack i = new ItemStack(material, anzahl, neuesubid);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setDurability((short)durabilty);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack createHeadItem(String owner, String displayname) {
        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta sm = (SkullMeta)i.getItemMeta();
        sm.setOwner(owner);
        sm.setDisplayName(displayname);
        i.setItemMeta((ItemMeta)sm);
        return i;
    }

    public static ItemStack createEnchantment(Material material, int anzahl, int subid, String displayname, Enchantment enchantment, int enchantmentID) {
        short neuesubid = (short)subid;
        ItemStack i = new ItemStack(material, anzahl, neuesubid);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        m.addEnchant(enchantment, enchantmentID, true);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack createLeatherBoots(String displayname, Color color, Enchantment enchantment, int enchantmentID) {
        ItemStack i = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.setColor(color);
        im.addEnchant(enchantment, enchantmentID, true);
        i.setItemMeta((ItemMeta)im);
        return i;
    }

    public static ItemStack createNormalLeatherBoots(String displayname, Color color) {
        ItemStack i = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.setColor(color);
        i.setItemMeta((ItemMeta)im);
        return i;
    }

    public static ItemStack createGlowLeatherBoots(String displayname, Color color) {
        ItemStack i = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.setColor(color);
        im.addEnchant(Enchantment.DURABILITY, 0, true);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        i.setItemMeta((ItemMeta)im);
        return i;
    }

    public static ItemStack createItem(Material mat, int subid, String DisplayName) {
        ItemStack item = new ItemStack(mat, 1, (short)subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DisplayName);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createLeatherHelmet(String displayname, Color color, Enchantment enchantment, int enchantmentID) {
        ItemStack i = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.addEnchant(enchantment, enchantmentID, true);
        im.setColor(color);
        i.setItemMeta((ItemMeta)im);
        return i;
    }

    public static ItemStack createLeatherChest(String displayname, Color color, Enchantment enchantment, int enchantmentID) {
        ItemStack i = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta lam = (LeatherArmorMeta)i.getItemMeta();
        lam.setDisplayName(displayname);
        lam.addEnchant(enchantment, enchantmentID, true);
        lam.setColor(color);
        i.setItemMeta((ItemMeta)lam);
        return i;
    }

    public static ItemStack createLeatherleggings(String displayname, Color color, Enchantment enchantment, int enchantmentID) {
        ItemStack i = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta lam = (LeatherArmorMeta)i.getItemMeta();
        lam.setDisplayName(displayname);
        lam.addEnchant(enchantment, enchantmentID, true);
        lam.setColor(color);
        i.setItemMeta((ItemMeta)lam);
        return i;
    }

    public static ItemStack addLore(String Display, Material mat, String lores, int Anzahl) {
        ItemStack is = new ItemStack(mat, Anzahl);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Display);
        List<String> lore = new ArrayList<>();
        lore.add(lores);
        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack addGlow(String Display, int id, String lores, int Anzahl) {
        ItemStack is = new ItemStack(id, Anzahl);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Display);
        List<String> lore = new ArrayList<>();
        lore.add(lores);
        im.setLore(lore);
        im.addEnchant(Enchantment.DURABILITY, 0, true);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack Skull(String Display, Material m, String lores, int Anzahl, short Shorts, String Owner) {
        ItemStack is = new ItemStack(m, Anzahl, (short)3);
        SkullMeta im = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        im.setOwner(Owner);
        im.setDisplayName(Display);
        List<String> lore = new ArrayList<>();
        lore.add(lores);
        im.setLore(lore);
        is.setItemMeta((ItemMeta)im);
        return is;
    }

    public static ItemStack coloredArmor(String Display, Material m, String lores, int Anzahl, short Shorts, int RGB1, int RGB2, int RGB3) {
        ItemStack istack52 = new ItemStack(m, Anzahl, Shorts);
        LeatherArmorMeta istackMeta52 = (LeatherArmorMeta)istack52.getItemMeta();
        istackMeta52.setColor(Color.fromRGB(RGB1, RGB2, RGB3));
        istackMeta52.setDisplayName(Display);
        List<String> lore = new ArrayList<>();
        lore.add(lores);
        istackMeta52.setLore(lore);
        istack52.setItemMeta((ItemMeta)istackMeta52);
        return istack52;
    }

    public static ItemStack addGlowMitMehrerenLores(String Display, Material material, String[] lores, int Anzahl) {
        ItemStack is = new ItemStack(material, Anzahl);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Display);
        List<String> lore = new ArrayList<>();
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = lores).length, b = 0; b < i; ) {
            String Text = arrayOfString[b];
            lore.add(Text);
            b++;
        }
        im.setLore(lore);
        im.addEnchant(Enchantment.DURABILITY, 0, true);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        is.setItemMeta(im);
        return is;
    }



    public static ItemStack createItemMitMehrerenLores(Material material, int anzahl, int subid, String displayname, String[] lores) {
        short newsubid = (short)subid;
        ItemStack is = new ItemStack(material, anzahl, newsubid);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayname);
        List<String> lore = new ArrayList<>();
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = lores).length, b = 0; b < i; ) {
            String Text = arrayOfString[b];
            lore.add(Text);
            b++;
        }
        im.setLore(lore);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        is.setItemMeta(im);
        return is;
    }

    @Deprecated
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle1(player, fadeIn, stay, fadeOut, message, null);
    }

    @Deprecated
    public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle1(player, fadeIn, stay, fadeOut, null, message);
    }

    @Deprecated
    public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        sendTitle1(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        PlayerConnection connection = (((CraftPlayer)player).getHandle()).playerConnection;
        PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
        connection.sendPacket((Packet)packetPlayOutTimes);
        if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket((Packet)packetPlayOutSubTitle);
        }
        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('&', title);
            IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket((Packet)packetPlayOutTitle);
        }
    }

    public static void sendTabTitle(Player player, String header, String footer) {
        if (header == null)
            header = "";
        header = ChatColor.translateAlternateColorCodes('&', header);
        if (footer == null)
            footer = "";
        footer = ChatColor.translateAlternateColorCodes('&', footer);
        header = header.replaceAll("%player%", player.getDisplayName());
        footer = footer.replaceAll("%player%", player.getDisplayName());
        PlayerConnection connection = (((CraftPlayer)player).getHandle()).playerConnection;
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
        try {
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            connection.sendPacket((Packet)headerPacket);
        }
        connection.sendPacket((Packet)headerPacket);
    }

    public static void sendToServer(Player player, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(target);
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
        Messages.sendMessage(player, "Du wirst zur Lobby connectet!");
    }

}
