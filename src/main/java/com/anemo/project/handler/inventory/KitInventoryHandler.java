package com.anemo.project.handler.inventory;

import com.anemo.project.AnemoPlugin;
import com.anemo.project.entity.Kit;
import com.anemo.project.service.KitService;
import com.anemo.project.util.InventorySupplier;
import com.anemo.project.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class KitInventoryHandler {
    private final AnemoPlugin plugin;

    public KitInventoryHandler(AnemoPlugin plugin) {
        this.plugin = plugin;
    }

    public void openToPlayer(Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 6*9, "§b§lAnemo §8- §7Kits");

        addBorder(inventory);
        addKitsToInventory(inventory);

        player.openInventory(inventory);
    }

    private void addKitsToInventory(Inventory inventory) {
        final KitService kitService = plugin.getKitService();
        for (final Kit kit : kitService.getKits()) {
            ItemBuilder builder = new ItemBuilder(kit.getIcon())
                    .name("§e" + kit.getName())
                    .addLoreLine("");

            for (String descriptionLine : kit.getDescription()) {
                builder = builder.addLoreLine("§7"+descriptionLine);
            }

            builder = builder
                    .addLoreLine("")
                    .addLoreLine("§fPreço: §a" + kit.getPrice());

            inventory.addItem(builder.build());
        }
    }

    private void addBorder(Inventory inventory) {
        InventorySupplier supplier = new InventorySupplier(inventory);

        supplier.fillLine(setupBorderItem(Material.STAINED_GLASS_PANE, (short) 14), 0);

        supplier.fillLine(setupBorderItem(Material.LEAVES), 1);
        supplier.fillLine(setupBorderItem(Material.LEAVES), 5);

        final ItemStack fence = setupBorderItem(Material.IRON_FENCE);

        inventory.setItem(0, fence);
        inventory.setItem(2, fence);

        inventory.setItem(4, setupBorderItem(Material.ENDER_CHEST));

        inventory.setItem(6, fence);
        inventory.setItem(8, fence);
    }

    private ItemStack setupBorderItem(Material item) {
        return setupBorderItem(item, (short) 0);
    }

    private ItemStack setupBorderItem(Material item, short data) {
        return new ItemBuilder(item, 1, data).name("§cKits").build();
    }
}
