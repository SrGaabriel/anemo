package com.anemo.project.worker;

import com.anemo.project.entity.Kit;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class KitWorkerManagerImpl implements KitWorkerManager {

    private final LinkedHashMap<Kit, KitWorker> kitWorkerLinkedHashMap = new LinkedHashMap<>();

    @Override
    public void register(KitWorker worker, Kit kit) {
        kitWorkerLinkedHashMap.put(kit, worker);
    }

    @Override
    public LinkedList<KitWorker> find(Kit kit) {
        return new LinkedList<>(kitWorkerLinkedHashMap.values());
    }

    @Override
    public LinkedList<KitWorker> findAll() {
        return new LinkedList<>(kitWorkerLinkedHashMap.values());
    }

    @Override
    public void unregister(KitWorker worker, Kit kit) {
        kitWorkerLinkedHashMap.remove(kit, worker);
    }
}