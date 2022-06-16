
package live.nerotv.projectsbase.commands;

import java.io.File;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Force
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Force")) {
            if (s.hasPermission("zyneon.team")) {
                if (args.length >= 3) {
                    File List2 = PlayerAPI.isRegistered(args[1]) ? new File("plugins/Zyneon/Players/" + PlayerAPI.registeredSID(args[1]) + ".yml") : new File("plugins/Zyneon/Players/" + args[1] + ".yml");
                    YamlConfiguration UUIDs = YamlConfiguration.loadConfiguration((File)List2);
                    if (args[0].equalsIgnoreCase("job")) {
                        Object m = "";
                        for (int i = 2; i < args.length; ++i) {
                            m = (String)m + args[i] + " ";
                        }
                        m = ((String)m).substring(0, ((String)m).length() - 1);
                        UUIDs.set("Spieler.RPJob", m);
                        ConfigAPI.saveConfig(List2, UUIDs);
                        API.sendMessage(s, "Du hast den RP-Job von \u00a7e" + args[1] + "\u00a77 auf \u00a7e" + (String)m + "\u00a77 gesetzt!");
                    } else if (args[0].equalsIgnoreCase("name")) {
                        Object m = "";
                        for (int i = 2; i < args.length; ++i) {
                            m = (String)m + args[i] + " ";
                        }
                        if (API.isNameEnabled((String)(m = ((String)m).substring(0, ((String)m).length() - 1)))) {
                            API.disableName((String)m);
                            API.setNameOwner((String)m, PlayerAPI.registeredSID(args[1]));
                            API.enableName(PlayerAPI.registeredSID(args[1]));
                            UUIDs.set("Spieler.RPName", m);
                            ConfigAPI.saveConfig(List2, UUIDs);
                            API.sendMessage(s, "Du hast den RP-Namen von \u00a7e" + args[1] + "\u00a77 auf \u00a7e" + (String)m + "\u00a77 gesetzt!");
                        } else {
                            s.sendMessage("\u00a7cDieser Name ist bereits vergeben!");
                            if (s instanceof Player) {
                                PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
                            }
                        }
                    } else {
                        s.sendMessage("\u00a74Fehler: \u00a7c/force [Job/Name] [Spieler] [Wert]");
                    }
                } else {
                    s.sendMessage("\u00a74Fehler: \u00a7c/force [Job/Name] [Spieler] [Wert]");
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

