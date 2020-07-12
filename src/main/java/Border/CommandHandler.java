package Border;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class listens and processes any chat commands issued for this plugin
 * @author etsubu
 */
public class CommandHandler implements CommandExecutor {
    private BorderBuilderTask builderTask;
    private final Plugin plugin;

    public CommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if(!player.isOp() && !player.hasPermission("border")) {
            commandSender.sendMessage("You lack the permission to use this command");
            return true;
        }
        if(strings.length < 1) {
            return false;
        }
        try {
            int size = Integer.parseInt(strings[0]);
            if(size > 0) {
                synchronized (this) {
                    if(builderTask != null && !Bukkit.getScheduler().isCurrentlyRunning(builderTask.getId())) {
                        player.sendMessage("Border build is already running. Wait for the previous one to finish");
                        return false;
                    }
                    builderTask = new BorderBuilderTask(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockZ(), size);
                    builderTask.setId(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, builderTask, 20L, 3L));
                    Bukkit.broadcastMessage("Initialized border creation");
                    return true;
                }
            }
        } catch (NumberFormatException ignored) {
        }
        player.sendMessage("Size must be a number greater than 0");
        return false;
    }
}
