package sample.spring.chapter14.service;

import org.springframework.security.access.prepost.PreAuthorize;

import sample.spring.chapter14.domain.Product;

public interface ProductImageService {
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getImagePathSuffix(String productCode);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Integer getProductId(String productCode);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Product getProduct(String productCode);
}
