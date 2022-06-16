
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.WorldAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Weather
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (!(s instanceof Player)) {
            s.sendMessage("\u00a74Fehler: \u00a7c/weather [sun/rain/thunder] [Welt]");
        } else {
            API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/weather [sun/rain/thunder] \u00a77[Welt]");
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Weather")) {
            if (!(s instanceof Player)) {
                if (args.length >= 2) {
                    if (Bukkit.getWorld((String)args[1]) != null) {
                        World world = Bukkit.getWorld((String)args[1]);
                        if (args[0].equalsIgnoreCase("sun")) {
                            WorldAPI.setSun(world);
                            API.sendMessage(s, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eSonnig\u00a77!");
                        } else if (args[0].equalsIgnoreCase("clear")) {
                            WorldAPI.setSun(world);
                            API.sendMessage(s, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eSonnig\u00a77!");
                        } else if (args[0].equalsIgnoreCase("rain")) {
                            WorldAPI.setRain(world);
                            API.sendMessage(s, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eRegen\u00a77!");
                        } else if (args[0].equalsIgnoreCase("thunder")) {
                            WorldAPI.setStorm(world);
                            API.sendMessage(s, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eGewitter\u00a77!");
                        } else if (args[0].equalsIgnoreCase("storm")) {
                            WorldAPI.setStorm(world);
                            API.sendMessage(s, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eGewitter\u00a77!");
                        } else {
                            this.sendSyntax(s);
                        }
                    } else {
                        s.sendMessage("\u00a7cDie Welt \u00a74" + args[1] + "\u00a7c existiert nicht!");
                    }
                } else {
                    this.sendSyntax(s);
                }
            } else {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        this.sendSyntax((CommandSender)p);
                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("sun")) {
                            WorldAPI.setSun(WorldAPI.getPlayerWorld(p));
                            API.sendMessage((CommandSender)p, "Das Wetter steht nun auf \u00a7eSonnig\u00a77!");
                        } else if (args[0].equalsIgnoreCase("clear")) {
                            WorldAPI.setSun(WorldAPI.getPlayerWorld(p));
                            API.sendMessage((CommandSender)p, "Das Wetter steht nun auf \u00a7eSonnig\u00a77!");
                        } else if (args[0].equalsIgnoreCase("rain")) {
                            WorldAPI.setRain(WorldAPI.getPlayerWorld(p));
                            API.sendMessage((CommandSender)p, "Das Wetter steht nun auf \u00a7eRegen\u00a77!");
                        } else if (args[0].equalsIgnoreCase("thunder")) {
                            WorldAPI.setStorm(WorldAPI.getPlayerWorld(p));
                            API.sendMessage((CommandSender)p, "Das Wetter steht nun auf \u00a7eGewitter\u00a77!");
                        } else if (args[0].equalsIgnoreCase("storm")) {
                            WorldAPI.setStorm(WorldAPI.getPlayerWorld(p));
                            API.sendMessage((CommandSender)p, "Das Wetter steht nun auf \u00a7eGewitter\u00a77!");
                        } else {
                            this.sendSyntax((CommandSender)p);
                        }
                    } else if (Bukkit.getWorld((String)args[1]) != null) {
                        World world = Bukkit.getWorld((String)args[1]);
                        if (args[0].equalsIgnoreCase("sun")) {
                            WorldAPI.setSun(world);
                            API.sendMessage((CommandSender)p, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eSonnig\u00a77!");
                        } else if (args[0].equalsIgnoreCase("clear")) {
                            WorldAPI.setSun(world);
                            API.sendMessage((CommandSender)p, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eSonnig\u00a77!");
                        } else if (args[0].equalsIgnoreCase("rain")) {
                            WorldAPI.setRain(world);
                            API.sendMessage((CommandSender)p, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eRegen\u00a77!");
                        } else if (args[0].equalsIgnoreCase("thunder")) {
                            WorldAPI.setStorm(world);
                            API.sendMessage((CommandSender)p, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eGewitter\u00a77!");
                        } else if (args[0].equalsIgnoreCase("storm")) {
                            WorldAPI.setStorm(world);
                            API.sendMessage((CommandSender)p, "Das Wetter der Welt \u00a7e" + world.getName() + "\u00a77 steht nun auf \u00a7eGewitter\u00a77!");
                        } else {
                            this.sendSyntax((CommandSender)p);
                        }
                    } else {
                        p.sendMessage("\u00a7cDie Welt \u00a74" + args[1] + "\u00a7c existiert nicht!");
                        PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            }
        }
        return false;
    }
}

