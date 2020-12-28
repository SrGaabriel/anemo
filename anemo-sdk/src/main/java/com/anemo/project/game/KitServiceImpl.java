package com.anemo.project.game;

import com.anemo.project.entity.Kit;
import com.anemo.project.service.KitService;

import java.util.*;

public class KitServiceImpl implements KitService {
    private final LinkedHashMap<String, Kit> kitHandlerHashMap = new LinkedHashMap<>();

    @Override
    public void register(Kit kit) {
        kitHandlerHashMap.put(kit.getName(), kit);
    }

    @Override
    public Optional<Kit> find(String name) {
        return Optional.ofNullable(kitHandlerHashMap.get(name));
    }

    @Override
    public LinkedList<Kit> findAll() {
        return new LinkedList<>(kitHandlerHashMap.values());
    }

    @Override
    public void unregister(Kit kit) {
        kitHandlerHashMap.remove(kit.getName(), kit);
    }
}