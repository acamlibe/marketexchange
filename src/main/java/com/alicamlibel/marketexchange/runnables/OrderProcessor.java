package com.alicamlibel.marketexchange.runnables;

import com.alicamlibel.marketexchange.core.orders.BuyOrderRepository;
import com.alicamlibel.marketexchange.core.orders.SellOrderRepository;
import com.alicamlibel.marketexchange.core.orders.entities.Order;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class OrderProcessor extends BukkitRunnable {

    private final BuyOrderRepository buyOrderRepository;
    private final SellOrderRepository sellOrderRepository;

    public OrderProcessor(BuyOrderRepository buyOrderRepository, SellOrderRepository sellOrderRepository) {
        this.buyOrderRepository = buyOrderRepository;
        this.sellOrderRepository = sellOrderRepository;
    }

    @Override
    public void run() {
//        List<Order> buyOrders = buyOrderRepository.getOrders();
//        List<Order> sellOrders = sellOrderRepository.getOrders();
//
//        for (Order buyOrder : buyOrders) {
//            Material buyOrderItemMaterial = buyOrder.getItemStack();
//
//            for (Order sellOrder : sellOrders) {
//                if (sellOrder.getItemStack() != buyOrderItemMaterial) {
//                    continue;
//                }
//
//                if (buyOrder.getPricePerQuantity() < sellOrder.getPricePerQuantity()) {
//                    continue;
//                }
//
//                if (sellOrder.getRemainingQuantity() >= buyOrder.getRemainingQuantity()) {
//                    sellOrder.setQuantity(sellOrder.getRemainingQuantity() - buyOrder.getRemainingQuantity());
//                    buyOrder.setQuantity(0);
//                    buyOrder.completeOrder();
//                }
//                else {
//                    buyOrder.setQuantity(buyOrder.getRemainingQuantity() - sellOrder.getRemainingQuantity());
//                    sellOrder.setQuantity(0);
//                    sellOrder.completeOrder();
//                }
//            }
//        }
    }

}
