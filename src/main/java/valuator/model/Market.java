package main.java.valuator.model;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import main.java.valuator.config.H2DataSource;
import main.java.valuator.input.CsvReader;
import main.java.valuator.repositories.InvestmentPositionDaoImpl;
import main.java.valuator.repositories.OptionDaoImpl;
import main.java.valuator.repositories.OptionPositionDaoImpl;
import main.java.valuator.repositories.StockDaoImpl;

import java.util.List;

/**
 * Created by williamxuxianglin on 1/1/17.
 */
public class Market {
    private CsvReader reader;
    public Market(String portfolio) {
        reader = new CsvReader(portfolio);
    }

    public void init() {
        initDb();
    }

    private void initDb() {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(new H2DataSource().dataSource());

        StockDaoImpl stockDao = StockDaoImpl.getInstance();
        stockDao.setNamedParameterJdbcTemplate(template);

        OptionDaoImpl optionDaoImpl = OptionDaoImpl.getInstance();
        optionDaoImpl.setNamedParameterJdbcTemplate(template);

        InvestmentPositionDaoImpl investmentPositionDao = InvestmentPositionDaoImpl.getInstance();
        investmentPositionDao.setNamedParameterJdbcTemplate(template);

        OptionPositionDaoImpl optionPositionDao = OptionPositionDaoImpl.getInstance();
        optionPositionDao.setNamedParameterJdbcTemplate(template);

        List<AssetPosition> assetPositionList = reader.read();

        saveAssetsPositions(assetPositionList);

        List<Stock> stockList = stockDao.findAll();

        System.out.println("Market initiated");
        for (Stock stock : stockList) {
            System.out.println("Stock ticker: " + stock.getTicker() + " name: " + stock.getName());
        }

        System.out.println("Portfolio initiated");
        List<InvestmentPosition> positionList = investmentPositionDao.findAll();
        positionList.forEach(System.out::println);

        List<OptionPosition> optionPositionList = optionPositionDao.findAll();
        optionPositionList.forEach(System.out::println);
    }

    private void saveAssetsPositions(List<AssetPosition> assetPositionList) {
        for (AssetPosition pos : assetPositionList) {
            if(pos instanceof OptionPosition){
                OptionPositionDaoImpl.getInstance().create((OptionPosition) pos);
            }

            if(pos instanceof InvestmentPosition) {
                InvestmentPositionDaoImpl.getInstance().create((InvestmentPosition) pos);
            }
        }
    }
}
