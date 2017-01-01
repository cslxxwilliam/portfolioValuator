package portfolio.valuator.model;

import portfolio.valuator.model.AssetPosition;
import portfolio.valuator.model.Option;

import java.math.BigDecimal;

/**
 * Created by williamxuxianglin on 28/12/16.
 */
public class OptionPosition extends AssetPosition{
    private long id;

    public OptionPosition(Option option, int quantity) {
        super(option,quantity,BigDecimal.ZERO);
    }

    public OptionPosition() {
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOption(Option option) {
        this.asset = option;
    }

    public void setContractsHeld(int contractsHeld) {
        this.quantity = contractsHeld;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "OptionPosition{" +
                "id=" + id +
                ", option=" + getOption() +
                ", contractsHeld=" + getContractsHeld() +
                ", marketValue=" + marketValue +
                '}';
    }

    public BigDecimal getOptionPrice() {
        return asset.getPrice();
    }

    public int getContractsHeld() {
        return quantity;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public long getId() {
        return id;
    }

    public Option getOption() {
        return (Option) asset;
    }
}
