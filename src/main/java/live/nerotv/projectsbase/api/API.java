
package live.nerotv.projectsbase.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.FilterAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class API {
    public static PluginManager PM = Main.PM;
    static File Config = ConfigAPI.Config;
    static YamlConfiguration cfg = ConfigAPI.CFG;
    public static String Prefix = API.PN() + " \u00a78| \u00a77";
    public static String noPlayer = "\u00a7cDieser Spieler ist nicht zu finden!";
    public static String noPerms = "\u00a7cDazu bist du nicht berechtigt!";
    public static String noMoney = "\u00a7cDazu hast du nicht genug AP!";
    public static String needPlayer = "\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!";
    public static boolean maintenance = false;
    public static ArrayList<Player> protectedPlayers = new ArrayList();
    public static boolean isStopping = false;
    public static int RestartDay = API.getYearDay() + 1;

    public static String PN() {
        if (cfg.getString("Core.Strings.Projektname") == null) {
            return "Unbekannt";
        }
        return cfg.getString("Core.Strings.Projektname");
    }

    public static void clearPlayerChat(Player p) {
        for (int i = 0; i < 150; ++i) {
            p.sendMessage("\u00a70");
        }
    }

    public static void checkForRestart() {
        if (API.getYearDay() == RestartDay && !isStopping) {
            ServerAPI.scheduledShutdown();
        }
    }

    public static void initCommand(String commandName, CommandExecutor command) {
        ServerAPI.sendConsoleMessage("\u00a7f  -> \u00a77Lade Command \"\u00a7e" + commandName + "\u00a77\"...");
        Main.getInstance().getCommand(commandName).setExecutor(command);
    }

    public static void initListeners(Listener listener) {
        ServerAPI.sendConsoleMessage("\u00a7f  -> \u00a77Lade Listener in der Klasse \"\u00a7e" + listener.getClass().getSimpleName() + "\u00a77\"...");
        PM.registerEvents(listener, (Plugin)Main.getInstance());
    }

    public static void checkConfig() {
        ConfigAPI.reloadConfig(Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.Projektname", "Projekt", Config, cfg);
        StaticManager.projectName = cfg.getString("Core.Strings.Projektname");
        ConfigAPI.checkEntry("Core.Strings.Projektleitung", "Unbekannt", Config, cfg);
        StaticManager.projectLeading = cfg.getString("Core.Strings.Projektleitung");
        ConfigAPI.checkEntry("Core.Strings.Projektart", "Unbekannt", Config, cfg);
        StaticManager.projectTheme = cfg.getString("Core.Strings.Projektart");
        ConfigAPI.checkEntry("Core.Strings.Projektversion", "1.0 Alpha", Config, cfg);
        StaticManager.projectVersion = cfg.getString("Core.Strings.Projektversion");
        ConfigAPI.checkEntry("Core.Strings.Projektregeln", "https:", Config, cfg);
        StaticManager.projectRules = cfg.getString("Core.Strings.Projektregeln");
        ConfigAPI.checkEntry("Core.Arrays.BlockedStrings.General", FilterAPI.blockedStrings, Config, cfg);
        ConfigAPI.checkEntry("Core.Arrays.BlockedStrings.Names", FilterAPI.blockedNames, Config, cfg);
        ConfigAPI.checkEntry("Core.Arrays.BlockedStrings.Jobs", FilterAPI.blockedJobs, Config, cfg);
        FilterAPI.blockedStrings = (ArrayList)cfg.getList("Core.Arrays.BlockedStrings.General");
        FilterAPI.blockedNames = (ArrayList)cfg.getList("Core.Arrays.BlockedStrings.Names");
        FilterAPI.blockedJobs = (ArrayList)cfg.getList("Core.Arrays.BlockedStrings.Jobs");
        ConfigAPI.checkEntry("Core.Strings.HelpLine01", "1", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpLine02", "2", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpLine03", "3", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpLine04", "4", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpLine05", "5", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.Tablist.Image", "https:",Config, cfg);
        StaticManager.tablistImage = cfg.getString("Core.Strings.Tablist.Image");
        ConfigAPI.checkEntry("Core.Strings.Tablist.Header", "Header", Config, cfg);
        StaticManager.tablistHeader = cfg.getString("Core.Strings.Tablist.Header");
        ConfigAPI.checkEntry("Core.Strings.Tablist.Footer", "Footer", Config, cfg);
        StaticManager.tablistFooter = cfg.getString("Core.Strings.Tablist.Footer");
        ConfigAPI.checkEntry("Core.Strings.HelpTeam101", "\u00a78=================================================", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam102", "1", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam103", "2", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam104", "3", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam105", "4", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam106", "5", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam107", "6", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam108", "7", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam109", "8", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam110", "\u00a78=================================================", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam201", "\u00a78=================================================", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam202", "1", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam203", "2", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam204", "3", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam205", "4", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam206", "5", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam207", "6", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam208", "7", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam209", "8", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam210", "\u00a78=================================================", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam301", "\u00a78=================================================", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam302", "1", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam303", "2", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam304", "3", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam305", "4", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam306", "5", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam307", "6", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam308", "7", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam309", "8", Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.HelpTeam310", "\u00a78=================================================", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.StartMenu01", "Stadt 1\u00a7r", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.StartMenu02", "Stadt 2\u00a7f", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.StartMenu03", "Sonstiges \u00a77(Farmwelt...)", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.Sub1Menu01", "Warp 1\u00a74", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.Sub1Menu02", "Warp 2\u00a74", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.Sub2Menu01", "Warp 1\u00a71", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.Sub2Menu02", "Warp 2\u00a71", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.Sub3Menu01", "\u00a7eFarmwelt", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Names.Sub3Menu02", "\u00a7cNether", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.StartMenu01", "DIAMOND", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.StartMenu02", "EMERALD", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.StartMenu03", "QUARTZ", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.Sub1Menu01", "DIAMOND_HORSE_ARMOR", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.Sub1Menu02", "IRON_HORSE_ARMOR", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.Sub2Menu01", "DIAMOND_HORSE_ARMOR", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.Sub2Menu02", "IRON_HORSE_ARMOR", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.Sub3Menu01", "IRON_PICKAXE", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Items.Sub3Menu02", "NETHERITE_INGOT", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Warps.Sub1Menu01", "Warp_01", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Warps.Sub1Menu02", "Warp_02", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Warps.Sub2Menu01", "Warp_03", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Warps.Sub2Menu02", "Warp_04", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Warps.Sub3Menu01", "Warp_05", Config, cfg);
        ConfigAPI.checkEntry("Core.WarpGUI.Warps.Sub3Menu02", "Warp_06", Config, cfg);
        ConfigAPI.checkEntry("Core.Init.Line01", "\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2557", Config, cfg);
        ConfigAPI.checkEntry("Core.Init.Line02", "\u00a78\u255a\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u255d\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u255d\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u2550\u00a78\u2550\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551", Config, cfg);
        ConfigAPI.checkEntry("Core.Init.Line03", "\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u255d\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a75\u2588\u00a75\u2588\u00a78\u2551", Config, cfg);
        ConfigAPI.checkEntry("Core.Init.Line04", "\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u2550\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u2591\u00a78\u2591\u00a78\u2591\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u255d\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u2550\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2551", Config, cfg);
        ConfigAPI.checkEntry("Core.Init.Line05", "\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u2591\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u2591\u00a78\u2591\u00a78\u2591\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u2591\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2557\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2554\u00a78\u255d\u00a75\u2588\u00a75\u2588\u00a78\u2551\u00a78\u2591\u00a78\u255a\u00a75\u2588\u00a75\u2588\u00a75\u2588\u00a78\u2551", Config, cfg);
        ConfigAPI.checkEntry("Core.Init.Line06", "\u00a78\u255a\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u2591\u00a78\u2591\u00a78\u255a\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u2591\u00a78\u2591\u00a78\u255a\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u2591\u00a78\u255a\u00a78\u2550\u00a78\u2550\u00a78\u255d\u00a78\u255a\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u255a\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u255a\u00a78\u2550\u00a78\u255d\u00a78\u2591\u00a78\u2591\u00a78\u255a\u00a78\u2550\u00a78\u2550\u00a78\u255d", Config, cfg);
        ConfigAPI.checkEntry("Core.Settings.Commands.Buch", false, Config, cfg);
        StaticManager.bookCommand = cfg.getBoolean("Core.Settings.Buch");
        ConfigAPI.checkEntry("Core.Settings.RestrictedNether", false, Config, cfg);
        StaticManager.restrictedNether = cfg.getBoolean("Core.Settings.RestrictedNether");
        ConfigAPI.checkEntry("Core.Settings.Death.Chest", false, Config, cfg);
        StaticManager.deathChest = cfg.getBoolean("Core.Settings.Death.Chest");
        ConfigAPI.checkEntry("Core.Settings.DailyRewardAP.Enable", false, Config, cfg);
        StaticManager.dailyReward = cfg.getBoolean("Core.Settings.DailyRewardAP.Enable");
        ConfigAPI.checkEntry("Core.Settings.DailyRewardAP.FirstLogin", 120, Config, cfg);
        StaticManager.rewardFirst = cfg.getInt("Core.Settings.DailyRewardAP.FirstLogin");
        ConfigAPI.checkEntry("Core.Settings.DailyRewardAP.DefaultLogin", 60, Config, cfg);
        StaticManager.rewardDaily = cfg.getInt("Core.Settings.DailyRewardAP.DefaultLogin");
        ConfigAPI.checkEntry("Core.Settings.RequiredRulesLevel", 1, Config, cfg);
        StaticManager.requiredRulesLevel = cfg.getInt("Core.Settings.RequiredRulesLevel");
        ConfigAPI.checkEntry("Core.Settings.Farmwelt.Enable", false, Config, cfg);
        StaticManager.farmworld = cfg.getBoolean("Core.Settings.Farmwelt.Enable");
        ConfigAPI.checkEntry("Core.Settings.Commands.Shop.Enable", false, Config, cfg);
        StaticManager.shopCommand = cfg.getBoolean("Core.Settings.Commands.Shop.Enable");
        ConfigAPI.checkEntry("Core.Settings.Commands.Shop.MarkReward", false, Config, cfg);
        StaticManager.shopEconomy = cfg.getBoolean("Core.Settings.Commands.Shop.MarkReward");
        ConfigAPI.checkEntry("Core.Settings.Farmwelt.Name", "FW1", Config, cfg);
        StaticManager.farmworldName = cfg.getString("Core.Settings.Farmwelt.Name");
        ConfigAPI.checkEntry("Core.Settings.Difficulty", "NORMAL", Config, cfg);
        StaticManager.difficulty = cfg.getString("Core.Settings.Difficulty");
        ConfigAPI.checkEntry("Core.Settings.Maintenance", false, Config, cfg);
        maintenance = cfg.getBoolean("Core.Settings.Maintenance");
        ConfigAPI.checkEntry("Core.Settings.Commands.Check.showIP", true, Config, cfg);
        StaticManager.checkIP = cfg.getBoolean("Core.Settings.Commands.Check.ShowIP");
        ConfigAPI.checkEntry("Core.Settings.BedrockWarning.Enable", true, Config, cfg);
        StaticManager.bedrockWarning = cfg.getBoolean("Core.Settings.BedrockWarning.Enable");
        ConfigAPI.checkEntry("Core.Settings.BedrockWarning.Title", "Hallo", Config, cfg);
        StaticManager.bedrockWarnTitle = "\u00a7c" + cfg.getString("Core.Settings.BedrockWarning.Title");
        ConfigAPI.checkEntry("Core.Settings.BedrockWarning.Message", "Du nutzt Bedrock!", Config, cfg);
        StaticManager.bedrockWarnMessage = "\u00a77" + cfg.getString("Core.Settings.BedrockWarning.Message");
        ConfigAPI.checkEntry("Core.Settings.Projectiles.Break.Glass", false, Config, cfg);
        StaticManager.projectileGlassBreak = cfg.getBoolean("Core.Settings.Projectiles.Break.Glass");
        ConfigAPI.checkEntry("Core.Settings.CustomItems.IRON_KNIFE", false, Config, cfg);
        ConfigAPI.checkEntry("Core.Settings.CustomItems.BATTLE_AXE", false, Config, cfg);
        ConfigAPI.checkEntry("Core.Settings.CustomItems.SALOON_DOOR", false, Config, cfg);
        ConfigAPI.checkEntry("Core.Settings.CustomItems.DARK_SALOON_DOOR", false, Config, cfg);
        ConfigAPI.saveConfig(Config, cfg);
    }

    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy.HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static boolean isSpeedCompatible(String Check2) {
        if (API.isNumeric(Check2)) {
            int i = Integer.parseInt(Check2);
            return i >= 0 && i <= 9;
        }
        return false;
    }

    public static String getTime() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        return format.format(now);
    }

    public static int getYearDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(6);
    }

    public static int getMonth() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return Integer.valueOf(format.format(now).replace("0", ""));
    }

    public static int getYear() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return Integer.valueOf(format.format(now));
    }

    public static void switchServer(Player player, String serverName) {
        ServerAPI.sendConsoleMessage("switchServer(" + player.getName() + "," + serverName + ")");
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArray);
            out.writeUTF("Connect");
            out.writeUTF(serverName);
            player.sendPluginMessage((Plugin)Main.getInstance(), "BungeeCord", byteArray.toByteArray());
        }
        catch (Exception ex) {
            ServerAPI.sendConsoleMessage("\u00a7cEin Fehler ist beim Senden von \u00a74" + player.getName() + "\u00a7c zu \u00a74" + serverName + "\u00a7c aufgetreten.");
        }
    }

    public static void dispatchConsoleCommand(String Command) {
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)Command);
    }

    public static void sendInit() {
        ConfigAPI.reloadConfig(Config, cfg);
        ServerAPI.sendConsoleMessage(Objects.requireNonNull(cfg.getString("Core.Init.Line01")));
        ServerAPI.sendConsoleMessage(Objects.requireNonNull(cfg.getString("Core.Init.Line02")));
        ServerAPI.sendConsoleMessage(Objects.requireNonNull(cfg.getString("Core.Init.Line03")));
        ServerAPI.sendConsoleMessage(Objects.requireNonNull(cfg.getString("Core.Init.Line04")));
        ServerAPI.sendConsoleMessage(Objects.requireNonNull(cfg.getString("Core.Init.Line05")));
        ServerAPI.sendConsoleMessage(Objects.requireNonNull(cfg.getString("Core.Init.Line06")));
        ServerAPI.sendConsoleMessage("\u00a77Plugin \u00a7aProjectsBase (v" + Main.Version + ") by \u00a7cnerotvlive\u00a77!");
    }

    public static void sendMessage(CommandSender s, String msg) {
        if (!(s instanceof Player)) {
            s.sendMessage(Prefix + msg.replace("&", "\u00a7"));
        } else {
            Player p = (Player)s;
            PlayerAPI.sendPlayerMessage(p, Prefix + msg.replace("&", "\u00a7"), NewSound.ENTITY_CHICKEN_EGG);
        }
    }

    public static void sendErrorMessage(CommandSender s, String Message) {
        if (s instanceof Player) {
            Player p = (Player)s;
            PlayerAPI.sendPlayerMessage(p, Message, NewSound.BLOCK_ANVIL_BREAK);
        } else {
            s.sendMessage(Message);
        }
    }

    public static boolean isNumericPart(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String Check2) {
        if (API.isNumericPart(Check2)) {
            return !(Double.parseDouble(Check2) > 9.99999998E8);
        }
        return false;
    }

    public static boolean hasAvailableSlot(Player player) {
        return !API.invFull(player);
    }

    public static boolean invFull(Player p) {
        return !Arrays.asList(p.getInventory().getStorageContents()).contains(null);
    }

    public static void changeGamemode(Player p, String GameMode2) {
        if (GameMode2.equalsIgnoreCase("0")) {
            p.setGameMode(GameMode.SURVIVAL);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        } else if (GameMode2.equalsIgnoreCase("1")) {
            p.setGameMode(GameMode.CREATIVE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        } else if (GameMode2.equalsIgnoreCase("2")) {
            p.setGameMode(GameMode.ADVENTURE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        } else if (GameMode2.equalsIgnoreCase("3")) {
            p.setGameMode(GameMode.SPECTATOR);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        } else if (GameMode2.equalsIgnoreCase("SURVIVAL")) {
            p.setGameMode(GameMode.SURVIVAL);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        } else if (GameMode2.equalsIgnoreCase("CREATIVE")) {
            p.setGameMode(GameMode.CREATIVE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        } else if (GameMode2.equalsIgnoreCase("ADVENTURE")) {
            p.setGameMode(GameMode.ADVENTURE);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        } else if (GameMode2.equalsIgnoreCase("SPECTATOR")) {
            p.setGameMode(GameMode.SPECTATOR);
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
            API.sendMessage((CommandSender)p, "Du bist nun im " + API.getGamemode(p) + "\u00a77!");
        }
    }

    public static String getGamemode(Player p) {
        String GameMode2 = "";
        GameMode2 = p != null ? (p.getGameMode().toString().equals("SURVIVAL") ? "\u00a7a\u00dcberlebensmodus" : (p.getGameMode().toString().equals("CREATIVE") ? "\u00a7aKreativmodus" : (p.getGameMode().toString().equals("ADVENTURE") ? "\u00a7aAbenteuermodus" : (p.getGameMode().toString().equals("SPECTATOR") ? "\u00a7aZuschauermodus" : "Nothing")))) : "Nothing";
        return GameMode2;
    }

    public static void enableName(String Name2) {
        File NameFile = new File("plugins/Zyneon/Names/" + Name2 + ".yml");
        YamlConfiguration NF = YamlConfiguration.loadConfiguration((File)NameFile);
        NameFile.delete();
    }

    public static void disableName(String Name2) {
        File NameFile = new File("plugins/Zyneon/Names/" + Name2 + ".yml");
        YamlConfiguration NF = YamlConfiguration.loadConfiguration((File)NameFile);
        NF.set("Name.isEnabled", (Object)false);
        ConfigAPI.saveConfig(NameFile, NF);
    }

    public static boolean isNameEnabled(String Name2) {
        return true;
    }

    public static void setNameOwner(String Name2, String SID) {
        File NameFile = new File("plugins/Zyneon/Names/" + Name2 + ".yml");
        YamlConfiguration NF = YamlConfiguration.loadConfiguration((File)NameFile);
        NF.set("Name.Owner", (Object)SID);
        ConfigAPI.saveConfig(NameFile, NF);
    }

    public static String getNameOwner(String Name2) {
        File NameFile = new File("plugins/Zyneon/Names/" + Name2 + ".yml");
        YamlConfiguration NF = YamlConfiguration.loadConfiguration((File)NameFile);
        if (NameFile.exists()) {
            if (NF.contains("Name.Owner")) {
                return NF.getString("Name.Owner");
            }
            return null;
        }
        return null;
    }
}

