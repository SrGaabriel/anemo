package com.anemo.project.service;

import com.anemo.project.entity.Kit;
import com.anemo.project.worker.KitWorker;

import java.util.LinkedList;
import java.util.Optional;

public interface KitService {

    void register(Kit kit);

    Optional<Kit> find(String name);

    LinkedList<Kit> findAll();

    void unregister(Kit kit);

}