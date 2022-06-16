
package live.nerotv.projectsbase.api;

import live.nerotv.projectsbase.api.NewSound;
import org.bukkit.Sound;

public class SoundAPI {
    public static Sound getNewSound(NewSound NewSound2) {
        return Sound.valueOf((String)NewSound2.toString());
    }
}

