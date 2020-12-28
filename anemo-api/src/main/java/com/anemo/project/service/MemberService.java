package com.anemo.project.service;

import com.anemo.project.entity.Member;

import java.util.Optional;
import java.util.UUID;

public interface MemberService {

    void register(Member member);

    Optional<Member> find(UUID uniqueId);

    void unregister(Member member);

}