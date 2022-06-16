
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Invsee
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Invsee")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        p.sendMessage("\u00a74Fehler: \u00a7c/invsee [Spieler]");
                    } else if (Bukkit.getPlayer((String)args[0]) != null) {
                        Player t = Bukkit.getPlayer((String)args[0]);
                        p.openInventory((Inventory)t.getInventory());
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPlayer);
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            } else {
                API.sendErrorMessage(s, API.needPlayer);
            }
        }
        return false;
    }
}

