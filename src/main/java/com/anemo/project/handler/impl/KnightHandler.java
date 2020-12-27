package com.anemo.project.handler.impl;

import com.anemo.project.handler.KitHandler;
import com.anemo.project.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class KnightHandler extends KitHandler {

    @Override
    public void onKitApply(Player player) {
        player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).acceptItemMeta((meta) -> {
            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        }).build());
    }
}
