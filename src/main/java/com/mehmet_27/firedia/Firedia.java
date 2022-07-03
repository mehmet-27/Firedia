package com.mehmet_27.firedia;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Firedia extends JavaPlugin {

    private static Firedia instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new BlockListener(), this);
        Objects.requireNonNull(this.getCommand("firedia")).setExecutor(new FirediaCommand());
        Objects.requireNonNull(this.getCommand("firedia")).setTabCompleter(new FirediaCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Firedia getInstance() {
        return instance;
    }
}
