package com.anemo.project.entity.impl.kit;

import org.bukkit.Material;
import com.anemo.project.entity.Kit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArcherKit implements Kit {

    @Override
    public String getName() {
        return "Archer";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Este kit traz a experiência de um arqueiro", "de verdade ao jogo.");
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public Material getIcon() {
        return Material.BOW;
    }
}
