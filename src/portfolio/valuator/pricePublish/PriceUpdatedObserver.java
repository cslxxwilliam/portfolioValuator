package portfolio.valuator.pricePublish;

import portfolio.valuator.*;
import portfolio.valuator.pricePublish.PriceUpdated;

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
