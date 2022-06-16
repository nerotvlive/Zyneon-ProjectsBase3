
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class PlayerCommand
implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (p.getName().equalsIgnoreCase("nerotvlive") && p.hasPermission("ZySys.Commands.Bypass")) {
            return;
        }
        String CN = e.getMessage().toLowerCase();
        if (CN.contains("/plugins")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/pl")) {
            if (CN.contains("/playsound")) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
                p.performCommand("neino");
            }
        } else if (CN.contains("/ver")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/version")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/about")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/pex")) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                Main.setState(all);
            }
        } else if (CN.contains("/timings")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/aa")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/aach")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/aachievements")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("advancedachievements")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("gsit")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/spigot")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/bukkit")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("iinfo")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("csgive")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("chestshop")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("csaccess")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("cstoggle")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("iteminfo")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("csmetrics")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("csversion")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if (CN.contains("/stop")) {
            e.setCancelled(true);
            if (p.hasPermission("zyneon.leading.stop")) {
                InventoryManager.openConfirmStopInventory(p);
            } else {
                p.performCommand("neino");
            }
        } else if (CN.contains("/restart")) {
            e.setCancelled(true);
            if (p.hasPermission("zyneon.leading.stop")) {
                InventoryManager.openConfirmStopInventory(p);
            } else {
                p.performCommand("neino");
            }
        } else if (CN.contains("/rl")) {
            e.setCancelled(true);
            if (p.hasPermission("zyneon.team")) {
                InventoryManager.openConfirmReloadInventory(p);
            } else {
                p.performCommand("neino");
            }
        } else if (CN.contains("/reload")) {
            e.setCancelled(true);
            if (p.hasPermission("zyneon.team")) {
                InventoryManager.openConfirmReloadInventory(p);
            } else {
                p.performCommand("neino");
            }
        }
        for (Player all : Bukkit.getOnlinePlayers()) {
            Main.setState(all);
        }
    }

    @EventHandler
    public void onPlayerTab(PlayerCommandSendEvent e) {
        e.getCommands().removeAll(e.getCommands());
        e.getCommands().add("author");
    }
}

