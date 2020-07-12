package Border;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.LinkedList;

/**
 * Scheduled task which builds the bedrock pvp borders to the world, task runs in chunks to prevent server freezing
 * @author etsubu
 */
public class BorderBuilderTask implements Runnable {
    private final LinkedList<Vector> blockLocations;
    private final World world;
    private int id;

    public BorderBuilderTask(World world, int x, int z, int radius) {
        blockLocations = new LinkedList<>();
        this.world = world;
        for(int i = -radius; i <= radius; i++) {
            blockLocations.add(new Vector(x + i, 0, z - radius));
            blockLocations.add(new Vector(x + i, 0, z + radius));
            blockLocations.add(new Vector(x - radius, 0, z + i));
            blockLocations.add(new Vector(x + radius, 0, z + i));
        }
        Bukkit.broadcastMessage("Initialized border creation with " + blockLocations.size() + " points.");
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        Vector v = blockLocations.pop();
        for(int y = 0; y <= world.getMaxHeight(); y++) {
            world.getBlockAt((int) v.getX(), y, v.getBlockZ()).setType(Material.BEDROCK);
        }
        if(blockLocations.isEmpty()) {
            Bukkit.broadcastMessage("Borders created.");
            Bukkit.getScheduler().cancelTask(id);
        }
    }
}
