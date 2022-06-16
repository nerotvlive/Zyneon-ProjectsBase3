
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sudo
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Sudo")) {
            if (!(s instanceof Player)) {
                if (args.length >= 2) {
                    Player p2 = Bukkit.getPlayer((String)args[0]);
                    Object c = "";
                    for (int i = 1; i < args.length; ++i) {
                        c = (String)c + args[i] + " ";
                    }
                    p2.performCommand((String)c);
                    s.sendMessage(API.Prefix + "Du hast \u00a7e" + p2.getName() + "\u00a77 dazu gezwungen \u00a7e/" + (String)c + "\u00a77auszuf\u00fchren.");
                } else {
                    s.sendMessage("\u00a74Fehler: \u00a7c/sudo [Spieler] [Aktion]");
                }
            } else {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.leading.sudo")) {
                    if (args.length >= 2) {
                        if (Bukkit.getPlayer((String)args[0]) != null) {
                            Player p2 = Bukkit.getPlayer((String)args[0]);
                            Object c = "";
                            for (int i = 1; i < args.length; ++i) {
                                c = (String)c + args[i] + " ";
                            }
                            p2.performCommand((String)c);
                            API.sendMessage((CommandSender)p, "Du hast \u00a7e" + p2.getName() + "\u00a77 dazu gezwungen \u00a7e/" + (String)c + "\u00a77auszuf\u00fchren.");
                        } else if (args[0].equalsIgnoreCase("console")) {
                            Object c = "";
                            for (int i = 1; i < args.length; ++i) {
                                c = (String)c + args[i] + " ";
                            }
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)c);
                            API.sendMessage((CommandSender)p, "Du hast \u00a7edie Konsole\u00a77 dazu gezwungen \u00a7e/" + (String)c + "\u00a77auszuf\u00fchren.");
                        } else {
                            API.sendErrorMessage((CommandSender)p, API.noPlayer);
                        }
                    } else {
                        API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/sudo [Spieler] [Aktion]");
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            }
        }
        return false;
    }
}

