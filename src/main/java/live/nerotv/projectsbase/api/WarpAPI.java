
package live.nerotv.projectsbase.api;

import java.io.File;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.WorldAPI;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class WarpAPI {
    public static boolean ifWarpExists(String Warpname) {
        File WarpFile = new File("plugins/Zyneon/Warps/" + (Warpname = Warpname.toLowerCase()) + ".yml");
        return WarpFile.exists();
    }

    public static boolean isWarpEnabled(String Warpname) {
        if (WarpAPI.ifWarpExists(Warpname = Warpname.toLowerCase())) {
            File WarpFile = new File("plugins/Zyneon/Warps/" + Warpname + ".yml");
            YamlConfiguration WF = YamlConfiguration.loadConfiguration((File)WarpFile);
            return WF.getBoolean("Warp.isEnabled");
        }
        return false;
    }

    public static void setWarp(String Warpname, World w, double x, double y, double z, float yaw, float pitch, boolean enable) {
        Warpname = Warpname.toLowerCase();
        File WarpFile = new File("plugins/Zyneon/Warps/" + Warpname + ".yml");
        YamlConfiguration WF = YamlConfiguration.loadConfiguration((File)WarpFile);
        WF.set("Warp.World", (Object)w.getName());
        WF.set("Warp.X", (Object)x);
        WF.set("Warp.Y", (Object)y);
        WF.set("Warp.Z", (Object)z);
        WF.set("Warp.Yaw", (Object)Float.valueOf(yaw));
        WF.set("Warp.Pitch", (Object)Float.valueOf(pitch));
        if (enable) {
            WF.set("Warp.isEnabled", (Object)true);
        } else {
            WF.set("Warp.isEnabled", (Object)false);
        }
        ConfigAPI.saveConfig(WarpFile, WF);
    }

    public static void setWarp(String Warpname, Player p, boolean enable) {
        Warpname = Warpname.toLowerCase();
        WarpAPI.setWarp(Warpname, p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch(), enable);
    }

    public static Location getWarp(String Warpname) {
        Warpname = Warpname.toLowerCase();
        File WarpFile = new File("plugins/Zyneon/Warps/" + Warpname + ".yml");
        YamlConfiguration WF = YamlConfiguration.loadConfiguration((File)WarpFile);
        return new Location(WorldAPI.getWorld(WF.getString("Warp.World")), WF.getDouble("Warp.X"), WF.getDouble("Warp.Y"), WF.getDouble("Warp.Z"), (float)WF.getDouble("Warp.Yaw"), (float)WF.getDouble("Warp.Pitch"));
    }

    public static void removeWarp(String Warpname) {
        if (WarpAPI.ifWarpExists(Warpname = Warpname.toLowerCase())) {
            File WarpFile = new File("plugins/Zyneon/Warps/" + Warpname + ".yml");
            WarpFile.delete();
        }
    }

    public static void enableWarp(String Warpname) {
        if (WarpAPI.ifWarpExists(Warpname = Warpname.toLowerCase())) {
            File WarpFile = new File("plugins/Zyneon/Warps/" + Warpname + ".yml");
            YamlConfiguration WF = YamlConfiguration.loadConfiguration((File)WarpFile);
            WF.set("Warp.isEnabled", (Object)true);
            ConfigAPI.saveConfig(WarpFile, WF);
        }
    }

    public static void disableWarp(String Warpname) {
        if (WarpAPI.ifWarpExists(Warpname = Warpname.toLowerCase())) {
            File WarpFile = new File("plugins/Zyneon/Warps/" + Warpname + ".yml");
            YamlConfiguration WF = YamlConfiguration.loadConfiguration((File)WarpFile);
            WF.set("Warp.isEnabled", (Object)false);
            ConfigAPI.saveConfig(WarpFile, WF);
        }
    }

    public static void getWarpList(CommandSender s) {
        File folder = new File("plugins/Zyneon/Warps/");
        File[] listOfFiles = folder.listFiles();
        s.sendMessage(folder.listFiles().toString());
    }
}

