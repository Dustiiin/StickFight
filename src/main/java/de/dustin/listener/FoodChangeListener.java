package de.dustin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeListener implements Listener {

    @EventHandler
    public void onChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

}
