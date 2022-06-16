
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tell
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/tell \u00a7c[Spieler] [Nachricht]");
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Tell")) {
            if (!(s instanceof Player)) {
                if (args.length >= 2) {
                    if (Bukkit.getPlayer((String)args[0]) != null) {
                        Object msg = "";
                        for (int i = 1; i < args.length; ++i) {
                            msg = (String)msg + args[i] + " ";
                        }
                        Player t = Bukkit.getPlayer((String)args[0]);
                        msg = ServerAPI.formatMessage((String)msg);
                        API.sendMessage(s, "\u00a78[\u00a77MSG\u00a78] \u00a76Du \u00a7f-> \u00a7e" + t.getName() + "\u00a78: \u00a77" + (String)msg);
                        PlayerAPI.sendPlayerMessage(t, "\u00a78[\u00a77MSG\u00a78] \u00a7cKONSOLE\u00a7f -> \u00a76Dir \u00a78: \u00a77" + (String)msg, NewSound.ENTITY_CAT_AMBIENT);
                    } else {
                        API.sendErrorMessage(s, API.noPlayer);
                    }
                } else {
                    this.sendSyntax(s);
                }
            } else {
                Player p = (Player)s;
                String PN = p.getName();
                if (args.length >= 2) {
                    Object msg = "";
                    for (int i = 1; i < args.length; ++i) {
                        msg = (String)msg + args[i] + " ";
                    }
                    msg = ServerAPI.formatMessage((String)msg);
                    if (Bukkit.getPlayer((String)args[0]) != null) {
                        Player p2 = Bukkit.getPlayer((String)args[0]);
                        String P2N = p2.getName();
                        if (PN.equals(P2N)) {
                            p.sendMessage("\u00a7cDu solltest eventuell dar\u00fcber nachdenken, mit wem anders zu schreiben, statt mit dir selber.");
                            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                        } else {
                            PlayerAPI.sendPlayerMessage(p, "\u00a78[\u00a77MSG\u00a78] \u00a76Du \u00a7f-> \u00a7e" + P2N + "\u00a78: \u00a77" + (String)msg, NewSound.ENTITY_CHICKEN_EGG);
                            PlayerAPI.sendPlayerMessage(p2, "\u00a78[\u00a77MSG\u00a78] \u00a7e" + PN + "\u00a7f -> \u00a76Dir \u00a78: \u00a77" + (String)msg, NewSound.ENTITY_CAT_AMBIENT);
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPlayer);
                    }
                } else {
                    this.sendSyntax((CommandSender)p);
                }
            }
        }
        return false;
    }
}

