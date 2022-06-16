
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Enderchest
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Enderchest")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (args.length == 0) {
                    if (p.hasPermission("zyneon.premium")) {
                        p.openInventory(p.getEnderChest());
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ENDER_CHEST_OPEN);
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPerms);
                    }
                } else if (p.hasPermission("zyneon.team")) {
                    if (Bukkit.getPlayer((String)args[0]) != null) {
                        Player t = Bukkit.getPlayer((String)args[0]);
                        p.openInventory(t.getEnderChest());
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

