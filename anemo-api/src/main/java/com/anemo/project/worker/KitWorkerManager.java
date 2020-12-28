package com.anemo.project.worker;

import com.anemo.project.entity.Kit;

import java.util.LinkedList;

public interface KitWorkerManager {

    void register(KitWorker worker, Kit kit);

    LinkedList<KitWorker> find(Kit kit);

    LinkedList<KitWorker> findAll();

    void unregister(KitWorker worker, Kit kit);

}