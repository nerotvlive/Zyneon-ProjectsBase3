
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Team
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Team")) {
            if (s.hasPermission("zyneon.team")) {
                if (args.length == 0) {
                    Bukkit.dispatchCommand((CommandSender)s, (String)"help team 1");
                } else if (Main.PM.getPlugin("LuckPerms") != null) {
                    if (args[0].equalsIgnoreCase("on")) {
                        if (s instanceof Player) {
                            PlayerAPI.addBypassPerms((Player)s);
                            API.sendMessage(s, "Du hast die Sicherungsumgehungen \u00a7aaktiviert\u00a77!");
                        } else {
                            Bukkit.dispatchCommand((CommandSender)s, (String)("help team " + args[0]));
                        }
                    } else if (args[0].equalsIgnoreCase("off")) {
                        if (s instanceof Player) {
                            PlayerAPI.removeBypassPerms((Player)s);
                            API.sendMessage(s, "Du hast die Sicherungsumgehungen \u00a7cdeaktiviert\u00a77!");
                        } else {
                            Bukkit.dispatchCommand((CommandSender)s, (String)("help team " + args[0]));
                        }
                    } else {
                        Bukkit.dispatchCommand((CommandSender)s, (String)("help team " + args[0]));
                    }
                } else {
                    API.sendErrorMessage(s, "\u00a7cDieses Feature ist zurzeit deaktiviert!");
                }
            } else {
                Bukkit.dispatchCommand((CommandSender)s, (String)"help");
            }
        }
        return false;
    }
}

