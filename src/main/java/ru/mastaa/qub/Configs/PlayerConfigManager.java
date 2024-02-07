package ru.mastaa.qub.Configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.mastaa.qub.ItemsLoader;

import java.util.HashMap;
import java.util.Map;

public class PlayerConfigManager {

    private static Map<String, ConfigType> playerConfigMap = new HashMap<>(); // Используем имя игрока для идентификации

    public static void setPlayerConfigType(Player player, ConfigType configType) {
        playerConfigMap.put(player.getName(), configType);
        savePlayerConfig(player);
    }

    public static ConfigType getPlayerConfigType(Player player) {
        return playerConfigMap.getOrDefault(player.getName(), ConfigType.A); // Возвращаем ConfigType.A по умолчанию
    }

    private static void savePlayerConfig(Player player) {
        Plugin plugin = ItemsLoader.getInstance();
        FileConfiguration playerConfig = plugin.getConfig();
        playerConfig.set("player_configs." + player.getName(), getPlayerConfigType(player).name());
        plugin.saveConfig();
    }

    public static void loadPlayerConfigs() {
        Plugin plugin = ItemsLoader.getInstance();
        FileConfiguration playerConfig = plugin.getConfig();

        if (playerConfig.contains("player_configs")) {
            for (String playerName : playerConfig.getConfigurationSection("player_configs").getKeys(false)) {
                String configTypeName = playerConfig.getString("player_configs." + playerName);
                ConfigType configType = ConfigType.valueOf(configTypeName);
                playerConfigMap.put(playerName, configType);
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!playerConfigMap.containsKey(player.getName())) {
                setPlayerConfigType(player, ConfigType.A);
            }
        }
    }
}
