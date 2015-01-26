package sample.spring.chapter14.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import sample.spring.chapter14.domain.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public List<Product> getAllProducts() {
		final String sql = "select * from product";
		return namedParameterJdbcTemplate.query(
				sql,
				new ResultSetExtractor<List<Product>>() {
					List<Product> ps = new ArrayList<Product>();
					
					@Override
					public List<Product> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
						while(rs.next())
						{
							Product p = new Product();
							p.setProductId(rs.getInt("product_id"));
							p.setDescription(rs.getString("description"));
							p.setProductCode(rs.getString("product_code"));
							p.setQtyPerUnit(rs.getInt("qty_per_unit"));
							p.setResourceURL(rs.getString("resource_url"));
							p.setRetailPriceUSD(rs.getDouble("retail_price_usd"));
							p.setSupplyCostUSD(rs.getDouble("supply_cost_usd"));
							p.setUnitOnHand(rs.getInt("stock_quantity"));
							ps.add(p);
						}
						return ps;
					}
				});
	}

	@Override
	public void saveProduct(Product product) {
		final String sql = "INSERT INTO product(`description`,`product_code`,`qty_per_unit`" +
								",`resource_url`,`retail_price_usd`,`supply_cost_usd`,`stock_quantity`) " +
								"VALUES (:description, :productCode, :qtyPerUnit, :resourceURL,"
								+ " :retailPriceUSD, :supplyCostUSD, :stockQuantity)";
		Map<String, String> namedParams = new HashMap<String, String>();
		namedParams.put("description", product.getDescription());
		namedParams.put("productCode", product.getProductCode());
		namedParams.put("qtyPerUnit", product.getQtyPerUnit().toString().trim());
		namedParams.put("resourceURL", product.getResourceURL());
		namedParams.put("retailPriceUSD", product.getRetailPriceUSD().toString().trim());
		namedParams.put("supplyCostUSD", product.getSupplyCostUSD().toString().trim());
		namedParams.put("stockQuantity", product.getUnitOnHand().toString().trim());
		SqlParameterSource sqlParams = new MapSqlParameterSource(namedParams);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, sqlParams, keyHolder);
		product.setProductId(keyHolder.getKey().intValue());
	}

	@Override
	public Product getProduct(Integer productId) {
		List<Product> productList = getAllProducts();
		for(Product p: productList)
		{
			if(p.getProductId().equals(productId)){
				return p;
			}
		}
		return null;
	}

	@Override
	public void editProduct(Product product) {
		final String sql = "UPDATE product SET `archive` = 1 WHERE `product_code` = :productCode";
		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"productCode", product.getProductCode());

		namedParameterJdbcTemplate.update(sql, namedParameters);
		saveProduct(product);
		
		
	}

	@Override
	public Product getProductByCode(String productCode) {
		//this will need to be changed TODO
		
		List<Product> productList = getAllProducts();
		for(Product p: productList)
		{
			if(p.getProductCode().equalsIgnoreCase(productCode.trim())){
				return p;
			}
		}
		return null;
	}

	@Override
	public void deleteProduct(Product product) {
		final String sql = "UPDATE product SET `archive` = 1 WHERE `product_code` = :productCode";
		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"productCode", product.getProductCode());

		namedParameterJdbcTemplate.update(sql, namedParameters);		
	}

}