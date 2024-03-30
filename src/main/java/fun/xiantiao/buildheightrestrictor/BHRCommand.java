package fun.xiantiao.buildheightrestrictor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import static fun.xiantiao.buildheightrestrictor.BuildHeightRestrictor.getInstance;

/**
 * @author xiantiao
 * @date 2024/3/29
 * BuildHeightRestrictor
 */
public class BHRCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1 && (sender.hasPermission("buildheightrestrictor.bypass")) || (sender.isOp() && getInstance().getConfig().getBoolean("op"))) {
            if ("reload".equalsIgnoreCase(args[0])) {
                getInstance().reloadConfig();
                sender.sendMessage("reload ok!");
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 1 && sender.hasPermission("buildheightrestrictor.bypass") || (sender.isOp() && getInstance().getConfig().getBoolean("op"))) {
            list.add("reload");
        }

        return list;
    }
}
