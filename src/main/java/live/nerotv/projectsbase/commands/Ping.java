
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Ping")) {
            if (!(s instanceof Player)) {
                API.sendErrorMessage(s, "\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
            } else {
                Player p = (Player)s;
                if (p.getName().equalsIgnoreCase("ideallauch")) {
                    int Ping2 = PlayerAPI.getPing(p);
                    String ping = Ping2 < 10 ? "\u00a75" + Ping2 : (Ping2 < 20 ? "\u00a7d" + Ping2 : (Ping2 < 30 ? "\u00a7e" + Ping2 : (Ping2 < 40 ? "\u00a76" + Ping2 : (Ping2 < 100 ? "\u00a7c" + Ping2 : "\u00a74" + Ping2))));
                    API.sendMessage(s, "Du hast einen Ping von " + ping + "ms\u00a77!");
                } else {
                    int Ping3 = PlayerAPI.getPing(p);
                    String ping = Ping3 < 10 ? "\u00a7a" + Ping3 : (Ping3 < 20 ? "\u00a72" + Ping3 : (Ping3 < 30 ? "\u00a7e" + Ping3 : (Ping3 < 40 ? "\u00a76" + Ping3 : (Ping3 < 100 ? "\u00a7c" + Ping3 : "\u00a74" + Ping3))));
                    API.sendMessage(s, "Du hast einen Ping von " + ping + "ms\u00a77!");
                }
            }
        }
        return false;
    }
}

