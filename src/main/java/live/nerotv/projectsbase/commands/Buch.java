
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Buch
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Buch")) {
            if (!(s instanceof Player)) {
                s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
            } else {
                Player p = (Player)s;
                if (ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Buch")) {
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("minecraft:give " + p.getName() + " writable_book"));
                    API.sendMessage((CommandSender)p, "Du hast ein beschreibbares Buch erhalten.");
                } else {
                    p.performCommand("dieserbefehlwirdniemalsexistieren");
                }
            }
        }
        return false;
    }
}

