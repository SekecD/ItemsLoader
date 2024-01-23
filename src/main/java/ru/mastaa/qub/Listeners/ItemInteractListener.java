package ru.mastaa.qub.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import ru.mastaa.qub.Configs.ConfigManager;
import ru.mastaa.qub.Configs.ConfigType;

public class ItemInteractListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != null && event.getClickedInventory() != null) {
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                ItemStack clickedItem = event.getCurrentItem();

                ConfigType playerConfigType = ConfigManager.getPlayerConfigType((Player) event.getWhoClicked());
                if (clickedItem != null && ConfigManager.getItemConfigs(playerConfigType).stream().anyMatch(itemConfig -> ConfigManager.itemStackMatches(itemConfig, clickedItem, player))) {
                    event.setCancelled(true);
                    player.updateInventory();
                }
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory() != null && event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            ItemStack draggedItem = event.getOldCursor();

            ConfigType playerConfigType = ConfigManager.getPlayerConfigType((Player) event.getWhoClicked());
            if (draggedItem != null && ConfigManager.getItemConfigs(playerConfigType).stream().anyMatch(itemConfig -> ConfigManager.itemStackMatches(itemConfig, draggedItem, player))) {
                event.setCancelled(true);
                player.updateInventory();
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack droppedItem = event.getItemDrop().getItemStack();

        ConfigType playerConfigType = ConfigManager.getPlayerConfigType(event.getPlayer());
        if (droppedItem != null && ConfigManager.getItemConfigs(playerConfigType).stream().anyMatch(itemConfig -> ConfigManager.itemStackMatches(itemConfig, droppedItem, event.getPlayer()))) {
            event.setCancelled(true);
        }
    }
}
