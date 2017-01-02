package main.java.valuator.pricePublish;

import main.java.valuator.model.InvestmentPosition;
import main.java.valuator.model.OptionPosition;
import main.java.valuator.model.Portfolio;
import main.java.valuator.repositories.InvestmentPositionDaoImpl;
import main.java.valuator.repositories.OptionPositionDaoImpl;
import main.java.valuator.service.PortfolioNavPrinter;
import main.java.valuator.service.PriceObserver;

import java.util.List;

/**
 * Created by williamxuxianglin on 26/12/16.
 */
public class PriceUpdatedObserver extends PriceObserver {
    public PriceUpdatedObserver(PriceUpdated updated) {
        priceUpdated = updated;
        updated.attach(this);
    }

    @Override
    public void calculateNav() {
        List<InvestmentPosition> positionList = InvestmentPositionDaoImpl.getInstance().findAll();
        List<OptionPosition> optionPositionList = OptionPositionDaoImpl.getInstance().findAll();

        Portfolio portfolio = new Portfolio(positionList, optionPositionList);
        portfolio.calculateMarketValue();

        PortfolioNavPrinter printer = new PortfolioNavPrinter();
        System.out.print(printer.printNav());
    }
}
