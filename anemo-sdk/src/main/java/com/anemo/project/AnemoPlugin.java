package com.anemo.project;

import com.anemo.project.database.DatabaseService;
import com.anemo.project.manager.AnemoServiceImpl;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnemoPlugin extends JavaPlugin {
    private final AnemoService anemoService = new AnemoServiceImpl(this);
    private final ServicesManager servicesManager = getServer().getServicesManager();

    private DatabaseService databaseService;

    @Override
    public void onEnable() {
        databaseService = new DatabaseService(
                getConfig().getString("host"),
                getConfig().getString("port"),
                getConfig().getString("database"),
                getConfig().getString("user"),
                getConfig().getString("password")
        );

        servicesManager.register(AnemoService.class, anemoService, this, ServicePriority.High);
    }

    @Override
    public void onDisable() {
        servicesManager.unregister(AnemoService.class, anemoService);
    }

    public AnemoService getAnemoService() {
        return anemoService;
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }
}