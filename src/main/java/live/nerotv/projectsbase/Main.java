
package live.nerotv.projectsbase;

import java.io.File;
import live.nerotv.Preloader;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import live.nerotv.projectsbase.api.WorldAPI;
import live.nerotv.projectsbase.commands.AchievementPoints;
import live.nerotv.projectsbase.commands.Achievements;
import live.nerotv.projectsbase.commands.Author;
import live.nerotv.projectsbase.commands.Autoclose;
import live.nerotv.projectsbase.commands.Back;
import live.nerotv.projectsbase.commands.Brieftasche;
import live.nerotv.projectsbase.commands.Broadcast;
import live.nerotv.projectsbase.commands.Buch;
import live.nerotv.projectsbase.commands.ChangeMap;
import live.nerotv.projectsbase.commands.Check;
import live.nerotv.projectsbase.commands.Clearchat;
import live.nerotv.projectsbase.commands.CustomModelData;
import live.nerotv.projectsbase.commands.Day;
import live.nerotv.projectsbase.commands.Difficulty;
import live.nerotv.projectsbase.commands.Disconnect;
import live.nerotv.projectsbase.commands.DiscordLink;
import live.nerotv.projectsbase.commands.Enderchest;
import live.nerotv.projectsbase.commands.Feed;
import live.nerotv.projectsbase.commands.Fly;
import live.nerotv.projectsbase.commands.Force;
import live.nerotv.projectsbase.commands.Gamemode;
import live.nerotv.projectsbase.commands.God;
import live.nerotv.projectsbase.commands.Heal;
import live.nerotv.projectsbase.commands.Help;
import live.nerotv.projectsbase.commands.INF;
import live.nerotv.projectsbase.commands.Invsee;
import live.nerotv.projectsbase.commands.Item;
import live.nerotv.projectsbase.commands.Job;
import live.nerotv.projectsbase.commands.Kill;
import live.nerotv.projectsbase.commands.Maintenance;
import live.nerotv.projectsbase.commands.Me;
import live.nerotv.projectsbase.commands.Name;
import live.nerotv.projectsbase.commands.Night;
import live.nerotv.projectsbase.commands.Ping;
import live.nerotv.projectsbase.commands.Rain;
import live.nerotv.projectsbase.commands.RedstoneLock;
import live.nerotv.projectsbase.commands.Roleplay;
import live.nerotv.projectsbase.commands.Rucksack;
import live.nerotv.projectsbase.commands.Rules;
import live.nerotv.projectsbase.commands.SRL;
import live.nerotv.projectsbase.commands.Say;
import live.nerotv.projectsbase.commands.Settings;
import live.nerotv.projectsbase.commands.Shop;
import live.nerotv.projectsbase.commands.Shout;
import live.nerotv.projectsbase.commands.Speed;
import live.nerotv.projectsbase.commands.Sudo;
import live.nerotv.projectsbase.commands.Sun;
import live.nerotv.projectsbase.commands.Team;
import live.nerotv.projectsbase.commands.Teleport;
import live.nerotv.projectsbase.commands.Tell;
import live.nerotv.projectsbase.commands.Thunder;
import live.nerotv.projectsbase.commands.Time;
import live.nerotv.projectsbase.commands.Vanish;
import live.nerotv.projectsbase.commands.Warp;
import live.nerotv.projectsbase.commands.Weather;
import live.nerotv.projectsbase.commands.Whisper;
import live.nerotv.projectsbase.commands.World;
import live.nerotv.projectsbase.listener.PlayerCommand;
import live.nerotv.projectsbase.listener.PlayerCraft;
import live.nerotv.projectsbase.listener.PlayerDamage;
import live.nerotv.projectsbase.listener.PlayerInteract;
import live.nerotv.projectsbase.listener.PlayerInventory;
import live.nerotv.projectsbase.listener.PlayerJoin;
import live.nerotv.projectsbase.listener.PlayerQuit;
import live.nerotv.projectsbase.listener.ProjectileHit;
import live.nerotv.projectsbase.listener.WorldChange;
import live.nerotv.projectsbase.listener.WorldSignChange;
import live.nerotv.projectsbase.manager.BroadcastManager;
import live.nerotv.projectsbase.manager.ChatManager;
import live.nerotv.projectsbase.manager.DeathChestManager;
import live.nerotv.projectsbase.manager.StaticManager;
import live.nerotv.projectsbase.modules.ModuleLoader;
import live.nerotv.projectsbase.utils.Receiver;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;

