
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import live.nerotv.projectsbase.api.WorldAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class World
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (s instanceof Player) {
            API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/world \u00a77(load) [Welt]");
        } else {
            API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/world load [Welt]");
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("world")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        this.sendSyntax((CommandSender)p);
                    } else if (args.length == 1) {
                        if (Bukkit.getWorld((String)args[0]) != null) {
                            PlayerAPI.sendPlayerMessage(p, "\u00a77Teleportiere\u00a78...", NewSound.ENTITY_CHICKEN_EGG);
                            p.teleport(Bukkit.getWorld((String)args[0]).getSpawnLocation());
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDiese Welt existiert nicht oder ist nicht geladen. Versuche einen anderen Namen, oder mache \u00a74/world load " + args[0] + "\u00a7c!");
                        }
                    } else if (Bukkit.getWorld((String)args[0]) != null) {
                        p.performCommand("world " + args[0]);
                    } else if (args[0].equalsIgnoreCase("load")) {
                        if (Bukkit.getWorld((String)args[1]) == null) {
                            PlayerAPI.sendPlayerMessage(p, "\u00a77Erstelle Welt\u00a78,\u00a77 dies kann ein bisschen dauern\u00a78...", NewSound.ENTITY_CHICKEN_EGG);
                            WorldAPI.createWorld(args[1]);
                            p.teleport(Bukkit.getWorld((String)args[1]).getSpawnLocation());
                            PlayerAPI.sendPlayerMessage(p, "\u00a77Die Welt wurde geladen und du wurdest zu ihr teleportiert\u00a78!", NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDiese Welt ist bereits geladen. Mache \u00a74/world " + args[1] + "\u00a7c um zu ihr zu gelangen!");
                        }
                    } else {
                        this.sendSyntax((CommandSender)p);
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("load")) {
                    if (Bukkit.getWorld((String)args[1]) == null) {
                        ServerAPI.sendConsoleMessage("\u00a77Erstelle Welt\u00a78,\u00a77 dies kann ein bisschen dauern\u00a78...");
                        WorldAPI.createWorld(args[1]);
                        ServerAPI.sendConsoleMessage("\u00a77Welt geladen!");
                    } else {
                        API.sendErrorMessage(s, "\u00a7cDiese Welt ist bereits geladen!");
                    }
                } else {
                    this.sendSyntax(s);
                }
            } else {
                this.sendSyntax(s);
            }
        }
        return false;
    }
}

