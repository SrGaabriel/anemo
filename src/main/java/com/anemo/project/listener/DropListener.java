package com.anemo.project.listener;

import com.anemo.project.AnemoPlugin;
import com.anemo.project.entity.Kit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Arrays;
import java.util.List;

public class DropListener implements Listener {
    private final AnemoPlugin plugin;

    private final static List<Material> ALLOWED_ITEM_DROPS_DURING_PVP = Arrays.asList(
            Material.BOWL,
            Material.MUSHROOM_SOUP,
            Material.RED_MUSHROOM,
            Material.BROWN_MUSHROOM
    );

    public DropListener(AnemoPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        final Item item = event.getItemDrop();

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (plugin.getGameService().getMemberCurrentKit(player.getUniqueId()).isPresent()) {
                if (!ALLOWED_ITEM_DROPS_DURING_PVP.contains(item.getItemStack().getType())) {
                    event.setCancelled(true);
                }
                return;
            }
            event.setCancelled(true);
        }
    }
}
