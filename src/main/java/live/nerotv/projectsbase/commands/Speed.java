
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed
implements CommandExecutor {
    private void sendSyntax(CommandSender s) {
        if (s instanceof Player) {
            Player p = (Player)s;
            API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/speed [0-9/d/default] \u00a77[Spieler]");
        } else {
            API.sendErrorMessage(s, "\u00a74Fehler: \u00a7c/speed [0-9/d/default] [Spieler]");
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Speed")) {
            if (s instanceof Player) {
                Player p = (Player)s;
                if (p.hasPermission("zyneon.team.speed")) {
                    if (args.length == 0) {
                        this.sendSyntax((CommandSender)p);
                    } else if (args.length == 1) {
                        if (API.isSpeedCompatible(args[0])) {
                            if (p.isFlying()) {
                                String Speed2 = "0." + args[0];
                                p.setFlySpeed(Float.parseFloat(Speed2));
                                API.sendMessage((CommandSender)p, "Du hast deine \u00a7eFluggeschwindigkeit\u00a77 auf \u00a7e" + args[0] + "\u00a77 gesetzt!");
                            } else {
                                String Speed3 = "0." + args[0];
                                p.setWalkSpeed(Float.parseFloat(Speed3));
                                API.sendMessage((CommandSender)p, "Du hast deine \u00a7eLaufgeschwindigkeit\u00a77 auf \u00a7e" + args[0] + "\u00a77 gesetzt!");
                            }
                        } else if (args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("default")) {
                            if (p.isFlying()) {
                                String Speed4 = "0.1";
                                p.setFlySpeed(Float.parseFloat(Speed4));
                                API.sendMessage((CommandSender)p, "Du hast deine \u00a7eFluggeschwindigkeit\u00a77 auf \u00a7eStandard\u00a77 gesetzt!");
                            } else {
                                String Speed5 = "0.2";
                                p.setWalkSpeed(Float.parseFloat(Speed5));
                                API.sendMessage((CommandSender)p, "Du hast deine \u00a7eLaufgeschwindigkeit\u00a77 auf \u00a7eStandard\u00a77 gesetzt!");
                            }
                        } else {
                            API.sendErrorMessage(s, "\u00a7cDas ist keine g\u00fcltige Zahl!");
                        }
                    } else if (p.hasPermission("zyneon.team.speed.other")) {
                        if (Bukkit.getPlayer((String)args[1]) != null) {
                            if (API.isSpeedCompatible(args[0])) {
                                Player t = Bukkit.getPlayer((String)args[1]);
                                if (t.isFlying()) {
                                    String Speed6 = "0." + args[0];
                                    t.setFlySpeed(Float.parseFloat(Speed6));
                                    API.sendMessage(s, "Du hast die \u00a7eFluggeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7e" + args[0] + "\u00a77 gesetzt!");
                                } else {
                                    String Speed7 = "0." + args[0];
                                    t.setWalkSpeed(Float.parseFloat(Speed7));
                                    API.sendMessage(s, "Du hast die \u00a7eLaufgeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7e" + args[0] + "\u00a77 gesetzt!");
                                }
                            } else if (args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("default")) {
                                Player t = Bukkit.getPlayer((String)args[1]);
                                if (t.isFlying()) {
                                    String Speed8 = "0.1";
                                    t.setFlySpeed(Float.parseFloat(Speed8));
                                    API.sendMessage(s, "Du hast die \u00a7eFluggeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7eStandard\u00a77 gesetzt!");
                                } else {
                                    String Speed9 = "0.2";
                                    t.setWalkSpeed(Float.parseFloat(Speed9));
                                    API.sendMessage(s, "Du hast die \u00a7eLaufgeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7eStandard\u00a77 gesetzt!");
                                }
                            } else {
                                API.sendErrorMessage(s, "\u00a7cDas ist keine g\u00fcltige Zahl!");
                            }
                        } else {
                            API.sendErrorMessage(s, API.noPlayer);
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPerms);
                    }
                } else {
                    API.sendErrorMessage((CommandSender)p, API.noPerms);
                }
            } else if (args.length == 0) {
                this.sendSyntax(s);
            } else if (args.length == 1) {
                this.sendSyntax(s);
            } else if (Bukkit.getPlayer((String)args[1]) != null) {
                if (API.isSpeedCompatible(args[0])) {
                    Player t = Bukkit.getPlayer((String)args[1]);
                    if (t.isFlying()) {
                        String Speed10 = "0." + args[0];
                        t.setFlySpeed(Float.parseFloat(Speed10));
                        API.sendMessage(s, "Du hast die \u00a7eFluggeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7e" + args[0] + "\u00a77 gesetzt!");
                    } else {
                        String Speed11 = "0." + args[0];
                        t.setWalkSpeed(Float.parseFloat(Speed11));
                        API.sendMessage(s, "Du hast die \u00a7eLaufgeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7e" + args[0] + "\u00a77 gesetzt!");
                    }
                } else if (args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("default")) {
                    Player t = Bukkit.getPlayer((String)args[1]);
                    if (t.isFlying()) {
                        String Speed12 = "0.1";
                        t.setFlySpeed(Float.parseFloat(Speed12));
                        API.sendMessage(s, "Du hast die \u00a7eFluggeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7eStandard\u00a77 gesetzt!");
                    } else {
                        String Speed13 = "0.2";
                        t.setWalkSpeed(Float.parseFloat(Speed13));
                        API.sendMessage(s, "Du hast die \u00a7eLaufgeschwindigkeit\u00a77 von \u00a7a" + t.getName() + "\u00a77auf \u00a7eStandard\u00a77 gesetzt!");
                    }
                } else {
                    API.sendErrorMessage(s, "\u00a7cDas ist keine g\u00fcltige Zahl!");
                }
            } else {
                API.sendErrorMessage(s, API.noPlayer);
            }
        }
        return false;
    }
}

