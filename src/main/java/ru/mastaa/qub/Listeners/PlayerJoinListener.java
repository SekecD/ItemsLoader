package ru.mastaa.qub.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.mastaa.qub.Configs.ConfigManager;
import ru.mastaa.qub.Configs.ConfigType;
import ru.mastaa.qub.Configs.ItemConfig;
import ru.mastaa.qub.ItemsLoader;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();

        ConfigType playerConfigType = ConfigManager.getPlayerConfigType(player);
        for (ItemConfig itemConfig : ConfigManager.getItemConfigs(playerConfigType)) {
            ItemStack itemStack = ConfigManager.createItemStack(itemConfig, player);
            inventory.setItem(itemConfig.getSlot(), itemStack);
        }

        if (event.getPlayer().hasMetadata("config_updated")) {
            event.getPlayer().removeMetadata("config_updated", ItemsLoader.getInstance());
            player.sendMessage(ChatColor.GREEN + "Конфиг был обновлен. Перезайдите для применения изменений.");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ConfigType selectedConfigType = ConfigManager.getPlayerConfigType(player);
        ConfigManager.setPlayerConfigType(player, selectedConfigType);
    }
}
