
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

public class Job
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (!(s instanceof Player)) {
            s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
        } else {
            Player p = (Player)s;
            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
            p.sendMessage("\u00a74Fehler: \u00a7c/job [Job]");
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Job")) {
            if (!(s instanceof Player)) {
                this.sendSyntax(s);
            } else {
                Player p = (Player)s;
                File PlayerFile = new File("plugins/Zyneon/Players/" + p.getUniqueId() + ".yml");
                YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
                if (args.length == 0) {
                    this.sendSyntax(s);
                } else if (args.length == 1) {
                    String Job2 = args[0];
                    if (FilterAPI.isJobBlocked(Job2)) {
                        p.sendMessage("\u00a7cDer Jobname \u00a74" + Job2 + "\u00a7c ist ung\u00fcltig.");
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    } else {
                        PF.set(PlayerAPI.RPJ, (Object)Job2);
                        ConfigAPI.saveConfig(PlayerFile, PF);
                        API.sendMessage((CommandSender)p, "Dein Roleplay-Job lautet nun: \u00a7e" + Job2);
                    }
                } else if (args.length == 2) {
                    String Job3 = args[0] + " " + args[1];
                    if (FilterAPI.isJobBlocked(Job3)) {
                        p.sendMessage("\u00a7cDer Jobname \u00a74" + Job3 + "\u00a7c ist ung\u00fcltig.");
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    } else {
                        PF.set(PlayerAPI.RPJ, (Object)Job3);
                        ConfigAPI.saveConfig(PlayerFile, PF);
                        API.sendMessage((CommandSender)p, "Dein Roleplay-Job lautet nun: \u00a7e" + Job3);
                    }
                } else if (args.length == 3) {
                    String Job4 = args[0] + " " + args[1] + " " + args[2];
                    if (FilterAPI.isJobBlocked(Job4)) {
                        p.sendMessage("\u00a7cDer Jobname \u00a74" + Job4 + "\u00a7c ist ung\u00fcltig.");
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    } else {
                        PF.set(PlayerAPI.RPJ, (Object)Job4);
                        ConfigAPI.saveConfig(PlayerFile, PF);
                        API.sendMessage((CommandSender)p, "Dein Roleplay-Job lautet nun: \u00a7e" + Job4);
                    }
                } else {
                    p.sendMessage("\u00a7cDer Job darf nicht l\u00e4nger als drei Worte sein.");
                }
            }
        }
        return false;
    }
}

