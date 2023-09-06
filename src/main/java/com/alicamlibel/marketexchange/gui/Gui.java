package com.alicamlibel.marketexchange.gui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class Gui {

    protected static final int MAX_INVENTORY_SIZE = 54;

    protected Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    protected void setItem(int index, Material material, int quantity, String name, String... lore) {
        inventory.setItem(index, createInventoryItem(material, quantity, name, lore));
    }

    protected void setItem(int index, Material material, String name, String... lore) {
        inventory.setItem(index, createInventoryItem(material, 1, name, lore));
    }

    private ItemStack createInventoryItem(Material material, int quantity, String name, String... lore) {
        ItemStack item = new ItemStack(material, quantity);

        ItemMeta meta = item.getItemMeta();

        if (meta == null)
            return item;

        if (name != null && !name.isEmpty()) {
            meta.setDisplayName(name);
        }

        if (lore != null && lore.length > 0) {
            meta.setLore(Arrays.asList(lore));
        }

        item.setItemMeta(meta);

        return item;
    }
}
