
package live.nerotv.projectsbase.modules;

import live.nerotv.projectsbase.manager.StaticManager;
import live.nerotv.projectsbase.modules.ModuleAPI;
import live.nerotv.projectsbase.modules.bounty.BountyMain;

public class ModuleLoader {
    public static void loadModules() {
        if (StaticManager.bountyModule) {
            ModuleAPI.sendSystemMessage("Lade BountyModule...");
            if (BountyMain.onLoad()) {
                ModuleAPI.sendSystemMessage("BountyModule erfolgreich geladen!");
            }
        }
    }

    public static void enableModules() {
        if (StaticManager.bountyModule) {
            ModuleAPI.sendSystemMessage("Aktiviere BountyModule...");
            if (BountyMain.onEnable()) {
                ModuleAPI.sendSystemMessage("BountyModule erfolgreich aktiviert!");
            }
        }
    }

    public static void disableModules() {
        if (StaticManager.bountyModule) {
            ModuleAPI.sendSystemMessage("Deaktiviere BountyModule...");
            if (BountyMain.onDisable()) {
                ModuleAPI.sendSystemMessage("BountyModule erfolgreich deaktiviert!");
            }
        }
    }
}

