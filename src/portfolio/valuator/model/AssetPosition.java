package portfolio.valuator.model;

import java.math.BigDecimal;

/**
 * Created by williamxuxianglin on 1/1/17.
 */
//CREATE TABLE INVESTMENT_POSITION(
//        id int primary key,
//        stock_id int not null,
//        shares_held int not null,
//        market_value number not null);
//
//        CREATE TABLE OPTION_POSITION(
//        id int primary key,
//        option_id int not null,
//        contracts_held int not null,
//        market_value number not null
//        );
public abstract class AssetPosition {
    protected Asset asset;
    protected int quantity;
    protected BigDecimal marketValue;

    public AssetPosition(Asset asset, int quantity, BigDecimal marketValue) {
        this.asset = asset;
        this.quantity = quantity;
        this.marketValue = marketValue;
    }

    public AssetPosition() {

    }
}
