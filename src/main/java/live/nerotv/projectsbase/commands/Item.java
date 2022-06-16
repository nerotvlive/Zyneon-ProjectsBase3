
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ItemAPI;
import live.nerotv.projectsbase.manager.CustomItemManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Item
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Item")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team.item")) {
                    if (args.length == 1) {
                        if (ItemAPI.checkItemStack(args[0])) {
                            if (API.invFull(p)) {
                                API.sendErrorMessage((CommandSender)p, "\u00a7cDein Inventar ist voll\u00a78!");
                                return false;
                            }
                            ItemStack item = CustomItemManager.getCustomItem(args[0]) != null ? CustomItemManager.getCustomItem(args[0]) : new ItemStack(Material.getMaterial((String)args[0]));
                            p.getInventory().addItem(new ItemStack[]{item});
                            API.sendMessage((CommandSender)p, "\u00a77Du hast dir das Item \u00a7e" + args[0] + "\u00a77 gegeben\u00a78!");
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDieses Item gibt es nicht\u00a78!");
                        }
                    } else if (args.length == 2) {
                        if (!API.isNumeric(args[1])) {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDas ist keine g\u00fcltige Anzahl\u00a78!");
                            return false;
                        }
                        int i = Integer.parseInt(args[1]);
                        if (i > 64) {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDas ist keine g\u00fcltige Anzahl\u00a78!");
                            return false;
                        }
                        if (i < 1) {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDas ist keine g\u00fcltige Anzahl\u00a78!");
                            return false;
                        }
                        if (ItemAPI.checkItemStack(args[0])) {
                            if (API.invFull(p)) {
                                API.sendErrorMessage((CommandSender)p, "\u00a7cDein Inventar ist voll\u00a78!");
                                return false;
                            }
                            ItemStack item = CustomItemManager.getCustomItem(args[0]) != null ? CustomItemManager.getCustomItem(args[0]) : new ItemStack(Material.getMaterial((String)args[0]));
                            item.setAmount(i);
                            p.getInventory().addItem(new ItemStack[]{item});
                            API.sendMessage((CommandSender)p, "\u00a77Du hast dir das Item \u00a7e" + args[0] + "\u00a77 gegeben\u00a78!");
                        } else {
                            API.sendErrorMessage((CommandSender)p, "\u00a7cDieses Item gibt es nicht\u00a78!");
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, "\u00a74Fehler: \u00a7c/item [Item] \u00a77[Anzahl]");
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            } else {
                API.sendErrorMessage(s, API.needPlayer);
            }
        }
        return false;
    }
}

