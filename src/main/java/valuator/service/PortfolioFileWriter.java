package main.java.valuator.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by williamxuxianglin on 31/12/16.
 */
public class PortfolioFileWriter {

    private static final String FILENAME = "portfolioSummary.txt";

    public static void writeToFile(String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            String timestamp = "Refresh time: "+new Date();
            String combined = timestamp + "\n" + content;
            bw.write(combined);

            System.out.println("Printed portfolio summary to portfolioSummary.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}