package main.java.valuator.service;

import main.java.valuator.model.InvestmentPosition;
import main.java.valuator.model.OptionPosition;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

/**
 * Created by williamxuxianglin on 31/12/16.
 */
public class PortfolioNavCalculator {
    private List<InvestmentPosition> investmentPositionList;
    private List<OptionPosition> optionPositionList;
    public PortfolioNavCalculator(List<InvestmentPosition> stockPositionList, List<OptionPosition> optionPositionList) {
        this.investmentPositionList = stockPositionList;
        this.optionPositionList = optionPositionList;
    }

    public BigDecimal calculate() {
        BigDecimal stockNav = investmentPositionList.stream().map(stock -> stock.getMarketValue()).reduce(ZERO, BigDecimal::add);
        BigDecimal optionNav = optionPositionList.stream().map(option -> option.getMarketValue()).reduce(ZERO, BigDecimal::add);
        return stockNav.add(optionNav);
    }
}
