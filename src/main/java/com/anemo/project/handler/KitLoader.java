package com.anemo.project.handler;

import com.anemo.project.AnemoPlugin;
import com.anemo.project.entity.Kit;
import com.anemo.project.service.GameService;
import com.anemo.project.service.KitService;
import com.anemo.project.util.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;

public class KitLoader {

    private final KitService kitService;
    private final GameService gameService;

    public KitLoader(AnemoPlugin plugin) {
        this.kitService = plugin.getKitService();
        this.gameService = plugin.getGameService();
    }

    public void applyKit(Player player, Kit kit) {
        PlayerInventory inventory = player.getInventory();

        inventory.clear();
        inventory.setArmorContents(null);

        fillSoups(inventory);

        inventory.setHelmet(unbreakable(new ItemStack(Material.IRON_HELMET)));
        inventory.setChestplate(unbreakable(new ItemStack(Material.IRON_CHESTPLATE)));
        inventory.setLeggings(unbreakable(new ItemStack(Material.IRON_LEGGINGS)));
        inventory.setBoots(unbreakable(new ItemStack(Material.IRON_BOOTS)));

        inventory.setItem(13, new ItemStack(Material.BOWL, 64));
        inventory.setItem(14, new ItemStack(Material.RED_MUSHROOM, 64));
        inventory.setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 64));

        inventory.setItem(0, unbreakable(new ItemStack(Material.STONE_SWORD)));
        inventory.setItemInOffHand(null);

        player.setHealth(20);
        player.setGameMode(GameMode.ADVENTURE);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);

        gameService.setMemberCurrentKit(player.getUniqueId(), kit);
        kitService.getKitHandler(kit).onKitApply(player);
    }

    private void fillSoups(PlayerInventory inventory) {
        ItemStack mushroomSoup = new ItemStack(Material.MUSHROOM_SOUP);

        inventory.setContents(Arrays.stream(inventory.getContents()).map((item) -> item == null ? mushroomSoup : item).toArray(ItemStack[]::new));
    }

    private ItemStack unbreakable(ItemStack item) {
        return new ItemBuilder(item).acceptItemMeta((meta) -> {
            meta.setUnbreakable(true);
        }).build();
    }
}
