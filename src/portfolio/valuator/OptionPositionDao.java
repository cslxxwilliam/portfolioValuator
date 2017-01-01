package portfolio.valuator;

import portfolio.valuator.model.AssetPosition;

import java.util.List;

/**
 * Created by williamxuxianglin on 28/12/16.
 */
public interface OptionPositionDao {
    List<OptionPosition> findAll();

    void save(OptionPosition position);

    void create(OptionPosition pos);
}
