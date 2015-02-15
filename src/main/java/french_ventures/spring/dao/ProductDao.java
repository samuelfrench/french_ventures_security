
package french_ventures.spring.dao;

import java.util.List;

import french_ventures.spring.domain.Product;

public interface ProductDao {
	List<Product> getAllProducts();
	void saveProduct(Product product);
	Product getProduct(Integer productId); // //
	void editProduct(Product product);
	Product getProductByCode(String productCode);
	void deleteProduct(Product product);
}
