
package live.nerotv.projectsbase.manager;

import live.nerotv.projectsbase.api.ItemAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomItemManager {
    public static ItemStack KNIFE = ItemAPI.knifeItem();
    public static ItemStack BATTLE_AXE = ItemAPI.battleAxeItem();
    public static ItemStack REVOLVER = new ItemStack(Material.CROSSBOW);
    public static ItemStack SHOTGUN = ItemAPI.shotgunItem();
    public static ItemStack SLINGSHOT = new ItemStack(Material.BOW);
    public static ItemStack AMMO = new ItemStack(Material.ARROW);
    public static ItemStack DARK_SALOON_DOOR = new ItemStack(Material.CRIMSON_DOOR);
    public static ItemStack SALOON_DOOR = new ItemStack(Material.WARPED_DOOR);

    public static ItemStack getCustomItem(String name) {
        if (name.equalsIgnoreCase("KNIFE")) {
            return KNIFE;
        }
        if (name.equalsIgnoreCase("SHOTGUN")) {
            return SHOTGUN;
        }
        if (name.equalsIgnoreCase("REVOLVER")) {
            return REVOLVER;
        }
        if (name.equalsIgnoreCase("SLINGSHOT")) {
            return SLINGSHOT;
        }
        if (name.equalsIgnoreCase("AMMO")) {
            return AMMO;
        }
        if (name.equalsIgnoreCase("SALOON_DOOR")) {
            return SALOON_DOOR;
        }
        if (name.equalsIgnoreCase("DARK_SALOON_DOOR")) {
            return DARK_SALOON_DOOR;
        }
        if (name.equalsIgnoreCase("BATTLE_AXE")) {
            return BATTLE_AXE;
        }
        return null;
    }
}

