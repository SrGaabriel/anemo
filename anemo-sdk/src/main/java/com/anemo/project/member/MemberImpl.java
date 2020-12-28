package com.anemo.project.member;

import com.anemo.project.entity.Member;

import java.util.UUID;

public class MemberImpl implements Member {
    private final UUID uniqueId;

    public MemberImpl(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }
}