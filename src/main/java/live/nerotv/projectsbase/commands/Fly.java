
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Fly")) {
            if (!(s instanceof Player)) {
                if (args.length == 0) {
                    s.sendMessage("\u00a74Fehler: \u00a7c/fly [Spieler]");
                } else if (Bukkit.getPlayer((String)args[0]) != null) {
                    Player p = Bukkit.getPlayer((String)args[0]);
                    if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        API.sendMessage((CommandSender)p, "Du kannst nun nicht mehr fliegen!");
                        s.sendMessage(API.Prefix + "\u00a7e" + p.getName() + "\u00a77 kann nun nicht mehr fliegen!");
                    } else {
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        API.sendMessage((CommandSender)p, "Du kannst nun fliegen!");
                        s.sendMessage(API.Prefix + "\u00a7e" + p.getName() + "\u00a77 kann nun fliegen!");
                    }
                } else {
                    s.sendMessage("\u00a7cDieser Spieler ist nicht online!");
                }
            } else {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        if (p.getAllowFlight()) {
                            p.setAllowFlight(false);
                            p.setFlying(false);
                            API.sendMessage((CommandSender)p, "Du kannst nun nicht mehr fliegen!");
                        } else {
                            p.setAllowFlight(true);
                            p.setFlying(true);
                            API.sendMessage((CommandSender)p, "Du kannst nun fliegen!");
                        }
                    } else if (Bukkit.getPlayer((String)args[0]) != null) {
                        Player p2 = Bukkit.getPlayer((String)args[0]);
                        if (p2.getAllowFlight()) {
                            p2.setAllowFlight(false);
                            p2.setFlying(false);
                            API.sendMessage((CommandSender)p2, "Du kannst nun nicht mehr fliegen!");
                            API.sendMessage((CommandSender)p, "\u00a7e" + p2.getName() + "\u00a77 kann nun nicht mehr fliegen!");
                        } else {
                            p2.setAllowFlight(true);
                            p2.setFlying(true);
                            API.sendMessage((CommandSender)p2, "Du kannst nun fliegen!");
                            API.sendMessage((CommandSender)p, "\u00a7e" + p2.getName() + "\u00a77 kann nun fliegen!");
                        }
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

