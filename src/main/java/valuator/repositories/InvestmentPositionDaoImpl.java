package main.java.valuator.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import main.java.valuator.model.InvestmentPosition;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by williamxuxianglin on 27/12/16.
 */
@Repository
public class InvestmentPositionDaoImpl implements InvestmentPositionDao{
    public static final InvestmentPositionDaoImpl INSTANCE = new InvestmentPositionDaoImpl();
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private InvestmentPositionDaoImpl(){}

    public static InvestmentPositionDaoImpl getInstance(){return INSTANCE;}

    @Override
    public List<InvestmentPosition> findAll() {

        Map<String, Object> params = new HashMap<String, Object>();

        String sql = "SELECT * FROM INVESTMENT_POSITION";

        return namedParameterJdbcTemplate.query(sql, params, new InvestmentPositionMapper());
    }

    @Override
    public void save(InvestmentPosition position) {
        String sql = "Update investment_position set market_value =:marketValue where id=:id";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("id", position.getId());
        params.put("marketValue", position.getMarketValue());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void create(InvestmentPosition pos) {
        String sql = "insert into INVESTMENT_POSITION(id, stock_id, shares_held, market_value) values(:id,:stock_id,:shares_held,:market_value)";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("id", generateId());
        params.put("stock_id", pos.getStock().getId());
        params.put("shares_held", pos.getSharesHeld());
        params.put("market_value", pos.getMarketValue());
        namedParameterJdbcTemplate.update(sql, params);
    }

    private long generateId() {
        try {
            return  SecureRandom.getInstance("SHA1PRNG").nextInt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static final class InvestmentPositionMapper implements RowMapper<InvestmentPosition> {

        public InvestmentPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvestmentPosition investmentPosition = new InvestmentPosition();
            investmentPosition.setId(rs.getLong("id"));
            investmentPosition.setStock(StockDaoImpl.getInstance().findById(rs.getLong("stock_id")));
            investmentPosition.setSharesHeld(rs.getInt("shares_held"));
            investmentPosition.setMarketValue(rs.getBigDecimal("market_value"));
            return investmentPosition;
        }
    }

}
