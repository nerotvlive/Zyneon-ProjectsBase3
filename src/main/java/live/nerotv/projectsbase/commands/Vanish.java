
package live.nerotv.projectsbase.commands;

import java.util.ArrayList;
import live.nerotv.projectsbase.api.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish
implements CommandExecutor {
    public static ArrayList<Player> vP = new ArrayList();

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Vanish")) {
            if (s instanceof Player) {
                if (s.hasPermission("zyneon.team")) {
                    Player p = (Player)s;
                    if (vP.contains((Object)p)) {
                        vP.remove((Object)p);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(p);
                            if (all.getName().equals(p.getName())) continue;
                            if (all.hasPermission("zyneon.team")) {
                                API.sendMessage((CommandSender)all, "\u00a7e" + p.getName() + "\u00a77 ist nun \u00a7enicht mehr \u00a7r\u00a7eunsichtbar\u00a78!");
                                continue;
                            }
                            all.sendMessage("\u00a78\u00bb \u00a7a" + p.getName());
                        }
                        API.sendMessage((CommandSender)p, "\u00a77Du bist nun \u00a7anicht mehr\u00a7r\u00a7a unsichtbar\u00a78!");
                    } else {
                        vP.add(p);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.hidePlayer(p);
                            if (all.getName().equals(p.getName())) continue;
                            if (all.hasPermission("zyneon.team")) {
                                API.sendMessage((CommandSender)all, "\u00a7e" + p.getName() + "\u00a77 ist nun \u00a7eunsichtbar\u00a78! \u00a78(Du kannst sie/ihn aber noch sehen, da du die Rechte dazu hast!)");
                                all.showPlayer(p);
                                continue;
                            }
                            all.sendMessage("\u00a78\u00ab \u00a7c" + p.getName());
                        }
                        API.sendMessage((CommandSender)p, "\u00a77Du bist nun \u00a7aunsichtbar\u00a78!");
                    }
                } else {
                    API.sendErrorMessage(s, API.noPerms);
                }
            } else {
                API.sendErrorMessage(s, API.needPlayer);
            }
        }
        return false;
    }
}

