
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ServerAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SRL
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("SRL")) {
            if (s.hasPermission("zyneon.leading")) {
                if (API.isStopping) {
                    API.sendMessage(s, "\u00a7cDer Server f\u00e4hrt bereits runter!");
                    return false;
                }
                API.sendMessage(s, "\u00a77Du hast den \u00a7eStopvorgang\u00a77 gestartet\u00a78.");
                ServerAPI.scheduledShutdown();
            } else {
                API.sendErrorMessage(s, API.noPerms);
            }
        }
        return false;
    }
}

