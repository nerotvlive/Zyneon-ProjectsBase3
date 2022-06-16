
package live.nerotv.projectsbase.api;

import com.comphenix.protocol.ProtocolLibrary;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.commands.Vanish;
import live.nerotv.projectsbase.manager.PointsManager;
import live.nerotv.projectsbase.manager.StaticManager;
import live.nerotv.projectsbase.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.geysermc.floodgate.api.FloodgateApi;

public class PlayerAPI {
    public static ArrayList<UUID> roleplayPlayers = new ArrayList();
    public static String NameString = "Spieler.Name";
    public static String UUIDString = "Spieler.UUID";
    public static String RPN = "Spieler.RPName";
    public static String RPJ = "Spieler.RPJob";

    public static void sendPlayerMessage(Player player, String message) {
        player.sendMessage(ServerAPI.formatMessage(message).replace("\u00a77", "\u00a7" + ChatAPI.getMainColor(player)).replace("\u00a7e", "\u00a7" + ChatAPI.getTypeColor(player)).replace("\u00a7a", "\u00a7" + ChatAPI.getUserColor(player)));
    }

    public static void sendPlayerMessage(Player player, String message, NewSound newSound) {
        player.playSound(player.getLocation(), Sound.valueOf((String)newSound.toString()), 100.0f, 100.0f);
        player.sendMessage(ServerAPI.formatMessage(message).replace("\u00a77", "\u00a7" + ChatAPI.getMainColor(player)).replace("\u00a7e", "\u00a7" + ChatAPI.getTypeColor(player)).replace("\u00a7a", "\u00a7" + ChatAPI.getUserColor(player)));
    }

    public static void sendPlayerMessage(Player player, String message, Sound bukkitSound) {
        player.playSound(player.getLocation(), bukkitSound, 100.0f, 100.0f);
        player.sendMessage(ServerAPI.formatMessage(message).replace("\u00a77", "\u00a7" + ChatAPI.getMainColor(player)).replace("\u00a7e", "\u00a7" + ChatAPI.getTypeColor(player)).replace("\u00a7a", "\u00a7" + ChatAPI.getUserColor(player)));
    }

    public static void playNewSound(Player player, NewSound newSound) {
        Sound sound = SoundAPI.getNewSound(newSound);
        player.playSound(player.getLocation(), sound, 100.0f, 100.0f);
    }

    public static void playNewSound(Player player, NewSound newSound, float v) {
        Sound sound = SoundAPI.getNewSound(newSound);
        player.playSound(player.getLocation(), sound, v, 100.0f);
    }

    public static void playNewSound(Player player, NewSound newSound, float v, float v1) {
        Sound sound = SoundAPI.getNewSound(newSound);
        player.playSound(player.getLocation(), sound, v, v1);
    }

    public static boolean isRP(Player player) {
        return roleplayPlayers.contains(player.getUniqueId());
    }

    public static String getRPN(String SID) {
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        ConfigAPI.reloadConfig(PlayerFile, PF);
        if (PF.contains(RPN)) {
            if (PlayerAPI.hasINF(SID)) {
                return "\u00a7l" + PF.getString(RPN);
            }
            return PF.getString(RPN);
        }
        return null;
    }

    public static boolean hasINF(String SID) {
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (PF.contains("Spieler.INF")) {
            return PF.getBoolean("Spieler.INF");
        }
        return false;
    }

    public static void setINF(String SID, boolean state) {
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        PF.set("Spieler.INF", (Object)state);
        ConfigAPI.saveConfig(PlayerFile, PF);
    }

    public static void setINF(UUID UID, boolean state) {
        PlayerAPI.setINF(UID.toString(), state);
    }

    public static void setINF(Player player, boolean state) {
        PlayerAPI.setINF(player.getUniqueId(), state);
    }

    public static boolean hasINF(UUID UID) {
        return PlayerAPI.hasINF(UID.toString());
    }

    public static boolean hasINF(Player player) {
        return PlayerAPI.hasINF(player.getUniqueId());
    }

    public static String getRPJ(String SID) {
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        ConfigAPI.reloadConfig(PlayerFile, PF);
        if (PF.contains(RPJ)) {
            return PF.getString(RPJ);
        }
        return null;
    }

    public static void setRoleplay(UUID UID, Boolean Roleplay2) {
        if (Bukkit.getPlayer((UUID)UID) != null) {
            PlayerAPI.setRoleplay(Bukkit.getPlayer((UUID)UID), Roleplay2);
        }
    }

    public static void setRoleplay(Player player, Boolean Roleplay2) {
        if (Roleplay2.booleanValue()) {
            if (!roleplayPlayers.contains(player.getUniqueId())) {
                roleplayPlayers.add(player.getUniqueId());
            }
        } else {
            roleplayPlayers.remove(player.getUniqueId());
        }
    }

