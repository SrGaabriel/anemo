package com.anemo.project.manager;

import com.anemo.project.AnemoPlugin;
import com.anemo.project.AnemoService;
import com.anemo.project.database.DatabaseService;
import com.anemo.project.game.GameServiceImpl;
import com.anemo.project.game.KitServiceImpl;
import com.anemo.project.member.MemberServiceImpl;
import com.anemo.project.service.GameService;
import com.anemo.project.service.KitService;
import com.anemo.project.service.MemberService;
import com.anemo.project.worker.KitWorkerExecutor;
import com.anemo.project.worker.KitWorkerExecutorImpl;
import com.anemo.project.worker.KitWorkerManager;
import com.anemo.project.worker.KitWorkerManagerImpl;

public class AnemoServiceImpl implements AnemoService {
    private final AnemoPlugin plugin;
    private final DatabaseService databaseService;

    private final KitService kitService;
    private final GameService gameService;
    private final MemberService memberService;

    private final KitWorkerManager kitWorkerManager;
    private final KitWorkerExecutor kitWorkerExecutor;

    public AnemoServiceImpl(AnemoPlugin plugin) {
        this.plugin = plugin;
        this.databaseService = plugin.getDatabaseService();

        this.kitService = new KitServiceImpl();
        this.gameService = new GameServiceImpl();
        this.memberService = new MemberServiceImpl(databaseService);

        this.kitWorkerManager = new KitWorkerManagerImpl();
        this.kitWorkerExecutor = new KitWorkerExecutorImpl(gameService, kitWorkerManager);
    }

    @Override
    public KitService getKitService() {
        return kitService;
    }

    @Override
    public GameService getGameService() {
        return gameService;
    }

    @Override
    public MemberService getMemberService() {
        return memberService;
    }

    @Override
    public KitWorkerManager getKitWorkerManager() {
        return kitWorkerManager;
    }

    @Override
    public KitWorkerExecutor getKitWorkerExecutor() {
        return kitWorkerExecutor;
    }
}