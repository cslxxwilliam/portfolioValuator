package portfolio.valuator.service;

import portfolio.valuator.*;
import portfolio.valuator.model.AssetPosition;
import portfolio.valuator.model.OptionPosition;
import portfolio.valuator.repositories.OptionDaoImpl;
import portfolio.valuator.repositories.StockDaoImpl;

/**
 * Created by williamxuxianglin on 1/1/17.
 */
public class AssetPositionFactory {

    public static AssetPosition create(String[] inputs) {
        int quantity = Integer.parseInt(inputs[1]);

        AssetPosition assetPosition = null;
        switch (inputs[2]){
            case "Stock":
                assetPosition =  new InvestmentPosition(StockDaoImpl.getInstance().findByTicker(inputs[0]), quantity);
                break;
            case "Option":
                assetPosition = new OptionPosition(OptionDaoImpl.getInstance().findByTicker(inputs[0]), quantity);
        }

        return assetPosition;
    }
}
