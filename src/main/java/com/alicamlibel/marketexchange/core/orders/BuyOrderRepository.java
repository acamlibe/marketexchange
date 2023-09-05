package com.alicamlibel.marketexchange.core.orders;

import com.alicamlibel.marketexchange.core.orders.entities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuyOrderRepository {

    private final List<Order> orders;

    public BuyOrderRepository() {
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void updateQuantity(int orderId, int newQuantity) {
        Order order = orders.stream().filter(o -> o.getOrderId() == orderId).findFirst().orElse(null);

        //TODO: handle
        if (order != null) {
            order.setQuantity(newQuantity);
        }
    }

    //TODO: temporary
    public int getNextId() {
        if (orders.isEmpty()) {
            return 0;
        }

        return orders.get(orders.size() - 1).getOrderId();
    }
}