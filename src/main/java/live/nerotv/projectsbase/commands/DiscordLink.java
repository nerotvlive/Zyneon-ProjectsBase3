
package live.nerotv.projectsbase.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DiscordLink
implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (s == null) {
            DiscordLink.$$$reportNull$$$0(0);
        }
        if (cmd == null) {
            DiscordLink.$$$reportNull$$$0(1);
        }
        if (label == null) {
            DiscordLink.$$$reportNull$$$0(2);
        }
        if (args == null) {
            DiscordLink.$$$reportNull$$$0(3);
        }
        if (cmd.getName().equalsIgnoreCase("DiscordLink")) {
            Bukkit.dispatchCommand((CommandSender)s, (String)"minecord link");
        }
        return false;
    }

    private static  void $$$reportNull$$$0(int n) {
        Object[] arrobject;
        Object[] arrobject2 = new Object[3];
        switch (n) {
            default: {
                arrobject = arrobject2;
                arrobject2[0] = "s";
                break;
            }
            case 1: {
                arrobject = arrobject2;
                arrobject2[0] = "cmd";
                break;
            }
            case 2: {
                arrobject = arrobject2;
                arrobject2[0] = "label";
                break;
            }
            case 3: {
                arrobject = arrobject2;
                arrobject2[0] = "args";
                break;
            }
        }
        arrobject[1] = "live/nerotv/projectsbase/commands/DiscordLink";
        arrobject[2] = "onCommand";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", arrobject));
    }
}

