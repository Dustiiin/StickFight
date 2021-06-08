package de.dustin.commands;

import de.dustin.Main;
import de.dustin.utils.LocationManager;
import de.dustin.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setlocation_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("stickrush.location")) {
                if (args.length == 0) {
                    Messages.sendMessage(player, "/setlocation [spawn | Pos1 | Pos2 | ending]");
                }
                if (args.length == 1) {
                    if (args[0].equals("spawn")) {
                        LocationManager.setLocation(player, "spawn");
                    }
                    if (args[0].equals("Pos1")) {
                        LocationManager.setLocation(player, "Pos1");
                    }
                    if (args[0].equals("Pos2")) {
                        LocationManager.setLocation(player, "Pos2");
                    }
                    if (args[0].equals("ending")) {
                        LocationManager.setLocation(player, "ending");
                    }
                    if (args[0].equals("test")) {
                        Main.variablen.killsone = 48;
                    }

                }
                if (args.length == 2) {
                    if (args[0].equals("test")) {
                        Main.variablen.killsone = Integer.parseInt(args[1]);
                    }
                }



            } else {
                player.sendMessage("Dazu hast du keine Rechte haha");
            }




        } else {
            sender.sendMessage("Du musst eine Spieler sein!");
        }

        return false;
    }
}
