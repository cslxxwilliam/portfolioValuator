package portfolio.valuator;

import portfolio.valuator.pricePublish.PriceUpdated;

import java.util.Random;

/**
 * Created by williamxuxianglin on 26/12/16.
 */
class PricePublisher {
    private static final int MAX_INTERVAL_MILLISECONDS = 2000;
    private static final int MIN_INTERVAL_MILLISECONDS = 500;
    private final PriceUpdated priceUpdated;


    public PricePublisher(PriceUpdated priceUpdated) {
        this.priceUpdated = priceUpdated;
    }

    public void start() {
        while (true) {
            try {
                long interval = getRandomizedInterval();
                Thread.sleep(interval);
                priceUpdated.updatePrice(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long getRandomizedInterval() {
        Random generator = new Random();
        return (long) (generator.nextDouble()*(MAX_INTERVAL_MILLISECONDS - MIN_INTERVAL_MILLISECONDS)+MIN_INTERVAL_MILLISECONDS);
    }
}
