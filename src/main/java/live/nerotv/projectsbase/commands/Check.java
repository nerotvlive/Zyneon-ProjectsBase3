
package live.nerotv.projectsbase.commands;

import java.io.File;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.manager.PointsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Check
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Check")) {
            if (s.hasPermission("zyneon.team")) {
                if (args.length == 0) {
                    s.sendMessage("\u00a74Fehler: \u00a7c/check [Spieler]");
                } else {
                    String SID = Bukkit.getPlayer((String)args[0]) != null ? Bukkit.getPlayer((String)args[0]).getUniqueId().toString() : (PlayerAPI.isRegistered(args[0]) ? PlayerAPI.registeredSID(args[0]) : args[0]);
                    File List2 = new File("plugins/Zyneon/Players/" + SID + ".yml");
                    YamlConfiguration UUIDs = YamlConfiguration.loadConfiguration((File)List2);
                    if (List2.exists()) {
                        String World2;
                        String State;
                        String Location2;
                        Player t;
                        ConfigAPI.reloadConfig(List2, UUIDs);
                        String Name2 = UUIDs.getString("Spieler.Name");
                        Object Platform = Name2.contains("*") ? "\u00a7aBedrock" : "\u00a7eJava";
                        String IP = ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Check.showIP") ? UUIDs.getString("Spieler.IP") : "\u00a7c-";
                        String Gamemode2 = UUIDs.getString("Spieler.Gamemode");
                        Object LastLogin = "\u00a7cUnbekannt";
                        if (UUIDs.getString("Spieler.Login.Date") != null && UUIDs.getString("Spieler.Login.Time") != null) {
                            LastLogin = UUIDs.getString("Spieler.Login.Date").replace("-", "\u00a78.\u00a7f") + "\u00a78,\u00a7f " + UUIDs.getString("Spieler.Login.Time").replace(":", "\u00a78:\u00a7f");
                            LastLogin = ((String)LastLogin).substring(0, ((String)LastLogin).length() - 7) + " Uhr";
                            LastLogin = Bukkit.getPlayer((String)args[0]) != null ? (String)LastLogin + "\u00a78 (\u00a7aOnline\u00a78)" : (Bukkit.getPlayer((String)UUIDs.getString("Spieler.Name")) != null ? (String)LastLogin + "\u00a78 (\u00a7aOnline\u00a78)" : (String)LastLogin + "\u00a78 (\u00a7cOffline\u00a78)");
                        }
                        String Job2 = PlayerAPI.getRPJ(SID) != null ? PlayerAPI.getRPJ(SID) : "\u00a7cNicht gesetzt";
                        String RPName = PlayerAPI.getRPN(SID) != null ? PlayerAPI.getRPN(SID) : "\u00a7cNicht gesetzt";
                        String AP = PointsManager.getAP(SID).toString();
                        if (Bukkit.getPlayer((String)args[0]) != null) {
                            t = Bukkit.getPlayer((String)args[0]);
                            Gamemode2 = t.getGameMode().toString();
                            Location2 = t.getLocation().getBlockX() + " " + t.getLocation().getBlockY() + " " + t.getLocation().getBlockZ();
                        } else if (Bukkit.getPlayer((String)UUIDs.getString("Spieler.Name")) != null) {
                            t = Bukkit.getPlayer((String)UUIDs.getString("Spieler.Name"));
                            Location2 = t.getLocation().getBlockX() + " " + t.getLocation().getBlockY() + " " + t.getLocation().getBlockZ();
                        } else {
                            Location2 = UUIDs.getString("Spieler.LastLoc.X") + " " + UUIDs.getString("Spieler.LastLoc.Y") + " " + UUIDs.getString("Spieler.LastLoc.Z");
                        }
                        if (Bukkit.getPlayer((String)args[0]) != null) {
                            t = Bukkit.getPlayer((String)args[0]);
                            Platform = PlayerAPI.isBedrock((CommandSender)t) ? "\u00a7aBedrock" : "\u00a7eJava\u00a78-\u00a7e" + PlayerAPI.getVersion(t);
                            State = PlayerAPI.roleplayPlayers.contains((Object)t) ? "\u00a7aRoleplay" : "Normal";
                            World2 = t.getWorld().getName();
                        } else if (Bukkit.getPlayer((String)UUIDs.getString("Spieler.Name")) != null) {
                            t = Bukkit.getPlayer((String)UUIDs.getString("Spieler.Name"));
                            Platform = PlayerAPI.isBedrock((CommandSender)t) ? "\u00a7aBedrock" : "\u00a7eJava\u00a78-\u00a7e" + PlayerAPI.getVersion(t);
                            State = PlayerAPI.roleplayPlayers.contains((Object)t) ? "\u00a7aRoleplay" : "Normal";
                            Gamemode2 = t.getGameMode().toString();
                            World2 = t.getWorld().getName();
                        } else {
                            State = "Normal";
                            World2 = UUIDs.getString("Spieler.LastLoc.W");
                        }
                        String Money = "\u00a7cUnbekannt";
                        if (PlayerAPI.isBedrock(s)) {
                            s.sendMessage("\u00a78=========================================");
                        } else {
                            s.sendMessage("\u00a78=================================================");
                        }
                        s.sendMessage("\u00a76Name\u00a78: \u00a7f" + Name2 + " \u00a78(" + (String)Platform + "\u00a78)");
                        s.sendMessage("\u00a76UUID\u00a78: \u00a7f" + SID);
                        s.sendMessage("\u00a76State\u00a78: \u00a7f" + State);
                        s.sendMessage("\u00a76Kontostand\u00a78: \u00a7f" + Money + " \u00a78(\u00a7fAP\u00a78: \u00a77" + AP + "\u00a78)");
                        s.sendMessage("\u00a76Zuletzt Eingeloggt\u00a78: \u00a7f" + (String)LastLogin);
                        if (ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Check.showIP")) {
                            s.sendMessage("\u00a76IP-Adresse\u00a78: \u00a7f" + IP);
                        }
                        s.sendMessage("\u00a76Gamemode\u00a78: \u00a7f" + Gamemode2);
                        s.sendMessage("\u00a76RP-Name\u00a78: \u00a7f" + RPName);
                        s.sendMessage("\u00a76RP-Job\u00a78: \u00a7f" + Job2);
                        s.sendMessage("\u00a76Befindet sich bei\u00a78: \u00a7f" + Location2 + "\u00a78 (\u00a77" + World2 + "\u00a78)\u00a7r");
                        if (PlayerAPI.isBedrock(s)) {
                            s.sendMessage("\u00a78=========================================");
                        } else {
                            s.sendMessage("\u00a78=================================================");
                        }
                        if (s instanceof Player) {
                            PlayerAPI.playNewSound((Player)s, NewSound.ENTITY_CHICKEN_EGG);
                        }
                    } else {
                        s.sendMessage("\u00a7cDieser Spieler existiert nicht.");
                        if (s instanceof Player) {
                            PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
                        }
                    }
                }
            } else {
                s.sendMessage(API.noPerms);
                if (s instanceof Player) {
                    PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
                }
            }
        }
        return false;
    }
}

