package com.alicamlibel.marketexchange.listeners;

import com.alicamlibel.marketexchange.gui.BuyGui;
import com.alicamlibel.marketexchange.gui.CreateSellOrderGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MexGuiListener implements Listener {

    @EventHandler
    public void onMexGuiInteract(InventoryClickEvent e) {
        String inventoryName = e.getView().getTitle();

        if (!inventoryName.equals("Mex")) {
            return;
        }

        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) {
            return;
        }

        String itemName = e.getCurrentItem().getItemMeta().getDisplayName();

        if (itemName.contains("Buy")) {
            BuyGui gui = new BuyGui();
            player.openInventory(gui.getInventory());
        }
        else if (itemName.contains("Sell")) {
            CreateSellOrderGui gui = new CreateSellOrderGui(null, 0, 0, getFeePercentage());
            player.openInventory(gui.getInventory());
        }
    }

    private double getFeePercentage() {
        return 0.1;
    }
}
