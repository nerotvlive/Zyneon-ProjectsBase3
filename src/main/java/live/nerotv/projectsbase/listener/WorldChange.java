
package live.nerotv.projectsbase.listener;

import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.world.ChunkLoadEvent;

public class WorldChange
implements Listener {
    World mainWorld = (World)Bukkit.getWorlds().get(0);
    public static boolean started = false;

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onEntityExplosion(EntityExplodeEvent e) {
        if (e.getEntity().getWorld().getName().equals(this.mainWorld.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onBlockExplosion(BlockExplodeEvent e) {
        if (e.getBlock().getWorld().getName().equals(this.mainWorld.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onExplosion(ExplosionPrimeEvent e) {
        if (e.getEntity().getWorld().getName().equals(this.mainWorld.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onChunk(ChunkLoadEvent e) {
        if (e.getWorld().equals(Bukkit.getWorlds().get(0))) {
            Chunk chunk = e.getChunk();
            int cX = chunk.getX() * 16;
            int cZ = chunk.getZ() * 16;
            World w = chunk.getWorld();
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    w.setBiome(cX + x, cZ + z, Biome.SNOWY_PLAINS);
                    if (!w.getBiome(cX + x, cZ + z).equals((Object)Biome.SNOWY_PLAINS)) continue;
                    w.setBiome(cX + x, cZ + z, Biome.PLAINS);
                }
            }
        }
    }

    public static void replaceChunk(Chunk chunk) {
        if (chunk.getWorld().equals(Bukkit.getWorlds().get(0))) {
            int X = chunk.getX() * 16;
            int Z = chunk.getZ() * 16;
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    for (int y = 50; y < 160; ++y) {
                        if (chunk.getWorld().getBlockAt(X + x, y, Z + z).getType() == Material.ICE) {
                            chunk.getWorld().getBlockAt(X + x, y, Z + z).setType(Material.WATER);
                        }
                        if (chunk.getWorld().getBlockAt(X + x, y, Z + z).getType() == Material.SNOW) {
                            chunk.getWorld().getBlockAt(X + x, y, Z + z).setType(Material.AIR);
                        }
                        if (chunk.getWorld().getBlockAt(X + x, y, Z + z).getType() == Material.PACKED_ICE || chunk.getWorld().getBlockAt(X + x, y, Z + z).getType() == Material.BLUE_ICE) {
                            chunk.getWorld().getBlockAt(X + x, y, Z + z).setType(Material.DIRT);
                        }
                        if (chunk.getWorld().getBlockAt(X + x, y, Z + z).getType() != Material.GRASS_BLOCK) continue;
                        chunk.getWorld().getBlockAt(X + x, y, Z + z).setType(Material.GRASS_BLOCK);
                    }
                }
            }
        }
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onChunk2(PlayerChunkLoadEvent e) {
        WorldChange.replaceChunk(e.getChunk());
    }
}

