package com.anemo.project.service;

import com.anemo.project.entity.Kit;

import java.util.Optional;
import java.util.UUID;

public interface GameService {

    void setMemberCurrentKit(UUID memberId, Kit kit);

    Optional<Kit> getMemberCurrentKit(UUID memberId);

}