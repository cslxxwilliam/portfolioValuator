package portfolio.valuator;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by williamxuxianglin on 27/12/16.
 */
public class Portfolio {
    private List<OptionPosition> optionPositionList;
    private List<InvestmentPosition> positionList;
    InvestmentPositionDaoImpl investmentPositionDao;
    OptionPositionDaoImpl optionPositionDao;

    public Portfolio(List<InvestmentPosition> positionList, List<OptionPosition> optionPositionList) {
        this.positionList = positionList;
        this.optionPositionList = optionPositionList;
        investmentPositionDao = InvestmentPositionDaoImpl.getInstance();
        optionPositionDao = OptionPositionDaoImpl.getInstance();
    }

    public void calculateMarketValue() {
        //if market value changes, printout
        for (InvestmentPosition position : positionList) {
            BigDecimal newMarketValue = position.getStockPrice().multiply(BigDecimal.valueOf(position.getSharesHeld()));
            if(!newMarketValue.equals(position.getMarketValue())){
                position.setMarketValue(newMarketValue);
                investmentPositionDao.save(position);
            }
        }

        for (OptionPosition position : optionPositionList) {
            BigDecimal newMarketValue = position.getOptionPrice().multiply(BigDecimal.valueOf(position.getContractsHeld()));
            if(!newMarketValue.equals(position.getMarketValue())){
                position.setMarketValue(newMarketValue);
                optionPositionDao.save(position);
            }
        }
    }
}
