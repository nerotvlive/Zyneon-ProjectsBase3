
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Roleplay
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Roleplay")) {
            if (!(s instanceof Player)) {
                s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
            } else {
                Player p = (Player)s;
                if (PlayerAPI.isRP(p)) {
                    PlayerAPI.setRoleplay(p, (Boolean)false);
                    PlayerAPI.sendPlayerMessage(p, API.Prefix + "\u00a77Du bist nun \u00a7cnicht mehr\u00a77 im \u00a7eRoleplay-Modus\u00a77!", NewSound.ENTITY_CHICKEN_EGG);
                } else {
                    PlayerAPI.setRoleplay(p, (Boolean)true);
                    PlayerAPI.sendPlayerMessage(p, API.Prefix + "\u00a77Du hast den \u00a7eRoleplay-Modus\u00a7a aktiviert\u00a77!", NewSound.ENTITY_CHICKEN_EGG);
                }
                Main.setState(p);
            }
        }
        return false;
    }
}

