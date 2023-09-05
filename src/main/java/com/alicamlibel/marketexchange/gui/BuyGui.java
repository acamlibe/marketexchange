package com.alicamlibel.marketexchange.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BuyGui extends Gui {

    private int page = 1;

    public BuyGui() {
        this(1);
    }

    public BuyGui(int page) {
        this.page = page;
        this.inventory = Bukkit.createInventory(null, getInventorySize(), getInventoryName());
        initGui();
    }

    private void initGui() {
        List<Material> validMaterials = getValidMaterials();

        if (page > 1) {
            setItem(0, Material.ARROW, 1, "§dPrevious Page");
        }

        if (page * 45 <= validMaterials.size()) {
            setItem(8, Material.ARROW, 1, "§dNext Page");
        }

        int inventoryIndex = 9;


        for (int i = (page - 1) * 45; i < validMaterials.size(); i++) {
            Material material = validMaterials.get(i);

            if (inventoryIndex == 54) {
                break;
            }

            setItem(inventoryIndex, material, 1, "");

            inventoryIndex++;
        }
    }

    private int getInventorySize() {
        return MAX_INVENTORY_SIZE;
    }

    private String getInventoryName() {
        return "Mex - Buy | Page: " + page;
    }

    private List<Material> getValidMaterials() {
        ArrayList<Material> validMaterials = new ArrayList<>();

        Inventory tempInventory = Bukkit.createInventory(null, MAX_INVENTORY_SIZE, "Fake Inventory");

        for (Material material : Material.values()) {
            ItemStack item = new ItemStack(material, 1);

            tempInventory.setItem(0, item);

            try {
                ItemStack invItem = tempInventory.getItem(0);

                if (invItem != null) {
                    if (invItem.getType().isAir()) {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
            catch (NullPointerException ex) {
                continue;
            }

            validMaterials.add(material);
        }

        return validMaterials;
    }
}
