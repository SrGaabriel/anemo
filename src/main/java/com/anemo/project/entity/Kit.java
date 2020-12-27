package com.anemo.project.entity;

import org.bukkit.Material;

import java.util.List;

public interface Kit {

    String getName();

    List<String> getDescription();

    int getPrice();

    Material getIcon();

}
