package portfolio.valuator.repositories;

import portfolio.valuator.model.OptionPosition;

import java.util.List;

/**
 * Created by williamxuxianglin on 28/12/16.
 */
public interface OptionPositionDao {
    List<OptionPosition> findAll();

    void save(OptionPosition position);

    void create(OptionPosition pos);
}
