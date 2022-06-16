
package live.nerotv.projectsbase.manager;

import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.FilterAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager
implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String MSG = e.getMessage().replace("./", "\u00a77/");
        Player p = e.getPlayer();
        String SID = p.getUniqueId().toString();
        e.setCancelled(true);
        if (FilterAPI.isStringBlocked(e.getMessage())) {
            p.sendMessage("\u00a74Achtung:\u00a7c Achte auf deine Wortwahl, oder es wird eine Strafe mit sich f\u00fchren.");
            PlayerAPI.playNewSound(p, NewSound.ENTITY_BAT_DEATH);
            PlayerAPI.playNewSound(p, NewSound.ENTITY_BLAZE_DEATH);
            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
        } else if (PlayerAPI.isRP(p)) {
            if (PlayerAPI.getRPN(SID) == null) {
                PlayerAPI.sendPlayerMessage(p, "\u00a7cBitte setze dir zuerst deinen Roleplaynamen mit \u00a7c/name [Vorname] \u00a77[Zwischenname] &c[Nachname].", NewSound.BLOCK_ANVIL_BREAK);
            } else {
                String Job2 = PlayerAPI.getRPJ(SID) == null ? "Arbeitslos" : PlayerAPI.getRPJ(SID);
                String RPM = "\u00a76RP \u00a78\u25cf \u00a7e" + Job2 + "\u00a78 \u25cf \u00a76" + PlayerAPI.getRPN(SID) + "\u00a78 \u00bb \u00a7f" + MSG;
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!p.getLocation().getWorld().equals((Object)all.getLocation().getWorld()) || !(p.getLocation().distance(all.getLocation()) <= 30.0)) continue;
                    if (PlayerAPI.isBedrock((CommandSender)all)) {
                        all.sendMessage(RPM.replace("\u25cf", "|").replace(" \u00bb", ""));
                        continue;
                    }
                    all.sendMessage(RPM);
                }
                Bukkit.getConsoleSender().sendMessage(RPM);
            }
        } else {
            Object Name2 = PlayerAPI.hasINF(p) ? "\u00a7l" + p.getName() : p.getName();
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (PlayerAPI.isRP(all)) {
                    if (PlayerAPI.isBedrock((CommandSender)all)) {
                        all.sendMessage("\u00a78Off-RP | " + PlayerAPI.getPrefix(p) + " | " + (String)Name2 + ": " + MSG);
                        continue;
                    }
                    all.sendMessage("\u00a78Off-RP \u25cf " + PlayerAPI.getPrefix(p) + " \u25cf " + (String)Name2 + " \u00bb " + MSG);
                    continue;
                }
                if (PlayerAPI.isBedrock((CommandSender)all)) {
                    all.sendMessage("\u00a77Off-RP \u00a78| \u00a7e" + PlayerAPI.getPrefix(p) + " \u00a78| \u00a76" + (String)Name2 + "\u00a78: \u00a77" + MSG);
                    continue;
                }
                all.sendMessage("\u00a77Off-RP \u00a78\u25cf \u00a7e" + PlayerAPI.getPrefix(p) + " \u00a78\u25cf \u00a76" + (String)Name2 + "\u00a78 \u00bb \u00a77" + MSG);
            }
            Bukkit.getServer().getConsoleSender().sendMessage("\u00a77Off-RP \u00a78\u25cf \u00a7e" + PlayerAPI.getPrefix(p) + " \u00a78\u25cf \u00a76" + (String)Name2 + "\u00a78 \u00bb \u00a77" + MSG);
        }
        for (Player all : Bukkit.getOnlinePlayers()) {
            Main.setState(all);
        }
    }
}

