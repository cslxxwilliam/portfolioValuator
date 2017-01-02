package main.java.valuator.service;

import org.springframework.stereotype.Component;
import main.java.valuator.model.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Created by williamxuxianglin on 27/12/16.
 */
@Component
public class StockPriceCalculator {
    private static final int divisor = 7257600;
    private Random randomGenerator = new Random();
    public BigDecimal calculate(Stock stock, long interval) {
//        System.out.println("I'm calculating the stock price for "+stock.getName()+" based on "+interval +"ms");

        BigDecimal timeIntervalSecs = BigDecimal.valueOf(interval).divide(new BigDecimal("1000"),10, RoundingMode.HALF_UP);
        BigDecimal expectedReturnFactor = stock.getExpectedReturn().multiply(timeIntervalSecs).divide(BigDecimal.valueOf(divisor), 10, RoundingMode.HALF_UP);
        BigDecimal annualizedSdFactor = stock.getAnnualizedSd().multiply(BigDecimal.valueOf(randomGenerator.nextGaussian()))
                .multiply(BigDecimal.valueOf(Math.sqrt(timeIntervalSecs.divide(BigDecimal.valueOf(divisor), 10, RoundingMode.HALF_UP).doubleValue())));
        BigDecimal delta =  expectedReturnFactor.multiply(annualizedSdFactor).multiply(stock.getPrice());

//        System.out.println("Expected return factor: "+expectedReturnFactor);
//        System.out.println("Annualized SD factor: "+annualizedSdFactor);
//        System.out.println("Current stock price: "+stock.getPrice());
//        System.out.println("Delta: "+delta);
        return stock.getPrice().add(delta);
    }
}
