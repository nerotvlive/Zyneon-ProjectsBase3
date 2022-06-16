
package live.nerotv.projectsbase.utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class Countdown {
    private int time;
    protected BukkitTask task;
    protected final Plugin plugin;

    public Countdown(int time, Plugin plugin) {
        this.time = time;
        this.plugin = plugin;
    }

    public abstract void count(int var1);

    public final void start() {
        this.task = new BukkitRunnable(){

            public void run() {
                Countdown.this.count(Countdown.this.time);
                if (Countdown.this.time-- <= 0) {
                    this.cancel();
                }
            }
        }.runTaskTimer(this.plugin, 0L, 20L);
    }
}

