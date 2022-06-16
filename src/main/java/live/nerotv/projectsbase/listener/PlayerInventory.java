
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ChatAPI;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import live.nerotv.projectsbase.api.WarpAPI;
import live.nerotv.projectsbase.manager.InventoryManager;
import live.nerotv.projectsbase.manager.PointsManager;
import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInventory
implements Listener {
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if (e.getClickedInventory() != null) {
            Inventory inv = e.getClickedInventory();
            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
                ItemStack Item2 = e.getCurrentItem();
                ItemMeta ItemMeta2 = Item2.getItemMeta();
                String ItemName = ItemMeta2.getDisplayName();
                ItemMeta2.setDisplayName(ItemName);
                Item2.setItemMeta(ItemMeta2);
                if (ItemName.equals(ItemAPI.backItem.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                } else if (ItemName.equals("\u00a7bSpawn")) {
                    e.setCancelled(true);
                    if (WarpAPI.isWarpEnabled("Spawn")) {
                        p.teleport(WarpAPI.getWarp("Spawn"));
                    } else {
                        p.teleport(Bukkit.getWorld((String)"Welt").getSpawnLocation());
                    }
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                } else if (ItemName.equals("\u00a7eFarmwelt")) {
                    e.setCancelled(true);
                    if (StaticManager.farmworld) {
                        if (WarpAPI.isWarpEnabled("Farmwelt")) {
                            p.teleport(WarpAPI.getWarp("Farmwelt"));
                        } else {
                            p.teleport(Bukkit.getWorld((String)ConfigAPI.CFG.getString("Core.Settings.Farmwelt.Name")).getSpawnLocation());
                        }
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    }
                } else if (ItemName.equals("\u00a74Nether")) {
                    e.setCancelled(true);
                    API.sendErrorMessage((CommandSender)p, "\u00a7cDer Nether ist zurzeit deaktiviert\u00a78! &cVersuche es sp\u00e4ter erneut\u00a78!");
                } else if (ItemName.equals(ItemAPI.Placeholder.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                } else if (ItemName.equals(ItemAPI.Close.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.BLOCK_ENDER_CHEST_CLOSE, 80.0f, 80.0f);
                    p.closeInventory();
                } else if (ItemName.equals(ItemAPI.ElytraReward.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 5000).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.ELYTRA);
                            ItemMeta RewardMeta = Reward.getItemMeta();
                            RewardMeta.setDisplayName("Elytren");
                            RewardMeta.setUnbreakable(true);
                            Reward.setItemMeta(RewardMeta);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 5000));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark001.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 5).booleanValue()) {
                        PlayerAPI.addMoney(p, 1000.0);
                        PointsManager.setAP(p, (Integer)(Money - 5));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark002.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 10).booleanValue()) {
                        PlayerAPI.addMoney(p, 2000.0);
                        PointsManager.setAP(p, (Integer)(Money - 10));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark005.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 25).booleanValue()) {
                        PlayerAPI.addMoney(p, 5000.0);
                        PointsManager.setAP(p, (Integer)(Money - 25));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark010.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 50).booleanValue()) {
                        PlayerAPI.addMoney(p, 10000.0);
                        PointsManager.setAP(p, (Integer)(Money - 50));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark020.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 100).booleanValue()) {
                        PlayerAPI.addMoney(p, 20000.0);
                        PointsManager.setAP(p, (Integer)(Money - 100));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark050.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 250).booleanValue()) {
                        PlayerAPI.addMoney(p, 50000.0);
                        PointsManager.setAP(p, (Integer)(Money - 250));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark100.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 500).booleanValue()) {
                        PlayerAPI.addMoney(p, 100000.0);
                        PointsManager.setAP(p, (Integer)(Money - 500));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark200.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 1000).booleanValue()) {
                        PlayerAPI.addMoney(p, 200000.0);
                        PointsManager.setAP(p, (Integer)(Money - 1000));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RewardMark500.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 2500).booleanValue()) {
                        PlayerAPI.addMoney(p, 500000.0);
                        PointsManager.setAP(p, (Integer)(Money - 2500));
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.BottleReward().getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 250).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.EXPERIENCE_BOTTLE);
                            Reward.setAmount(32);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 250));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.CoalReward().getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 250).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.COAL);
                            Reward.setAmount(32);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 250));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.GoldReward().getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 250).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.GOLD_INGOT);
                            Reward.setAmount(8);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 250));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.IronReward().getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 300).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.IRON_INGOT);
                            Reward.setAmount(8);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 300));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.LapisReward().getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 350).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.LAPIS_LAZULI);
                            Reward.setAmount(16);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 350));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.DiamondReward().getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 450).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.DIAMOND);
                            Reward.setAmount(2);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 450));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.NetherReward.getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 1250).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.NETHERITE_INGOT);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 1250));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.RocketReward().getItemMeta().getDisplayName())) {
                    Integer Money = PointsManager.getAP(p);
                    if (PointsManager.checkPayment(Money, 50).booleanValue()) {
                        if (API.hasAvailableSlot(p)) {
                            ItemStack Reward = new ItemStack(Material.FIREWORK_ROCKET);
                            Reward.setAmount(64);
                            p.getInventory().addItem(new ItemStack[]{Reward});
                            PointsManager.setAP(p, (Integer)(Money - 50));
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast nicht genug Platz im Inventar!");
                        }
                    } else {
                        API.sendMessage((CommandSender)p, API.noMoney);
                    }
                    e.setCancelled(true);
                } else if (ItemName.equals("\u00a7cPlatzhalter\u00a74")) {
                    PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.WarpBack.getItemMeta().getDisplayName())) {
                    InventoryManager.openInv002(p);
                    PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.WarpC1.getItemMeta().getDisplayName())) {
                    InventoryManager.openInv_WarpC1(p);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.WarpC2.getItemMeta().getDisplayName())) {
                    InventoryManager.openInv_WarpC2(p);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    e.setCancelled(true);
                } else if (ItemName.equals(ItemAPI.WarpETC.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    InventoryManager.openInv_WarpETC(p);
                } else if (ItemName.equals(ItemAPI.WarpC101.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    p.teleport(WarpAPI.getWarp(ConfigAPI.CFG.getString("Core.WarpGUI.Warps.Sub1Menu01")));
                } else if (ItemName.equals(ItemAPI.WarpC102.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    p.teleport(WarpAPI.getWarp(ConfigAPI.CFG.getString("Core.WarpGUI.Warps.Sub1Menu02")));
                } else if (ItemName.equals(ItemAPI.WarpC201.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    p.teleport(WarpAPI.getWarp(ConfigAPI.CFG.getString("Core.WarpGUI.Warps.Sub2Menu01")));
                } else if (ItemName.equals(ItemAPI.WarpC202.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    p.teleport(WarpAPI.getWarp(ConfigAPI.CFG.getString("Core.WarpGUI.Warps.Sub2Menu02")));
                } else if (ItemName.equals(ItemAPI.WarpETC01.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    p.teleport(WarpAPI.getWarp(ConfigAPI.CFG.getString("Core.WarpGUI.Warps.Sub3Menu01")));
                } else if (ItemName.equals(ItemAPI.WarpETC02.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    p.teleport(WarpAPI.getWarp(ConfigAPI.CFG.getString("Core.WarpGUI.Warps.Sub3Menu02")));
                } else if (ItemName.equals(ItemAPI.Cancel.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                    p.closeInventory();
                } else if (ItemName.equals(ItemAPI.StopYes.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if (p.hasPermission("zyneon.leading.stop")) {
                        ServerAPI.scheduledShutdown();
                        API.sendMessage((CommandSender)p, "\u00a77Du hast den \u00a7eStopvorgang\u00a77 gestartet\u00a78.");
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPerms);
                    }
                } else if (ItemName.equals(ItemAPI.RulesPlaceHolder.getItemMeta().getDisplayName())) {
                    if (e.getClickedInventory().equals((Object)InventoryManager.confirmRulesInventory)) {
                        e.setCancelled(true);
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    } else if (e.getClickedInventory().equals((Object)p.getInventory())) {
                        e.getCurrentItem().setAmount(0);
                    }
                } else if (ItemName.equals(ItemAPI.AcceptRules.getItemMeta().getDisplayName())) {
                    if (e.getClickedInventory().equals((Object)InventoryManager.confirmRulesInventory)) {
                        e.setCancelled(true);
                        PlayerAPI.setRulesLevel(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0f, 100.0f);
                    } else if (e.getClickedInventory().equals((Object)p.getInventory())) {
                        e.getCurrentItem().setAmount(0);
                    }
                } else if (ItemName.equals(ItemAPI.GameModeAdventure.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.performCommand("gamemode 2");
                    p.closeInventory();
                } else if (ItemName.equals(ItemAPI.GameModeCreative.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.performCommand("gamemode 1");
                    p.closeInventory();
                } else if (ItemName.equals(ItemAPI.GameModeSpectator.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.performCommand("gamemode 3");
                    p.closeInventory();
                } else if (ItemName.equals(ItemAPI.GameModeSurvival.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.performCommand("gamemode 0");
                    p.closeInventory();
                } else if (ItemName.equals(ItemAPI.ReloadYes.getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if (p.hasPermission("zyneon.team")) {
                        PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            API.switchServer(all, "Lobby-1");
                        }
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)"reload confirm");
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPerms);
                    }
                } else if (ItemName.equals("\u00a77Hauptchatfarbe")) {
                    e.setCancelled(true);
                    ChatAPI.changeMainColor(p, Item2);
                } else if (ItemName.equals("\u00a7eHervorhebungsfarbe 1")) {
                    e.setCancelled(true);
                    ChatAPI.changeTypeColor(p, Item2);
                } else if (ItemName.equals("\u00a7aHervorhebungsfarbe 2")) {
                    e.setCancelled(true);
                    ChatAPI.changeUserColor(p, Item2);
                }
            }
        }
    }

    @EventHandler
    public void onInventory(InventoryOpenEvent e) {
        Player p = (Player)e.getPlayer();
        org.bukkit.inventory.PlayerInventory pInv = p.getInventory();
        Inventory eInv = e.getInventory();
        if (eInv.getType().equals((Object)InventoryType.CHEST) && (!e.getView().getTitle().contains("Chest") || e.getView().getTitle().equalsIgnoreCase("Kiste") || e.getView().getTitle().equalsIgnoreCase("Truhe") || e.getView().getTitle().equalsIgnoreCase("") || e.getView().getTitle().equalsIgnoreCase(" ") || e.getView().getTitle().equalsIgnoreCase("Item Hopper"))) {
            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player)e.getPlayer();
        if (e.getInventory().equals((Object)InventoryManager.confirmRulesInventory) && !PlayerAPI.checkRulesLevel(p)) {
            p.kickPlayer("\u00a7cDu musst die Regeln und die Datenschutzerkl\u00e4rung akzeptieren.");
        }
    }
}

