package com.alicamlibel.marketexchange.listeners;

import com.alicamlibel.marketexchange.core.orders.BuyOrderRepository;
import com.alicamlibel.marketexchange.core.orders.entities.Order;
import com.alicamlibel.marketexchange.gui.CreateBuyOrderGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class CreateBuyOrderGuiListener extends CreateOrderGuiListener {

    private final BuyOrderRepository buyOrderRepository;

    public CreateBuyOrderGuiListener(BuyOrderRepository buyOrderRepository) {
        this.buyOrderRepository = buyOrderRepository;
    }

    @EventHandler
    public void onCreateBuyOrderGuiInteract(InventoryClickEvent e) {
        String inventoryName = e.getView().getTitle();

        if (!inventoryName.contains("Mex - Buy Order")) {
            return;
        }

        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getItemMeta() == null) {
            return;
        }

        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        String clickedItemName = clickedItem.getItemMeta().getDisplayName();

        ItemStack buyItem = e.getInventory().getItem(CreateBuyOrderGui.BUY_ITEM_INVENTORY_INDEX);
        ItemStack buyOrder = e.getInventory().getItem(CreateBuyOrderGui.BUY_ORDER_INVENTORY_INDEX);

        Material buyItemMaterial = buyItem.getType();

        List<String> buyOrderLore = buyOrder.getItemMeta().getLore();

        int currentQuantity = getCurrentQuantity(buyOrderLore);
        int currentPricePerQuantity = getCurrentPricePerQuantity(buyOrderLore);

        if (clickedItemName.contains("Price/Quantity: ")) {
            int increment = getIncrement(clickedItemName);
            int newPricePerQuantity = getIncrementedValue(currentPricePerQuantity, increment);

            showBuyGui(player, buyItemMaterial, currentQuantity, newPricePerQuantity, getFeePercentage());
        }
        else if (clickedItemName.contains("Quantity: ")) {
            int increment = getIncrement(clickedItemName);
            int newQuantity = getIncrementedValue(currentQuantity, increment);

            showBuyGui(player, buyItemMaterial, newQuantity, currentPricePerQuantity, getFeePercentage());
        }
        else if (clickedItemName.contains("Confirm Buy Order")) {
            if (currentQuantity == 0 || currentPricePerQuantity == 0) {
                player.sendMessage("§cYou must set the quantity and price/quantity to confirm your buy order.");
                return;
            }

            createBuyOrder(player, buyItemMaterial, currentPricePerQuantity, currentQuantity);
            player.closeInventory();
        }
        else if (clickedItemName.contains("Cancel Buy Order")) {
            player.closeInventory();
        }
    }

    private void createBuyOrder(Player player, Material buyItemMaterial, int pricePerQuantity, int quantity) {
        int id = buyOrderRepository.getNextId();
        UUID playerId = player.getUniqueId();
        ItemStack item = new ItemStack(buyItemMaterial, 1);

        Order order = new Order(id, playerId, item, pricePerQuantity, quantity);
        buyOrderRepository.addOrder(order);
    }

    private double getFeePercentage() {
        return 0.1;
    }
}
