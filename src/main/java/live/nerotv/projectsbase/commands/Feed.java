
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Feed")) {
            if (!(s instanceof Player)) {
                if (args.length == 0) {
                    s.sendMessage("\u00a74Fehler: \u00a7c/feed [Spieler]");
                } else if (Bukkit.getPlayer((String)args[0]) != null) {
                    Player p = Bukkit.getPlayer((String)args[0]);
                    p.setFoodLevel(20);
                    API.sendMessage((CommandSender)p, "Du wurdest \u00a7agef\u00fcttert\u00a77!");
                    s.sendMessage(API.Prefix + "Du hast den Spieler \u00a7e" + p.getName() + "\u00a7a gef\u00fcttert\u00a77!");
                } else {
                    s.sendMessage(API.noPlayer);
                }
            } else {
                Player p = (Player)s;
                if (s.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        p.setFoodLevel(20);
                        API.sendMessage((CommandSender)p, "Du hast dich \u00a7agef\u00fcttert\u00a77!");
                    } else if (Bukkit.getPlayer((String)args[0]) != null) {
                        Player t = Bukkit.getPlayer((String)args[0]);
                        t.setFoodLevel(20);
                        API.sendMessage((CommandSender)t, "Du wurdest \u00a7agef\u00fcttert\u00a77!");
                        API.sendMessage((CommandSender)p, "Du hast den Spieler \u00a7e" + t.getName() + "\u00a7a gef\u00fcttert\u00a77!");
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPlayer);
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            }
        }
        return false;
    }
}

