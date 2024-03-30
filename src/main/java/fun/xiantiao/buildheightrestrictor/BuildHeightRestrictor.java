package fun.xiantiao.buildheightrestrictor;

import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author xiantiao
 */
public final class BuildHeightRestrictor extends JavaPlugin implements Listener {

    private static BuildHeightRestrictor instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        reloadConfig();

        getServer().getPluginManager().registerEvents(this,this);

        PluginCommand buildheightrestrictor = getCommand("buildheightrestrictor");
        if (buildheightrestrictor != null) {
            buildheightrestrictor.setExecutor(new BHRCommand());
            buildheightrestrictor.setTabCompleter(new BHRCommand());
        } else {
            getLogger().warning("加载指令时出现错误，指令将无法使用");
            getLogger().warning("An error occurred while loading instructions, and the instructions will be unusable");
        }

        new Metrics(this, 21462);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (player.isOp() || player.hasPermission("buildheightrestrictor.bypass")) {return;}

        if (event.getBlockPlaced().getY() > getConfig().getInt("Height")) {
            event.setBuild(false);
            for (String message : getConfig().getStringList("Message")) {
                player.sendMessage(message);
            }
        }
    }

    public static BuildHeightRestrictor getInstance() {
        return instance;
    }
}