package main.java.valuator.repositories;

import main.java.valuator.model.InvestmentPosition;

import java.util.List;

/**
 * Created by williamxuxianglin on 27/12/16.
 */
public interface InvestmentPositionDao {
    List<InvestmentPosition> findAll();

    void save(InvestmentPosition position);

    void create(InvestmentPosition pos);
}
