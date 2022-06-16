
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.api.FilterAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class WorldSignChange
implements Listener {
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        for (int i = 0; i < 4; ++i) {
            String line = e.getLine(i);
            if (line != null && !line.equals("")) {
                e.setLine(i, ChatColor.translateAlternateColorCodes((char)'&', (String)line));
                if (FilterAPI.isStringBlocked(line)) {
                    e.getPlayer().sendMessage("\u00a7cAchte auf deine Wortwahl!");
                    e.setCancelled(true);
                }
            }
            if (e.getLine(1) == null || e.getLine(1).equals("") || !e.getLine(1).contains("Zug nach") || e.getPlayer().hasPermission("zyneon.team.trainssigns")) continue;
            e.setLine(1, "\u00a7cKeine");
            e.setLine(2, "\u00a7cBerechtigung");
            e.setLine(0, "");
            e.setLine(3, "");
            e.getPlayer().sendMessage("\u00a7cDas darfst du nicht!");
            e.setCancelled(true);
        }
    }
}

