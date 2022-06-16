
package live.nerotv.projectsbase.manager;

import java.io.File;
import java.util.HashMap;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DeathChestManager
implements Listener {
    public static HashMap<Block, Inventory> DeathChest = new HashMap();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location l = p.getLocation();
        String ls = "\u00a74 X\u00a7c" + l.getBlockX() + "\u00a74 Y\u00a7c" + l.getBlockY() + "\u00a74 Z\u00a7c" + l.getBlockZ() + " \u00a78(\u00a77" + l.getWorld().getName() + "\u00a78) \u00a77";
        if (StaticManager.deathChest) {
            if (e.getDrops().size() == 0) {
                PlayerAPI.sendPlayerMessage(p, "\u00a77Du bist bei" + ls + "\u00a7cgestorben\u00a78! \u00a77Es wurde keine \u00a7eTodeskiste erstellt\u00a78,\u00a77 da dein Inventar leer war\u00a78!", NewSound.BLOCK_ANVIL_BREAK);
            } else {
                String SID = p.getUniqueId().toString();
                File file = new File("plugins/Zyneon/Players/" + SID + ".yml");
                YamlConfiguration usr = YamlConfiguration.loadConfiguration((File)file);
                String DESC = API.getDateTime();
                usr.set("Death." + DESC, (Object)e.getDrops());
                ConfigAPI.saveConfig(file, usr);
                Block Chest2 = p.getWorld().getBlockAt(l.add(0.0, 1.0, 0.0)).getType().equals((Object)Material.CHEST) ? (p.getWorld().getBlockAt(l.add(0.0, 2.0, 0.0)).getType().equals((Object)Material.CHEST) ? p.getWorld().getBlockAt(l.add(0.0, 4.0, 0.0)) : p.getWorld().getBlockAt(l.add(0.0, 2.0, 0.0))) : p.getWorld().getBlockAt(l.add(0.0, 1.0, 0.0));
                Chest2.setType(Material.CHEST);
                Inventory inv = Bukkit.createInventory(null, (int)45, (String)("\u00a7c\u00a7lTodeskiste von \u00a7e\u00a7l" + p.getName()));
                inv.clear();

                DeathChest.put(Chest2, inv);
                Chest chest1 = (Chest)Chest2.getState();

                if (Chest2.getType().equals((Object)Material.CHEST)) {
                    e.getDrops().clear();
                    PlayerAPI.sendPlayerMessage(p, "\u00a77Du bist bei" + ls + "\u00a7cgestorben\u00a78! \u00a77Eine \u00a7eTodeskiste\u00a77 mit deinen Items wurde erstellt und verschwindet in 5 Minuten\u00a78. \u00a7eBeeil dich\u00a78!", NewSound.BLOCK_ANVIL_BREAK);
                } else {
                    PlayerAPI.sendPlayerMessage(p, "\u00a77Du bist bei" + ls + "\u00a7cgestorben\u00a78! \u00a77Deine Items liegen frei herum und verschwinden bald. \u00a7eBeeil dich\u00a78!", NewSound.BLOCK_ANVIL_BREAK);
                }
            }
        } else if (e.getDrops().size() == 0) {
            PlayerAPI.sendPlayerMessage(p, "\u00a77Du bist bei" + ls + "\u00a7cgestorben\u00a78! \u00a77Da du aber keine Items im Inventar gehabt hast\u00a78,\u00a77 ist dies nicht so wichtig\u00a78!", NewSound.BLOCK_ANVIL_BREAK);
        } else {
            PlayerAPI.sendPlayerMessage(p, "\u00a77Du bist bei" + ls + "\u00a7cgestorben\u00a78! \u00a77Deine Items liegen frei herum und verschwinden bald. \u00a7eBeeil dich\u00a78!", NewSound.BLOCK_ANVIL_BREAK);
        }
    }

    @EventHandler
    public void onChest(PlayerInteractEvent e) {
        if (StaticManager.deathChest && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CHEST) {
            Block block = e.getClickedBlock();
            for (Block blocks : DeathChest.keySet()) {
                if (!blocks.getLocation().equals((Object)block.getLocation())) continue;
                e.setCancelled(true);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CHEST_OPEN, 100.0f, 100.0f);
                e.getPlayer().openInventory(DeathChest.get((Object)blocks));
            }
        }
    }

    @EventHandler
    public void onChestDestroy(BlockBreakEvent e) {
        if (StaticManager.deathChest && e.getBlock().getType() == Material.CHEST) {
            Block block = e.getBlock();
            for (Block blocks : DeathChest.keySet()) {
                if (!blocks.getLocation().equals((Object)block.getLocation())) continue;
                if (!DeathChest.get((Object)blocks).isEmpty()) {
                    e.setCancelled(true);
                    Player p = e.getPlayer();
                    API.sendErrorMessage((CommandSender)p, "\u00a74Die Todeskiste ist nicht leer! \u00a7cLeere sie, damit du sie entfernen kannst\u00a7c!");
                    continue;
                }
                e.setDropItems(false);
            }
        }
    }
}

