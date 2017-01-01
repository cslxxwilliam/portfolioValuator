package portfolio.valuator;

import portfolio.valuator.pricePublish.PriceUpdated;
import portfolio.valuator.pricePublish.PriceUpdatedObserver;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            validate(args);

            String portfolio = args[1];
            Market market = new Market(portfolio);
            market.init();

            NavFilePublisher navFilePublisher = new NavFilePublisher();
            navFilePublisher.start();

            PriceUpdated priceUpdated = new PriceUpdated();

            new PriceUpdatedObserver(priceUpdated);

            PricePublisher pricePublisher = new PricePublisher(priceUpdated);
            pricePublisher.start();
        } catch (Exception e) {
            System.out.println("Invalid usage. Valid: portfolioNav.exe -c portfolio.csv");
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Invalid usage");
        }

        if (!"-c".equals(args[0])) {
            throw new Exception("Invalid usage");
        }
    }

}
