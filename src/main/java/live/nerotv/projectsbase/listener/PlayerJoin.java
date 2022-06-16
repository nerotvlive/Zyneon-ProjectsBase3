
package live.nerotv.projectsbase.listener;

import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.BedrockAPI;
import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import live.nerotv.projectsbase.api.WarpAPI;
import live.nerotv.projectsbase.commands.Vanish;
import live.nerotv.projectsbase.manager.StaticManager;
import live.nerotv.projectsbase.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerJoin
implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!PlayerAPI.isLastLocSave(p)) {
            if (WarpAPI.isWarpEnabled("Spawn")) {
                p.teleport(WarpAPI.getWarp("Spawn"));
            } else {
                p.teleport(((World)Bukkit.getWorlds().get(0)).getSpawnLocation());
            }
        }
        if (API.isStopping) {
            e.setJoinMessage("");
            p.sendMessage("\u00a7cDer Server startet zurzeit neu!");
            API.switchServer(p, "Lobby-1");
            p.kickPlayer("\u00a7cDer Server startet zurzeit neu!");
            return;
        }
        API.protectedPlayers.remove((Object)p);
        PlayerAPI.setRulesLevel(p);
        if (p.getInventory().getItem(8) != null && p.getInventory().getItem(8).getItemMeta().getDisplayName().equals(ItemAPI.backItem.getItemMeta().getDisplayName())) {
            p.getInventory().clear(8);
        }
        if (PlayerAPI.isBedrock((CommandSender)p)) {
            ServerAPI.sendConsoleMessage("\u00a7e" + p.getName() + "\u00a77 ist ein Bedrock-User\u00a78!");
            new Countdown(3, (Plugin)Main.getInstance()){

                @Override
                public void count(int current) {
                    if (current == 1) {
                        BedrockAPI.sendBedrockWarning(p);
                    }
                }
            }.start();
            if (p.getInventory().getItem(8) != null) {
                ItemStack item = p.getInventory().getItem(8);
                p.getInventory().clear(8);
                p.getInventory().setItem(8, ItemAPI.backItem);
                if (!item.getItemMeta().getDisplayName().equals(ItemAPI.backItem.getItemMeta().getDisplayName())) {
                    if (API.hasAvailableSlot(p)) {
                        p.getInventory().addItem(new ItemStack[]{item});
                    } else {
                        p.getWorld().dropItem(p.getLocation(), item);
                    }
                }
            } else {
                p.getInventory().setItem(8, ItemAPI.backItem);
            }
        }
        if (p.hasPermission("zyneon.premium")) {
            PlayerAPI.setINF(p, true);
        } else {
            PlayerAPI.setINF(p, false);
        }
        if (Vanish.vP.contains((Object)p)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.showPlayer(p);
            }
            Vanish.vP.remove((Object)p);
        }
        if (!p.hasPermission("zyneon.team")) {
            for (Player vanished : Vanish.vP) {
                p.hidePlayer(vanished);
            }
        }
        for (Player vanished : API.protectedPlayers) {
            p.hidePlayer(vanished);
        }
        PlayerAPI.removeBypassPerms(p);
        p.setPlayerListHeader(StaticManager.tablistHeader.replace("&", "\u00a7"));
        p.setPlayerListFooter(StaticManager.tablistFooter.replace("&", "\u00a7"));
        if (p.isInvisible()) {
            p.setInvisible(false);
        }
        if (p.isInvulnerable()) {
            p.setInvulnerable(false);
        }
        if (ServerAPI.isMaintenance()) {
            e.setJoinMessage("");
            if (p.hasPermission("zyneon.bypassmaintenance")) {
                PlayerAPI.setRoleplay(p, (Boolean)false);
                PlayerAPI.registerPlayer(p);
                Main.setState(p);
                e.setJoinMessage("\u00a78\u00bb \u00a7a" + p.getName());
                p.sendMessage("\u00a7cDer Server befindet sich momentan in \u00a74Wartungsarbeiten\u00a7c!");
            } else {
                p.kickPlayer("\u00a7cDer Server befindet sich momentan in \u00a74Wartungsarbeiten\u00a7c!");
            }
        } else {
            PlayerAPI.setRoleplay(p, (Boolean)false);
            PlayerAPI.giveDailyReward(p);
            PlayerAPI.registerPlayer(p);
            Main.setState(p);
            e.setJoinMessage("\u00a78\u00bb \u00a7a" + p.getName());
        }
        if (!PlayerAPI.checkRulesLevel(p) && !PlayerAPI.isBedrock((CommandSender)p)) {
            p.performCommand("rules");
        }
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("op " + p.getName()));
        p.setOp(false);
        if (PlayerAPI.getRPN(p.getUniqueId().toString()) != null) {
            p.performCommand("rp");
            API.sendMessage((CommandSender)p, "\u00a7eWICHTIG\u00a78: \u00a77Du bist nun im \u00a7eRoleplay\u00a78-\u00a7eModus\u00a78,\u00a77 solltest du nicht roleplayen wollen\u00a78, \u00a77mache \u00a7a/roleplay\u00a78!");
        } else {
            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast dir noch keinen Roleplay-Namen gesetzt\u00a78!\u00a7c Tue dies mit /name\u00a78!");
        }
    }
}

