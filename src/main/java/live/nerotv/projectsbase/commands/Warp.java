
package live.nerotv.projectsbase.commands;

import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.api.WarpAPI;
import live.nerotv.projectsbase.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Warp
implements CommandExecutor {
    void sendES(Player p) {
        PlayerAPI.sendPlayerMessage(p, "\u00a7cDieser Warp existiert bereits!", NewSound.BLOCK_ANVIL_BREAK);
    }

    void sendESN(Player p) {
        PlayerAPI.sendPlayerMessage(p, "\u00a7cDieser Warp existiert nicht!", NewSound.BLOCK_ANVIL_BREAK);
    }

    public boolean onCommand(@NotNull CommandSender s, Command cmd, @NotNull String label, String[] args) {
        if (s == null) {
            Warp.$$$reportNull$$$0(0);
        }
        if (label == null) {
            Warp.$$$reportNull$$$0(1);
        }
        if (cmd.getName().equalsIgnoreCase("Warp")) {
            if (!(s instanceof Player)) {
                s.sendMessage("\u00a7cDazu \u00a74musst\u00a7c du ein Spieler sein\u00a74!");
            } else {
                Player p = (Player)s;
                if (args.length == 0) {
                    InventoryManager.openInv002(p);
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {
                        if (p.hasPermission("zyneon.team")) {
                            p.performCommand("warp list 1");
                        } else if (args[0].equalsIgnoreCase("old") && ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Map.Enable")) {
                            Location l = p.getLocation();
                            p.teleport(new Location(Bukkit.getWorld((String)"Welt_old"), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch()));
                        } else if (args[0].equalsIgnoreCase("new") && ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Map.Enable")) {
                            Location l = p.getLocation();
                            p.teleport(new Location(Bukkit.getWorld((String)"Welt"), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch()));
                        } else if (args[0].equalsIgnoreCase("map") && ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Map.Enable")) {
                            this.processTeleport(p);
                        } else {
                            p.performCommand("warp");
                        }
                    } else {
                        p.performCommand("warp");
                    }
                } else {
                    String Warpname = args[1];
                    if (p.hasPermission("zyneon.team")) {
                        if (args[0].equalsIgnoreCase("set")) {
                            if (WarpAPI.ifWarpExists(Warpname)) {
                                this.sendES(p);
                            } else {
                                WarpAPI.setWarp(Warpname, p, false);
                                API.sendMessage((CommandSender)p, "Du hast \u00a7aerfolgreich den Warp-Punkt \"\u00a7e" + Warpname + "\u00a77\" hinzugef\u00fcgt. Nutze \u00a7f/warp enable " + Warpname + "\u00a77 oder \u00a7f/warp toggle " + Warpname + "\u00a77, um den Warp-Punkt zu aktivieren.");
                            }
                        } else if (args[0].equalsIgnoreCase("teleport")) {
                            if (WarpAPI.isWarpEnabled(Warpname)) {
                                p.teleport(WarpAPI.getWarp(Warpname));
                                PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("tp")) {
                            if (WarpAPI.isWarpEnabled(Warpname)) {
                                p.teleport(WarpAPI.getWarp(Warpname));
                                PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("enable")) {
                            if (WarpAPI.ifWarpExists(Warpname)) {
                                WarpAPI.enableWarp(Warpname);
                                API.sendMessage((CommandSender)p, "Du hast \u00a7aerfolgreich\u00a77 den Warp \"\u00a7e" + Warpname + "\u00a77\" aktiviert!");
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("disable")) {
                            if (WarpAPI.isWarpEnabled(Warpname)) {
                                WarpAPI.disableWarp(Warpname);
                                API.sendMessage((CommandSender)p, "Du hast erfolgreich den Warp \"\u00a7e" + Warpname + "\u00a77\" \u00a7cdeaktiviert\u00a77!");
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("toggle")) {
                            if (WarpAPI.ifWarpExists(Warpname)) {
                                if (WarpAPI.isWarpEnabled(Warpname)) {
                                    p.performCommand("warp disable " + Warpname);
                                } else {
                                    p.performCommand("warp enable " + Warpname);
                                }
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("map") && ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Map.Enable")) {
                            this.processTeleport(p);
                        } else if (args[0].equalsIgnoreCase("new") && ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Map.Enable")) {
                            Location l = p.getLocation();
                            p.teleport(new Location(Bukkit.getWorld((String)"Welt"), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch()));
                        } else if (args[0].equalsIgnoreCase("old") && ConfigAPI.CFG.getBoolean("Core.Settings.Commands.Map.Enable")) {
                            Location l = p.getLocation();
                            p.teleport(new Location(Bukkit.getWorld((String)"Welt_old"), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch()));
                        } else if (args[0].equalsIgnoreCase("delete")) {
                            if (WarpAPI.ifWarpExists(Warpname)) {
                                WarpAPI.removeWarp(Warpname);
                                API.sendMessage((CommandSender)p, "Du hast erfolgreich den Warp-Punkt \u00a7e" + Warpname + "\u00a7c entfernt\u00a77!");
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("remove")) {
                            if (WarpAPI.ifWarpExists(Warpname)) {
                                WarpAPI.removeWarp(Warpname);
                                API.sendMessage((CommandSender)p, "Du hast erfolgreich den Warp-Punkt \u00a7e" + Warpname + "\u00a7c entfernt\u00a77!");
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("del")) {
                            if (WarpAPI.ifWarpExists(Warpname)) {
                                WarpAPI.removeWarp(Warpname);
                                API.sendMessage((CommandSender)p, "Du hast erfolgreich den Warp-Punkt \u00a7e" + Warpname + "\u00a7c entfernt\u00a77!");
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("rem")) {
                            if (WarpAPI.ifWarpExists(Warpname)) {
                                WarpAPI.removeWarp(Warpname);
                                API.sendMessage((CommandSender)p, "Du hast erfolgreich den Warp-Punkt \u00a7e" + Warpname + "\u00a7c entfernt\u00a77!");
                            } else {
                                this.sendESN(p);
                            }
                        } else if (args[0].equalsIgnoreCase("list")) {
                            API.sendMessage((CommandSender)p, "\u00a77Es existieren folgende Warp-Punkte:");
                            WarpAPI.getWarpList(s);
                        } else {
                            p.performCommand("warp");
                        }
                    } else {
                        p.performCommand("warp");
                    }
                }
            }
        }
        return false;
    }

    private void processTeleport(Player p) {
        Location l = p.getLocation();
        if (p.getWorld().getName().equals("Welt")) {
            p.teleport(new Location(Bukkit.getWorld((String)"Welt_old"), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch()));
        } else {
            p.teleport(new Location(Bukkit.getWorld((String)"Welt"), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch()));
        }
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
                arrobject2[0] = "label";
                break;
            }
        }
        arrobject[1] = "live/nerotv/projectsbase/commands/Warp";
        arrobject[2] = "onCommand";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", arrobject));
    }
}