    public static void registerPlayer(Player player) {
        String SID = player.getUniqueId().toString();
        Date nowDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date nowTime = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        PF.set(NameString, (Object)player.getName());
        PF.set(UUIDString, (Object)SID);
        if (!PF.contains("Spieler.ChatColor.Main")) {
            PF.set("Spieler.ChatColor.Main", (Object)"7");
        }
        if (!PF.contains("Spieler.ChatColor.User")) {
            PF.set("Spieler.ChatColor.User", (Object)"a");
        }
        if (!PF.contains("Spieler.ChatColor.Type")) {
            PF.set("Spieler.ChatColor.Type", (Object)"e");
        }
        PF.set("Spieler.IP", (Object)player.getAddress().getHostString());
        PF.set("Spieler.Login.Date", (Object)formatDate.format(nowDate));
        PF.set("Spieler.Login.Time", (Object)formatTime.format(nowTime));
        PF.set("Spieler.Gamemode", (Object)player.getGameMode().toString());
        ConfigAPI.saveConfig(PlayerFile, PF);
        ConfigAPI.UID.set("NameList." + player.getName(), (Object)SID);
        ConfigAPI.UID.set("UniqueList." + SID, (Object)player.getName());
        ConfigAPI.saveConfig(ConfigAPI.UUID, ConfigAPI.UID);
    }

    public static void saveLocation(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        String w = player.getWorld().getName();
        double x = player.getLocation().getBlockX();
        double y = player.getLocation().getBlockY();
        double z = player.getLocation().getBlockZ();
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        PF.set("Spieler.Gamemode", (Object)player.getGameMode().toString());
        PF.set("Spieler.LastLoc.W", (Object)w);
        PF.set("Spieler.LastLoc.X", (Object)x);
        PF.set("Spieler.LastLoc.Y", (Object)y);
        PF.set("Spieler.LastLoc.Z", (Object)z);
        PF.set("Spieler.LastLoc.Yaw", (Object)Float.valueOf(yaw));
        PF.set("Spieler.LastLoc.Pitch", (Object)Float.valueOf(pitch));
        ConfigAPI.saveConfig(PlayerFile, PF);
    }

    public static boolean isRegistered(String UniqueOrName) {
        return ConfigAPI.UID.contains("NameList." + UniqueOrName);
    }

    public static String getPrefix(Player player) {
        if (player.hasPermission("zyneon.team")) {
            return "Team";
        }
        if (player.hasPermission("zyneon.creator")) {
            return "Creator";
        }
        if (player.hasPermission("zyneon.premium")) {
            return "Premium";
        }
        return "Spieler";
    }

    public static String getColorPrefix(Player player) {
        if (player.hasPermission("zyneon.team")) {
            return "\u00a75Team";
        }
        if (player.hasPermission("zyneon.creator")) {
            return "\u00a75Creator";
        }
        if (player.hasPermission("zyneon.premium")) {
            return "\u00a7dPremium";
        }
        return "\u00a7dSpieler";
    }

    public static String registeredSID(String Playername) {
        if (PlayerAPI.isRegistered(Playername)) {
            return ConfigAPI.UID.getString("NameList." + Playername);
        }
        return null;
    }

    public static Location registeredLocation(String SID) {
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        return new Location(Bukkit.getWorld((String)PF.getString("Spieler.LastLoc.W")), Double.parseDouble(PF.getString("Spieler.LastLoc.X")), Double.parseDouble(PF.getString("Spieler.LastLoc.Y")), Double.parseDouble(PF.getString("Spieler.LastLoc.Z")), (float)Double.parseDouble(PF.getString("Spieler.LastLoc.Yaw")), (float)Double.parseDouble(PF.getString("Spieler.LastLoc.Pitch")));
    }

    public static boolean checkRulesLevel(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (PF.contains("Spieler.RulesLevel")) {
            return PF.getInt("Spieler.RulesLevel") == StaticManager.requiredRulesLevel;
        }
        return false;
    }

    public static void setRulesLevel(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        PF.set("Spieler.RulesLevel", (Object)StaticManager.requiredRulesLevel);
        ConfigAPI.saveConfig(PlayerFile, PF);
    }

    public static boolean isLastLocSave(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (PF.contains("Spieler.LastLoc.W")) {
            return Bukkit.getWorld((String)PF.getString("Spieler.LastLoc.W")) != null;
        }
        return false;
    }

    public static void addMoney(Player p, double amount) {
        API.dispatchConsoleCommand("money give " + p.getName() + " " + amount);
    }

