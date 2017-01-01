package portfolio.valuator;

import com.sun.deploy.util.StringUtils;
import portfolio.valuator.model.Option;
import portfolio.valuator.model.OptionPosition;
import portfolio.valuator.repositories.InvestmentPositionDaoImpl;
import portfolio.valuator.repositories.OptionPositionDaoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static portfolio.valuator.formatter.OutputFormatter.format;

/**
 * Created by williamxuxianglin on 31/12/16.
 */
public class PortfolioNavPrinter {

    private List<OptionPosition> optionPositionList;
    private List<InvestmentPosition> stockPositionList;

    public PortfolioNavPrinter() {
        optionPositionList = OptionPositionDaoImpl.getInstance().findAll();
        stockPositionList = InvestmentPositionDaoImpl.getInstance().findAll();
    }

    public String printNav() {
        optionPositionList = OptionPositionDaoImpl.getInstance().findAll();
        stockPositionList = InvestmentPositionDaoImpl.getInstance().findAll();
        PortfolioNavCalculator portfolioNavCalculator = new PortfolioNavCalculator(stockPositionList, optionPositionList);
        BigDecimal totalNav = portfolioNavCalculator.calculate();

        return "Portfolio total NAV: "+format(totalNav)+"\n";
    }

    String printNavBreakdown() {
        optionPositionList = OptionPositionDaoImpl.getInstance().findAll();
        stockPositionList = InvestmentPositionDaoImpl.getInstance().findAll();

        List<String> resultStringList = new ArrayList<>();
        resultStringList.add(printNav());

        resultStringList.addAll(stockPositionList.stream().map(this::printMarketValue).collect(toList()));
        resultStringList.addAll(optionPositionList.stream().map(this::printMarketValue).collect(toList()));
        return StringUtils.join(resultStringList, "\n");
    }

    private String printMarketValue(InvestmentPosition pos) {
        return pos.getStock().getName()+" market value: "+ format(pos.getMarketValue())+", price: "+format(pos.getStockPrice())+", shares held: "+pos.getSharesHeld();
    }

    private String printMarketValue(OptionPosition pos) {
        Option option = pos.getOption();
        return option.getType()+" option"+" with underlying stock "+ option.getUnderlying().getName()+ " market value: "+format(pos.getMarketValue())+", price: "+format(option.getPrice())+", contracts held: "+pos.getContractsHeld();
    }
}
