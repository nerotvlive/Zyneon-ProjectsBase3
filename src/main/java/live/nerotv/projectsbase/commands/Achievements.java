
package live.nerotv.projectsbase.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Achievements
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Achievements")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                p.performCommand("advancedachievements:aa list");
            } else {
                Bukkit.dispatchCommand((CommandSender)s, (String)"advancedachievements:aa list");
            }
        }
        return false;
    }
}

