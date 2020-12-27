package com.anemo.project.listener;

import com.anemo.project.AnemoPlugin;
import com.anemo.project.entity.Kit;
import com.anemo.project.service.GameService;
import com.anemo.project.service.KitService;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Optional;

public class GameListener implements Listener {

    private final AnemoPlugin plugin;
    private final KitService kitService;
    private final GameService gameService;

    public GameListener(AnemoPlugin plugin) {
        this.plugin = plugin;
        this.kitService = plugin.getKitService();
        this.gameService = plugin.getGameService();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            final Optional<Kit> entityKit = gameService.getMemberCurrentKit(event.getEntity().getUniqueId());
            final Optional<Kit> attackerKit = gameService.getMemberCurrentKit(event.getDamager().getUniqueId());

            if (entityKit.isPresent() && attackerKit.isPresent()) {
                kitService.getKitHandler(entityKit.get()).onKitUserDamage(event, true);
                kitService.getKitHandler(attackerKit.get()).onKitUserDamage(event, false);
            } else {
                event.setCancelled(true);
            }
        } else {
            if (event.getEntity() instanceof Player && ((Player) event.getEntity()).getGameMode() == GameMode.CREATIVE) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent event) {
        gameService.getMemberCurrentKit(event.getPlayer().getUniqueId()).ifPresent((kit) -> event.setCancelled(true));
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDeath(PlayerDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();

        gameService.setMemberCurrentKit(event.getEntity().getUniqueId(), null);
        gameService.getMemberCurrentKit(event.getEntity().getUniqueId()).ifPresent((kit) ->
                kitService.getKitHandler(kit).onKitUserDeath(event)
        );
    }
}
