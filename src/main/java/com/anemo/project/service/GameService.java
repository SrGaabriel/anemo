package com.anemo.project.service;

import com.anemo.project.entity.Kit;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class GameService {
    private final HashMap<UUID, Kit> currentKitHashMap = new HashMap<>();

    public void setMemberCurrentKit(UUID uniqueId, Kit kit) {
        currentKitHashMap.put(uniqueId, kit);
    }

    public Optional<Kit> getMemberCurrentKit(UUID uniqueId) {
        return Optional.ofNullable(currentKitHashMap.get(uniqueId));
    }
}