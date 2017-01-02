package main.java.valuator.model;

import java.math.BigDecimal;

/**
 * Created by williamxuxianglin on 1/1/17.
 */
//CREATE TABLE STOCK(
//        id int primary key,
//        ticker varchar(255),
//        name varchar(255),
//        expected_return number not null,
//        annualized_sd number not null,
//        price number not null);
//
//        CREATE TABLE OPTION(
//        id int primary key,
//        type varchar(255),
//        underlying_id int not null,
//        maturity int not null,
//        strike number not null,
//        price number not null
//        );
public abstract class Asset {
    protected String ticker;
    BigDecimal price;

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }


}
