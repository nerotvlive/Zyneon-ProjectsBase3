
package live.nerotv.projectsbase.api;

import java.util.Arrays;
import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ChatAPI;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.manager.CustomItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ItemAPI {
    public static ItemStack Placeholder = ItemAPI.createItem(Material.BLACK_STAINED_GLASS_PANE, "\u00a70", "\u00a70");
    public static ItemStack Placeholder2 = ItemAPI.createItem(Material.RED_STAINED_GLASS_PANE, "\u00a7cKommt noch...", "\u00a70");
    public static ItemStack Close = ItemAPI.createItem(Material.BARRIER, "\u00a7cSchlie\u00dfen", new String[0]);
    public static ItemStack WarpBack = ItemAPI.createItem(Material.DARK_OAK_DOOR, "\u00a7cZur\u00fcck", new String[0]);
    public static ItemStack WarpC1 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.StartMenu01")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.StartMenu01"), new String[0]);
    public static ItemStack WarpC2 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.StartMenu02")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.StartMenu02"), new String[0]);
    public static ItemStack WarpETC = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.StartMenu03")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.StartMenu03"), new String[0]);
    public static ItemStack WarpC101 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.Sub1Menu01")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.Sub1Menu01"), new String[0]);
    public static ItemStack WarpC201 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.Sub2Menu01")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.Sub2Menu01"), new String[0]);
    public static ItemStack WarpETC01 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.Sub3Menu01")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.Sub3Menu01"), new String[0]);
    public static ItemStack WarpC102 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.Sub1Menu02")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.Sub1Menu02"), new String[0]);
    public static ItemStack WarpC202 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.Sub2Menu02")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.Sub2Menu02"), new String[0]);
    public static ItemStack WarpETC02 = ItemAPI.createItem(Material.getMaterial((String)ConfigAPI.CFG.getString("Core.WarpGUI.Items.Sub3Menu02")), ConfigAPI.CFG.getString("Core.WarpGUI.Names.Sub3Menu02"), new String[0]);
    public static ItemStack ElytraReward = ItemAPI.createItem(Material.ELYTRA, "\u00a7bUnkaputtbare Elytren", "\u00a78\u00a7m10.000 AP\u00a7r \u00a795.000 AP");
    public static ItemStack RewardMark001 = ItemAPI.createItem(Material.IRON_NUGGET, "\u00a7a1000 Mark", "\u00a78\u00a7m10 AP\u00a7r \u00a795 AP");
    public static ItemStack RewardMark002 = ItemAPI.createItem(Material.GOLD_NUGGET, "\u00a7a2000 Mark", "\u00a78\u00a7m20 AP\u00a7r \u00a7910 AP");
    public static ItemStack RewardMark005 = ItemAPI.createItem(Material.PAPER, "\u00a7a5000 Mark", "\u00a78\u00a7m50 AP\u00a7r \u00a7925 AP");
    public static ItemStack RewardMark010 = ItemAPI.createItem(Material.PAPER, "\u00a7110000 Mark", "\u00a78\u00a7m100 AP\u00a7r \u00a7950 AP");
    public static ItemStack RewardMark020 = ItemAPI.createItem(Material.PAPER, "\u00a7e20000 Mark", "\u00a78\u00a7m200 AP\u00a7r \u00a79100 AP");
    public static ItemStack RewardMark050 = ItemAPI.createItem(Material.PAPER, "\u00a7c50000 Mark", "\u00a78\u00a7m500 AP\u00a7r \u00a79250 AP");
    public static ItemStack RewardMark100 = ItemAPI.createItem(Material.PAPER, "\u00a7d100000 Mark", "\u00a78\u00a7m1.000 AP\u00a7r \u00a79500 AP");
    public static ItemStack RewardMark200 = ItemAPI.createItem(Material.PAPER, "\u00a72200000 Mark", "\u00a78\u00a7m2.000 AP\u00a7r \u00a791.000 AP");
    public static ItemStack RewardMark500 = ItemAPI.createItem(Material.PAPER, "\u00a74500000 Mark", "\u00a78\u00a7m5.000 AP\u00a7r \u00a792.500 AP");
    public static ItemStack NetherReward = ItemAPI.createItem(Material.NETHERITE_INGOT, "\u00a7cNetherit", "\u00a78\u00a7m2.500 AP\u00a7r \u00a791.250 AP");
    public static ItemStack ReloadYes = ItemAPI.createItem(Material.GREEN_CONCRETE, "\u00a7aJa\u00a7r", new String[0]);
    public static ItemStack Cancel = ItemAPI.createItem(Material.RED_CONCRETE, "\u00a7cNein\u00a7r", new String[0]);
    public static ItemStack StopYes = ItemAPI.createItem(Material.GREEN_CONCRETE, "\u00a7aJa\u00a72", new String[0]);
    public static ItemStack GameModeSurvival = ItemAPI.createItem(Material.APPLE, "\u00a7b\u00dcberlebensmodus\u00a7r", new String[0]);
    public static ItemStack GameModeCreative = ItemAPI.createItem(Material.GOLDEN_APPLE, "\u00a7bKreativmodus\u00a7r", new String[0]);
    public static ItemStack GameModeAdventure = ItemAPI.createItem(Material.STICK, "\u00a7bAbenteuermodus\u00a7r", new String[0]);
    public static ItemStack GameModeSpectator = ItemAPI.createItem(Material.DIAMOND_HELMET, "\u00a7bZuschauermodus\u00a7r", new String[0]);
    public static ItemStack RulesPlaceHolder = ItemAPI.createItem(Material.PAPER, "\u00a7bRegeln auf:", "\u00a79\u00a7n" + ConfigAPI.CFG.getString("Core.Strings.Projektregeln"));
    public static ItemStack AcceptRules = ItemAPI.createItem(Material.GREEN_CONCRETE, "Mit dem Klicken best\u00e4tige ich, dass:", "\u00a7a- ich die Regeln gelesen habe,", "\u00a7a- ich den Regeln zustimme,", "\u00a7a- ich verspreche mich an die Regeln zu halten", "\u00a77und dass", "\u00a7b- ich der \u00a79Nerofy.de\u00a7b Datenschutzerkl\u00e4rung zustimme.");
    public static ItemStack Mark001 = ItemAPI.createItem(Material.IRON_NUGGET, "\u00a7a\u00a7o1 \u00a7mM", new String[0]);
    public static ItemStack Mark002 = ItemAPI.createItem(Material.GOLD_NUGGET, "\u00a7a\u00a7o2 \u00a7mM", new String[0]);
    public static ItemStack Mark005 = ItemAPI.createItem(Material.PAPER, "\u00a7a\u00a7o5 \u00a7mM", new String[0]);
    public static ItemStack Mark010 = ItemAPI.createItem(Material.PAPER, "\u00a79\u00a7o10 \u00a7mM", new String[0]);
    public static ItemStack Mark020 = ItemAPI.createItem(Material.PAPER, "\u00a7e\u00a7o20 \u00a7mM", new String[0]);
    public static ItemStack Mark050 = ItemAPI.createItem(Material.PAPER, "\u00a7c\u00a7o50 \u00a7mM", new String[0]);
    public static ItemStack Mark100 = ItemAPI.createItem(Material.PAPER, "\u00a7d\u00a7o100 \u00a7mM", new String[0]);
    public static ItemStack Mark200 = ItemAPI.createItem(Material.PAPER, "\u00a72\u00a7o200 \u00a7mM", new String[0]);
    public static ItemStack Mark500 = ItemAPI.createItem(Material.PAPER, "\u00a74\u00a7o500 \u00a7mM", new String[0]);
    public static ItemStack backItem = ItemAPI.createItem(Material.SLIME_BALL, "\u00a7aAktionsmen\u00fc", new String[0]);

    public static ItemStack createItem(Material material, String name, String ... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static boolean checkItemStack(String item) {
        item = item.toUpperCase().replace("-", "_");
        try {
            ItemStack itemStack = new ItemStack(Material.getMaterial((String)item));
            return true;
        }
        catch (IllegalArgumentException e) {
            try {
                if (CustomItemManager.getCustomItem(item) == null) {
                    return false;
                }
                ItemStack itemStack = CustomItemManager.getCustomItem(item);
                return true;
            }
            catch (IllegalArgumentException e2) {
                return false;
            }
        }
    }

    public static void addCraftings() {
        if (ConfigAPI.CFG.getBoolean("Core.Settings.CustomItems.IRON_KNIFE")) {
            ItemAPI.addIronKnifeCrafting();
        }
        if (ConfigAPI.CFG.getBoolean("Core.Settings.CustomItems.BATTLE_AXE")) {
            ItemAPI.addBattleAxeCrafting();
        }
        if (ConfigAPI.CFG.getBoolean("Core.Settings.CustomItems.SALOON_DOOR")) {
            ItemAPI.addSaloonDoorCrafting();
        }
        if (ConfigAPI.CFG.getBoolean("Core.Settings.CustomItems.DARK_SALOON_DOOR")) {
            ItemAPI.addDarkSaloonDoorCrafting();
        }
    }

    private static void addSaloonDoorCrafting() {
        NamespacedKey key = new NamespacedKey((Plugin)Main.getInstance(), "saloon_door");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.WARPED_DOOR));
        recipe.shape(new String[]{"IW"});
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('W', Material.OAK_DOOR);
        Bukkit.addRecipe((Recipe)recipe);
    }

    private static void addDarkSaloonDoorCrafting() {
        NamespacedKey key = new NamespacedKey((Plugin)Main.getInstance(), "dark_saloon_door");
        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.CRIMSON_DOOR));
        recipe.shape(new String[]{"IW"});
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('W', Material.SPRUCE_DOOR);
        Bukkit.addRecipe((Recipe)recipe);
    }

    private static void addIronKnifeCrafting() {
        NamespacedKey key = new NamespacedKey((Plugin)Main.getInstance(), "iron_knife");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemAPI.knifeItem());
        recipe.shape(new String[]{"I", "S"});
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe((Recipe)recipe);
    }

    private static void addBattleAxeCrafting() {
        NamespacedKey key = new NamespacedKey((Plugin)Main.getInstance(), "battle_axe");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemAPI.battleAxeItem());
        recipe.shape(new String[]{"NI*", "CS*", "*S*"});
        ItemAPI.battleAxe(recipe);
        ItemAPI.alternativeBattleAxeCrafting();
    }

    private static void alternativeBattleAxeCrafting() {
        NamespacedKey key = new NamespacedKey((Plugin)Main.getInstance(), "battle_axe_2");
        ShapedRecipe recipe = new ShapedRecipe(key, ItemAPI.battleAxeItem());
        recipe.shape(new String[]{"IN*", "CS*", "*S*"});
        ItemAPI.battleAxe(recipe);
    }

    public static void blockCrafting(PrepareItemCraftEvent e) {
        e.getInventory().setResult(new ItemStack(Material.AIR));
        for (HumanEntity p : e.getViewers()) {
            if (!(p instanceof Player)) continue;
            API.sendErrorMessage((CommandSender)((Player)p), "\u00a7cDu kannst das so nicht bekommen\u00a78!");
        }
    }

    private static void battleAxe(ShapedRecipe recipe) {
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('C', Material.COPPER_INGOT);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('*', Material.AIR);
        Bukkit.addRecipe((Recipe)recipe);
    }

    public static void fillChestInventory(Inventory inv, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6, ItemStack i7, ItemStack i8, ItemStack i9, ItemStack i10, ItemStack i11, ItemStack i12, ItemStack i13, ItemStack i14, ItemStack i15, ItemStack i16, ItemStack i17, ItemStack i18) {
        inv.setItem(0, i1);
        inv.setItem(1, i2);
        inv.setItem(2, i3);
        inv.setItem(3, i4);
        inv.setItem(4, i5);
        inv.setItem(5, i6);
        inv.setItem(6, i7);
        inv.setItem(7, i8);
        inv.setItem(8, i9);
        inv.setItem(9, i10);
        inv.setItem(10, i11);
        inv.setItem(11, i12);
        inv.setItem(12, i13);
        inv.setItem(13, i14);
        inv.setItem(14, i15);
        inv.setItem(15, i16);
        inv.setItem(16, i17);
        inv.setItem(17, i18);
        inv.setItem(18, Placeholder);
        inv.setItem(19, Placeholder);
        inv.setItem(20, Placeholder);
        inv.setItem(21, Placeholder);
        inv.setItem(22, Close);
        inv.setItem(23, Placeholder);
        inv.setItem(24, Placeholder);
        inv.setItem(25, Placeholder);
        inv.setItem(26, Placeholder);
    }

    public static void fillShopInventory(Inventory inv, ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6, ItemStack i7, ItemStack i8, ItemStack i9) {
        inv.setItem(0, i1);
        inv.setItem(1, i2);
        inv.setItem(2, i3);
        inv.setItem(3, i4);
        inv.setItem(4, i5);
        inv.setItem(5, i6);
        inv.setItem(6, i7);
        inv.setItem(7, i8);
        inv.setItem(8, i9);
        inv.setItem(9, Placeholder);
        inv.setItem(10, Placeholder);
        inv.setItem(11, Placeholder);
        inv.setItem(12, Placeholder);
        inv.setItem(13, Close);
        inv.setItem(14, Placeholder);
        inv.setItem(15, Placeholder);
        inv.setItem(16, Placeholder);
        inv.setItem(17, Placeholder);
    }

    public static ItemStack RocketReward() {
        ItemStack Reward = ItemAPI.createItem(Material.FIREWORK_ROCKET, "\u00a7r\u00a7fRaketen", "\u00a78\u00a7m100 AP\u00a7r \u00a7950 AP");
        Reward.setAmount(64);
        return Reward;
    }

    public static ItemStack BottleReward() {
        ItemStack Reward = ItemAPI.createItem(Material.EXPERIENCE_BOTTLE, "XP-Flasche", "\u00a78\u00a7m500 AP\u00a7r \u00a79250 AP");
        Reward.setAmount(32);
        return Reward;
    }

    public static ItemStack CoalReward() {
        ItemStack Reward = ItemAPI.createItem(Material.COAL, "\u00a7fKohle\u00a7r \u00a7r", "\u00a78\u00a7m500 AP\u00a7r \u00a79250 AP");
        Reward.setAmount(32);
        return Reward;
    }

    public static ItemStack GoldReward() {
        ItemStack Reward = ItemAPI.createItem(Material.GOLD_INGOT, "\u00a76Gold", "\u00a78\u00a7m500 AP\u00a7r \u00a79250 AP");
        Reward.setAmount(8);
        return Reward;
    }

    public static ItemStack IronReward() {
        ItemStack Reward = ItemAPI.createItem(Material.IRON_INGOT, "\u00a77Eisen", "\u00a78\u00a7m600 AP\u00a7r \u00a79300");
        Reward.setAmount(8);
        return Reward;
    }

    public static ItemStack LapisReward() {
        ItemStack Reward = ItemAPI.createItem(Material.LAPIS_LAZULI, "\u00a71Lapis-Lazuli", "\u00a78\u00a7m700 AP\u00a7r \u00a79350");
        Reward.setAmount(16);
        return Reward;
    }

    public static ItemStack DiamondReward() {
        ItemStack Reward = ItemAPI.createItem(Material.DIAMOND, "\u00a7bDiamanten", "\u00a78\u00a7m900 AP\u00a7r \u00a79450");
        Reward.setAmount(2);
        return Reward;
    }

    public static ItemStack mainColorItem(Player player) {
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("0")) {
            return ItemAPI.createItem(Material.BLACK_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a70Schwarz");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("1")) {
            return ItemAPI.createItem(Material.BLUE_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a71Dunkelblau");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("2")) {
            return ItemAPI.createItem(Material.GREEN_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a72Dunkelgr\u00fcn");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("3")) {
            return ItemAPI.createItem(Material.CYAN_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a73Dunkelaqua");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("4")) {
            return ItemAPI.createItem(Material.RED_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a74Dunkelrot");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("5")) {
            return ItemAPI.createItem(Material.PURPLE_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a75Lila");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("6")) {
            return ItemAPI.createItem(Material.YELLOW_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a76Gold");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("7")) {
            return ItemAPI.createItem(Material.LIGHT_GRAY_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a77Hellgrau");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("8")) {
            return ItemAPI.createItem(Material.GRAY_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a78Dunkelgrau");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("9")) {
            return ItemAPI.createItem(Material.BLUE_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a79Blau");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("a")) {
            return ItemAPI.createItem(Material.LIME_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a7aHellgr\u00fcn");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("b")) {
            return ItemAPI.createItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a7bAqua");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("c")) {
            return ItemAPI.createItem(Material.RED_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a7cRot");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("d")) {
            return ItemAPI.createItem(Material.PINK_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a7dPink");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("e")) {
            return ItemAPI.createItem(Material.YELLOW_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a7eGelb");
        }
        if (ChatAPI.getMainColor(player).equalsIgnoreCase("f")) {
            return ItemAPI.createItem(Material.WHITE_TERRACOTTA, "\u00a77Hauptchatfarbe", "\u00a78-> \u00a7fWei\u00df");
        }
        return null;
    }

    public static ItemStack typeColorItem(Player player) {
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("0")) {
            return ItemAPI.createItem(Material.BLACK_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a70Schwarz");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("1")) {
            return ItemAPI.createItem(Material.BLUE_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a71Dunkelblau");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("2")) {
            return ItemAPI.createItem(Material.GREEN_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a72Dunkelgr\u00fcn");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("3")) {
            return ItemAPI.createItem(Material.CYAN_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a73Dunkelaqua");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("4")) {
            return ItemAPI.createItem(Material.RED_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a74Dunkelrot");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("5")) {
            return ItemAPI.createItem(Material.PURPLE_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a75Lila");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("6")) {
            return ItemAPI.createItem(Material.YELLOW_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a76Gold");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("7")) {
            return ItemAPI.createItem(Material.LIGHT_GRAY_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a77Hellgrau");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("8")) {
            return ItemAPI.createItem(Material.GRAY_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a78Dunkelgrau");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("9")) {
            return ItemAPI.createItem(Material.BLUE_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a79Blau");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("a")) {
            return ItemAPI.createItem(Material.LIME_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a7aHellgr\u00fcn");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("b")) {
            return ItemAPI.createItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a7bAqua");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("c")) {
            return ItemAPI.createItem(Material.RED_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a7cRot");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("d")) {
            return ItemAPI.createItem(Material.PINK_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a7dPink");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("e")) {
            return ItemAPI.createItem(Material.YELLOW_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a7eGelb");
        }
        if (ChatAPI.getTypeColor(player).equalsIgnoreCase("f")) {
            return ItemAPI.createItem(Material.WHITE_TERRACOTTA, "\u00a7eHervorhebungsfarbe 1", "\u00a78-> \u00a7fWei\u00df");
        }
        return null;
    }

    public static ItemStack userColorItem(Player player) {
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("0")) {
            return ItemAPI.createItem(Material.BLACK_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a70Schwarz");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("1")) {
            return ItemAPI.createItem(Material.BLUE_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a71Dunkelblau");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("2")) {
            return ItemAPI.createItem(Material.GREEN_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a72Dunkelgr\u00fcn");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("3")) {
            return ItemAPI.createItem(Material.CYAN_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a73Dunkelaqua");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("4")) {
            return ItemAPI.createItem(Material.RED_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a74Dunkelrot");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("5")) {
            return ItemAPI.createItem(Material.PURPLE_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a75Lila");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("6")) {
            return ItemAPI.createItem(Material.YELLOW_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a76Gold");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("7")) {
            return ItemAPI.createItem(Material.LIGHT_GRAY_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a77Hellgrau");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("8")) {
            return ItemAPI.createItem(Material.GRAY_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a78Dunkelgrau");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("9")) {
            return ItemAPI.createItem(Material.BLUE_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a79Blau");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("a")) {
            return ItemAPI.createItem(Material.LIME_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a7aHellgr\u00fcn");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("b")) {
            return ItemAPI.createItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a7bAqua");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("c")) {
            return ItemAPI.createItem(Material.RED_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a7cRot");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("d")) {
            return ItemAPI.createItem(Material.PINK_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a7dPink");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("e")) {
            return ItemAPI.createItem(Material.YELLOW_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a7eGelb");
        }
        if (ChatAPI.getUserColor(player).equalsIgnoreCase("f")) {
            return ItemAPI.createItem(Material.WHITE_TERRACOTTA, "\u00a7aHervorhebungsfarbe 2", "\u00a78-> \u00a7fWei\u00df");
        }
        return null;
    }

    public static ItemStack knifeItem() {
        ItemStack knife = new ItemStack(Material.STONE_SWORD);
        ItemMeta knifeMeta = knife.getItemMeta();
        knifeMeta.setDisplayName("\u00a7rMesser");
        knifeMeta.setCustomModelData(Integer.valueOf(1));
        knife.setItemMeta(knifeMeta);
        return knife;
    }

    public static ItemStack battleAxeItem() {
        ItemStack battleAxe = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta battleAxeMeta = battleAxe.getItemMeta();
        battleAxeMeta.setDisplayName("\u00a7rBeil");
        battleAxeMeta.setCustomModelData(Integer.valueOf(1));
        battleAxe.setItemMeta(battleAxeMeta);
        return battleAxe;
    }

    public static ItemStack shotgunItem() {
        ItemStack shotgun = new ItemStack(Material.CROSSBOW);
        ItemMeta shotgunMeta = shotgun.getItemMeta();
        shotgunMeta.setDisplayName("\u00a7rSchrotflinte");
        shotgunMeta.setCustomModelData(Integer.valueOf(1));
        shotgunMeta.addEnchant(Enchantment.MULTISHOT, 1, false);
        shotgunMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE});
        shotgun.setItemMeta(shotgunMeta);
        return shotgun;
    }
}

