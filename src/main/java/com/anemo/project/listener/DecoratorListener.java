package com.anemo.project.listener;

import com.anemo.project.AnemoPlugin;
import com.destroystokyo.paper.Title;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DecoratorListener implements Listener {
    private final AnemoPlugin plugin;

    public DecoratorListener(AnemoPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        for (Player player : event.getPlayer().getServer().getOnlinePlayers()) {
            player.sendActionBar("§8[§a+§8] §7" + event.getPlayer().getName());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        for (Player player : event.getPlayer().getServer().getOnlinePlayers()) {
            player.sendActionBar("§8[§c-§8] §7" + event.getPlayer().getName());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();

        player.spigot().respawn();
        player.sendTitle(new Title("§c§lMORTE", ""));
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0f, 1.0f);
    }
}
