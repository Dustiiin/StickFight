package de.dustin.listener;

import de.dustin.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDmg(EntityDamageEvent e) {

        if (GameState.getState() == GameState.WAITING || GameState.getState() == GameState.LOBBY || GameState.getState() == GameState.ENDING) {
            e.setCancelled(true);
        }

        if (GameState.getState() == GameState.INGAME) {
            e.setDamage(0.0D);
        }

    }

}
