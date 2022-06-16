
package live.nerotv.projectsbase.commands;

import java.io.File;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.FilterAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Name
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (!(s instanceof Player)) {
            s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
        } else {
            Player p = (Player)s;
            API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/name [Vorname] \u00a77[Mittelname] \u00a7c[Nachname]");
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Name")) {
            if (!(s instanceof Player)) {
                this.sendSyntax(s);
            } else {
                Player p = (Player)s;
                File PlayerFile = new File("plugins/Zyneon/Players/" + p.getUniqueId() + ".yml");
                YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
                if (args.length == 0) {
                    this.sendSyntax(s);
                } else if (args.length == 1) {
                    this.sendSyntax(s);
                } else if (args.length == 2) {
                    String Name2 = args[0] + " " + args[1];
                    if (FilterAPI.isNameBlocked(Name2)) {
                        p.sendMessage("\u00a7cDer Name \u00a74" + Name2 + "\u00a7c ist ung\u00fcltig.");
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    } else if (API.isNameEnabled(Name2)) {
                        API.disableName(Name2);
                        API.setNameOwner(Name2, p.getUniqueId().toString());
                        API.enableName(PlayerAPI.getRPN(p.getUniqueId().toString()));
                        PF.set(PlayerAPI.RPN, (Object)Name2);
                        ConfigAPI.saveConfig(PlayerFile, PF);
                        API.sendMessage((CommandSender)p, "Dein Roleplay-Name lautet nun: \u00a7e" + Name2);
                    } else {
                        s.sendMessage("\u00a7cDieser Name ist bereits vergeben!");
                        PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
                    }
                } else if (args.length == 3) {
                    String Name3 = args[0] + " " + args[1] + " " + args[2];
                    if (FilterAPI.isNameBlocked(Name3)) {
                        p.sendMessage("\u00a7cDer Name \u00a74" + Name3 + "\u00a7c ist ung\u00fcltig.");
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    } else if (API.isNameEnabled(Name3)) {
                        API.disableName(Name3);
                        API.setNameOwner(Name3, p.getUniqueId().toString());
                        API.enableName(PlayerAPI.getRPN(p.getUniqueId().toString()));
                        PF.set(PlayerAPI.RPN, (Object)Name3);
                        ConfigAPI.saveConfig(PlayerFile, PF);
                        API.sendMessage((CommandSender)p, "Dein Roleplay-Name lautet nun: \u00a7e" + Name3);
                    } else {
                        s.sendMessage("\u00a7cDieser Name ist bereits vergeben!");
                        PlayerAPI.playNewSound((Player)s, NewSound.BLOCK_ANVIL_BREAK);
                    }
                } else {
                    p.sendMessage("\u00a7cDer Name darf nicht l\u00e4nger als drei Worte sein.");
                }
            }
        }
        return false;
    }
}

