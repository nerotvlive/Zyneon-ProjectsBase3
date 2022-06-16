
package live.nerotv.projectsbase.api;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigAPI {
    public static File Config = new File("plugins/Zyneon/config.yml");
    public static YamlConfiguration CFG = YamlConfiguration.loadConfiguration((File)Config);
    public static File UUID = new File("plugins/Zyneon/Players/all.yml");
    public static YamlConfiguration UID = YamlConfiguration.loadConfiguration((File)UUID);

    public static void checkEntry(String path, Object content, File file, YamlConfiguration cfg) {
        ConfigAPI.reloadConfig(file, cfg);
        if (!file.exists()) {
            cfg.set(path, content);
            ConfigAPI.saveConfig(file, cfg);
        } else if (!cfg.contains(path)) {
            cfg.set(path, content);
            ConfigAPI.saveConfig(file, cfg);
        }
    }

    public static void saveConfig(File file, YamlConfiguration cfg) {
        try {
            cfg.save(file);
        }
        catch (IOException ef) {
            ef.printStackTrace();
        }
        ConfigAPI.reloadConfig(file, cfg);
    }

    public static void reloadConfig(File file, YamlConfiguration cfg) {
        cfg = YamlConfiguration.loadConfiguration((File)file);
    }
}

