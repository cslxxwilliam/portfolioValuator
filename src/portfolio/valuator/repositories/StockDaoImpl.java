package portfolio.valuator.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import portfolio.valuator.model.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by williamxuxianglin on 26/12/16.
 */
@Repository
public class StockDaoImpl implements StockDao{
    private static final StockDaoImpl INSTANCE = new StockDaoImpl();

    private StockDaoImpl(){}

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Stock> findAll() {
        Map<String, Object> params = new HashMap<String, Object>();

        String sql = "SELECT * FROM stock";

        List<Stock> result = namedParameterJdbcTemplate.query(sql, params, new StockMapper());

        return result;
    }

    @Override
    public void save(Stock stock) {
        String sql = "Update stock set price=:price where id=:id";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("id", stock.getId());
        params.put("price", stock.getPrice());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Stock findById(long id) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("id", id);

        String sql = "SELECT * FROM Stock where id=:id";

        return namedParameterJdbcTemplate.queryForObject(sql, params, new StockMapper());
    }

    public static StockDaoImpl getInstance() {
        return INSTANCE;
    }

    public Stock findByTicker(String ticker) {
        String sql = "SELECT * FROM Stock where ticker=:ticker";

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("ticker", ticker);

        return namedParameterJdbcTemplate.queryForObject(sql, params, new StockMapper());
    }

    private static final class StockMapper implements RowMapper<Stock> {

        public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
            Stock stock = new Stock();
            stock.setName(rs.getString("name"));
            stock.setTicker(rs.getString("ticker"));
            stock.setId(rs.getInt("id"));
            stock.setPrice(rs.getBigDecimal("price"));
            stock.setExpectedReturn(rs.getBigDecimal("expected_return"));
            stock.setAnnualizedSd(rs.getBigDecimal("annualized_sd"));
            return stock;
        }
    }
}
