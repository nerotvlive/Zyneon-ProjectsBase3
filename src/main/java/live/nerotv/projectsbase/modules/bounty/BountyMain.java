
package live.nerotv.projectsbase.modules.bounty;

import live.nerotv.projectsbase.modules.ModuleAPI;
import live.nerotv.projectsbase.modules.bounty.BountyAPI;

public class BountyMain {
    public static boolean isActive = false;

    private static void sendSystemMessage(String message) {
        ModuleAPI.sendSystemMessage("\u00a7e[Bounties] \u00a7r" + message.replace("&", "\u00a7"));
    }

    public static boolean onLoad() {
        isActive = false;
        BountyMain.sendSystemMessage("\u00dcberpr\u00fcfe Bounties Configdatei...");
        BountyAPI.checkConfig();
        return true;
    }

    public static boolean onEnable() {
        BountyAPI.checkConfig();
        isActive = true;
        return true;
    }

    public static boolean onDisable() {
        isActive = false;
        return true;
    }
}

