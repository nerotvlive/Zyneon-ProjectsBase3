
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.manager.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Settings
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Settings")) {
            if (!(s instanceof Player)) {
                if (args.length >= 3) {
                    if (args[0].equalsIgnoreCase("Config")) {
                        Object c = "";
                        for (int i = 2; i < args.length; ++i) {
                            c = (String)c + args[i] + " ";
                        }
                        if (((String)(c = ((String)c).substring(0, ((String)c).length() - 1))).equalsIgnoreCase("false")) {
                            ConfigAPI.CFG.set(args[1], (Object)false);
                        } else if (((String)c).equalsIgnoreCase("true")) {
                            ConfigAPI.CFG.set(args[1], (Object)true);
                        } else {
                            ConfigAPI.CFG.set(args[1], c);
                        }
                        ConfigAPI.saveConfig(ConfigAPI.Config, ConfigAPI.CFG);
                        ConfigAPI.reloadConfig(ConfigAPI.Config, ConfigAPI.CFG);
                        s.sendMessage(API.Prefix + "\u00a77Du hast den Config-Wert des Pfades \u00a7e" + args[1] + "\u00a77 auf \u00a7e" + (String)c + " \u00a77gesetzt!");
                    } else {
                        s.sendMessage("\u00a74Fehler: \u00a7c/settings config [Pfad] [Wert]");
                    }
                } else {
                    s.sendMessage("\u00a74Fehler: \u00a7c/settings config [Pfad] [Wert]");
                }
            } else {
                Player p = (Player)s;
                if (args.length >= 3) {
                    if (args[0].equalsIgnoreCase("config")) {
                        if (p.hasPermission("zyneon.leading")) {
                            Object c = "";
                            for (int i = 2; i < args.length; ++i) {
                                c = (String)c + args[i] + " ";
                            }
                            if (((String)(c = ((String)c).substring(0, ((String)c).length() - 1))).equalsIgnoreCase("false")) {
                                ConfigAPI.CFG.set(args[1], (Object)false);
                            } else if (((String)c).equalsIgnoreCase("true")) {
                                ConfigAPI.CFG.set(args[1], (Object)true);
                            } else {
                                ConfigAPI.CFG.set(args[1], c);
                            }
                            ConfigAPI.saveConfig(ConfigAPI.Config, ConfigAPI.CFG);
                            ConfigAPI.reloadConfig(ConfigAPI.Config, ConfigAPI.CFG);
                            API.sendMessage((CommandSender)p, "Du hast den Config-Wert des Pfades \u00a7e" + args[1] + "\u00a77 auf \u00a7e" + (String)c + " \u00a77gesetzt!");
                        } else {
                            p.performCommand("settings");
                        }
                    } else {
                        p.performCommand("settings");
                    }
                } else {
                    InventoryManager.openSettingsInventory(p);
                }
            }
        }
        return false;
    }
}

