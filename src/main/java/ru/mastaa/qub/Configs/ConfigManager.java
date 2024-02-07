package ru.mastaa.qub.Configs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import ru.mastaa.qub.ItemsLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigManager {

    private static Map<ConfigType, List<ItemConfig>> itemConfigsMap = new HashMap<>();

    public static void loadConfigs() {
        itemConfigsMap.clear();
        loadConfig(ConfigType.A);
        loadConfig(ConfigType.B);

        if (itemConfigsMap.isEmpty()) {
            System.out.println("Ошибка загрузки конфига");
        }
    }

    public static void loadConfig(ConfigType configType) {
        Plugin plugin = ItemsLoader.getInstance();

        String configPath = configType.name().toLowerCase() + "_config.yml";

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        File configFile = new File(plugin.getDataFolder(), configPath);

        if (!configFile.exists()) {
            plugin.saveResource(configPath, true);
        }

        // Продолжаем загрузку конфигурации
        List<ItemConfig> itemConfigs = new ArrayList<>();
        FileConfiguration config = plugin.getConfig();

        if (config.contains(configType.name().toLowerCase() + "_items")) {
            ConfigurationSection itemsSection = config.getConfigurationSection(configType.name().toLowerCase() + "_items");

            for (String itemName : itemsSection.getKeys(false)) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(itemName);
                ItemConfig itemConfig = parseItemConfig(itemSection);
                itemConfigs.add(itemConfig);
            }
        }

        itemConfigsMap.put(configType, itemConfigs);
    }

    public static List<ItemConfig> getItemConfigs(ConfigType configType) {
        return itemConfigsMap.getOrDefault(configType, new ArrayList<>());
    }

    public static void setPlayerConfigType(Player player, ConfigType configType) {
        PlayersDataManager.setPlayerConfigType(player, configType);
    }

    public static ConfigType getPlayerConfigType(Player player) {
        return PlayersDataManager.getPlayerConfigType(player);
    }

    public static ItemStack createItemStack(ItemConfig itemConfig, Player player) {
        ItemStack itemStack;

        if (itemConfig.getMaterial() == Material.PLAYER_HEAD) {
            itemStack = createSkullItemStack(itemConfig.getName());
        } else {
            itemStack = new ItemStack(itemConfig.getMaterial());
        }

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemConfig.getName()));
        meta.setLore(itemConfig.getLore());

        itemStack.setItemMeta(meta);

        if (!itemConfig.getCommand().isEmpty()) {
            executeCommand(player, itemConfig);
        }

        itemStack = setUnbreakable(itemStack);
        itemStack = setUndroppable(itemStack);
        itemStack = setUnmovable(itemStack);

        return itemStack;
    }

    public static boolean itemStackMatches(ItemConfig itemConfig, ItemStack itemStack, Player player) {
        if (itemStack.getType() == itemConfig.getMaterial() &&
                itemStack.getItemMeta().getDisplayName().equals(itemConfig.getName()) &&
                itemStack.getItemMeta().getLore().equals(itemConfig.getLore())) {
            return true;
        }
        return false;
    }

    private static ItemConfig parseItemConfig(ConfigurationSection section) {
        Material material = Material.matchMaterial(section.getString("id", "STONE"));
        int slot = section.getInt("slot", 0);
        String name = ChatColor.translateAlternateColorCodes('&', section.getString("name", "Unnamed Item"));
        List<String> lore = section.getStringList("lore");

        List<String> coloredLore = lore.stream()
                .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                .collect(Collectors.toList());

        String command = section.getString("command", "");

        return new ItemConfig(material, slot, name, coloredLore, command);
    }

    private static ItemStack createSkullItemStack(String owner) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(owner);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    private static void executeCommand(Player player, ItemConfig itemConfig) {
        String command = itemConfig.getCommand();
        if (!command.isEmpty()) {
            ItemsLoader.getInstance().getServer().dispatchCommand(player, command);
        }
    }

    private static ItemStack setUnbreakable(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setUnbreakable(true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static ItemStack setUndroppable(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static ItemStack setUnmovable(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static void setupConfig(Player player, ConfigType configType) {
        loadConfig(configType);

        PlayerConfigManager.setPlayerConfigType(player, configType);
    }
}
