
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Author
implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Author")) {
            s.sendMessage("\u00a78");
            s.sendMessage("\u00a78");
            s.sendMessage("\u00a78");
            s.sendMessage("\u00a78");
            s.sendMessage("\u00a78=================================================");
            s.sendMessage("\u00a78");
            s.sendMessage("\u00a7d Zyneon\u00a78: \u00a77ProjectsBase \u00a79" + Main.Version);
            s.sendMessage("\u00a7f by \u00a7cnerotvlive\u00a7f.");
            s.sendMessage("\u00a78");
            s.sendMessage("\u00a78=================================================");
            s.sendMessage("\u00a78");
            s.sendMessage("\u00a78");
        }
        return false;
    }
}

