package com.alicamlibel.marketexchange;

import com.alicamlibel.marketexchange.commands.MexCommand;
import com.alicamlibel.marketexchange.core.orders.BuyOrderRepository;
import com.alicamlibel.marketexchange.core.orders.SellOrderRepository;
import com.alicamlibel.marketexchange.gui.BuyGui;
import com.alicamlibel.marketexchange.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class MarketExchangePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        BuyOrderRepository buyOrderRepository = new BuyOrderRepository();
        SellOrderRepository sellOrderRepository = new SellOrderRepository();

        this.getCommand("mex").setExecutor(new MexCommand());
        this.getServer().getPluginManager().registerEvents(new MexGuiInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new MexGuiListener(), this);
        this.getServer().getPluginManager().registerEvents(new BuyGuiListener(), this);
        this.getServer().getPluginManager().registerEvents(new CreateBuyOrderGuiListener(buyOrderRepository), this);
        this.getServer().getPluginManager().registerEvents(new CreateSellOrderGuiListener(sellOrderRepository), this);


//        this.getServer().getScheduler().scheduleSyncRepeatingTask(this,
//                new OrderProcessor(buyOrderRepository, sellOrderRepository), 20L * 30, 20L * 5);
    }

    @Override
    public void onDisable() {

    }
}
