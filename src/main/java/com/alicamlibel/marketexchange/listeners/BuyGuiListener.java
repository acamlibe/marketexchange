package com.alicamlibel.marketexchange.listeners;

import com.alicamlibel.marketexchange.gui.BuyGui;
import com.alicamlibel.marketexchange.gui.CreateBuyOrderGui;
import com.alicamlibel.marketexchange.util.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BuyGuiListener implements Listener {

    @EventHandler
    public void onBuyGuiInteract(InventoryClickEvent e) {
        String inventoryName = e.getView().getTitle();

        if (!inventoryName.contains("Mex - Buy | Page: ")) {
            return;
        }

        e.setCancelled(true);

        if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
        ItemStack currentItem = e.getCurrentItem();

        String itemName = currentItem.getItemMeta().getDisplayName();

        int currentPage = getCurrentPage(inventoryName);

        if (itemName.contains("Previous Page")) {
            changePage(player, currentPage - 1);
        }
        else if (itemName.contains("Next Page")) {
            changePage(player, currentPage + 1);
        }
        else {
            Material buyItem = currentItem.getType();

            CreateBuyOrderGui gui = new CreateBuyOrderGui(buyItem, 0, 0, getTax());
            player.openInventory(gui.getInventory());
        }
    }

    private double getTax() {
        return 0.1;
    }

    private void changePage(Player player, int page) {
        BuyGui gui = new BuyGui(page);
        player.openInventory(gui.getInventory());
    }

    private int getCurrentPage(String inventoryName) {
        try {
            return StringUtils.getNumberFromString(inventoryName);
        } catch (Exception e) {
            return 1;
        }
    }
}
