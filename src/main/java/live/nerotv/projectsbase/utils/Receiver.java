
package live.nerotv.projectsbase.utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import live.nerotv.projectsbase.api.NewSound;
import live.nerotv.projectsbase.api.PlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Receiver
implements PluginMessageListener {
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase("base:bungee")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput((byte[])bytes);
        String subChannel = in.readUTF();
        if (subChannel.equalsIgnoreCase("spigot")) {
            String data1 = in.readUTF();
            int n = in.readInt();
        } else if (subChannel.contains("playSound_")) {
            String data1 = in.readUTF();
            int data2 = in.readInt();
            if (Bukkit.getPlayer((String)data1) != null) {
                String nS = subChannel.replace("playSound_", "");
                Player p = Bukkit.getPlayer((String)data1);
                PlayerAPI.playNewSound(p, NewSound.valueOf(nS));
            }
        }
    }
}

