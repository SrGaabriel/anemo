package com.anemo.project.listener;

import com.anemo.project.AnemoPlugin;
import com.anemo.project.entity.Kit;
import com.anemo.project.handler.inventory.KitInventoryHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class KitInventoryListener implements Listener {
    private final AnemoPlugin plugin;

    public KitInventoryListener(AnemoPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.ENDER_CHEST && !plugin.getGameService().getMemberCurrentKit(event.getPlayer().getUniqueId()).isPresent()) {
            new KitInventoryHandler(plugin).openToPlayer(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerInventoryInteract(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getTitle().equals("§b§lAnemo §8- §7Kits")) {
            final ItemStack item = event.getCurrentItem();
            final String clickedName = item.getItemMeta().getDisplayName().substring(2);

            System.out.println(clickedName);

            final Kit kit = plugin.getKitService().getKit(clickedName);

            if (kit != null) {
                plugin.getKitLoader().applyKit((Player) event.getWhoClicked(), kit);
            }
            event.setCancelled(true);
        }

    }
}