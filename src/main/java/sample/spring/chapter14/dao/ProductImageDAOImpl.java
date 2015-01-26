/**
 * 
 */
package sample.spring.chapter14.dao;

import sample.spring.chapter14.domain.Product;

/**
 * @author samuelfrench
 *
 */
public class ProductImageDAOImpl implements ProductImageDAO {

	final static String EMPTY_RESULTS_SET = "empty";
	final static Integer EMPTY_RESULTS_ID = -1;
	
	@Override
	public String getImagePathSuffix(String productCode) {
		MockDatabase db = null;
		String returnValue = null;
		Product p = null;
		try {
			db = new MockDatabase(); 
			p = db.getProductByProductCode(productCode);
			returnValue = p.getResourceURL();
		} catch (NullPointerException e) {
			returnValue = EMPTY_RESULTS_SET;
		}
		return returnValue;
	}

	@Override
	public Integer getProductId(String productCode) {
		MockDatabase db = null;
		Integer returnValue = null;
		Product p = null;
		try {
			db = new MockDatabase(); 
			p = db.getProductByProductCode(productCode);
			returnValue = p.getProductId();
		} catch (NullPointerException e) {
			returnValue = EMPTY_RESULTS_ID;
		}
		return returnValue;
	}
	
	public Product getProduct(String productCode) {
		MockDatabase db = null;
		Product p = null;
		try {
			db = new MockDatabase(); 
			p = db.getProductByProductCode(productCode);
		} catch (NullPointerException e) {
			p = new Product();
			p.setDescription("Product Code Not Found!");
		}
		return p;
	}
}
