package main.java.valuator.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import main.java.valuator.model.OptionType;
import main.java.valuator.model.Option;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * Created by williamxuxianglin on 28/12/16.
 */
public class OptionDaoImpl implements OptionDao {
    private static OptionDaoImpl INSTANCE = new OptionDaoImpl();
    NamedParameterJdbcTemplate template;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    private OptionDaoImpl() {
    }

    @Override
    public List<Option> findAll() {
        String sql = "select * from option";

        return template.query(sql, new HashMap<String, Object>(),new OptionMapper());
    }

    @Override
    public void save(Option option) {
        String sql = "Update option set price =:price where id=:id";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("id", option.getId());
        params.put("price", option.getPrice());
        template.update(sql, params);
    }

    @Override
    public Option findById(int id) {
        String sql = "select * from option where id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return template.queryForObject(sql, params,new OptionMapper());
    }

    @Override
    public Option findByTicker(String ticker) {
        String sql = "select * from option where ticker=:ticker";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ticker", ticker);
        return template.queryForObject(sql, params,new OptionMapper());
    }

    public static OptionDaoImpl getInstance() {
        return INSTANCE;
    }

    private static final class OptionMapper implements RowMapper<Option> {
        public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
            Option option = new Option();
            option.setId(rs.getLong("id"));
            option.setTicker(rs.getString("ticker"));
            option.setType(OptionType.valueOf(rs.getString("type")));
            option.setStock(StockDaoImpl.getInstance().findById(rs.getLong("underlying_id")));
            option.setMaturity(rs.getInt("maturity"));
            option.setStrike(rs.getBigDecimal("strike"));
            option.setPrice(rs.getBigDecimal("price"));
            return option;
        }
    }
}
