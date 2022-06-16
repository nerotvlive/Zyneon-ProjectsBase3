
package live.nerotv.projectsbase.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Disconnect
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Disconnect")) {
            if (!(s instanceof Player)) {
                s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
            } else {
                Player p = (Player)s;
                if (p.getName().equalsIgnoreCase("ideallauch")) {
                    p.kickPlayer("\u00a77und erneut...\n\u00a7fwurdest du von \u00a7cnerotvlive\u00a77 abgezockt und ausgenutzt\u00a78.\n\u00a77Selbst Schuld, wie immer.\n\n\u00a77Aber Hey: \u00a7aDANKE\u00a77 f\u00fcr's Spielen.");
                } else {
                    p.kickPlayer("\u00a77Du hast den Server verlassen\u00a78.\u00a7a Danke\u00a77 f\u00fcr's Spielen!");
                }
            }
        }
        return false;
    }
}

