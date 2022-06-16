
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Me
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Me")) {
            if (!(s instanceof Player)) {
                s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
            } else {
                Player p = (Player)s;
                if (args.length == 0) {
                    API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/do [Aktion]");
                } else if (PlayerAPI.isRP(p)) {
                    if (PlayerAPI.getRPN(p.getUniqueId().toString()) == null) {
                        PlayerAPI.sendPlayerMessage(p, "\u00a7cBitte setze dir zuerst deinen Roleplaynamen mit \u00a7c/name [Vorname] \u00a77[Zwischenname] &c[Nachname].", NewSound.BLOCK_ANVIL_BREAK);
                    } else {
                        Object m = "";
                        for (int i = 0; i < args.length; ++i) {
                            m = (String)m + args[i] + " ";
                        }
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!p.getLocation().getWorld().equals((Object)all.getLocation().getWorld()) || !(p.getLocation().distance(all.getLocation()) <= 29.0)) continue;
                            all.sendMessage("\u00a78* \u00a7e" + PlayerAPI.getRPN(p.getUniqueId().toString()) + "\u00a77 " + (String)m + "\u00a78*");
                        }
                    }
                } else {
                    p.sendMessage("\u00a7cBitte gehe dazu mit \u00a74/rp\u00a7c in den Roleplay-Modus!");
                    PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                }
            }
        }
        return false;
    }
}

