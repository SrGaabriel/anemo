package com.anemo.project.member;

import com.anemo.project.database.DatabaseService;
import com.anemo.project.entity.Member;
import com.anemo.project.service.MemberService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MemberServiceImpl implements MemberService {
    private final DatabaseService databaseService;

    public MemberServiceImpl(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public void register(Member member) {
        databaseService.executeUpdate(String.format("REPLACE into %s (uniqueId) values(%s)", DatabaseService.USER_TABLE_NAME, member.getUniqueId().toString()));
    }

    @Override
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

    @Override
    public void unregister(Member member) {
        databaseService.executeUpdate(String.format("DELETE FROM %s WHERE uniqueId=%s", DatabaseService.USER_TABLE_NAME, member.getUniqueId().toString()));
    }
}