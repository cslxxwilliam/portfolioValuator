package portfolio.valuator.model;

import java.math.BigDecimal;

/**
 * Created by williamxuxianglin on 28/12/16.
 */

public class Option extends Asset{
    private long id;
    private Stock underlying;
    private int maturity;
    private BigDecimal strike;
    private OptionType type;

    public void setId(long id) {
        this.id = id;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public void setStock(Stock stock) {
        this.underlying = stock;
    }

    public void setMaturity(int maturity) {
        this.maturity = maturity;
    }

    public void setStrike(BigDecimal strike) {
        this.strike = strike;
    }

    public void updatePrice(BigDecimal newPrice) {
        setPrice(newPrice);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OptionType getType() {
        return type;
    }

    public BigDecimal getStockPrice() {
        return getUnderlying().getPrice();
    }

    public Stock getUnderlying() {
        return underlying;
    }

    public BigDecimal getStrike() {
        return strike;
    }

    public int getMaturity() {
        return maturity;
    }

    public long getId() {
        return id;
    }

    public void setUnderlying(Stock underlying) {
        this.underlying = underlying;
    }
}
