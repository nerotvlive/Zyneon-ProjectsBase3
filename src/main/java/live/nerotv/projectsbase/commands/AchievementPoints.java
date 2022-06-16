
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.ServerAPI;
import live.nerotv.projectsbase.manager.PointsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AchievementPoints
implements CommandExecutor {
    private void sendSyntax(Player p) {
        if (p.hasPermission("zyneon.team")) {
            PlayerAPI.sendPlayerMessage(p, "\u00a74Fehler: \u00a7c/ap \u00a77[set/\u00a7fget\u00a77/remove/add\u00a77] \u00a77[Spieler/\u00a7fSpieler\u00a77] [Wert]", NewSound.BLOCK_ANVIL_BREAK);
        } else {
            PlayerAPI.sendPlayerMessage(p, "\u00a74Fehler: \u00a7c/ap \u00a77[get] [Spielername]", NewSound.BLOCK_ANVIL_BREAK);
        }
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("AchievementPoints")) {
            if (!(s instanceof Player)) {
                if (args.length < 2) {
                    s.sendMessage("Fehler: ap get [Spieler]");
                    s.sendMessage("Fehler: ap set/add/remove [Spieler] [Wert]");
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("get")) {
                        if (Bukkit.getPlayer((String)args[1]) != null) {
                            Player p = Bukkit.getPlayer((String)args[1]);
                            s.sendMessage(API.Prefix + "\u00a77Der Spieler \u00a7e" + p.getName() + "\u00a77 hat \u00a7a" + PointsManager.getAP(p) + " AP\u00a77!");
                        } else {
                            ServerAPI.sendConsoleMessage(API.noPlayer);
                        }
                    } else {
                        s.sendMessage("Fehler: ap get [Spieler]");
                        s.sendMessage("Fehler: ap set/add/remove [Spieler] [Wert]");
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (Bukkit.getPlayer((String)args[1]) != null) {
                        Player p = Bukkit.getPlayer((String)args[1]);
                        if (API.isNumeric(args[2])) {
                            PointsManager.setAP(p, (Integer)Integer.parseInt(args[2]));
                            s.sendMessage(API.Prefix + "\u00a77Der Spieler \u00a7e" + p.getName() + "\u00a77 hat nun \u00a7a" + PointsManager.getAP(p) + " AP\u00a77!");
                            API.sendMessage((CommandSender)p, "Du hast nun \u00a7e" + PointsManager.getAP(p) + " AP\u00a77!");
                        } else {
                            ServerAPI.sendConsoleMessage("\u00a7cDie AP-Anzahl muss eine Zahl sein!");
                        }
                    } else {
                        ServerAPI.sendConsoleMessage(API.noPlayer);
                    }
                } else if (args[0].equalsIgnoreCase("add")) {
                    if (Bukkit.getPlayer((String)args[1]) != null) {
                        Player p = Bukkit.getPlayer((String)args[1]);
                        if (API.isNumeric(args[2])) {
                            Integer Value = PointsManager.getAP(p) + Integer.parseInt(args[2]);
                            PointsManager.setAP(p, Value);
                            s.sendMessage(API.Prefix + "\u00a77Der Spieler \u00a7e" + p.getName() + "\u00a77 hat nun \u00a7a" + PointsManager.getAP(p) + " AP\u00a77!");
                            API.sendMessage((CommandSender)p, "Du hast nun \u00a7e" + PointsManager.getAP(p) + " AP\u00a77!");
                        } else {
                            ServerAPI.sendConsoleMessage("\u00a7cDie AP-Anzahl muss eine Zahl sein!");
                        }
                    } else {
                        ServerAPI.sendConsoleMessage(API.noPlayer);
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (Bukkit.getPlayer((String)args[1]) != null) {
                        Player p = Bukkit.getPlayer((String)args[1]);
                        if (API.isNumeric(args[2])) {
                            Integer Value = PointsManager.getAP(p) - Integer.parseInt(args[2]);
                            PointsManager.setAP(p, Value);
                            s.sendMessage(API.Prefix + "\u00a77Der Spieler \u00a7e" + p.getName() + "\u00a77 hat nun \u00a7a" + PointsManager.getAP(p) + " AP\u00a77!");
                            API.sendMessage((CommandSender)p, "Du hast nun \u00a7e" + PointsManager.getAP(p) + " AP\u00a77!");
                        } else {
                            ServerAPI.sendConsoleMessage("\u00a7cDie AP-Anzahl muss eine Zahl sein!");
                        }
                    } else {
                        ServerAPI.sendConsoleMessage(API.noPlayer);
                    }
                } else {
                    s.sendMessage("Fehler: ap set/add/remove [Spieler] [Wert]");
                    s.sendMessage("Fehler: ap get [Spieler]");
                }
            } else {
                Player p = (Player)s;
                if (args.length == 0) {
                    API.sendMessage((CommandSender)p, "Du hast momentan \u00a7e" + PointsManager.getAP(p) + " AP\u00a77.");
                } else if (args.length == 1) {
                    this.sendSyntax(p);
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("get")) {
                        if (Bukkit.getPlayer((String)args[1]) != null) {
                            Player p2 = Bukkit.getPlayer((String)args[1]);
                            API.sendMessage((CommandSender)p, "Der Spieler \u00a7e" + p2.getName() + "\u00a77 hat \u00a7a" + PointsManager.getAP(p2) + " AP\u00a77.");
                        } else {
                            API.sendErrorMessage(s, API.noPlayer);
                        }
                    } else {
                        this.sendSyntax(p);
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (p.hasPermission("zyneon.team")) {
                        if (Bukkit.getPlayer((String)args[1]) != null) {
                            Player p2 = Bukkit.getPlayer((String)args[1]);
                            if (API.isNumeric(args[2])) {
                                PointsManager.setAP(p2, (Integer)Integer.parseInt(args[2]));
                                API.sendMessage((CommandSender)p, "Der Spieler \u00a7e" + p2.getName() + "\u00a77 hat nun \u00a7a" + PointsManager.getAP(p2) + " AP\u00a77!");
                                API.sendMessage((CommandSender)p2, "Du hast nun \u00a7e" + PointsManager.getAP(p2) + " AP\u00a77!");
                            } else {
                                PlayerAPI.sendPlayerMessage(p, "\u00a7cDie AP-Anzahl muss eine Zahl sein!", NewSound.BLOCK_ANVIL_BREAK);
                            }
                        } else {
                            API.sendErrorMessage((CommandSender)p, API.noPlayer);
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPerms);
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (p.hasPermission("zyneon.team")) {
                        if (Bukkit.getPlayer((String)args[1]) != null) {
                            Player p2 = Bukkit.getPlayer((String)args[1]);
                            if (API.isNumeric(args[2])) {
                                Integer Value = PointsManager.getAP(p2) - Integer.parseInt(args[2]);
                                PointsManager.setAP(p2, Value);
                                API.sendMessage((CommandSender)p, "Der Spieler \u00a7e" + p2.getName() + "\u00a77 hat nun \u00a7a" + PointsManager.getAP(p2) + " AP\u00a77!");
                                API.sendMessage((CommandSender)p2, "Du hast nun \u00a7e" + PointsManager.getAP(p2) + " AP\u00a77!");
                            } else {
                                PlayerAPI.sendPlayerMessage(p, "\u00a7cDie AP-Anzahl muss eine Zahl sein!", NewSound.BLOCK_ANVIL_BREAK);
                            }
                        } else {
                            API.sendErrorMessage((CommandSender)p, API.noPlayer);
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPerms);
                    }
                } else if (args[0].equalsIgnoreCase("add")) {
                    if (p.hasPermission("zyneon.team")) {
                        if (Bukkit.getPlayer((String)args[1]) != null) {
                            Player p2 = Bukkit.getPlayer((String)args[1]);
                            if (API.isNumeric(args[2])) {
                                Integer Value = PointsManager.getAP(p2) + Integer.parseInt(args[2]);
                                PointsManager.setAP(p2, Value);
                                API.sendMessage((CommandSender)p, "Der Spieler \u00a7e" + p2.getName() + "\u00a77 hat nun \u00a7a" + PointsManager.getAP(p2) + " AP\u00a77!");
                                API.sendMessage((CommandSender)p2, "Du hast nun \u00a7e" + PointsManager.getAP(p2) + " AP\u00a77!");
                            } else {
                                PlayerAPI.sendPlayerMessage(p, "\u00a7cDie AP-Anzahl muss eine Zahl sein!", NewSound.BLOCK_ANVIL_BREAK);
                            }
                        } else {
                            API.sendErrorMessage((CommandSender)p, API.noPlayer);
                        }
                    } else {
                        API.sendErrorMessage((CommandSender)p, API.noPerms);
                    }
                }
            }
        }
        return false;
    }
}

