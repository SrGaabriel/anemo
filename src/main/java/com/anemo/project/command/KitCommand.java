package com.anemo.project.command;

import com.anemo.project.AnemoPlugin;
import com.anemo.project.entity.Kit;
import com.anemo.project.handler.inventory.KitInventoryHandler;
import com.anemo.project.service.KitService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    private final AnemoPlugin plugin;

    public KitCommand(AnemoPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        KitService repository = plugin.getKitService();

        if (args.length == 0) {
            new KitInventoryHandler(plugin).openToPlayer((Player) sender);
            return true;
        }

        plugin.getKitLoader().applyKit((Player) sender, repository.getKit(args[0]));
        return true;
    }
}
