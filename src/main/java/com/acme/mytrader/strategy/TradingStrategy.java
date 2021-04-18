package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    ExecutionService executionService;

    private String monitorSecurity;
    private double monitorPrice;
    private int lot;

    public TradingStrategy(String monitorSecurity, double monitorPrice, int lot, ExecutionService executionService) {
        this.monitorSecurity = monitorSecurity;
        this.monitorPrice = monitorPrice;
        this.lot = lot;
        this. executionService = executionService;
    }

    public void monitor(String security, double price) {
        if (security.equals(monitorSecurity) && price < monitorPrice) {
            executionService.buy(security, price, lot);
        }
    }
}