public class Main {
    private static Preloader instance;
    public static Scoreboard State;
    public static PluginManager PM;
    public static String Version;

    public static Preloader getInstance() {
        return instance;
    }

    public static void onLoad() {
        Version = Preloader.getVersion();
        API.checkConfig();
        API.sendInit();
        ServerAPI.sendConsoleMessage("\u00a70");
        ServerAPI.sendConsoleMessage("\u00a70");
        ServerAPI.sendConsoleMessage("\u00a77Die \u00a7fProjectsBase\u00a77 wird \u00a7egeladen\u00a77...");
        ModuleLoader.loadModules();
        ServerAPI.sendConsoleMessage("\u00a70");
        ServerAPI.sendConsoleMessage("\u00a70");
        API.sendInit();
    }

    public static void onEnable() {
        instance = Preloader.getInstance();
        if (ServerAPI.isLegacy().booleanValue()) {
            Main.onDisable();
        } else {
            API.checkConfig();
            Main.initPrioritised();
            for (Player all : Bukkit.getOnlinePlayers()) {
                Main.setState(all);
            }
            ModuleLoader.enableModules();
            ServerAPI.sendConsoleMessage("\u00a77Die \u00a7fProjectsBase\u00a77 (v" + Version + ") wurde \u00a7aaktiviert\u00a77!");
            API.sendInit();
            if (StaticManager.restrictedNether) {
                WorldAPI.createNetherWorld("FWN");
            }
            if (StaticManager.farmworld) {
                WorldAPI.createFarmworld(StaticManager.farmworldName);
                File FarmFile = new File("plugins/Zyneon/Warps/farmwelt.yml");
                YamlConfiguration FF = YamlConfiguration.loadConfiguration((File)FarmFile);
                FF.set("Warp.World", (Object)ConfigAPI.CFG.getString("Core.Settings.Farmwelt.Name"));
                ConfigAPI.saveConfig(FarmFile, FF);
            }
        }
        if (ConfigAPI.CFG.getBoolean("Core.Settings.Maintenance")) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission("zyneon.bypassmaintenance")) {
                    all.sendMessage("\u00a7cDer Server befindet sich momentan in \u00a74Wartungsarbeiten\u00a7c!");
                    continue;
                }
                all.kickPlayer("\u00a7cDer Server befindet sich nun in \u00a74Wartungsarbeiten\u00a7c!");
            }
        }
    }

    public static void onDisable() {
        WorldChange.started = false;
        API.sendInit();
        for (Player all : Bukkit.getOnlinePlayers()) {
            PlayerAPI.saveLocation(all);
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)"save-all");
            API.switchServer(all, "Lobby-1");
        }
        ModuleLoader.disableModules();
        Bukkit.getServer().getMessenger().unregisterIncomingPluginChannel((Plugin)instance, "base:bungee", (PluginMessageListener)new Receiver());
        Bukkit.getServer().getMessenger().unregisterIncomingPluginChannel((Plugin)instance, "labymod3:main", (PluginMessageListener)new Receiver());
        Bukkit.getServer().getMessenger().unregisterOutgoingPluginChannel((Plugin)instance, "BungeeCord");
        Bukkit.getServer().getMessenger().unregisterOutgoingPluginChannel((Plugin)instance, "base:spigot");
        ServerAPI.sendConsoleMessage("\u00a77Die \u00a7fProjectsBase\u00a77 wurde \u00a7cdeaktiviert\u00a77!");
        ServerAPI.sendConsoleMessage("\u00a7a");
        API.sendInit();
        instance = null;
        Version = null;
        State = null;
        PM = null;
    }

    private static void initPrioritised() {
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel((Plugin)instance, "base:bungee", (PluginMessageListener)new Receiver());
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)instance, "base:spigot");
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)instance, "BungeeCord");
        ItemAPI.addCraftings();
        API.sendInit();
        State = Bukkit.getScoreboardManager().getNewScoreboard();
        State.registerNewTeam("0RP0");
        State.registerNewTeam("1NP0");
        State.registerNewTeam("0RP1");
        State.registerNewTeam("1NP1");
        State.registerNewTeam("0RP2");
        State.registerNewTeam("1NP2");
        State.registerNewTeam("0RP3");
        State.registerNewTeam("1NP3");
        State.getTeam("0RP0").setSuffix("\u00a78 \u25cf \u00a7eRP");
        State.getTeam("1NP0").setSuffix("\u00a78 \u25cf \u00a77Off-RP");
        State.getTeam("0RP0").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("1NP0").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("0RP0").setCanSeeFriendlyInvisibles(false);
        State.getTeam("1NP0").setCanSeeFriendlyInvisibles(false);
        State.getTeam("0RP1").setSuffix("\u00a78 \u25cf \u00a7eRP");
        State.getTeam("1NP1").setSuffix("\u00a78 \u25cf \u00a77Off-RP");
        State.getTeam("0RP1").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("1NP1").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("0RP1").setCanSeeFriendlyInvisibles(false);
        State.getTeam("1NP1").setCanSeeFriendlyInvisibles(false);
        State.getTeam("0RP2").setSuffix("\u00a78 \u25cf \u00a7eRP");
        State.getTeam("1NP2").setSuffix("\u00a78 \u25cf \u00a77Off-RP");
        State.getTeam("0RP2").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("1NP2").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("0RP2").setCanSeeFriendlyInvisibles(false);
        State.getTeam("1NP2").setCanSeeFriendlyInvisibles(false);
        State.getTeam("0RP3").setSuffix("\u00a78 \u25cf \u00a7eRP");
        State.getTeam("1NP3").setSuffix("\u00a78 \u25cf \u00a77Off-RP");
        State.getTeam("0RP3").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("1NP3").setNameTagVisibility(NameTagVisibility.NEVER);
        State.getTeam("0RP3").setCanSeeFriendlyInvisibles(false);
        State.getTeam("1NP3").setCanSeeFriendlyInvisibles(false);
        Main.initCommands();
        BroadcastManager.start();
    }

    private static void initCommands() {
        ServerAPI.sendConsoleMessage("\u00a7fLade Commands...");
        Main.CommandInitMessage("AchievementPoints");
        instance.getCommand("AchievementPoints").setExecutor((CommandExecutor)new AchievementPoints());
        Main.CommandInitMessage("Roleplay");
        instance.getCommand("Roleplay").setExecutor((CommandExecutor)new Roleplay());
        Main.CommandInitMessage("Tell");
        instance.getCommand("Tell").setExecutor((CommandExecutor)new Tell());
        Main.CommandInitMessage("Author");
        instance.getCommand("Author").setExecutor((CommandExecutor)new Author());
        Main.CommandInitMessage("Shop");
        instance.getCommand("Shop").setExecutor((CommandExecutor)new Shop());
        Main.CommandInitMessage("Gamemode");
        instance.getCommand("Gamemode").setExecutor((CommandExecutor)new Gamemode());
        Main.CommandInitMessage("Warp");
        instance.getCommand("Warp").setExecutor((CommandExecutor)new Warp());
        Main.CommandInitMessage("Teleport");
        instance.getCommand("Teleport").setExecutor((CommandExecutor)new Teleport());
        Main.CommandInitMessage("SRL");
        instance.getCommand("SRL").setExecutor((CommandExecutor)new SRL());
        Main.CommandInitMessage("Heal");
        instance.getCommand("Heal").setExecutor((CommandExecutor)new Heal());
        Main.CommandInitMessage("Kill");
        instance.getCommand("Kill").setExecutor((CommandExecutor)new Kill());
        Main.CommandInitMessage("Feed");
        instance.getCommand("Feed").setExecutor((CommandExecutor)new Feed());
        Main.CommandInitMessage("Clearchat");
        instance.getCommand("Clearchat").setExecutor((CommandExecutor)new Clearchat());
        Main.CommandInitMessage("Sudo");
        instance.getCommand("Sudo").setExecutor((CommandExecutor)new Sudo());
        Main.CommandInitMessage("Time");
        instance.getCommand("Time").setExecutor((CommandExecutor)new Time());
        Main.CommandInitMessage("Day");
        instance.getCommand("Day").setExecutor((CommandExecutor)new Day());
        Main.CommandInitMessage("Night");
        instance.getCommand("Night").setExecutor((CommandExecutor)new Night());
        Main.CommandInitMessage("Broadcast");
        instance.getCommand("Broadcast").setExecutor((CommandExecutor)new Broadcast());
        Main.CommandInitMessage("Help");
        instance.getCommand("?").setExecutor((CommandExecutor)new Help());
        Main.CommandInitMessage("Job");
        instance.getCommand("Job").setExecutor((CommandExecutor)new Job());
        Main.CommandInitMessage("Name");
        instance.getCommand("Name").setExecutor((CommandExecutor)new Name());
        Main.CommandInitMessage("Difficulty");
        instance.getCommand("Difficulty").setExecutor((CommandExecutor)new Difficulty());
        Main.CommandInitMessage("Settings");
        instance.getCommand("Settings").setExecutor((CommandExecutor)new Settings());
        Main.CommandInitMessage("Rucksack");
        instance.getCommand("Rucksack").setExecutor((CommandExecutor)new Rucksack());
        Main.CommandInitMessage("Fly");
        instance.getCommand("Fly").setExecutor((CommandExecutor)new Fly());
        Main.CommandInitMessage("Weather");
        instance.getCommand("Weather").setExecutor((CommandExecutor)new Weather());
        Main.CommandInitMessage("Rain");
        instance.getCommand("Rain").setExecutor((CommandExecutor)new Rain());
        Main.CommandInitMessage("Sun");
        instance.getCommand("Sun").setExecutor((CommandExecutor)new Sun());
        Main.CommandInitMessage("Thunder");
        instance.getCommand("Thunder").setExecutor((CommandExecutor)new Thunder());
        Main.CommandInitMessage("Say");
        instance.getCommand("Say").setExecutor((CommandExecutor)new Say());
        Main.CommandInitMessage("Do");
        instance.getCommand("Me").setExecutor((CommandExecutor)new Me());
        Main.CommandInitMessage("Buch");
        instance.getCommand("Buch").setExecutor((CommandExecutor)new Buch());
        Main.CommandInitMessage("Disconnect");
        instance.getCommand("Disconnect").setExecutor((CommandExecutor)new Disconnect());
        Main.CommandInitMessage("Maintenance");
        instance.getCommand("Maintenance").setExecutor((CommandExecutor)new Maintenance());
        Main.CommandInitMessage("Check");
        instance.getCommand("Check").setExecutor((CommandExecutor)new Check());
        Main.CommandInitMessage("Vanish");
        instance.getCommand("Vanish").setExecutor((CommandExecutor)new Vanish());
        Main.CommandInitMessage("Force");
        instance.getCommand("Force").setExecutor((CommandExecutor)new Force());
        Main.CommandInitMessage("Team");
        instance.getCommand("Team").setExecutor((CommandExecutor)new Team());
        Main.CommandInitMessage("God");
        instance.getCommand("God").setExecutor((CommandExecutor)new God());
        Main.CommandInitMessage("INF");
        instance.getCommand("INF").setExecutor((CommandExecutor)new INF());
        Main.CommandInitMessage("Ping");
        instance.getCommand("Ping").setExecutor((CommandExecutor)new Ping());
        Main.CommandInitMessage("ChangeMap");
        instance.getCommand("ChangeMap").setExecutor((CommandExecutor)new ChangeMap());
        Main.CommandInitMessage("Brieftasche");
        instance.getCommand("Brieftasche").setExecutor((CommandExecutor)new Brieftasche());
        Main.CommandInitMessage("Achievements");
        instance.getCommand("Achievements").setExecutor((CommandExecutor)new Achievements());
        Main.CommandInitMessage("Rules");
        instance.getCommand("Rules").setExecutor((CommandExecutor)new Rules());
        Main.CommandInitMessage("Speed");
        instance.getCommand("Speed").setExecutor((CommandExecutor)new Speed());
        Main.CommandInitMessage("Back");
        instance.getCommand("Back").setExecutor((CommandExecutor)new Back());
        Main.CommandInitMessage("Autoclose");
        instance.getCommand("Autoclose").setExecutor((CommandExecutor)new Autoclose());
        Main.CommandInitMessage("RedstoneLock");
        instance.getCommand("RedstoneLock").setExecutor((CommandExecutor)new RedstoneLock());
        Main.CommandInitMessage("CustomModelData");
        instance.getCommand("CustomModelData").setExecutor((CommandExecutor)new CustomModelData());
        Main.CommandInitMessage("Enderchest");
        instance.getCommand("Enderchest").setExecutor((CommandExecutor)new Enderchest());
        Main.CommandInitMessage("World");
        instance.getCommand("World").setExecutor((CommandExecutor)new World());
        Main.CommandInitMessage("Item");
        instance.getCommand("Item").setExecutor((CommandExecutor)new Item());
        Main.CommandInitMessage("Invsee");
        instance.getCommand("Invsee").setExecutor((CommandExecutor)new Invsee());
        Main.CommandInitMessage("DiscordLink");
        instance.getCommand("DiscordLink").setExecutor((CommandExecutor)new DiscordLink());
        Main.CommandInitMessage("Shout");
        instance.getCommand("Shout").setExecutor((CommandExecutor)new Shout());
        Main.CommandInitMessage("Whisper");
        instance.getCommand("Whisper").setExecutor((CommandExecutor)new Whisper());
        ServerAPI.sendConsoleMessage("\u00a7fCommands geladen!");
        Main.initListener();
    }

    private static void initListener() {
        ServerAPI.sendConsoleMessage("\u00a7fLade Listener...");
        API.initListeners(new PlayerJoin());
        API.initListeners(new PlayerQuit());
        API.initListeners(new PlayerInventory());
        API.initListeners(new PlayerDamage());
        API.initListeners(new PlayerCommand());
        API.initListeners(new PlayerInteract());
        API.initListeners(new WorldSignChange());
        API.initListeners(new PlayerCraft());
        API.initListeners(new ProjectileHit());
        API.initListeners(new WorldChange());
        API.initListeners(new ChatManager());
        API.initListeners(new DeathChestManager());
        ServerAPI.sendConsoleMessage("\u00a7fListener geladen!");
    }

    private static void CommandInitMessage(String commandName) {
        ServerAPI.sendConsoleMessage("\u00a7f  -> \u00a77Lade Command \"\u00a7e" + commandName + "\u00a77\"...");
    }

    public static void setState(Player p) {
        if (StaticManager.farmworld) {
            WorldAPI.setDifficulty(StaticManager.farmworldName, org.bukkit.Difficulty.NORMAL);
        }
        WorldAPI.setDifficulty("Welt", org.bukkit.Difficulty.valueOf((String)StaticManager.difficulty));
        String Prefix = PlayerAPI.getColorPrefix(p) + " \u00a78\u25cf \u00a7f";
        if (p.hasPermission("zyneon.team")) {
            if (PlayerAPI.isRP(p)) {
                State.getTeam("0RP0").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("0RP0").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            } else {
                State.getTeam("1NP0").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("1NP0").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.setScoreboard(State);
            }
        } else if (p.hasPermission("zyneon.creator")) {
            if (PlayerAPI.isRP(p)) {
                State.getTeam("0RP1").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("0RP1").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            } else {
                State.getTeam("1NP1").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("1NP1").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.setScoreboard(State);
            }
        } else if (p.hasPermission("zyneon.premium")) {
            if (PlayerAPI.isRP(p)) {
                State.getTeam("0RP2").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("0RP2").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            } else {
                State.getTeam("1NP2").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("1NP2").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.setScoreboard(State);
            }
        } else {
            if (PlayerAPI.isRP(p)) {
                State.getTeam("0RP3").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("0RP3").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            } else {
                State.getTeam("1NP3").addPlayer((OfflinePlayer)p);
                p.setDisplayName(Prefix + p.getName() + State.getTeam("1NP3").getSuffix());
                p.setPlayerListName(p.getDisplayName());
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.setScoreboard(State);
            }
        }
    }

    static {
        PM = Bukkit.getPluginManager();
        Version = "v";
    }
}

