package main.java.valuator.input;

import main.java.valuator.model.AssetPosition;
import main.java.valuator.service.AssetPositionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by williamxuxianglin on 1/1/17.
 */
public class CsvReader {
    String fileName;

    public CsvReader(String fileName) {
        this.fileName = fileName;
    }

    public List<AssetPosition> read() {
        String line = "";
        String cvsSplitBy = ",";

        List<AssetPosition> assetPositionList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            line = br.readLine();
            validateHeader(line.split(cvsSplitBy));

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] inputs = line.split(cvsSplitBy);

                assetPositionList.add(AssetPositionFactory.create(inputs));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assetPositionList;
    }

    private void validateHeader(String[] split) throws Exception {
        if (split.length!=3 || !split[0].equals("Ticker") || !split[1].equals("Shares/Contracts") || !split[2].equals("Type")) {
            throw new Exception("Headers are invalid: "+split+". Expecting: Ticker,Shares/Contracts,Type");
        }
    }
}
