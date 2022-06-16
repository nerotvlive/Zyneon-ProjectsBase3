
package live.nerotv.projectsbase.modules.bounty;

import java.io.File;
import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.ConfigAPI;
import org.bukkit.configuration.file.YamlConfiguration;

public class BountyAPI {
    public static File bountyFile = new File("plugins/Zyneon/Modules/bountyModule/config.yml");
    public static YamlConfiguration bF = YamlConfiguration.loadConfiguration((File)bountyFile);

    public static void checkConfig() {
        bF.set("API.Version", (Object)Main.Version);
        ConfigAPI.saveConfig(bountyFile, bF);
    }
}

