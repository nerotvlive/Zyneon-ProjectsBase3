
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport
implements CommandExecutor {
    void sendSyntax(Player p) {
        PlayerAPI.sendPlayerMessage(p, "\u00a74Fehler: \u00a7c/tp [X] [Y] [Z]\u00a77, \u00a7c/tp [Spieler] \u00a77[Spieler]\u00a77 oder \u00a7c/tp [Spieler] [X] [Y] [Z]", NewSound.BLOCK_ANVIL_BREAK);
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Teleport")) {
            if (!(s instanceof Player)) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer((String)args[0]) != null) {
                        if (Bukkit.getPlayer((String)args[1]) != null) {
                            Player p = Bukkit.getPlayer((String)args[0]);
                            Player t = Bukkit.getPlayer((String)args[1]);
                            p.teleport(t.getLocation());
                            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                            s.sendMessage(API.Prefix + "Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 zu \u00a7e" + t.getName() + "\u00a77 teleportiert!");
                        } else {
                            s.sendMessage("\u00a7cDer Spieler \u00a74" + args[1] + "\u00a7c ist nicht online!");
                        }
                    } else {
                        s.sendMessage("\u00a7cDer Spieler \u00a74" + args[0] + "\u00a7c ist nicht online!");
                    }
                } else if (args.length == 4) {
                    if (Bukkit.getPlayer((String)args[0]) != null) {
                        if (API.isNumericPart(args[1])) {
                            if (API.isNumericPart(args[2])) {
                                if (API.isNumericPart(args[3])) {
                                    Player p = Bukkit.getPlayer((String)args[0]);
                                    double x = Double.parseDouble(args[1]);
                                    double y = Double.parseDouble(args[2]);
                                    double z = Double.parseDouble(args[3]);
                                    p.teleport(new Location(p.getWorld(), x, y, z, p.getLocation().getYaw(), p.getLocation().getPitch()));
                                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                                    s.sendMessage(API.Prefix + "Du hast den Spieler \u00a7e" + p.getName() + "\u00a77 zu \u00a7aX" + x + " Y" + y + " Z" + z + "\u00a77 teleportiert!");
                                } else {
                                    s.sendMessage("\u00a74Fehler: \u00a7c/tp [Spieler] \u00a77[Spieler]\u00a77 oder \u00a7c/tp [Spieler] [X] [Y] [Z]");
                                }
                            } else {
                                s.sendMessage("\u00a74Fehler: \u00a7c/tp [Spieler] \u00a77[Spieler]\u00a77 oder \u00a7c/tp [Spieler] [X] [Y] [Z]");
                            }
                        } else {
                            s.sendMessage("\u00a74Fehler: \u00a7c/tp [Spieler] \u00a77[Spieler]\u00a77 oder \u00a7c/tp [Spieler] [X] [Y] [Z]");
                        }
                    } else {
                        s.sendMessage(API.noPlayer);
                    }
                } else {
                    s.sendMessage("\u00a74Fehler: \u00a7c/tp [Spieler] \u00a77[Spieler]\u00a77 oder \u00a7c/tp [Spieler] [X] [Y] [Z]");
                }
            } else {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team")) {
                    if (args.length == 0) {
                        this.sendSyntax(p);
                    } else if (args.length == 1) {
                        if (Bukkit.getPlayer((String)args[0]) != null) {
                            Player t = Bukkit.getPlayer((String)args[0]);
                            p.teleport(t.getLocation());
                            API.sendMessage((CommandSender)p, "Du hast dich zu \u00a7e" + t.getName() + "\u00a77 teleportiert!");
                        } else if (PlayerAPI.isRegistered(args[0])) {
                            p.teleport(PlayerAPI.registeredLocation(PlayerAPI.registeredSID(args[0])));
                            API.sendMessage((CommandSender)p, "Du hast dich \u00a7nzu der letzten Position\u00a7r\u00a77 von \u00a7e" + args[0] + "\u00a77 teleportiert.");
                        } else {
                            API.sendErrorMessage((CommandSender)p, API.noPlayer);
                        }
                    } else if (args.length == 2) {
                        if (Bukkit.getPlayer((String)args[0]) != null) {
                            if (Bukkit.getPlayer((String)args[1]) != null) {
                                Player p1 = Bukkit.getPlayer((String)args[0]);
                                Player p2 = Bukkit.getPlayer((String)args[1]);
                                p1.teleport(p2.getLocation());
                                PlayerAPI.playNewSound(p1, NewSound.ENTITY_CHICKEN_EGG);
                                API.sendMessage((CommandSender)p, "Du hast \u00a7e" + p1.getName() + "\u00a77 zu \u00a7e" + p2.getName() + "\u00a77 teleportiert!");
                            } else {
                                PlayerAPI.sendPlayerMessage(p, "\u00a7cDer Spieler \u00a74" + args[0] + "\u00a7c ist nicht online!", NewSound.BLOCK_ANVIL_BREAK);
                            }
                        } else {
                            PlayerAPI.sendPlayerMessage(p, "\u00a7cDer Spieler \u00a74" + args[0] + "\u00a7c ist nicht online!", NewSound.BLOCK_ANVIL_BREAK);
                        }
                    } else if (args.length == 3) {
                        if (API.isNumericPart(args[0])) {
                            if (API.isNumericPart(args[1])) {
                                if (API.isNumericPart(args[2])) {
                                    double x = Double.parseDouble(args[0]);
                                    double y = Double.parseDouble(args[1]);
                                    double z = Double.parseDouble(args[2]);
                                    Location loc = new Location(p.getWorld(), x, y, z, p.getLocation().getYaw(), p.getLocation().getPitch());
                                    p.teleport(loc);
                                    API.sendMessage((CommandSender)p, "Du hast dich zu \u00a7eX" + x + " Y" + y + " Z" + z + "\u00a77 teleportiert!");
                                } else {
                                    this.sendSyntax(p);
                                }
                            } else {
                                this.sendSyntax(p);
                            }
                        } else {
                            this.sendSyntax(p);
                        }
                    } else if (Bukkit.getPlayer((String)args[0]) != null) {
                        if (API.isNumericPart(args[1])) {
                            if (API.isNumericPart(args[2])) {
                                if (API.isNumericPart(args[3])) {
                                    Player t = Bukkit.getPlayer((String)args[0]);
                                    t.teleport(new Location(t.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[2]), t.getLocation().getYaw(), t.getLocation().getPitch()));
                                    PlayerAPI.playNewSound(t, NewSound.ENTITY_CHICKEN_EGG);
                                    API.sendMessage((CommandSender)p, "Du hast den Spieler \u00a7e" + t.getName() + "\u00a77 zu \u00a7a X" + args[1] + " Y" + args[2] + " Z" + args[3] + "\u00a77 teleportiert!");
                                } else {
                                    this.sendSyntax(p);
                                }
                            } else {
                                this.sendSyntax(p);
                            }
                        } else {
                            this.sendSyntax(p);
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPlayer);
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            }
        }
        return false;
    }
}

