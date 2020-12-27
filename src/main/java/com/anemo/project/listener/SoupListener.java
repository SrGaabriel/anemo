package com.anemo.project.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupListener implements Listener {

    private static final float SOUP_REQUIREMENT_SCALE = 3.5f;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.getInventory().getItemInMainHand().getType() == Material.MUSHROOM_SOUP) {
            if (player.getHealth() != 20) {
                final double finalHealth = player.getHealth() + SOUP_REQUIREMENT_SCALE * 2;

                player.setHealth(finalHealth > 20 ? 20 : finalHealth);
                player.getInventory().getItemInMainHand().setType(Material.BOWL);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }
}