    public static boolean isBedrock(CommandSender s) {
        if (!(s instanceof Player)) {
            return false;
        }
        Player player = (Player)s;
        if (Main.PM.getPlugin("floodgate") != null) {
            return FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());
        }
        return false;
    }

    public static boolean isBedrock(UUID UID) {
        if (Bukkit.getPlayer((UUID)UID) != null) {
            return PlayerAPI.isBedrock((CommandSender)Bukkit.getPlayer((UUID)UID));
        }
        return false;
    }

    public static boolean isBedrock(String SIDName) {
        try {
            return PlayerAPI.isBedrock(UUID.fromString(SIDName));
        }
        catch (IllegalArgumentException e) {
            if (Bukkit.getPlayer((String)SIDName) != null) {
                return PlayerAPI.isBedrock((CommandSender)Bukkit.getPlayer((String)SIDName));
            }
            return false;
        }
    }

    public static String getVersion(Player player) {
        if (API.PM.getPlugin("ProtocolLib") != null) {
            Integer protocol = ProtocolLibrary.getProtocolManager().getProtocolVersion(player);
            if (protocol < 754) {
                return "Unsupported";
            }
            if (protocol == 754) {
                return "1.16.4/5";
            }
            if (protocol == 755) {
                return "1.17.0";
            }
            if (protocol == 756) {
                return "1.17.1";
            }
            return "Supported";
        }
        return "Supported";
    }

    public static void protectPlayer(final Player player) {
        if (!API.protectedPlayers.contains((Object)player)) {
            API.protectedPlayers.add(player);
        }
        if (!Vanish.vP.contains((Object)player)) {
            Vanish.vP.add(player);
        }
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.hidePlayer(player);
        }
        player.setInvulnerable(true);
        API.sendMessage((CommandSender)player, "\u00a77Du hast nun \u00a7a5 Sekunden \u00a7eSpawnprotection\u00a78!");
        new Countdown(5, (Plugin)Main.getInstance()){

            @Override
            public void count(int current) {
                if (current == 0) {
                    API.protectedPlayers.remove((Object)player);
                    API.sendMessage((CommandSender)player, "\u00a77Du hast nun \u00a7ckeine \u00a7eSpawnprotection\u00a7c mehr\u00a78!");
                    PlayerAPI.playNewSound(player, NewSound.BLOCK_NOTE_BLOCK_PLING);
                    player.setInvulnerable(false);
                    Vanish.vP.remove((Object)player);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.showPlayer(player);
                    }
                }
            }
        }.start();
    }

    public static void addBypassPerms(Player p) {
        if (Main.PM.getPlugin("LuckPerms") != null) {
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission set lwc.* true");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission set lwct.* true");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission set lwcx.* true");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission set lwctrust.* true");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission set ChestShop.* true");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission set GChestShop.* true");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission set ZySys.Commands.Bypass true");
        }
    }

    public static void removeBypassPerms(Player p) {
        if (Main.PM.getPlugin("LuckPerms") != null) {
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission unset lwc.*");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission unset lwct.*");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission unset lwcx.*");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission unset lwctrust.*");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission unset ChestShop.*");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission unset GChestShop.*");
            API.dispatchConsoleCommand("lp user " + p.getName() + " permission unset ZySys.Commands.Bypass");
        }
    }

    public static int getPing(Player p) {
        String v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if (!p.getClass().getName().equals("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer")) {
            p = Bukkit.getPlayer((UUID)p.getUniqueId());
        }
        try {
            Class<?> CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer");
            Object CraftPlayer = CraftPlayerClass.cast((Object)p);
            Method getHandle = CraftPlayer.getClass().getMethod("getHandle", new Class[0]);
            Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);
            Field ping = EntityPlayer.getClass().getDeclaredField("ping");
            return ping.getInt(EntityPlayer);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void giveDailyReward(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (StaticManager.dailyReward) {
            Integer now = PointsManager.getAP(player);
            if (PlayerFile.exists() && PF.contains("Spieler.Login.Date")) {
                Date nowDate = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                if (!PF.getString("Spieler.Login.Date").equals(formatDate.format(nowDate))) {
                    Integer val = now + StaticManager.rewardDaily;
                    PointsManager.setAP(player, val);
                    API.sendMessage((CommandSender)player, "\u00a77Du hast f\u00fcr das \u00a7aheutige Einloggen \u00a7e" + StaticManager.rewardDaily + " AP\u00a77 erhalten\u00a78!");
                    PlayerAPI.sendPlayerMessage(player, API.Prefix + "\u00a77Du hast nun insgesamt \u00a7e" + val + " AP\u00a78!");
                }
            } else {
                Integer val = now + StaticManager.rewardFirst;
                PointsManager.setAP(player, val);
                API.sendMessage((CommandSender)player, "\u00a77Du hast f\u00fcr das \u00a7aerste mal Einloggen \u00a7e" + StaticManager.rewardFirst + " AP\u00a77 erhalten\u00a78!");
                PlayerAPI.sendPlayerMessage(player, API.Prefix + "\u00a77Du hast nun insgesamt \u00a7e" + val + " AP\u00a78!");
            }
        }
    }
}

