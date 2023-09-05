package com.alicamlibel.marketexchange.core.orders.entities;

import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Order {
    private final int orderId;
    private final UUID playerId;
    private final ItemStack itemStack;
    private final int totalQuantity;
    private final double pricePerQuantity;

    private int remainingQuantity;
    private boolean orderComplete;

    public Order(int orderId, UUID playerId, ItemStack itemStack, int quantity, double pricePerQuantity) {
        this.orderId = orderId;
        this.playerId = playerId;
        this.itemStack = itemStack;
        this.totalQuantity = quantity;
        this.remainingQuantity = quantity;
        this.pricePerQuantity = pricePerQuantity;
        this.orderComplete = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setQuantity(int newQuantity) {
        this.remainingQuantity = newQuantity;
    }

    public double getPricePerQuantity() {
        return pricePerQuantity;
    }

    public void completeOrder() {
        orderComplete = true;
    }
}
