package com.anemo.project.entity.impl.kit;

import com.anemo.project.entity.Kit;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class KnightKit implements Kit {

    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Um grupo de itens feitos para um guerreiro", "que realmente gosta de usar uma espada bem afiada.");
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Material getIcon() {
        return Material.STONE_SWORD;
    }
}
