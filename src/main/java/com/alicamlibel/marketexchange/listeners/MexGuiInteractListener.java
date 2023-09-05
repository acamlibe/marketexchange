package com.alicamlibel.marketexchange.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class MexGuiInteractListener implements Listener {

    @EventHandler
    public void onInventoryItemDrag(InventoryDragEvent e) {
        if (!e.getView().getTitle().contains("Mex")) {
            return;
        }

        e.setCancelled(true);
    }
}
