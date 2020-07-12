package Border;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    public static void main(String[] args) {

    }

    public void onEnable() {
        getLogger().info("BorderPlugin has been invoked!");
        Objects.requireNonNull(getCommand("border")).setExecutor(new CommandHandler(this));
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new EventManager(), this);
    }
}
