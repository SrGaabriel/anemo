package com.anemo.project.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DatabaseService {

    private final String host;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    private Connection connection;
    private HikariDataSource dataSource;

    public static String USER_TABLE_NAME = "userTable";

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public DatabaseService(String host, String port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        HikariConfig config = new HikariConfig();

        config.setUsername(user);
        config.setPassword(password);
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?useTimezone=true&serverTimezone=UTC");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        try {
            dataSource = new HikariDataSource(config);
            connection = dataSource.getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void executeUpdate(String update) {
        executorService.submit(() -> {
            try (PreparedStatement statement = connection.prepareStatement(update)) {
                statement.executeUpdate();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public Future<ResultSet> executeQuery(String query) {
        return CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                return statement.executeQuery();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                return null;
            }
        }, executorService);
    }

    public void disconnect() {
        dataSource.close();
    }
}