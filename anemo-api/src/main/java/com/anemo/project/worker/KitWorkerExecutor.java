package com.anemo.project.worker;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public interface KitWorkerExecutor {

    void onEntityDamage(EntityDamageByEntityEvent event);

    void onEntityMovement(PlayerMoveEvent event);

    void onEntitySneak(PlayerToggleSneakEvent event);

    void onEntityDeath(PlayerDeathEvent event);

}