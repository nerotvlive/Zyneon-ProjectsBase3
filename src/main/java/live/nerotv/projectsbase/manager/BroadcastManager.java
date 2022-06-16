
package live.nerotv.projectsbase.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.listener.WorldChange;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class BroadcastManager {
    static File Config = new File("plugins/Zyneon/config.yml");
    static YamlConfiguration cfg = YamlConfiguration.loadConfiguration((File)Config);
    static ArrayList<String> Messages = new ArrayList();

    private static void saveDefaultConfig() {
        ConfigAPI.checkEntry("Core.Settings.Broadcasts.Enable", false, Config, cfg);
        ConfigAPI.checkEntry("Core.Settings.Broadcasts.SecondInterval", 10, Config, cfg);
        ConfigAPI.checkEntry("Core.Strings.Broadcasts", Messages, Config, cfg);
        ConfigAPI.saveConfig(Config, cfg);
        ConfigAPI.reloadConfig(Config, cfg);
        Messages = (ArrayList)cfg.getList("Core.Strings.Broadcasts");
    }

    public static void start() {
        BroadcastManager.saveDefaultConfig();
        if (cfg.getBoolean("Core.Settings.Broadcasts.Enable")) {
            BroadcastManager.startBroadcastTimer(Bukkit.getServer().getScheduler());
        }
    }

    private static void startBroadcastTimer(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask((Plugin)Main.getInstance(), () -> {
            Integer size = Messages.size();
            Integer random = ThreadLocalRandom.current().nextInt(0, size);
            Bukkit.broadcastMessage((String)Messages.get(random).replace("&", "\u00a7"));
            API.checkForRestart();
            BroadcastManager.startBroadcastTimer(scheduler);
            WorldChange.started = true;
        }, cfg.getLong("Core.Settings.Broadcasts.SecondInterval") * 20L);
    }
}

