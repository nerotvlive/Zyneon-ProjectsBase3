
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerDamage
implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location l = p.getLocation();
        String ls = "\u00a74 X\u00a7c" + l.getBlockX() + "\u00a74 Y\u00a7c" + l.getBlockY() + "\u00a74 Z\u00a7c" + l.getBlockZ() + " \u00a78(\u00a77" + l.getWorld().getName() + "\u00a78) \u00a77";
        Bukkit.getConsoleSender().sendMessage("\u00a74Zyneon \u00a78| \u00a7c" + p.getName() + "\u00a77 starb bei" + ls);
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        if (StaticManager.projectileGlassBreak && e.getEntity().getShooter() instanceof Player) {
            Player p = (Player)e.getEntity().getShooter();
            Projectile projectile = e.getEntity();
            if (p.getItemInHand().getType().equals((Object)Material.CROSSBOW)) {
                ItemStack gun = p.getItemInHand();
                ItemMeta gunMeta = gun.getItemMeta();
                if (gunMeta.hasCustomModelData()) {
                    if (gunMeta.getCustomModelData() == 1) {
                        projectile.setCustomName("SHOTGUN");
                    } else {
                        projectile.setCustomName("REVOLVER");
                    }
                } else {
                    projectile.setCustomName("REVOLVER");
                }
            } else if (p.getItemInHand().getType().equals((Object)Material.BOW)) {
                ItemStack gun = p.getItemInHand();
                ItemMeta gunMeta = gun.getItemMeta();
                projectile.setCustomName("SLINGSHOT");
            }
        }
    }

    @EventHandler
    public void bedrockFix(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (PlayerAPI.isBedrock((CommandSender)p)) {
            if (p.getInventory().getItem(8) != null) {
                ItemStack item = p.getInventory().getItem(8);
                p.getInventory().clear(8);
                p.getInventory().setItem(8, ItemAPI.backItem);
                if (API.hasAvailableSlot(p)) {
                    p.getInventory().addItem(new ItemStack[]{item});
                } else {
                    p.getWorld().dropItem(p.getLocation(), item);
                }
            } else {
                p.getInventory().setItem(8, ItemAPI.backItem);
            }
        }
    }

    @EventHandler
    public void onProjectile(ProjectileHitEvent e) {
        if (StaticManager.projectileGlassBreak && e.getHitEntity() != null && e.getEntity() instanceof Player) {
            Player t = (Player)e.getEntity();
            if (e.getEntity().getCustomName() != null) {
                if (e.getEntity().getCustomName().equalsIgnoreCase("SHOTGUN")) {
                    t.setHealth(t.getHealth() - 16.0);
                } else if (e.getEntity().getCustomName().equalsIgnoreCase("REVOLVER")) {
                    t.setHealth(t.getHealth() - 8.0);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Player p;
        if (e.getEntity() instanceof Player && API.protectedPlayers.contains((Object)(p = (Player)e.getEntity()))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Player p;
        if (e.getEntity() instanceof Player && API.protectedPlayers.contains((Object)(p = (Player)e.getEntity()))) {
            e.setCancelled(true);
        }
        if (e.getDamager() instanceof Player && API.protectedPlayers.contains((Object)(p = (Player)e.getDamager()))) {
            e.setCancelled(true);
        }
    }
}

