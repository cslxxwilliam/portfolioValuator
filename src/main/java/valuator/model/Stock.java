package main.java.valuator.model;

import java.math.BigDecimal;

/**
 * Created by williamxuxianglin on 26/12/16.
 */

public class Stock extends Asset{
    private String name;
    private int id;
    private BigDecimal expectedReturn;
    private BigDecimal annualizedSd;

    public Stock(){}

    public void updatePrice(BigDecimal price) {
        setPrice(price);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setExpectedReturn(BigDecimal expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    public void setAnnualizedSd(BigDecimal annualizedSd) {
        this.annualizedSd = annualizedSd;
    }

    public BigDecimal getExpectedReturn() {
        return expectedReturn;
    }

    public BigDecimal getAnnualizedSd() {
        return annualizedSd;
    }
}
