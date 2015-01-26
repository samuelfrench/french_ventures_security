package sample.spring.chapter14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.stereotype.Service;

import sample.spring.chapter14.dao.ProductDao;
import sample.spring.chapter14.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private MutableAclService mutableAclService;
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product getProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductByProductCode(String productCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editProduct(Product product) {
		// TODO Auto-generated method stub

	}

}
