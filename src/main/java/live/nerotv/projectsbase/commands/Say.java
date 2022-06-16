
package live.nerotv.projectsbase.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Say
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Say")) {
            if (!(s instanceof Player)) {
                if (args.length == 0) {
                    s.sendMessage("\u00a74Fehler: \u00a7c/say [Nachricht]");
                } else {
                    Object m = "";
                    for (int i = 0; i < args.length; ++i) {
                        m = (String)m + args[i] + " ";
                    }
                    Bukkit.getServer().broadcastMessage("\u00a7cKonsole\u00a78: \u00a77" + ((String)m).replace("&", "\u00a7"));
                }
            } else {
                Player p = (Player)s;
                p.performCommand("neino");
            }
        }
        return false;
    }
}

