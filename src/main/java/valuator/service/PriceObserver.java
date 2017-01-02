package main.java.valuator.service;

import main.java.valuator.pricePublish.PriceUpdated;

/**
 * Created by williamxuxianglin on 26/12/16.
 */
public abstract class PriceObserver {
    protected PriceUpdated priceUpdated;
    public abstract void calculateNav();
}
