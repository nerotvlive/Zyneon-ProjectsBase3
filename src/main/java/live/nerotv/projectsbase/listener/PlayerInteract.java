
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.BedrockAPI;
import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.WarpAPI;
import live.nerotv.projectsbase.listener.WorldChange;
import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract
implements Listener {
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        Sign sign;
        Block b;
        ItemStack Item2;
        Player p = e.getPlayer();
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND) {
            e.setCancelled(true);
        }
        if (e.getItem() != null && (Item2 = e.getItem()).getItemMeta() != null) {
            if (Item2.getItemMeta().getDisplayName().equals(ItemAPI.backItem.getItemMeta().getDisplayName())) {
                e.setCancelled(true);
                if (PlayerAPI.isBedrock((CommandSender)p)) {
                    BedrockAPI.bedrockMenu(p);
                } else {
                    e.getItem().setType(Material.AIR);
                }
            } else if (Item2.getItemMeta().getDisplayName().equals(ItemAPI.AcceptRules.getItemMeta().getDisplayName())) {
                Item2.setAmount(0);
            } else if (Item2.getItemMeta().getDisplayName().equals(ItemAPI.RulesPlaceHolder.getItemMeta().getDisplayName())) {
                Item2.setAmount(0);
            }
        }
        if (e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) && ((b = e.getClickedBlock()).getType().equals((Object)Material.OAK_WALL_SIGN) || b.getType().equals((Object)Material.OAK_SIGN)) && ChatColor.stripColor((String)(sign = (Sign)b.getState()).getLine(1)).toLowerCase().contains("zug nach")) {
            sign.setLine(1, "\u00a78Zug nach");
            sign.setLine(2, "\u00a7e" + sign.getLine(2));
            String name = ChatColor.stripColor((String)sign.getLine(2)).replace("\u00a7e", "");
            if (WarpAPI.ifWarpExists(name)) {
                p.teleport(WarpAPI.getWarp(name));
                API.sendMessage((CommandSender)p, "Du bist nun in \u00a7e" + name + "\u00a77.");
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getItemMeta() != null) {
            if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ItemAPI.backItem.getItemMeta().getDisplayName())) {
                e.setCancelled(true);
            } else {
                ItemStack item = e.getItemDrop().getItemStack();
                ItemMeta itemMeta = item.getItemMeta();
                Material itemType = item.getType();
                String itemName = itemMeta.getDisplayName();
                if (itemType == Material.PIG_SPAWN_EGG) {
                    if (itemName.contains("Chair")) {
                        itemMeta.setCustomModelData(Integer.valueOf(0));
                        e.getItemDrop().getItemStack().setItemMeta(itemMeta);
                    }
                } else if (itemType == Material.COW_SPAWN_EGG && itemName.contains("Stuhl1")) {
                    itemMeta.setCustomModelData(Integer.valueOf(0));
                    e.getItemDrop().getItemStack().setItemMeta(itemMeta);
                }
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPickup(PlayerPickupItemEvent e) {
        if (e.getItem().getItemStack().getItemMeta() != null) {
            if (e.getItem().getItemStack().getItemMeta().getDisplayName().equals(ItemAPI.backItem.getItemMeta().getDisplayName())) {
                e.setCancelled(true);
            } else {
                ItemStack item = e.getItem().getItemStack();
                ItemMeta itemMeta = item.getItemMeta();
                Material itemType = item.getType();
                String itemName = itemMeta.getDisplayName();
                if (itemType == Material.PIG_SPAWN_EGG) {
                    if (itemName.contains("WeaponStand")) {
                        itemMeta.setCustomModelData(Integer.valueOf(11));
                        e.getItem().getItemStack().setItemMeta(itemMeta);
                    } else if (itemName.contains("Tent #1")) {
                        itemMeta.setCustomModelData(Integer.valueOf(222));
                        e.getItem().getItemStack().setItemMeta(itemMeta);
                    } else if (itemName.contains("Tent #2")) {
                        itemMeta.setCustomModelData(Integer.valueOf(3333));
                        e.getItem().getItemStack().setItemMeta(itemMeta);
                    } else if (itemName.contains("Tent #3")) {
                        itemMeta.setCustomModelData(Integer.valueOf(44444));
                        e.getItem().getItemStack().setItemMeta(itemMeta);
                    } else if (itemName.contains("Table")) {
                        itemMeta.setCustomModelData(Integer.valueOf(77777777));
                        e.getItem().getItemStack().setItemMeta(itemMeta);
                    } else if (itemName.contains("MailBox")) {
                        itemMeta.setCustomModelData(Integer.valueOf(555555));
                        e.getItem().getItemStack().setItemMeta(itemMeta);
                    } else if (itemName.contains("Chair")) {
                        itemMeta.setCustomModelData(Integer.valueOf(6666666));
                        e.getItem().getItemStack().setItemMeta(itemMeta);
                    }
                } else if (itemType == Material.COW_SPAWN_EGG && itemName.contains("Stuhl1")) {
                    itemMeta.setCustomModelData(Integer.valueOf(1));
                    e.getItem().getItemStack().setItemMeta(itemMeta);
                }
            }
        }
    }

    @EventHandler
    public void playerPortal(PlayerPortalEvent e) {
        if (StaticManager.restrictedNether) {
            e.setCancelled(true);
            API.sendErrorMessage((CommandSender)e.getPlayer(), "\u00a7cDer Nether ist nur per \u00a74/warp zu erreichen\u00a78!");
        }
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        if (API.protectedPlayers.contains((Object)e.getPlayer())) {
            e.setCancelled(true);
            API.dispatchConsoleCommand("title " + e.getPlayer().getName() + " subtitle \"\u00a78...\u00a77warte bitte\u00a78,\u00a77 bis die Spawnprotection abgelaufen ist\u00a78!\"");
            API.dispatchConsoleCommand("title " + e.getPlayer().getName() + " title \"\u00a77Ladevorgang\u00a78...\"");
        }
        WorldChange.replaceChunk(e.getPlayer().getChunk());
    }
}

