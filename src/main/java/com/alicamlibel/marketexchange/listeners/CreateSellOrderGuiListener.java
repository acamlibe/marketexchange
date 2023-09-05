package com.alicamlibel.marketexchange.listeners;

import com.alicamlibel.marketexchange.core.orders.SellOrderRepository;
import com.alicamlibel.marketexchange.core.orders.entities.Order;
import com.alicamlibel.marketexchange.gui.CreateSellOrderGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.UUID;

public class CreateSellOrderGuiListener extends CreateOrderGuiListener {

    private final SellOrderRepository sellOrderRepository;

    public CreateSellOrderGuiListener(SellOrderRepository sellOrderRepository) {
        this.sellOrderRepository = sellOrderRepository;
    }

    @EventHandler
    public void onCreateBuyOrderGuiInteract(InventoryClickEvent e) {
        String inventoryName = e.getView().getTitle();

        if (!inventoryName.contains("Mex - Sell Order")) {
            return;
        }

        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getItemMeta() == null) {
            return;
        }

        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        if (e.getClickedInventory() instanceof PlayerInventory) {
            showSellGui(player, clickedItem.getType(), 0, 0, getFeePercentage());
            return;
        }

        String clickedItemName = clickedItem.getItemMeta().getDisplayName();

        ItemStack sellItem = e.getInventory().getItem(CreateSellOrderGui.SELL_ITEM_INVENTORY_INDEX);

        if (sellItem == null) {
            return;
        }

        ItemStack sellOrder = e.getInventory().getItem(CreateSellOrderGui.SELL_ORDER_INVENTORY_INDEX);

        List<String> sellOrderLore = sellOrder.getItemMeta().getLore();

        int currentQuantity = getCurrentQuantity(sellOrderLore);
        int currentPricePerQuantity = getCurrentPricePerQuantity(sellOrderLore);

        if (clickedItemName.contains("Price/Quantity: ")) {
            int increment = getIncrement(clickedItemName);
            int newPricePerQuantity = getIncrementedValue(currentPricePerQuantity, increment);

            Material sellItemMaterial = sellItem.getType();
            showSellGui(player, sellItemMaterial, currentQuantity, newPricePerQuantity, getFeePercentage());
        }
        else if (clickedItemName.contains("Quantity: ")) {
            int increment = getIncrement(clickedItemName);
            int newQuantity = getIncrementedValue(currentQuantity, increment);

            Material sellItemMaterial = sellItem.getType();
            showSellGui(player, sellItemMaterial, newQuantity, currentPricePerQuantity, getFeePercentage());
        }
        else if (clickedItemName.contains("Confirm Sell Order")) {
            if (currentQuantity == 0 || currentPricePerQuantity == 0) {
                player.sendMessage("§cYou must set the quantity and price/quantity to confirm your sell order.");
                return;
            }

            if (sellItem == null) {
                player.sendMessage("§cYou must set the item to confirm your sell order.");
                return;
            }

            Material sellItemMaterial = sellItem.getType();
            createSellOrder(player, sellItemMaterial, currentPricePerQuantity, currentQuantity);
            player.closeInventory();
        }
        else if (clickedItemName.contains("Cancel Sell Order")) {
            player.closeInventory();
        }
    }

    private void createSellOrder(Player player, Material buyItemMaterial, int pricePerQuantity, int quantity) {
        int id = sellOrderRepository.getNextId();
        UUID playerId = player.getUniqueId();
        ItemStack item = new ItemStack(buyItemMaterial, 1);

        Order order = new Order(id, playerId, item, pricePerQuantity, quantity);
        sellOrderRepository.addOrder(order);
    }

    private double getFeePercentage() {
        return 0.1;
    }
}
