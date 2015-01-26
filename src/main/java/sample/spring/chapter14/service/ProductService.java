package sample.spring.chapter14.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import sample.spring.chapter14.domain.Product;

public interface ProductService {
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<Product> getAllProducts();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void saveProduct(Product product);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Product getProduct(int productId);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	Product getProductByProductCode(String productCode);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void editProduct(Product product);
}
