
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Broadcast")) {
            if (!(s instanceof Player)) {
                if (args.length == 0) {
                    s.sendMessage("\u00a74Fehler: \u00a7c/broadcast [Nachricht]");
                } else {
                    Object m = "";
                    for (int i = 0; i < args.length; ++i) {
                        m = (String)m + args[i] + " ";
                    }
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        PlayerAPI.playNewSound(all, NewSound.ENTITY_CHICKEN_EGG);
                        all.sendMessage("\u00a7cWichtig \u00a78| \u00a7f" + ((String)m).replace("&", "\u00a7"));
                    }
                    Bukkit.getConsoleSender().sendMessage("\u00a7cWichtig \u00a78| \u00a7f" + ((String)m).replace("&", "\u00a7"));
                }
            } else {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        p.sendMessage("\u00a74Fehler: \u00a7c/broadcast [Nachricht]");
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    } else {
                        Object m = "";
                        for (int i = 0; i < args.length; ++i) {
                            m = (String)m + args[i] + " ";
                        }
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            PlayerAPI.playNewSound(all, NewSound.ENTITY_CHICKEN_EGG);
                            all.sendMessage("\u00a7cWichtig \u00a78| \u00a7f" + ((String)m).replace("&", "\u00a7"));
                        }
                        Bukkit.getConsoleSender().sendMessage("\u00a7cWichtig \u00a78| \u00a7f" + ((String)m).replace("&", "\u00a7"));
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            }
        }
        return false;
    }
}

