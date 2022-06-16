
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.ConfigAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChangeMap
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ChangeMap")) {
            if (ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Map.Enable")) {
                Bukkit.dispatchCommand((CommandSender)s, (String)"warp map 1");
            } else {
                Bukkit.dispatchCommand((CommandSender)s, (String)"neino");
            }
        }
        return false;
    }
}

