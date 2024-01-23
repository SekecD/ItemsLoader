package ru.mastaa.qub.Configs;

import org.bukkit.Material;

import java.util.List;

public class ItemConfig {

    private Material material;
    private int slot;
    private String name;
    private List<String> lore;
    private String command;

    public ItemConfig(Material material, int slot, String name, List<String> lore, String command) {
        this.material = material;
        this.slot = slot;
        this.name = name;
        this.lore = lore;
        this.command = command;
    }

    public Material getMaterial() {
        return material;
    }

    public int getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getCommand() {
        return command;
    }
}
