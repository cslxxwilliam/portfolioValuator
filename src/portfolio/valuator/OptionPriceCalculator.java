package portfolio.valuator;

import org.apache.commons.math3.distribution.NormalDistribution;
import portfolio.valuator.model.Option;
import portfolio.valuator.model.Stock;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static portfolio.valuator.OptionType.CALL;

/**
 * Created by williamxuxianglin on 29/12/16.
 */
public class OptionPriceCalculator {
    private static final BigDecimal RISK_FREE_INTEREST_RATE = new BigDecimal("0.02");

    public BigDecimal calculate(Option option) {

        Stock stock = option.getUnderlying();
//        System.out.println("Calculating option price, stock "+stock.getName()+" price:"+stock.getPrice());

        BigDecimal stockPrice = option.getStockPrice();
        BigDecimal strike = option.getStrike();
        BigDecimal annualizedSd = stock.getAnnualizedSd();
        int maturity = option.getMaturity();

        double d1 = Math.log(stockPrice.divide(strike, 2, HALF_UP).doubleValue()) +
                maturity *
                        RISK_FREE_INTEREST_RATE.add(annualizedSd.pow(2).divide(new BigDecimal("2"), 2, HALF_UP)).doubleValue();
        double divisor = annualizedSd.doubleValue() * Math.sqrt(maturity);
        d1 = d1 / divisor;

        double d2 = d1 - divisor;

        BigDecimal eFactor = strike.multiply(valueOf(Math.pow(Math.E, RISK_FREE_INTEREST_RATE.negate().multiply(valueOf(maturity)).doubleValue())));

        if(option.getType().equals(CALL)){
            return calculate(valueOf(d1), valueOf(d2), false, stockPrice, eFactor);
        }

        return calculate(valueOf(d2).negate(), valueOf(d1).negate(), false, stockPrice, eFactor);
    }

    BigDecimal calculate(BigDecimal firstArgument, BigDecimal secondArgument, boolean negated, BigDecimal stockPrice, BigDecimal eFactor){
        NormalDistribution normalDistribution = new NormalDistribution();
        BigDecimal optionPrice = stockPrice.multiply(valueOf(normalDistribution.cumulativeProbability(firstArgument.doubleValue()))).subtract(
                eFactor.multiply(valueOf(normalDistribution.cumulativeProbability(secondArgument.doubleValue()))));

        if(negated){
            return optionPrice.negate();
        }

        return optionPrice;
    }
}
