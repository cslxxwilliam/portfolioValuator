package main.java.valuator.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by williamxuxianglin on 1/1/17.
 */
public class OutputFormatter {
    private static String pattern = "#0.00";
    private static String patternFourDp = "#0.0000000000000";

    public static String format(BigDecimal bd){
        return new DecimalFormat(pattern).format(bd);
    };

    public static String formatFourDp(BigDecimal bd) {
        return new DecimalFormat(patternFourDp).format(bd);
    }
}
