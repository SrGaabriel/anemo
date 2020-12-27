package com.anemo.project.service;

import com.anemo.project.entity.Kit;
import com.anemo.project.handler.KitHandler;

import java.util.*;

public class KitService {

    private final LinkedHashMap<Kit, KitHandler> kitHandlerHashMap = new LinkedHashMap<>();

    public void addKit(Kit kit, KitHandler kitHandler) {
        kitHandlerHashMap.put(kit, kitHandler);
    }

    public Kit getKit(String name) {
        return kitHandlerHashMap.keySet().stream()
                .filter((kit) -> kit.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Kit> getKits() {
        return new ArrayList<>(kitHandlerHashMap.keySet());
    }

    public KitHandler getKitHandler(Kit kit) {
        return kitHandlerHashMap.get(kit);
    }
}
