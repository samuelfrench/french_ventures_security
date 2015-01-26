
package sample.spring.chapter14.dao;

import java.util.List;

import sample.spring.chapter14.domain.Product;

public interface ProductDao {
	List<Product> getAllProducts();
	void saveProduct(Product product);
	Product getProduct(Integer productId); // //
	void editProduct(Product product);
	Product getProductByCode(String productCode);
}
