
package live.nerotv.projectsbase.manager;

import java.io.File;
import java.util.UUID;
import live.nerotv.projectsbase.api.ConfigAPI;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PointsManager {
    public static Integer getAP(UUID UID) {
        return PointsManager.getAP(UID.toString());
    }

    public static Integer getAP(Player player) {
        return PointsManager.getAP(player.getUniqueId());
    }

    public static void setAP(UUID UID, Integer Value) {
        PointsManager.setAP(UID.toString(), Value);
    }

    public static void setAP(Player player, Integer Value) {
        PointsManager.setAP(player.getUniqueId(), Value);
    }

    public static Integer getAP(String SID) {
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        if (PlayerFile.exists()) {
            Integer Value;
            ConfigAPI.reloadConfig(PlayerFile, PF);
            if (PF.contains("AchievementPoints.Value")) {
                Value = PF.getInt("AchievementPoints.Value");
                if (Value < 1) {
                    PF.set("AchievementPoints.Value", (Object)1);
                    Value = 1;
                } else {
                    Value = PF.getInt("AchievementPoints.Value");
                }
            } else {
                PF.set("AchievementPoints.Value", (Object)1);
                Value = 1;
            }
            return Value - 1;
        }
        return 0;
    }

    public static void setAP(String SID, Integer Value) {
        File PlayerFile = new File("plugins/Zyneon/Players/" + SID + ".yml");
        YamlConfiguration PF = YamlConfiguration.loadConfiguration((File)PlayerFile);
        ConfigAPI.reloadConfig(PlayerFile, PF);
        Integer FinalValue = Value < 1 ? Integer.valueOf(1) : Integer.valueOf(Value + 1);
        PF.set("AchievementPoints.Value", (Object)FinalValue);
        ConfigAPI.saveConfig(PlayerFile, PF);
    }

    public static Boolean checkPayment(Integer AP, Integer Price) {
        Integer Value = AP + 1 - Price;
        if (Value < 1) {
            return false;
        }
        return true;
    }
}

