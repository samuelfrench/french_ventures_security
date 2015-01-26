package sample.spring.chapter14.dao;

import sample.spring.chapter14.domain.Product;


public interface ProductImageDAO {
	
	public String getImagePathSuffix(String productCode);
	
	public Integer getProductId(String productCode);
	
	public Product getProduct(String productCode);
}
