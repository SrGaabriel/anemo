package com.anemo.project;

import com.anemo.project.service.GameService;
import com.anemo.project.service.KitService;
import com.anemo.project.service.MemberService;
import com.anemo.project.worker.KitWorkerExecutor;
import com.anemo.project.worker.KitWorkerManager;

public interface AnemoService {

    KitService getKitService();

    GameService getGameService();

    MemberService getMemberService();

    KitWorkerManager getKitWorkerManager();

    KitWorkerExecutor getKitWorkerExecutor();

}