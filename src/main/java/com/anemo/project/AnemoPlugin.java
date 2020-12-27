package com.anemo.project;

import com.anemo.project.command.KitCommand;
import com.anemo.project.entity.impl.kit.KnightKit;
import com.anemo.project.handler.impl.KnightHandler;
import com.anemo.project.listener.*;
import com.anemo.project.service.DatabaseService;
import com.anemo.project.entity.impl.kit.ArcherKit;
import com.anemo.project.handler.impl.ArcherHandler;
import com.anemo.project.handler.KitLoader;
import com.anemo.project.service.GameService;
import com.anemo.project.service.KitService;
import com.anemo.project.service.MemberService;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnemoPlugin extends JavaPlugin {

    private GameService gameService;
    private MemberService memberService;
    private DatabaseService databaseService;

    private KitLoader kitLoader;
    private KitService kitService;

    @Override
    public void onEnable() {
        gameService = new GameService();
        databaseService = new DatabaseService(
                getConfig().getString("host"),
                getConfig().getString("port"),
                getConfig().getString("database"),
                getConfig().getString("user"),
                getConfig().getString("password")
        );
        memberService = new MemberService(databaseService);

        kitService = new KitService();
        kitLoader = new KitLoader(this);

        registerKits();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("kit").setExecutor(new KitCommand(this));
    }

    private void registerKits() {
        kitService.addKit(new KnightKit(), new KnightHandler());
        kitService.addKit(new ArcherKit(), new ArcherHandler());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new AttackDelayListener(), this);
        getServer().getPluginManager().registerEvents(new GameListener(this), this);
        getServer().getPluginManager().registerEvents(new DropListener(this), this);
        getServer().getPluginManager().registerEvents(new SoupListener(), this);
        getServer().getPluginManager().registerEvents(new KitInventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new DecoratorListener(this), this);
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public GameService getGameService() {
        return gameService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public KitLoader getKitLoader() {
        return kitLoader;
    }

    public KitService getKitService() {
        return kitService;
    }
}
