package com.anemo.project.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventorySupplier {
    private final Inventory inventory;

    public InventorySupplier(Inventory inventory) {
        this.inventory = inventory;
    }

    public void fillLine(ItemStack item, int line) {
        for (int i = 0; i < 8; i++) {
            inventory.setItem(i+(line*9), item);
        }
        inventory.setItem(((line+1)*9)-1, item);
    }
}
