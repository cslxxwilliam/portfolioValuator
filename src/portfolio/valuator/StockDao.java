package portfolio.valuator;

import portfolio.valuator.model.Stock;

import java.util.List;

/**
 * Created by williamxuxianglin on 26/12/16.
 */
public interface StockDao {
    List<Stock> findAll();

    void save(Stock stock);

    Stock findById(long id);
}
