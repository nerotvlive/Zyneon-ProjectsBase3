
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Shout
implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (s == null) {
            Shout.$$$reportNull$$$0(0);
        }
        if (cmd == null) {
            Shout.$$$reportNull$$$0(1);
        }
        if (label == null) {
            Shout.$$$reportNull$$$0(2);
        }
        if (args == null) {
            Shout.$$$reportNull$$$0(3);
        }
        if (s instanceof Player) {
            Player p = (Player)s;
            if (PlayerAPI.isRP(p)) {
                if (PlayerAPI.getRPN(p.getUniqueId().toString()) == null) {
                    PlayerAPI.sendPlayerMessage(p, "\u00a7cBitte setze dir zuerst deinen Roleplaynamen mit \u00a7c/name [Vorname] \u00a77[Zwischenname] &c[Nachname].", NewSound.BLOCK_ANVIL_BREAK);
                } else if (args.length == 0) {
                    API.sendErrorMessage((CommandSender)p, "\u00a74Fehler: \u00a7c/shout [Nachricht]");
                } else {
                    Object MSG = "";
                    for (int i = 0; i < args.length; ++i) {
                        MSG = (String)MSG + args[i] + " ";
                    }
                    String Job2 = PlayerAPI.getRPJ(p.getUniqueId().toString()) == null ? "Arbeitslos" : PlayerAPI.getRPJ(p.getUniqueId().toString());
                    String RPM = "\u00a76RP \u00a78\u25cf \u00a7e" + Job2 + "\u00a78 \u25cf \u00a76" + PlayerAPI.getRPN(p.getUniqueId().toString()) + "\u00a78 (SCHREIT) \u00bb \u00a7f" + ((String)MSG).toUpperCase();
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!p.getLocation().getWorld().equals((Object)all.getLocation().getWorld()) || !(p.getLocation().distance(all.getLocation()) <= 40.0)) continue;
                        if (PlayerAPI.isBedrock((CommandSender)all)) {
                            all.sendMessage(RPM.replace("\u25cf", "|").replace(" \u00bb", ""));
                            continue;
                        }
                        all.sendMessage(RPM);
                    }
                    Bukkit.getConsoleSender().sendMessage(RPM);
                }
            } else {
                p.sendMessage("\u00a7cBitte gehe dazu mit \u00a74/rp\u00a7c in den Roleplay-Modus!");
                PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
            }
        } else {
            API.sendErrorMessage(s, API.needPlayer);
        }
        return false;
    }

    private static  void $$$reportNull$$$0(int n) {
        Object[] arrobject;
        Object[] arrobject2 = new Object[3];
        switch (n) {
            default: {
                arrobject = arrobject2;
                arrobject2[0] = "s";
                break;
            }
            case 1: {
                arrobject = arrobject2;
                arrobject2[0] = "cmd";
                break;
            }
            case 2: {
                arrobject = arrobject2;
                arrobject2[0] = "label";
                break;
            }
            case 3: {
                arrobject = arrobject2;
                arrobject2[0] = "args";
                break;
            }
        }
        arrobject[1] = "live/nerotv/projectsbase/commands/Shout";
        arrobject[2] = "onCommand";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", arrobject));
    }
}

