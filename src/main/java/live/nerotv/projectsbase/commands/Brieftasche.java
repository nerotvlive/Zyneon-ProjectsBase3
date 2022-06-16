
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Brieftasche
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Brieftasche")) {
            if (!(s instanceof Player)) {
                if (args.length == 0) {
                    API.dispatchConsoleCommand("bal");
                } else {
                    Object m = "";
                    for (int i = 0; i < args.length; ++i) {
                        m = (String)m + args[i] + " ";
                    }
                    API.dispatchConsoleCommand("bal " + (String)m);
                }
            } else {
                Player p = (Player)s;
                PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                if (args.length == 0) {
                    p.performCommand("bal");
                } else {
                    Object m = "";
                    for (int i = 0; i < args.length; ++i) {
                        m = (String)m + args[i] + " ";
                    }
                    p.performCommand("bal " + (String)m);
                }
            }
        }
        return false;
    }
}

