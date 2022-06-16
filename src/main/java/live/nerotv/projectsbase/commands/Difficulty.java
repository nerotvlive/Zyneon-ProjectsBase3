
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.WorldAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Difficulty
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (!(s instanceof Player)) {
            s.sendMessage("\u00a74Fehler: \u00a7c/difficulty [Schwierigkeit] [Welt]");
        } else {
            Player p = (Player)s;
            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
            s.sendMessage("\u00a74Fehler: \u00a7c/difficulty [Schwierigkeit] \u00a77[Welt]");
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Difficulty")) {
            if (s instanceof Player) {
                if (s.hasPermission("zyneon.team")) {
                    Player p = (Player)s;
                    if (args.length > 0) {
                        if (args.length == 1) {
                            if (WorldAPI.resolveDifficulty(args[0]) != null) {
                                WorldAPI.setDifficulty(p.getWorld(), args[0]);
                                API.sendMessage((CommandSender)p, "\u00a77Du hast die Schwierigkeit von \u00a7a" + p.getWorld().getName() + "\u00a77 auf \u00a7e" + args[0] + "\u00a77 gesetzt\u00a78!");
                            } else {
                                API.sendErrorMessage((CommandSender)p, "\u00a7cDiese Schwierigkeitsstufe existiert nicht\u00a78!");
                                this.sendSyntax((CommandSender)p);
                            }
                        } else if (WorldAPI.resolveDifficulty(args[0]) != null) {
                            if (Bukkit.getWorld((String)args[1]) != null) {
                                WorldAPI.setDifficulty(args[1], args[0]);
                                API.sendMessage((CommandSender)p, "\u00a77Du hast die Schwierigkeit von \u00a7a" + args[1] + "\u00a77 auf \u00a7e" + args[0] + "\u00a77 gesetzt\u00a78!");
                            } else {
                                API.sendErrorMessage((CommandSender)p, "\u00a7cDiese Welt existiert nicht\u00a78!");
                                this.sendSyntax((CommandSender)p);
                            }
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDiese Schwierigkeitsstufe existiert nicht\u00a78!");
                            this.sendSyntax((CommandSender)p);
                        }
                    } else {
                        API.sendMessage((CommandSender)p, "\u00a77Die Schwierigkeit von \u00a7a" + p.getWorld().getName() + "\u00a77 steht auf\u00a78: \u00a7e" + p.getWorld().getDifficulty());
                        API.sendMessage((CommandSender)p, "\u00a77Um sie zu \u00e4ndern mache\u00a78: \u00a7e/difficulty [Schwierigkeit] \u00a77[Welt]");
                    }
                } else {
                    API.sendErrorMessage(s, API.noPerms);
                }
            } else if (args.length >= 2) {
                if (WorldAPI.resolveDifficulty(args[0]) != null) {
                    if (Bukkit.getWorld((String)args[1]) != null) {
                        WorldAPI.setDifficulty(args[1], args[0]);
                        API.sendMessage(s, "\u00a77Du hast die Schwierigkeit von \u00a7a" + args[1] + "\u00a77 auf \u00a7e" + args[0] + "\u00a77 gesetzt\u00a78!");
                    } else {
                        API.sendErrorMessage(s, "\u00a7cDiese Welt existiert nicht\u00a78!");
                        this.sendSyntax(s);
                    }
                } else {
                    API.sendErrorMessage(s, "\u00a7cDiese Schwierigkeitsstufe existiert nicht\u00a78!");
                    this.sendSyntax(s);
                }
            } else {
                API.sendErrorMessage(s, "\u00a7cZu wenig Argumente\u00a78!");
                this.sendSyntax(s);
            }
        }
        return false;
    }
}

