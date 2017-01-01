package portfolio.valuator;

import portfolio.valuator.model.Option;

import java.util.List;

/**
 * Created by williamxuxianglin on 28/12/16.
 */
public interface OptionDao {
    List<Option> findAll();

    void save(Option option);

    Option findById(int id);

    Option findByTicker(String ticker);
}
