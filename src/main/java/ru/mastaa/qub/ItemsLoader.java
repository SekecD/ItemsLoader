package ru.mastaa.qub;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mastaa.qub.Commands.ItemsAPI;
import ru.mastaa.qub.Configs.ConfigManager;
import ru.mastaa.qub.Configs.PlayerConfigManager;
import ru.mastaa.qub.Configs.PlayersDataManager;
import ru.mastaa.qub.Listeners.ItemInteractListener;
import ru.mastaa.qub.Listeners.PlayerJoinListener;

public final class ItemsLoader extends JavaPlugin {

    private static ItemsLoader instance;



    @Override
    public void onEnable() {
        instance = this;

        ConfigManager.loadConfigs();
        PlayerConfigManager.loadPlayerConfigs();
        PlayersDataManager.loadPlayersData();

        getCommand("itemsapi").setExecutor(new ItemsAPI());

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemInteractListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static ItemsLoader getInstance() {
        return instance;
    }
}
