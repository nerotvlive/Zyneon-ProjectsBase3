
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Maintenance
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Maintenance")) {
            if (s.hasPermission("zyneon.leading.maintenance")) {
                ServerAPI.toggleMaintenance();
                API.sendMessage(s, "Der Serverwartungsmodus steht nun auf: \u00a7e" + ServerAPI.isMaintenance());
            } else {
                s.sendMessage(API.noPerms);
                if (s instanceof Player) {
                    PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
                }
            }
        }
        return false;
    }
}

