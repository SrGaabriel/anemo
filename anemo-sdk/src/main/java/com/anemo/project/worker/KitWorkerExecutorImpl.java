package com.anemo.project.worker;

import com.anemo.project.entity.Kit;
import com.anemo.project.service.GameService;
import com.anemo.project.service.KitService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class KitWorkerExecutorImpl implements KitWorkerExecutor {
    private final GameService gameService;
    private final KitWorkerManager kitWorkerManager;

    public KitWorkerExecutorImpl(GameService gameService, KitWorkerManager kitWorkerManager) {
        this.gameService = gameService;
        this.kitWorkerManager = kitWorkerManager;
    }

    @Override
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        loopThroughWorkers(event.getEntity(), (kit, worker) -> {
            invokeWorkerListener(event, worker, kit);
        });
    }

    @Override
    public void onEntityMovement(PlayerMoveEvent event) {
        loopThroughWorkers(event.getPlayer(), (kit, worker) -> {
            invokeWorkerListener(event, worker, kit);
        });
    }

    @Override
    public void onEntitySneak(PlayerToggleSneakEvent event) {
        loopThroughWorkers(event.getPlayer(), (kit, worker) -> {
            invokeWorkerListener(event, worker, kit);
        });
    }

    @Override
    public void onEntityDeath(PlayerDeathEvent event) {
        loopThroughWorkers(event.getEntity(), (kit, worker) -> {
            invokeWorkerListener(event, worker, kit);
        });
    }

    private void loopThroughWorkers(Entity entity, BiConsumer<Kit, KitWorker> callback) {
        if (!(entity instanceof Player)) {
            return;
        }
        gameService.getMemberCurrentKit(entity.getUniqueId()).ifPresent((kit) -> {
            kitWorkerManager.find(null).forEach((worker) ->
                    callback.accept(kit, worker)
            );
            kitWorkerManager.find(kit).forEach((worker) ->
                    callback.accept(kit, worker)
            );
        });
    }

    private void invokeWorkerListener(Event event, KitWorker worker, Kit kit) {
        Arrays.stream(worker.getClass().getDeclaredMethods()).forEach((method) -> {
            if (method.getParameters()[0].getType().equals(event.getClass()) && method.isAnnotationPresent(EventHandler.class) && method.getParameterCount() == 2) {
                try {
                    method.invoke(worker, event, kit);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}