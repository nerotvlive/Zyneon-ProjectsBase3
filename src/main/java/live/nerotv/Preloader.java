
package live.nerotv;

import live.nerotv.projectsbase.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Preloader
extends JavaPlugin {
    private static Preloader instance;
    private static String version;

    public static String getVersion() {
        if (version.equalsIgnoreCase("v")) {
            return "0";
        }
        return version;
    }

    public static Preloader getInstance() {
        return instance;
    }

    public void onLoad() {
        this.sendMessage("Loading ProjectsBase preloader...");
        version = this.getDescription().getVersion();
        this.sendMessage("Loading ProjectsBase...");
        Main.onLoad();
    }

    public void onEnable() {
        this.sendMessage("Checking for plugin dependencies...");
        if (Main.PM.getPlugin("Vault") == null) {
            this.sendMessage("Couldn't find Vault, disabling economy features!");
        }
        if (Main.PM.getPlugin("LuckPerms") == null) {
            this.sendMessage("Couldn't find LuckPerms, disabling bypass features!");
        }
        if (Main.PM.getPlugin("floodgate") == null) {
            this.sendMessage("Couldn't find floodgate, disabling Bedrock-Protocol support!");
        }
        this.sendMessage("Enabling ProjectsBase...");
        instance = this;
        Main.onEnable();
    }

    public void onDisable() {
        this.sendMessage("Disabling ProjectsBase plugin...");
        Main.onDisable();
        version = null;
        instance = null;
    }

    private void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("\u00a77[ProjectsBase] \u00a7e[PRELOADER] \u00a77" + message);
    }

    static {
        version = "v";
    }
}

