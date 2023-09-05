package com.alicamlibel.marketexchange.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.text.NumberFormat;

public class CreateSellOrderGui extends Gui {

    public static final int SELL_ORDER_INVENTORY_INDEX = 4;
    public static final int SELL_ITEM_INVENTORY_INDEX = 13;

    private final Material material;
    private final int pricePerQuantity;
    private final int quantity;
    private final double feePercent;

    public CreateSellOrderGui(Material material, int pricePerQuantity, int quantity, double feePercent) {
        this.material = material;
        this.pricePerQuantity = pricePerQuantity;
        this.quantity = quantity;
        this.feePercent = feePercent;
        this.inventory = Bukkit.createInventory(null, getInventorySize(), getInventoryName());
        initGui();
    }

    private void initGui() {
        setItem(1, Material.CHAIN, "-");
        setItem(10, Material.LANTERN, "-");
        setItem(7, Material.CHAIN, "-");
        setItem(16, Material.LANTERN, "-");

        setItem(3, Material.RED_CANDLE, "§cCancel Sell Order");
        setBuyOrderItem(4);

        setItem(5, Material.GREEN_CANDLE, "§aConfirm Sell Order");


        if (material != null) {
            setItem(13, material, "", "Sell Order Item");
        }
        setItem(22, Material.LECTERN, "-");

        setItem(27, Material.LIME_STAINED_GLASS_PANE, "Price/Quantity: §a+1");
        setItem(28, Material.LIME_STAINED_GLASS_PANE, "Price/Quantity: §a+10");
        setItem(29, Material.LIME_STAINED_GLASS_PANE, "Price/Quantity: §a+100");
        setItem(30, Material.LIME_STAINED_GLASS_PANE, "Price/Quantity: §a+1000");

        setItem(45, Material.RED_STAINED_GLASS_PANE, "Price/Quantity: §c-1");
        setItem(46, Material.RED_STAINED_GLASS_PANE, "Price/Quantity: §c-10");
        setItem(47, Material.RED_STAINED_GLASS_PANE, "Price/Quantity: §c-100");
        setItem(48, Material.RED_STAINED_GLASS_PANE, "Price/Quantity: §c-1000");

        setItem(32, Material.LIME_STAINED_GLASS_PANE, "Quantity: §a+1");
        setItem(33, Material.LIME_STAINED_GLASS_PANE, "Quantity: §a+10");
        setItem(34, Material.LIME_STAINED_GLASS_PANE, "Quantity: §a+100");
        setItem(35, Material.LIME_STAINED_GLASS_PANE, "Quantity: §a+1000");

        setItem(50, Material.RED_STAINED_GLASS_PANE, "Quantity: §c-1");
        setItem(51, Material.RED_STAINED_GLASS_PANE, "Quantity: §c-10");
        setItem(52, Material.RED_STAINED_GLASS_PANE, "Quantity: §c-100");
        setItem(53, Material.RED_STAINED_GLASS_PANE, "Quantity: §c-1000");

        setItem(36, Material.GOLD_NUGGET, "1");
        setItem(37, Material.RAW_GOLD, "10");
        setItem(38, Material.GOLD_INGOT, "100");
        setItem(39, Material.GOLD_BLOCK, "1000");

        setItem(41, Material.MINECART, "1");
        setItem(42, Material.HOPPER_MINECART, "10");
        setItem(43, Material.FURNACE_MINECART, "100");
        setItem(44, Material.CHEST_MINECART, "1000");

        setItem(31, Material.RAIL, "-");
        setItem(40, Material.DETECTOR_RAIL, "-");
        setItem(49, Material.RAIL, "-");

        for (int i = 0; i < 54; i++) {
            if (i == 13)
                continue;

            if (inventory.getItem(i) == null) {
                setItem(i, Material.GLOW_LICHEN, "-");
            }
        }
    }

    private void setBuyOrderItem(int inventoryIndex) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String quantityStr = "§fQuantity: " + quantity;
        String pricePerQuantityStr = "§fPrice/Quantity: $" + pricePerQuantity;

        long price = (long) quantity * pricePerQuantity;
        double fee = price * feePercent;
        double totalPrice = price + fee;

        String priceStr = "§fPrice: " + currencyFormatter.format(price);
        String feeStr = "§cFee: " + currencyFormatter.format(fee) + " (" + (feePercent * 100) + "%)";

        String totalPriceStr = "§aTotal Price: " + currencyFormatter.format(totalPrice);

        if (material == null) {
            setItem(inventoryIndex, Material.PAPER, "§3Sell Order", "Select an item in your inventory first...");
        }
        else {
            setItem(inventoryIndex, Material.PAPER, "§3Sell Order",
                    quantityStr, pricePerQuantityStr, priceStr, feeStr, "", totalPriceStr);
        }
    }


    private int getInventorySize() {
        return MAX_INVENTORY_SIZE;
    }

    private String getInventoryName() {
        return "Mex - Sell Order";
    }
}
