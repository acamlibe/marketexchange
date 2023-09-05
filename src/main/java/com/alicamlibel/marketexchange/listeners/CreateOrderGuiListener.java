package com.alicamlibel.marketexchange.listeners;

import com.alicamlibel.marketexchange.gui.CreateBuyOrderGui;
import com.alicamlibel.marketexchange.gui.CreateSellOrderGui;
import com.alicamlibel.marketexchange.util.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;

public class CreateOrderGuiListener implements Listener {

    protected void showBuyGui(Player player, Material orderMaterial, int quantity, int pricePerQuantity, double fee) {
        CreateBuyOrderGui gui = new CreateBuyOrderGui(orderMaterial, pricePerQuantity, quantity, fee);
        player.openInventory(gui.getInventory());
    }

    protected void showSellGui(Player player, Material orderMaterial, int quantity, int pricePerQuantity, double fee) {
        CreateSellOrderGui gui = new CreateSellOrderGui(orderMaterial, pricePerQuantity, quantity, fee);
        player.openInventory(gui.getInventory());
    }

    protected int getIncrement(String itemName) {
        return parseIncrementLore(itemName);
    }

    protected int getCurrentQuantity(List<String> lore) {
        String quantityLoreLine = lore.get(0);
        return getNumberFromLore(quantityLoreLine);
    }

    protected int getCurrentPricePerQuantity(List<String> lore) {
        String pricePerQuantityLoreLine = lore.get(1);
        return getNumberFromLore(pricePerQuantityLoreLine);
    }

    protected int getNumberFromLore(String loreLine) {
        try {
            return StringUtils.getNumberFromString(loreLine);
        } catch (Exception e) {
            return 0;
        }
    }

    protected int getIncrementedValue(int current, int increment) {
        return Math.max(current + increment, 0);
    }

    private int parseIncrementLore(String itemLore) {
        try {
            int increment = StringUtils.getNumberFromString(itemLore);

            if (itemLore.contains("-")) {
                increment *= -1;
            }

            return increment;
        } catch (Exception e) {
            return 0;
        }
    }
}
