package com.anemo.project.service;

import com.anemo.project.entity.Member;
import com.anemo.project.entity.impl.MemberImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MemberService {

    private final DatabaseService databaseService;

    public MemberService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void register(Member member) {
        databaseService.executeUpdate(String.format("REPLACE into %s (uniqueId) values(%s)", DatabaseService.USER_TABLE_NAME, member.getUniqueId().toString()));
    }

    public Optional<Member> find(UUID uniqueId) {
        Member member;
        try {
            ResultSet result = databaseService.executeQuery(String.format("SELECT * FROM %s WHERE uniqueId=%s", DatabaseService.USER_TABLE_NAME, uniqueId.toString())).get();
            member = new MemberImpl(UUID.fromString(result.getString("uniqueId")));
        } catch (InterruptedException | ExecutionException | SQLException exception) {
            member = null;
            exception.printStackTrace();
        }
        return Optional.ofNullable(member);
    }

    public void remove(Member member) {
        databaseService.executeUpdate(String.format("DELETE FROM %s WHERE uniqueId=%s", DatabaseService.USER_TABLE_NAME, member.getUniqueId().toString()));
    }
}
