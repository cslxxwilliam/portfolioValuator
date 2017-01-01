package portfolio.valuator;

import portfolio.valuator.pricePublish.PriceUpdated;

/**
 * Created by williamxuxianglin on 26/12/16.
 */
public abstract class PriceObserver {
    protected PriceUpdated priceUpdated;
    public abstract void calculateNav();
}
