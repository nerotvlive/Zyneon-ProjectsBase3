
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHit
implements Listener {
    @EventHandler
    public void onProjectile(ProjectileHitEvent e) {
        if (StaticManager.projectileGlassBreak) {
            try {
                if ((e.getEntity().getType().equals((Object)EntityType.ARROW) || e.getEntity().getType().equals((Object)EntityType.SPECTRAL_ARROW)) && e.getEntity().getShooter() instanceof Player && e.getHitBlock() != null && e.getHitBlock().getType().toString().toLowerCase().contains("glass")) {
                    e.getHitBlock().setType(Material.AIR);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {

            }
        }
    }
}

