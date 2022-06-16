
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class INF
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (s instanceof Player) {
            PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
        }
        s.sendMessage("\u00a74Fehler: \u00a7c/inf [Spieler] \u00a77[true/false]");
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("INF")) {
            if (s.hasPermission("zyneon.team")) {
                if (args.length == 0) {
                    this.sendSyntax(s);
                } else if (args.length == 1) {
                    if (Bukkit.getPlayer((String)args[0]) != null) {
                        Player t = Bukkit.getPlayer((String)args[0]);
                        if (PlayerAPI.hasINF(t)) {
                            API.sendMessage(s, "Der Spieler \u00a7e" + t.getName() + " \u00a77hat eine Chathervorhebung!");
                        } else {
                            API.sendMessage(s, "Der Spieler \u00a7e" + t.getName() + " \u00a77hat keine Chathervorhebung!");
                        }
                    } else if (PlayerAPI.isRegistered(args[0])) {
                        if (PlayerAPI.hasINF(PlayerAPI.registeredSID(args[0]))) {
                            API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat eine Chathervorhebung!");
                        } else {
                            API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat keine Chathervorhebung!");
                        }
                    } else if (PlayerAPI.hasINF(args[0])) {
                        API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat eine Chathervorhebung!");
                    } else {
                        API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat keine Chathervorhebung!");
                    }
                } else if (Bukkit.getPlayer((String)args[0]) != null) {
                    Player t = Bukkit.getPlayer((String)args[0]);
                    if (args[1].equalsIgnoreCase("true")) {
                        PlayerAPI.setINF(t, true);
                        API.sendMessage(s, "Der Spieler \u00a7e" + t.getName() + " \u00a77hat nun eine Chathervorhebung!");
                    } else if (args[1].equalsIgnoreCase("false")) {
                        PlayerAPI.setINF(t, false);
                        API.sendMessage(s, "Der Spieler \u00a7e" + t.getName() + " \u00a77hat nun keine Chathervorhebung mehr!");
                    } else {
                        this.sendSyntax(s);
                    }
                } else if (PlayerAPI.isRegistered(args[0])) {
                    String t = PlayerAPI.registeredSID(args[0]);
                    if (args[1].equalsIgnoreCase("true")) {
                        PlayerAPI.setINF(t, true);
                        API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat nun eine Chathervorhebung!");
                    } else if (args[1].equalsIgnoreCase("false")) {
                        PlayerAPI.setINF(t, false);
                        API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat nun keine Chathervorhebung mehr!");
                    } else {
                        this.sendSyntax(s);
                    }
                } else if (args[1].equalsIgnoreCase("true")) {
                    PlayerAPI.setINF(args[0], true);
                    API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat nun eine Chathervorhebung!");
                } else if (args[1].equalsIgnoreCase("false")) {
                    PlayerAPI.setINF(args[0], false);
                    API.sendMessage(s, "Der Spieler \u00a7e" + args[0] + " \u00a77hat nun keine Chathervorhebung mehr!");
                } else {
                    this.sendSyntax(s);
                }
            } else {
                API.sendErrorMessage(s, API.noPerms);
            }
        }
        return false;
    }
}

