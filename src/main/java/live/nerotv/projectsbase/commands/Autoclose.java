
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Autoclose
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Autoclose")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (API.PM.getPlugin("LWC") != null) {
                    if (args.length == 0) {
                        p.performCommand("lwc flag autoclose on");
                    } else if (args[0].equalsIgnoreCase("off")) {
                        p.performCommand("lwc flag autoclose off");
                    } else if (args[0].equalsIgnoreCase("on")) {
                        p.performCommand("lwc flag autoclose on");
                    } else {
                        API.sendErrorMessage((CommandSender)p, "\u00a74Fehler:\u00a7c /autoclose \u00a77[on|off]");
                    }
                } else {
                    p.performCommand("/neino");
                }
            } else {
                s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
            }
        }
        return false;
    }
}

