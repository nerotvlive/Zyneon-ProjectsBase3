
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomModelData
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("CustomModelData")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        API.sendErrorMessage((CommandSender)p, "\u00a74Fehler: \u00a7c/custommodeldata [int]");
                    } else if (API.isNumeric(args[0])) {
                        if (p.getItemInHand().getItemMeta() != null) {
                            ItemStack item = p.getItemInHand();
                            ItemMeta itemMeta = item.getItemMeta();
                            Integer modelData = Integer.parseInt(args[0]);
                            itemMeta.setCustomModelData(modelData);
                            item.setItemMeta(itemMeta);
                            p.setItemInHand(new ItemStack(Material.AIR));
                            p.setItemInHand(item);
                            API.sendMessage((CommandSender)p, "\u00a77Du hast die CustomModelData von \u00a7a" + itemMeta.getDisplayName() + "\u00a77 auf \u00a7e" + modelData + "\u00a77 gesetzt\u00a78.");
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDu hast kein g\u00fcltiges Item in der Hand!");
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, "\u00a74Fehler: \u00a7c/custommodeldata [int]");
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            } else {
                API.sendErrorMessage(s, StaticManager.needPlayer);
            }
        }
        return false;
    }
}

