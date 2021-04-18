package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class TradingStrategyTest {

    TradingStrategy tradingStrategy;
    PriceListener priceListener;
    ExecutionService executionServiceMock;

    @Before
    public void setup(){
       executionServiceMock = mock(ExecutionService.class);
    }

    @Test
    public void testNothing() {
    }

    @Test
    public void testBuy() {
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            Object arg2 = invocation.getArgument(2);
            assertEquals("IBM", arg0);
            assertEquals(55.1, arg1);
            assertEquals(100, arg2);
            return null;
        }).when(executionServiceMock).buy(any(String.class), any(Float.class), any(Integer.class));

        tradingStrategy = new TradingStrategy("IBM", 60.0, 100, executionServiceMock);
        priceListener = new PriceListenerImpl(tradingStrategy);
        priceListener.priceUpdate("IBM", 55.1);
        verify(priceListener, times(1)).priceUpdate("IBM", 55.1);
        verify(executionServiceMock, times(1)).buy("IBM", 55.1, 100);
    }

    @Test
    public void testNoBuy() {
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            Object arg2 = invocation.getArgument(2);
            assertEquals("IBM", arg0);
            assertEquals(55.1, arg1);
            assertEquals(100, arg2);
            return null;
        }).when(executionServiceMock).buy(any(String.class), any(Float.class), any(Integer.class));

        tradingStrategy = new TradingStrategy("IBM", 50, 100, executionServiceMock);
        priceListener = new PriceListenerImpl(tradingStrategy);
        priceListener.priceUpdate("IBM", 55.1);
        verify(executionServiceMock, times(0)).buy("IBM", 55.1, 100);
    }
}
