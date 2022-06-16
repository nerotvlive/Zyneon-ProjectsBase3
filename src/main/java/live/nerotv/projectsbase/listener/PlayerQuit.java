
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit
implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        API.protectedPlayers.remove((Object)p);
        if (ServerAPI.isMaintenance()) {
            if (p.hasPermission("zyneon.bypassmaintenance")) {
                PlayerAPI.saveLocation(p);
                e.setQuitMessage("\u00a78\u00ab \u00a7c" + p.getName());
            } else {
                e.setQuitMessage("");
            }
        } else {
            if (!p.isInvisible()) {
                e.setQuitMessage("\u00a78\u00ab \u00a7c" + p.getName());
            } else {
                e.setQuitMessage("");
            }
            PlayerAPI.saveLocation(p);
        }
    }
}

