package main.java.valuator.model;

import java.math.BigDecimal;

/**
 * Created by williamxuxianglin on 27/12/16.
 */
public class InvestmentPosition extends AssetPosition{
    private long id;

    public InvestmentPosition(Stock byTicker, int quantity) {
        super(byTicker, quantity, BigDecimal.ZERO);
    }

    public InvestmentPosition() {

    }

    public BigDecimal getStockPrice() {
        return asset.getPrice();
    }

    public int getSharesHeld() {
        return quantity;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public Stock getStock() {
        return (Stock) asset;
    }

    @Override
    public String toString() {
        return "InvestmentPosition{" +
                "stock=" + asset +
                ", sharesHeld=" + getSharesHeld() +
                ", marketValue=" + marketValue +
                '}';
    }

    public void setStock(Stock stock) {
        this.asset = stock;
    }

    public long getId() {
        return id;
    }

    public void setSharesHeld(int sharesHeld) {
        this.quantity = sharesHeld;
    }

    public void setId(long id) {
        this.id = id;
    }
}
