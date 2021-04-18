package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.strategy.TradingStrategy;

public class PriceListenerImpl implements PriceListener {

    private final TradingStrategy tradingStrategy;

    public PriceListenerImpl(TradingStrategy tradingStrategy) {
        this.tradingStrategy = tradingStrategy;
    }

    @Override
    public void priceUpdate(String security, double price) {
        tradingStrategy.monitor(security, price);
    }
}
