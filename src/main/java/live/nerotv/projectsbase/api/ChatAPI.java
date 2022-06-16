
package live.nerotv.projectsbase.api;

import java.io.File;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.manager.InventoryManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChatAPI {
    public static String getMainColor(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (PlayerFile.exists() && PF.contains("Spieler.ChatColor.Main")) {
            return PF.getString("Spieler.ChatColor.Main");
        }
        return "7";
    }

    public static String getTypeColor(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (PlayerFile.exists() && PF.contains("Spieler.ChatColor.Type")) {
            return PF.getString("Spieler.ChatColor.Type");
        }
        return "e";
    }

    public static String getUserColor(Player player) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (PlayerFile.exists() && PF.contains("Spieler.ChatColor.User")) {
            return PF.getString("Spieler.ChatColor.User");
        }
        return "a";
    }

    public static void changeMainColor(Player player, ItemStack mainColorItem) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        String color = (String)mainColorItem.getItemMeta().getLore().get(0);
        String path = "Spieler.ChatColor.Main";
        ChatAPI.changeColor(PlayerFile, PF, path, color);
        player.closeInventory();
        InventoryManager.openSettingsInventory(player);
    }

    public static void changeTypeColor(Player player, ItemStack typeColorItem) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        String color = (String)typeColorItem.getItemMeta().getLore().get(0);
        String path = "Spieler.ChatColor.Type";
        ChatAPI.changeColor(PlayerFile, PF, path, color);
        player.closeInventory();
        InventoryManager.openSettingsInventory(player);
    }

    public static void changeUserColor(Player player, ItemStack userColorItem) {
        String SID = player.getUniqueId().toString();
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        String color = (String)userColorItem.getItemMeta().getLore().get(0);
        String path = "Spieler.ChatColor.User";
        ChatAPI.changeColor(PlayerFile, PF, path, color);
        player.closeInventory();
        InventoryManager.openSettingsInventory(player);
    }

    public static void changeColor(File playerFile, YamlConfiguration pF, String path, String color) {
        if (color.contains("\u00a70Schwarz")) {
            pF.set(path, (Object)"1");
        } else if (color.contains("\u00a71Dunkelblau")) {
            pF.set(path, (Object)"2");
        } else if (color.contains("\u00a72Dunkelgr\u00fcn")) {
            pF.set(path, (Object)"3");
        } else if (color.contains("\u00a73Dunkelaqua")) {
            pF.set(path, (Object)"4");
        } else if (color.contains("\u00a74Dunkelrot")) {
            pF.set(path, (Object)"5");
        } else if (color.contains("\u00a75Lila")) {
            pF.set(path, (Object)"6");
        } else if (color.contains("\u00a76Gold")) {
            pF.set(path, (Object)"7");
        } else if (color.contains("\u00a77Hellgrau")) {
            pF.set(path, (Object)"8");
        } else if (color.contains("\u00a78Dunkelgrau")) {
            pF.set(path, (Object)"9");
        } else if (color.contains("\u00a79Blau")) {
            pF.set(path, (Object)"a");
        } else if (color.contains("\u00a7aHellgr\u00fcn")) {
            pF.set(path, (Object)"b");
        } else if (color.contains("\u00a7bAqua")) {
            pF.set(path, (Object)"c");
        } else if (color.contains("\u00a7cRot")) {
            pF.set(path, (Object)"d");
        } else if (color.contains("\u00a7dPink")) {
            pF.set(path, (Object)"e");
        } else if (color.contains("\u00a7eGelb")) {
            pF.set(path, (Object)"f");
        } else if (color.contains("\u00a7fWei\u00df")) {
            pF.set(path, (Object)"0");
        }
        ConfigAPI.saveConfig(playerFile, pF);
    }
}

