
package live.nerotv.projectsbase.api;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class WorldAPI {
    public static World getPlayerWorld(Player player) {
        return player.getWorld();
    }

    public static World getWorld(String worldname) {
        if (Bukkit.getWorld((String)worldname) == null) {
            return null;
        }
        return Bukkit.getWorld((String)worldname);
    }

    public static void createWorld(String MapName) {
        new WorldCreator(MapName).environment(World.Environment.NORMAL).createWorld();
    }

    public static void createNetherWorld(String MapName) {
        new WorldCreator(MapName).environment(World.Environment.NETHER).createWorld();
    }

    public static void setTime(long time, World world) {
        world.setTime(time);
    }

    public static void addTime(long time, World world) {
        long t = world.getTime() + time;
        world.setTime(t);
    }

    public static void removeTime(long time, World world) {
        long t = world.getTime() - time;
        world.setTime(t);
    }

    public static void setSun(World world) {
        world.setThundering(false);
        world.setStorm(false);
    }

    public static void setRain(World world) {
        world.setThundering(false);
        world.setStorm(true);
    }

    public static void setStorm(World world) {
        world.setStorm(true);
        world.setThundering(true);
    }

    @Deprecated
    public static void setDifficulty(World world, String difficultyname) {
        Difficulty diff = WorldAPI.resolveDifficulty(difficultyname);
        world.setDifficulty(diff);
    }

    @Deprecated
    public static void setDifficulty(String worldname, Difficulty difficulty) {
        World world = Bukkit.getServer().getWorld(worldname);
        if (world != null) {
            world.setDifficulty(difficulty);
        }
    }

    @Deprecated
    public static void setDifficulty(String worldname, String difficultyname) {
        Difficulty diff = WorldAPI.resolveDifficulty(difficultyname);
        World world = Bukkit.getServer().getWorld(worldname);
        if (world != null) {
            world.setDifficulty(diff);
        }
    }

    public static Difficulty resolveDifficulty(String difficulty) {
        if (difficulty.equalsIgnoreCase("peaceful")) {
            return Difficulty.PEACEFUL;
        }
        if (difficulty.equalsIgnoreCase("peacefull")) {
            return Difficulty.PEACEFUL;
        }
        if (difficulty.equalsIgnoreCase("0")) {
            return Difficulty.PEACEFUL;
        }
        if (difficulty.equalsIgnoreCase("easy")) {
            return Difficulty.EASY;
        }
        if (difficulty.equalsIgnoreCase("1")) {
            return Difficulty.EASY;
        }
        if (difficulty.equalsIgnoreCase("normal")) {
            return Difficulty.NORMAL;
        }
        if (difficulty.equalsIgnoreCase("2")) {
            return Difficulty.NORMAL;
        }
        if (difficulty.equalsIgnoreCase("hard")) {
            return Difficulty.HARD;
        }
        if (difficulty.equalsIgnoreCase("3")) {
            return Difficulty.HARD;
        }
        return null;
    }

    public static void createFarmworld(String worldName) {
        WorldAPI.createWorld(worldName);
    }
}

