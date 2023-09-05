package com.alicamlibel.marketexchange.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class MexGui extends Gui {

    public MexGui() {
        this.inventory = Bukkit.createInventory(null, getInventorySize(), getInventoryName());
        initGui();
    }


    private void initGui() {
        int buyButtonPosition = 22;
        int sellButtonPosition = 31;

        setItem(buyButtonPosition, Material.PAPER, 1, "§9Buy", "Create a buy order...");
        setItem(sellButtonPosition, Material.PAPER, 1,"§cSell", "Create a sell order...");

        for (int i = 0; i < getInventorySize(); i++) {
            if (i == buyButtonPosition || i == sellButtonPosition) {
                continue;
            }

            setItem(i, Material.GLASS_PANE, 1,"-");
        }
    }

    private int getInventorySize() {
        return MAX_INVENTORY_SIZE;
    }

    private String getInventoryName() {
        return "Mex";
    }
}
