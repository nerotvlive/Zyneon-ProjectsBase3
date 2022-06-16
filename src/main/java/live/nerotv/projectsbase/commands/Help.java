
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("?")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
            }
            if (args.length == 0) {
                s.sendMessage("\u00a78=================================================");
                s.sendMessage("Projekt: " + ConfigAPI.CFG.getString("Core.Strings.Projektname").replace("&", "\u00a7") + "\u00a7f v\u00a7b" + ConfigAPI.CFG.getString("Core.Strings.Projektversion"));
                s.sendMessage("Leitung: " + ConfigAPI.CFG.getString("Core.Strings.Projektleitung").replace("&", "\u00a7") + "\u00a7f Art: " + ConfigAPI.CFG.getString("Core.Strings.Projektart").replace("&", "\u00a7"));
                s.sendMessage("\u00a77-------------------------------------------------");
                s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpLine01").replace("&", "\u00a7"));
                s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpLine02").replace("&", "\u00a7"));
                s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpLine03").replace("&", "\u00a7"));
                s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpLine04").replace("&", "\u00a7"));
                s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpLine05").replace("&", "\u00a7"));
                s.sendMessage("\u00a78=================================================");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("team")) {
                    Bukkit.dispatchCommand((CommandSender)s, (String)"help team 1");
                } else {
                    Bukkit.dispatchCommand((CommandSender)s, (String)"help");
                }
            } else if (args[0].equalsIgnoreCase("team")) {
                if (s.hasPermission("zyneon.team")) {
                    if (args[1].equalsIgnoreCase("1")) {
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam101"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam102"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam103"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam104"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam105"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam106"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam107"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam108"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam109"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam110"));
                    } else if (args[1].equalsIgnoreCase("2")) {
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam201"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam202"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam203"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam204"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam205"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam206"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam207"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam208"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam209"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam210"));
                    } else if (args[1].equalsIgnoreCase("3")) {
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam301"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam302"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam303"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam304"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam305"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam306"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam307"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam308"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam309"));
                        s.sendMessage(ConfigAPI.CFG.getString("Core.Strings.HelpTeam310"));
                    } else {
                        Bukkit.dispatchCommand((CommandSender)s, (String)"help");
                    }
                } else {
                    Bukkit.dispatchCommand((CommandSender)s, (String)"help");
                }
            }
        }
        return false;
    }
}

