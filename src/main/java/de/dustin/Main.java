package de.dustin;

import de.dustin.commands.Setlocation_CMD;
import de.dustin.listener.*;
import de.dustin.utils.GameState;
import de.dustin.utils.LocationManager;
import de.dustin.MySQL.MySQL;
import de.dustin.utils.Variablen;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.bind.ValidationEvent;
import java.util.ArrayList;

public final class Main extends JavaPlugin {

    public static Player one = null;

    public static Player two = null;

    public static Main instance;

    public static Variablen variablen;


    @Override
    public void onEnable() {
        instance = this;
        variablen = new Variablen();
        GameState.setState(GameState.WAITING);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        LocationManager.setupFiles();

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new MoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new DropListener(), this);
        Bukkit.getPluginManager().registerEvents(new FoodChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);



        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "difficulty peaceful");
        getCommand("setlocation").setExecutor(new Setlocation_CMD());

        MySQL.connect();
        MySQL.createStatsTable();

    }

    @Override
    public void onDisable() {

    }


    public static Main getInstance() {
        return instance;
    }


}
