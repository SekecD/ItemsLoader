package ru.mastaa.qub.Configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.mastaa.qub.ItemsLoader;

import java.util.HashMap;
import java.util.Map;

public class PlayersDataManager {

    private static Map<String, ConfigType> playerConfigMap = new HashMap<>();

    public static void setPlayerConfigType(Player player, ConfigType configType) {
        playerConfigMap.put(player.getUniqueId().toString(), configType);
        savePlayerConfig(player);
    }

    public static ConfigType getPlayerConfigType(Player player) {
        return playerConfigMap.getOrDefault(player.getUniqueId().toString(), ConfigType.A);
    }

    private static void savePlayerConfig(Player player) {
        Plugin plugin = ItemsLoader.getInstance();
        FileConfiguration playerConfig = plugin.getConfig();
        playerConfig.set("players_data." + player.getUniqueId().toString(), getPlayerConfigType(player).name());
        plugin.saveConfig();
    }

    public static void loadPlayersData() {
        Plugin plugin = ItemsLoader.getInstance();
        FileConfiguration playerConfig = plugin.getConfig();

        if (playerConfig.contains("players_data")) {
            for (String playerId : playerConfig.getConfigurationSection("players_data").getKeys(false)) {
                String configTypeName = playerConfig.getString("players_data." + playerId);
                ConfigType configType = ConfigType.valueOf(configTypeName);
                playerConfigMap.put(playerId, configType);
            }
        }
    }
}
