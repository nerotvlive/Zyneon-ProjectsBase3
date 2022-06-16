
package live.nerotv.projectsbase.api;

import live.nerotv.projectsbase.manager.StaticManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.component.ButtonComponent;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

public class BedrockAPI {
    public static void checkBedrockRules(Player player) {
        //nothing
    }

    public static void sendBedrockWarning(Player player) {
        FloodgatePlayer bP = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        if (StaticManager.bedrockWarning) {
            bP.sendForm(((SimpleForm.Builder)((SimpleForm.Builder)SimpleForm.builder().title(StaticManager.bedrockWarnTitle)).content(StaticManager.bedrockWarnMessage).button("Verstanden!").responseHandler((form, responseData) -> {
                SimpleFormResponse response = form.parseResponse(responseData);
                if (response.isCorrect() && !response.isClosed() && !response.isInvalid() || form.parseResponse(responseData).getClickedButton() != null) {
                    if (form.parseResponse(responseData).getClickedButton().getText().equals("Verstanden!")) {
                        BedrockAPI.checkBedrockRules(player);
                    }
                } else {
                    player.kickPlayer("\u00a7cBitte akzeptiere die Datenschutzerkl\u00e4rung und das Regelwerk, um zu spielen!");
                }
            })).build());
        }
    }

    public static void bedrockWarp(Player p) {
        FloodgatePlayer bP = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
        bP.sendForm(((SimpleForm.Builder)((SimpleForm.Builder)SimpleForm.builder().title("\u00a79Warp-Men\u00fc")).button("\u00a7bSpawn").button("\u00a7bFarmwelt").button("\u00a78Schlie\u00dfen").responseHandler((form, responseData) -> {
            SimpleFormResponse response = form.parseResponse(responseData);
            if (response.isCorrect() && !response.isClosed() && !response.isInvalid() || form.parseResponse(responseData).getClickedButton() != null) {
                ButtonComponent button = form.parseResponse(responseData).getClickedButton();
                String buttonText = button.getText();
                if (buttonText.equals("\u00a7bSpawn")) {
                    if (WarpAPI.isWarpEnabled("Spawn")) {
                        p.teleport(WarpAPI.getWarp("Spawn"));
                    } else {
                        p.teleport(Bukkit.getWorld((String)"Welt").getSpawnLocation());
                    }
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                } else if (buttonText.equals("\u00a7bFarmwelt")) {
                    BedrockAPI.processTeleport(p);
                }
            }
        })).build());
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
    }

    private static void processTeleport(Player p) {
        if (ConfigAPI.CFG.getBoolean("Core.Settings.Farmwelt.Enable")) {
            if (WarpAPI.isWarpEnabled("Farmwelt")) {
                p.teleport(WarpAPI.getWarp("Farmwelt"));
            } else {
                p.teleport(Bukkit.getWorld((String)ConfigAPI.CFG.getString("Core.Settings.Farmwelt.Name")).getSpawnLocation());
            }
            PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
        } else {
            PlayerAPI.playNewSound(p, NewSound.BLOCK_ANVIL_BREAK);
        }
    }

    public static void bedrockWarp(Player p, boolean state) {
        FloodgatePlayer bP = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
        bP.sendForm(((SimpleForm.Builder)((SimpleForm.Builder)SimpleForm.builder().title("\u00a79Warp-Men\u00fc")).button("\u00a7bSpawn").button("\u00a7bFarmwelt").button("\u00a78Zur\u00fcck").responseHandler((form, responseData) -> {
            SimpleFormResponse response = form.parseResponse(responseData);
            if (response.isCorrect() && !response.isClosed() && !response.isInvalid() || form.parseResponse(responseData).getClickedButton() != null) {
                ButtonComponent button = form.parseResponse(responseData).getClickedButton();
                String buttonText = button.getText();
                if (buttonText.equals("\u00a7bSpawn")) {
                    if (WarpAPI.isWarpEnabled("Spawn")) {
                        p.teleport(WarpAPI.getWarp("Spawn"));
                    } else {
                        p.teleport(Bukkit.getWorld((String)"Welt").getSpawnLocation());
                    }
                    PlayerAPI.playNewSound(p, NewSound.ENTITY_CHICKEN_EGG);
                } else if (buttonText.equals("\u00a7bFarmwelt")) {
                    BedrockAPI.processTeleport(p);
                } else {
                    BedrockAPI.bedrockMenu(p);
                }
            }
        })).build());
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
    }

    public static void showCoordinates(Player p) {
        FloodgatePlayer bP = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
    }

    public static void bedrockMenu(Player p) {
        FloodgatePlayer bP = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
        bP.sendForm(((SimpleForm.Builder)((SimpleForm.Builder)SimpleForm.builder().title("\u00a79Aktionsmen\u00fc")).content("\u00a77W\u00e4hle aus, was du tun willst:").button("\u00a79Warp-Men\u00fc \u00f6ffnen").button("\u00a79Erfolge-Men\u00fc \u00f6ffnen").button("\u00a79Rucksack-Men\u00fc \u00f6ffnen").button("\u00a78Zur\u00fcck in die Lobby").button("\u00a78Schlie\u00dfen").responseHandler((form, responseData) -> {
            SimpleFormResponse response = form.parseResponse(responseData);
            if (response.isCorrect() && !response.isClosed() && !response.isInvalid() || form.parseResponse(responseData).getClickedButton() != null) {
                ButtonComponent button = form.parseResponse(responseData).getClickedButton();
                String buttonText = button.getText();
                if (buttonText.contains("Warp")) {
                    BedrockAPI.bedrockWarp(p, true);
                } else if (buttonText.contains("Erfolg")) {
                    p.performCommand("ach");
                } else if (buttonText.contains("Rucksack")) {
                    p.performCommand("bp");
                } else if (buttonText.contains("Lobby")) {
                    API.switchServer(p, "Lobby-1");
                }
            }
        })).build());
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100.0f, 100.0f);
    }
}

