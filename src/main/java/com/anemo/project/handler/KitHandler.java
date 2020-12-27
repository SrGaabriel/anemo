package com.anemo.project.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class KitHandler {

    public void onKitApply(Player player) {
        // Method to be implemented
    }

    public void onKitUserDamage(EntityDamageByEntityEvent playerDamageEvent, boolean isUserTheVictim) {
        // Method to be implemented
    }

    public void onKitUserMovement(PlayerMoveEvent playerMoveEvent) {
        // Method to be implemented
    }

    public void onKitUserDeath(PlayerDeathEvent playerDeathEvent) {
        // Method to be implemented
    }

}
