
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

public class Time
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (!(s instanceof Player)) {
            s.sendMessage("\u00a74Fehler: \u00a7c/time [set/add/remove] [Wert] [Welt]");
        } else {
            API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/time [set/add/remove] [Wert] \u00a77[Welt]");
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Time")) {
            if (!(s instanceof Player)) {
                if (args.length < 3) {
                    s.sendMessage("\u00a74Fehler: \u00a7c/time [set/add/remove] [Wert] [Welt]");
                } else if (Bukkit.getWorld((String)args[2]) != null) {
                    World world = Bukkit.getWorld((String)args[2]);
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args[1].equalsIgnoreCase("day")) {
                            WorldAPI.setTime(0L, world);
                            API.sendMessage(s, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 auf \u00a7eTag\u00a77 gesetzt!");
                        } else if (args[1].equalsIgnoreCase("night")) {
                            WorldAPI.setTime(13500L, world);
                            API.sendMessage(s, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 auf \u00a7eNacht\u00a77 gesetzt!");
                        } else if (API.isNumeric(args[1])) {
                            WorldAPI.setTime(Integer.parseInt(args[1]), world);
                            API.sendMessage(s, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 auf \u00a7e" + args[1] + "\u00a77 gesetzt!");
                        } else {
                            this.sendSyntax(s);
                        }
                    } else if (args[0].equalsIgnoreCase("add")) {
                        if (API.isNumeric(args[1])) {
                            WorldAPI.addTime(Integer.parseInt(args[1]), world);
                            API.sendMessage(s, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 um \u00a7e" + args[1] + "\u00a77 erh\u00f6ht!");
                        } else {
                            this.sendSyntax(s);
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (API.isNumeric(args[1])) {
                            WorldAPI.removeTime(Integer.parseInt(args[1]), world);
                            API.sendMessage(s, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 um \u00a7e" + args[1] + "\u00a77 verringert!");
                        } else {
                            this.sendSyntax(s);
                        }
                    } else {
                        this.sendSyntax(s);
                    }
                } else {
                    s.sendMessage("\u00a7cDie Welt \u00a74" + args[2] + "\u00a7c existiert nicht!");
                }
            } else {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("set")) {
                            if (args[1].equalsIgnoreCase("day")) {
                                WorldAPI.setTime(0L, p.getWorld());
                                API.sendMessage((CommandSender)p, "Du hast die Zeit auf \u00a7eTag\u00a77 gesetzt!");
                            } else if (args[1].equalsIgnoreCase("night")) {
                                WorldAPI.setTime(13500L, p.getWorld());
                                API.sendMessage((CommandSender)p, "Du hast die Zeit auf \u00a7eNacht\u00a77 gesetzt!");
                            } else if (API.isNumeric(args[1])) {
                                WorldAPI.setTime(Integer.parseInt(args[1]), p.getWorld());
                                API.sendMessage((CommandSender)p, "Du hast die Zeit auf \u00a7e" + args[1] + "\u00a77 gesetzt!");
                            } else {
                                this.sendSyntax(s);
                            }
                        } else if (args[0].equalsIgnoreCase("add")) {
                            if (API.isNumeric(args[1])) {
                                WorldAPI.addTime(Integer.parseInt(args[1]), p.getWorld());
                                API.sendMessage((CommandSender)p, "Du hast die Zeit um \u00a7e" + args[1] + "\u00a77 erh\u00f6ht!");
                            } else {
                                this.sendSyntax(s);
                            }
                        } else if (args[0].equalsIgnoreCase("remove")) {
                            if (API.isNumeric(args[1])) {
                                WorldAPI.removeTime(Integer.parseInt(args[1]), p.getWorld());
                                API.sendMessage((CommandSender)p, "Du hast die Zeit um \u00a7e" + args[1] + "\u00a77 verringert!");
                            } else {
                                this.sendSyntax(s);
                            }
                        } else {
                            this.sendSyntax(s);
                        }
                    } else if (args.length > 2) {
                        if (Bukkit.getWorld((String)args[2]) != null) {
                            World world = Bukkit.getWorld((String)args[2]);
                            if (args[0].equalsIgnoreCase("set")) {
                                if (args[1].equalsIgnoreCase("day")) {
                                    WorldAPI.setTime(0L, world);
                                    API.sendMessage((CommandSender)p, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 auf \u00a7eTag\u00a77 gesetzt!");
                                } else if (args[1].equalsIgnoreCase("night")) {
                                    WorldAPI.setTime(13500L, world);
                                    API.sendMessage((CommandSender)p, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 auf \u00a7eNacht\u00a77 gesetzt!");
                                } else if (API.isNumeric(args[1])) {
                                    WorldAPI.setTime(Integer.parseInt(args[1]), world);
                                    API.sendMessage((CommandSender)p, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 auf \u00a7e" + args[1] + "\u00a77 gesetzt!");
                                } else {
                                    this.sendSyntax(s);
                                }
                            } else if (args[0].equalsIgnoreCase("add")) {
                                if (API.isNumeric(args[1])) {
                                    WorldAPI.addTime(Integer.parseInt(args[1]), world);
                                    API.sendMessage((CommandSender)p, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 um \u00a7e" + args[1] + "\u00a77 erh\u00f6ht!");
                                } else {
                                    this.sendSyntax(s);
                                }
                            } else if (args[0].equalsIgnoreCase("remove")) {
                                if (API.isNumeric(args[1])) {
                                    WorldAPI.removeTime(Integer.parseInt(args[1]), world);
                                    API.sendMessage((CommandSender)p, "Du hast die Zeit der Welt \u00a7e" + world.getName() + "\u00a77 um \u00a7e" + args[1] + "\u00a77 verringert!");
                                } else {
                                    this.sendSyntax(s);
                                }
                            } else {
                                this.sendSyntax(s);
                            }
                        } else {
                            p.sendMessage("\u00a7cDie Welt \u00a74" + args[2] + "\u00a7c existiert nicht!");
                            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
                        }
                    } else {
                        this.sendSyntax(s);
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            }
        }
        return false;
    }
}

