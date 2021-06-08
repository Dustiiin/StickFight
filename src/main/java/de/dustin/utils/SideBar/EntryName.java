package de.dustin.utils.SideBar;

import org.bukkit.ChatColor;

public enum EntryName {
    ENTRY_0(0, ChatColor.YELLOW.toString()),
    ENTRY_1(1, ChatColor.GREEN.toString()),
    ENTRY_2(2, ChatColor.BLUE.toString()),
    ENTRY_3(3, ChatColor.RED.toString()),
    ENTRY_4(4, ChatColor.GRAY.toString()),
    ENTRY_5(5, ChatColor.LIGHT_PURPLE.toString()),
    ENTRY_6(6, ChatColor.AQUA.toString()),
    ENTRY_7(7, ChatColor.UNDERLINE.toString()),
    ENTRY_8(8, ChatColor.BOLD.toString());

    private final int entry;
    private final String entryName;

    EntryName(int entry, String entryName) {
        this.entry = entry;
        this.entryName = entryName;
    }

    public int getEntry() {
        return entry;
    }

    public String getEntryName() {
        return entryName;
    }
}
