package com.anemo.project.game;

import com.anemo.project.entity.Kit;
import com.anemo.project.service.GameService;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameServiceImpl implements GameService {
    private final ConcurrentHashMap<UUID, Kit> memberCurrentKitHashMap = new ConcurrentHashMap<>();

    @Override
    public void setMemberCurrentKit(UUID memberId, Kit kit) {
        memberCurrentKitHashMap.put(memberId, kit);
    }

    @Override
    public Optional<Kit> getMemberCurrentKit(UUID memberId) {
        return Optional.ofNullable(memberCurrentKitHashMap.get(memberId));
    }
}