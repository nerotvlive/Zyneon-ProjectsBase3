
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clearchat
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Clearchat")) {
            if (!(s instanceof Player)) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("zyneon.team")) {
                        PlayerAPI.sendPlayerMessage(all, "\u00a77Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                        continue;
                    }
                    API.clearPlayerChat(all);
                }
                Bukkit.getServer().getConsoleSender().sendMessage("\u00a77Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
            } else {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("zyneon.team")) {
                            PlayerAPI.sendPlayerMessage(all, "\u00a77Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                            continue;
                        }
                        API.clearPlayerChat(all);
                    }
                    Bukkit.getServer().getConsoleSender().sendMessage("\u00a77Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            }
        }
        return false;
    }
}

