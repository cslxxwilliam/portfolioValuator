package main.java.valuator.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import main.java.valuator.model.OptionPosition;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by williamxuxianglin on 28/12/16.
 */
public class OptionPositionDaoImpl implements OptionPositionDao {
    private static OptionPositionDaoImpl INSTANCE = new OptionPositionDaoImpl();

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private OptionPositionDaoImpl(){}

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<OptionPosition> findAll() {

        Map<String, Object> params = new HashMap<String, Object>();

        String sql = "SELECT * FROM OPTION_POSITION";

        return namedParameterJdbcTemplate.query(sql, params, new OptionPositionRowMapper());

    }

    @Override
    public void save(OptionPosition position) {
        String sql = "Update option_position set market_value =:marketValue where id=:id";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("id", position.getId());
        params.put("marketValue", position.getMarketValue());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void create(OptionPosition pos) {
        String sql = "insert into option_position(id, option_id, contracts_held, market_value) values(:id,:option_id,:contracts_held,:market_value)";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("id", generateId());
        params.put("option_id", pos.getOption().getId());
        params.put("contracts_held", pos.getContractsHeld());
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

    public static OptionPositionDaoImpl getInstance() {
        return INSTANCE;
    }

    private static final class OptionPositionRowMapper implements RowMapper<OptionPosition> {
        public OptionPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
            OptionPosition position = new OptionPosition();
            position.setId(rs.getLong("id"));
            position.setOption(OptionDaoImpl.getInstance().findById(rs.getInt("option_id")));
            position.setContractsHeld(rs.getInt("contracts_held"));
            position.setMarketValue(rs.getBigDecimal("market_value"));
            return position;
        }
    }
}
