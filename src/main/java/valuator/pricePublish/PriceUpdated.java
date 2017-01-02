package main.java.valuator.pricePublish;

import main.java.valuator.formatter.OutputFormatter;
import main.java.valuator.model.Option;
import main.java.valuator.model.Stock;
import main.java.valuator.repositories.OptionDaoImpl;
import main.java.valuator.repositories.StockDaoImpl;
import main.java.valuator.service.OptionPriceCalculator;
import main.java.valuator.service.PriceObserver;
import main.java.valuator.service.StockPriceCalculator;

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
