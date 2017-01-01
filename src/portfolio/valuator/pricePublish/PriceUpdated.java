package portfolio.valuator.pricePublish;

import portfolio.valuator.*;
import portfolio.valuator.formatter.OutputFormatter;
import portfolio.valuator.model.Option;
import portfolio.valuator.model.Stock;
import portfolio.valuator.repositories.OptionDaoImpl;
import portfolio.valuator.repositories.StockDaoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by williamxuxianglin on 26/12/16.
 */
public class PriceUpdated {
    private List<Stock> stockList;
    private List<Option> optionList;
    private List<PriceObserver> observerList;
    private StockPriceCalculator stockPriceCalculator;
    private OptionPriceCalculator optionPriceCalculator;

    public PriceUpdated() {
        this.stockPriceCalculator = new StockPriceCalculator();
        this.optionPriceCalculator = new OptionPriceCalculator();
        this.observerList= new ArrayList<PriceObserver>();
    }

    public void attach(PriceUpdatedObserver observer) {
        observerList.add(observer);
    }

    public void updatePrice(long interval) {
        StockDaoImpl stockRepo = StockDaoImpl.getInstance();
        stockList = stockRepo.findAll();
        optionList = OptionDaoImpl.getInstance().findAll();
        for (Stock stock : stockList) {
            BigDecimal newPrice = stockPriceCalculator.calculate(stock, interval);
            System.out.println("New price published for "+stock.getTicker()+" "+stock.getName()+": "+ OutputFormatter.formatFourDp(newPrice));
            stock.updatePrice(newPrice);
            stockRepo.save(stock);
        }

        OptionDaoImpl optionRepo = OptionDaoImpl.getInstance();
        optionList = optionRepo.findAll();
        for (Option option : optionList) {
            BigDecimal newPrice = optionPriceCalculator.calculate(option);
            option.updatePrice(newPrice);
            optionRepo.save(option);
        }

        observerList.forEach(PriceObserver::calculateNav);
    }

}
