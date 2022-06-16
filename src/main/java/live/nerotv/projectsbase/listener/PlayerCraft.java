
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class PlayerCraft
implements Listener {
    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        if (StaticManager.projectileGlassBreak && e.getRecipe() != null) {
            if (e.getRecipe().getResult().getType().equals((Object)Material.CROSSBOW)) {
                ItemAPI.blockCrafting(e);
            } else if (e.getRecipe().getResult().getType().equals((Object)Material.ARROW)) {
                ItemAPI.blockCrafting(e);
            } else if (e.getRecipe().getResult().getType().equals((Object)Material.TNT)) {
                ItemAPI.blockCrafting(e);
            } else if (e.getRecipe().getResult().getType().equals((Object)Material.WARPED_DOOR)) {
                if (e.getInventory().contains(Material.WARPED_PLANKS)) {
                    ItemAPI.blockCrafting(e);
                }
            } else if (e.getRecipe().getResult().getType().equals((Object)Material.CRIMSON_DOOR)) {
                if (e.getInventory().contains(Material.CRIMSON_PLANKS)) {
                    ItemAPI.blockCrafting(e);
                }
            } else if (e.getRecipe().getResult().getType().equals((Object)Material.BOW)) {
                ItemAPI.blockCrafting(e);
            }
        }
    }

    @EventHandler
    public void onEnchant(PrepareItemEnchantEvent e) {
        if (StaticManager.projectileGlassBreak && e.getItem().getType().equals((Object)Material.CROSSBOW)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent e) {
        EnchantmentStorageMeta enchantmentStorageMeta;
        if (StaticManager.projectileGlassBreak && e.getInventory().getItem(0) != null && e.getInventory().getItem(1) != null && e.getInventory().getItem(1).getType().equals((Object)Material.ENCHANTED_BOOK) && e.getInventory().getItem(0).getType().equals((Object)Material.CROSSBOW) && (enchantmentStorageMeta = (EnchantmentStorageMeta)e.getInventory().getItem(1).getItemMeta()).hasStoredEnchant(Enchantment.MULTISHOT)) {
            e.setResult(new ItemStack(Material.AIR));
        }
    }
}

