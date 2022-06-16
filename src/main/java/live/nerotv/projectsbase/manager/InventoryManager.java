
package live.nerotv.projectsbase.manager;

import live.nerotv.projectsbase.api.BedrockAPI;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryManager {
    public static Inventory INV002 = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)"\u00a79Warp-Men\u00fc");
    public static Inventory INV003 = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)ItemAPI.WarpC1.getItemMeta().getDisplayName());
    public static Inventory INV004 = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)ItemAPI.WarpC2.getItemMeta().getDisplayName());
    public static Inventory INV005 = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)ItemAPI.WarpETC.getItemMeta().getDisplayName());
    public static Inventory INV006 = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)"\u00a7bW\u00e4hle \u00a7b\u00a7ldeinen\u00a7r\u00a7b Spielmodus\u00a7r\u00a78...\u00a7r");
    public static Inventory confirmStopInventory = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)"\u00a7fWillst du den Server \u00a7cstoppen\u00a7f?");
    public static Inventory confirmReloadInventory = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)"\u00a7fWillst du den Server \u00a7creloaden\u00a7f?");
    public static Inventory confirmRulesInventory = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)"\u00a7cRegelupdate\u00a78, \u00a7estimmst du ihnen zu\u00a78?");

    public static void openInv001(Player p) {
        Inventory INV001;
        if (ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Shop.MarkReward")) {
            INV001 = Bukkit.createInventory(null, (int)27, (String)"\u00a79AP-Shop");
            ItemAPI.fillChestInventory(INV001, ItemAPI.RewardMark001, ItemAPI.RewardMark002, ItemAPI.RewardMark005, ItemAPI.RewardMark010, ItemAPI.RewardMark020, ItemAPI.RewardMark050, ItemAPI.RewardMark100, ItemAPI.RewardMark200, ItemAPI.RewardMark500, ItemAPI.BottleReward(), ItemAPI.CoalReward(), ItemAPI.GoldReward(), ItemAPI.IronReward(), ItemAPI.LapisReward(), ItemAPI.DiamondReward(), ItemAPI.NetherReward, ItemAPI.RocketReward(), ItemAPI.ElytraReward);
        } else {
            INV001 = Bukkit.createInventory(null, (int)18, (String)"\u00a79AP-Shop");
            ItemAPI.fillShopInventory(INV001, ItemAPI.BottleReward(), ItemAPI.CoalReward(), ItemAPI.GoldReward(), ItemAPI.IronReward(), ItemAPI.LapisReward(), ItemAPI.DiamondReward(), ItemAPI.NetherReward, ItemAPI.RocketReward(), ItemAPI.ElytraReward);
        }
        p.openInventory(INV001);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
    }

    public static void openInv_APSHOP(Player p) {
        InventoryManager.openInv001(p);
    }

    public static void openInv002(Player p) {
        if (PlayerAPI.isBedrock((CommandSender)p)) {
            BedrockAPI.bedrockWarp(p);
        } else {
            INV002.setItem(0, ItemAPI.Placeholder);
            INV002.setItem(1, ItemAPI.WarpC1);
            INV002.setItem(2, ItemAPI.WarpC2);
            INV002.setItem(3, ItemAPI.WarpETC);
            INV002.setItem(4, ItemAPI.Placeholder);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            p.openInventory(INV002);
        }
    }

    public static void openInv_WarpM(Player p) {
        InventoryManager.openInv002(p);
    }

    public static void openInv_WarpC1(Player p) {
        INV003.setItem(0, ItemAPI.Placeholder);
        INV003.setItem(1, ItemAPI.WarpC101);
        INV003.setItem(2, ItemAPI.WarpC102);
        INV003.setItem(3, ItemAPI.WarpBack);
        INV003.setItem(4, ItemAPI.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
        p.openInventory(INV003);
    }

    public static void openInv_WarpC2(Player p) {
        INV004.setItem(0, ItemAPI.Placeholder);
        INV004.setItem(1, ItemAPI.WarpC201);
        INV004.setItem(2, ItemAPI.WarpC202);
        INV004.setItem(3, ItemAPI.WarpBack);
        INV004.setItem(4, ItemAPI.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
        p.openInventory(INV004);
    }

    public static void openInv_WarpETC(Player p) {
        INV005.setItem(0, ItemAPI.Placeholder);
        INV005.setItem(1, ItemAPI.WarpETC01);
        INV005.setItem(2, ItemAPI.WarpETC02);
        INV005.setItem(3, ItemAPI.WarpBack);
        INV005.setItem(4, ItemAPI.Placeholder);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
        p.openInventory(INV005);
    }

    public static void openInv_GameMode(Player p) {
        INV006.setItem(0, ItemAPI.GameModeSurvival);
        INV006.setItem(1, ItemAPI.GameModeCreative);
        INV006.setItem(2, ItemAPI.GameModeAdventure);
        INV006.setItem(3, ItemAPI.GameModeSpectator);
        INV006.setItem(4, ItemAPI.Close);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
        p.openInventory(INV006);
    }

    public static void openConfirmStopInventory(Player p) {
        confirmStopInventory.setItem(0, ItemAPI.Placeholder);
        confirmStopInventory.setItem(1, ItemAPI.Cancel);
        confirmStopInventory.setItem(2, ItemAPI.Placeholder);
        confirmStopInventory.setItem(3, ItemAPI.StopYes);
        confirmStopInventory.setItem(4, ItemAPI.Placeholder);
        p.openInventory(confirmStopInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
    }

    public static void openConfirmReloadInventory(Player p) {
        confirmReloadInventory.setItem(0, ItemAPI.Placeholder);
        confirmReloadInventory.setItem(1, ItemAPI.Cancel);
        confirmReloadInventory.setItem(2, ItemAPI.Placeholder);
        confirmReloadInventory.setItem(3, ItemAPI.ReloadYes);
        confirmReloadInventory.setItem(4, ItemAPI.Placeholder);
        p.openInventory(confirmReloadInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
    }

    public static void openConfirmRulesInventory(Player p) {
        confirmRulesInventory.setItem(0, ItemAPI.RulesPlaceHolder);
        confirmRulesInventory.setItem(1, ItemAPI.RulesPlaceHolder);
        confirmRulesInventory.setItem(2, ItemAPI.AcceptRules);
        confirmRulesInventory.setItem(3, ItemAPI.RulesPlaceHolder);
        confirmRulesInventory.setItem(4, ItemAPI.RulesPlaceHolder);
        p.openInventory(confirmRulesInventory);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
    }

    public static void openSettingsInventory(Player p) {
        Inventory settingsInventory = Bukkit.createInventory(null, (InventoryType)InventoryType.HOPPER, (String)"\u00a79Einstellungen");
        settingsInventory.setItem(0, ItemAPI.Placeholder);
        settingsInventory.setItem(1, ItemAPI.mainColorItem(p));
        settingsInventory.setItem(2, ItemAPI.typeColorItem(p));
        settingsInventory.setItem(3, ItemAPI.userColorItem(p));
        settingsInventory.setItem(4, ItemAPI.Placeholder);
        p.openInventory(settingsInventory);
        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
    }
}

