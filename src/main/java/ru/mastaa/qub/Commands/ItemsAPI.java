package ru.mastaa.qub.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import ru.mastaa.qub.Configs.ConfigManager;
import ru.mastaa.qub.Configs.ConfigType;
import ru.mastaa.qub.ItemsLoader;

public class ItemsAPI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("itemsapi")) {
            if (args.length == 2 && args[0].equalsIgnoreCase("setup")) {
                Player player = (Player) sender;
                ConfigType configType;
                try {
                    configType = ConfigType.valueOf(args[1].toUpperCase());
                } catch (IllegalArgumentException e) {
                    sender.sendMessage(ChatColor.RED + "Неверный тип конфига. Используйте A или B.");
                    return true;
                }

                ConfigManager.setupConfig(player, configType);
                player.setMetadata("config_updated", new FixedMetadataValue(ItemsLoader.getInstance(), true));
                sender.sendMessage(ChatColor.GREEN + "Вы выбрали конфиг " + configType.name());
                sender.sendMessage(ChatColor.GREEN + "Перезайдите для применения изменений.");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                ConfigManager.loadConfigs(); // Перезагрузка всех конфигов
                sender.sendMessage(ChatColor.GREEN + "Конфиги перезагружены.");
                return true;
            }
        }
        return false;
    }
}
