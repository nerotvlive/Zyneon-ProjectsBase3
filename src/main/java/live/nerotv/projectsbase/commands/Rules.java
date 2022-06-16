
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.manager.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rules
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rules")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (PlayerAPI.checkRulesLevel(p)) {
                    PlayerAPI.sendPlayerMessage(p, "\u00a77Regelwerk\u00a78: \u00a79\u00a7n" + ConfigAPI.CFG.getString("Core.Strings.Projektregeln"));
                } else {
                    InventoryManager.openConfirmRulesInventory(p);
                }
            }
        } else {
            API.sendMessage(s, "\u00a77Regelwerk\u00a78: \u00a79\u00a7n" + ConfigAPI.CFG.getString("Core.Strings.Projektregeln"));
        }
        return false;
    }
}

