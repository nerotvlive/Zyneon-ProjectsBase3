
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gamemode")) {
            if (s.hasPermission("zyneon.team")) {
                if (!(s instanceof Player)) {
                    if (args.length < 2) {
                        s.sendMessage("Fehler: /gamemode [0-3] [Spieler]");
                    } else {
                        Player p = Bukkit.getPlayer((String)args[1]);
                        if (p == null) {
                            s.sendMessage(API.noPlayer);
                        } else {
                            API.changeGamemode(p, args[0]);
                            s.sendMessage("Der Spieler " + p.getName() + " spielt nun im " + API.getGamemode(p) + "!");
                        }
                    }
                } else if (args.length == 0) {
                    Player p = (Player)s;
                    InventoryManager.openInv_GameMode(p);
                    PlayerAPI.sendPlayerMessage(p, "\u00a74Fehler: \u00a7c/gamemode \u00a7c[Gamemode] \u00a77[Spieler]", NewSound.BLOCK_ANVIL_BREAK);
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else if (args[0].equalsIgnoreCase("1")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else if (args[0].equalsIgnoreCase("2")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else if (args[0].equalsIgnoreCase("3")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else if (args[0].equalsIgnoreCase("Survival")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else if (args[0].equalsIgnoreCase("creative")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else if (args[0].equalsIgnoreCase("adventure")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else if (args[0].equalsIgnoreCase("spectator")) {
                        Player p = (Player)s;
                        API.changeGamemode(p, args[0]);
                    } else {
                        Player p = (Player)s;
                        InventoryManager.openInv_GameMode(p);
                        PlayerAPI.sendPlayerMessage(p, "\u00a74Fehler: \u00a7c/gamemode \u00a7c[Gamemode] \u00a77[Spieler]");
                    }
                } else {
                    Player p = Bukkit.getPlayer((String)args[1]);
                    if (p == null) {
                        API.sendErrorMessage(s, API.noPlayer);
                    } else if (args[0].equalsIgnoreCase("0")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("1")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("2")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("SURVIVAL")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("CREATIVE")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("ADVENTURE")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("SPECTATOR")) {
                        API.changeGamemode(p, args[0]);
                        API.sendMessage(s, "\u00a77Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 in den \u00a7a" + API.getGamemode(p) + "\u00a77 gesetzt!");
                    } else {
                        API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/gamemode \u00a7c[Gamemode] \u00a77[Spieler]");
                    }
                }
            } else if (!(s instanceof Player)) {
                s.sendMessage("\u00a7cDazu musst du ein Spieler sin!");
            }
        }
        return false;
    }
}

