
package live.nerotv.projectsbase.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rain
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Rain")) {
            if (!(s instanceof Player)) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)"weather rain");
            } else {
                Player p = (Player)s;
                p.performCommand("weather rain");
            }
        }
        return false;
    }
}

