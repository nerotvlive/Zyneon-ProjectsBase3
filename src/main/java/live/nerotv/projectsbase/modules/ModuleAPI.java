
package live.nerotv.projectsbase.modules;

import live.nerotv.projectsbase.api.ServerAPI;

public class ModuleAPI {
    public static void sendSystemMessage(String message) {
        ServerAPI.sendConsoleMessage("\u00a7e\u00a7l[ModuleSystem]\u00a7r " + message.replace("&", "\u00a7"));
    }
}

