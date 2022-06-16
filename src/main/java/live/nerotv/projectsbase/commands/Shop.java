
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Shop")) {
            if (ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Shop.Enable")) {
                if (!(s instanceof Player)) {
                    s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
                } else {
                    Player p = (Player)s;
                    InventoryManager.openInv001(p);
                }
            } else {
                Bukkit.dispatchCommand((CommandSender)s, (String)"neino");
            }
        }
        return false;
    }
}

