package com.anemo.project.handler.impl;

import com.anemo.project.handler.KitHandler;
import com.anemo.project.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ArcherHandler extends KitHandler {

    @Override
    public void onKitApply(Player player) {
        player.getInventory().setItem(17, new ItemStack(Material.ARROW));
        player.getInventory().setItem(1, new ItemBuilder(Material.BOW).acceptItemMeta(itemMeta -> {
            itemMeta.setUnbreakable(true);
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        }).build());
    }

    @Override
    public void onKitUserDamage(EntityDamageByEntityEvent playerDamageEvent, boolean isUserTheVictim) {
        if (isUserTheVictim) {
            playerDamageEvent.getEntity().sendMessage("Vítima de " + playerDamageEvent.getDamager().getName());
        } else {
            playerDamageEvent.getDamager().sendMessage("Assassino de " + playerDamageEvent.getEntity().getName());
        }
    }
}
