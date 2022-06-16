
package live.nerotv.projectsbase.api;

import live.nerotv.projectsbase.Main;
import live.nerotv.projectsbase.api.API;
import live.nerotv.projectsbase.api.ConfigAPI;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import live.nerotv.projectsbase.utils.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ServerAPI {
    public static void sendConsoleMessage(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage("\u00a7dZyneon \u00a78| \u00a77" + message.replace("&", "\u00a7"));
    }

    public static void scheduledShutdown() {
        if (!API.isStopping) {
            API.isStopping = true;
            new Countdown(27, (Plugin)Main.getInstance()){

                @Override
                public void count(int current) {
                    block17: {
                        block15: {
                            block16: {
                                if (current >= 11) break block15;
                                int cur = current - 1;
                                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)"title @a subtitle \"\u00a78...\u00a77startet der Server neu\u00a78!\"");
                                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("title @a title \"\u00a77In \u00a7e" + cur + " Sekunden\u00a78...\""));
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                                }
                                if (current <= 6) {
                                    Bukkit.broadcastMessage((String)("\u00a7cWICHTIG\u00a78: \u00a77Serverneustart in \u00a7e" + cur + " Sekunden\u00a78!"));
                                }
                                if (current != 1) break block16;
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    API.switchServer(all, "Lobby-1");
                                }
                                break block17;
                            }
                            if (current != 0) break block17;
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                API.switchServer(all, "Lobby-1");
                            }
                            Bukkit.shutdown();
                            break block17;
                        }
                        if (current == 26) {
                            Bukkit.broadcastMessage((String)"\u00a7cWICHTIG\u00a78: \u00a77Serverneustart in \u00a7e25 Sekunden\u00a78!");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        } else if (current == 21) {
                            Bukkit.broadcastMessage((String)"\u00a7cWICHTIG\u00a78: \u00a77Serverneustart in \u00a7e20 Sekunden\u00a78!");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        } else if (current == 16) {
                            Bukkit.broadcastMessage((String)"\u00a7cWICHTIG\u00a78: \u00a77Serverneustart in \u00a7e15 Sekunden\u00a78!");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        } else if (current == 11) {
                            Bukkit.broadcastMessage((String)"\u00a7cWICHTIG\u00a78: \u00a77Serverneustart in \u00a7e10 Sekunden\u00a78!");
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)"title @a subtitle \"\u00a78...\u00a77startet der Server neu\u00a78!\"");
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)"title @a title \"\u00a77In \u00a7e10 Sekunden\u00a78...\"");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                PlayerAPI.playNewSound(all, NewSound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }
                    }
                }
            }.start();
        }
    }

    public static String formatMessage(String message) {
        return message.replace("&", "\u00a7");
    }

    public static int getServerVersion() {
        if (Bukkit.getVersion().contains("1.0")) {
            return 100;
        }
        if (Bukkit.getVersion().contains("1.1.")) {
            return 101;
        }
        if (Bukkit.getVersion().contains("1.2.")) {
            return 102;
        }
        if (Bukkit.getVersion().contains("1.3.")) {
            return 103;
        }
        if (Bukkit.getVersion().contains("1.4.")) {
            return 104;
        }
        if (Bukkit.getVersion().contains("1.5.")) {
            return 105;
        }
        if (Bukkit.getVersion().contains("1.6.")) {
            return 106;
        }
        if (Bukkit.getVersion().contains("1.7.")) {
            return 107;
        }
        if (Bukkit.getVersion().contains("1.8.")) {
            return 108;
        }
        if (Bukkit.getVersion().contains("1.9")) {
            return 109;
        }
        if (Bukkit.getVersion().contains("1.10")) {
            return 110;
        }
        if (Bukkit.getVersion().contains("1.11")) {
            return 111;
        }
        if (Bukkit.getVersion().contains("1.12")) {
            return 112;
        }
        if (Bukkit.getVersion().contains("1.13")) {
            return 113;
        }
        if (Bukkit.getVersion().contains("1.14")) {
            return 114;
        }
        if (Bukkit.getVersion().contains("1.15")) {
            return 115;
        }
        if (Bukkit.getVersion().contains("1.16")) {
            return 116;
        }
        if (Bukkit.getVersion().contains("1.17")) {
            return 117;
        }
        if (Bukkit.getVersion().contains("1.18")) {
            return 118;
        }
        if (Bukkit.getVersion().contains("1.19")) {
            return 119;
        }
        if (Bukkit.getVersion().contains("1.20")) {
            return 120;
        }
        return 121;
    }

    public static Boolean isLegacy() {
        if (ServerAPI.getServerVersion() > 115) {
            return false;
        }
        return true;
    }

    public static void setMaintenance(boolean state) {
        ConfigAPI.CFG.set("Core.Settings.Maintenance", (Object)state);
        API.maintenance = state;
        ConfigAPI.saveConfig(ConfigAPI.Config, ConfigAPI.CFG);
    }

    public static void toggleMaintenance() {
        if (ServerAPI.isMaintenance()) {
            ServerAPI.setMaintenance(false);
        } else {
            ServerAPI.setMaintenance(true);
        }
    }

    public static boolean isMaintenance() {
        return API.maintenance;
    }
}

