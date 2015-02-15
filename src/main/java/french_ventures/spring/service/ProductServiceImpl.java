package french_ventures.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.stereotype.Service;

import french_ventures.spring.dao.ProductDao;
import french_ventures.spring.domain.Product;

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
	public List<Product> getProductsUser() {
		List<Product> userList = productDao.getAllProducts();
		for(Product p: userList)
		{
			p.setSupplyCostUSD(null);
			p.setProductId(null);
		}
		return userList;
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
		return productDao.getProductByCode(productCode);
	}

	@Override
	public void editProduct(Product product) {
		// TODO Auto-generated method stub

	}
	
	public final static String REQUESTED_DATA_NOT_AVAILABLE = "Data unavailable"; //change to 404
	public final static String IMAGE_BASE_PATH = "/french_ventures_secure/resources/image";
	public final static String PRODUCT_IMAGE_COMPRESSED_PATH = "/product/compressed/";
	public final static String PRODUCT_IMAGE_ORIGINAL_PATH = "/product/original/";
	public final static String PRODUCT_IMAGE_THUMBNAIL_PATH = "/product/thumb/";
	@Deprecated
	@Override
	public String getProductLink(String imgCode, boolean thumbnail) {

		Boolean foundImage = false;
		String returnValue = null;
		String imageSuffix = null;
		
		
		//check this stuff in controller
		if(imgCode != null) {
			try {
				imageSuffix = productDao.getProductByCode(imgCode).getResourceURL();
				System.out.println(imageSuffix);
				foundImage = true;
			} catch (Exception e) {
				//TODO - log error
				e.printStackTrace();
				foundImage = false;
			}
		} else {
			foundImage = false;
		}
		
		//build path for compressed or original
		if(foundImage == true)
		{
			returnValue = IMAGE_BASE_PATH;
			System.out.println("Compressed: " + thumbnail);
			if(thumbnail){
				returnValue = returnValue.concat(PRODUCT_IMAGE_THUMBNAIL_PATH);
				System.out.println("Compressed path: " + returnValue);
			} else {
				returnValue = returnValue.concat(PRODUCT_IMAGE_COMPRESSED_PATH);
			}
			returnValue = returnValue.concat(imageSuffix);
		} else {
			returnValue = REQUESTED_DATA_NOT_AVAILABLE;
		}
		return returnValue;
	}
	

	@Deprecated
	@Override
	public String getProductImageContainer(String imgCode, boolean thumbnail) {
		String imageLink = getProductLink(imgCode, thumbnail);
		List<String> productImageClasses = new ArrayList<String>();
		productImageClasses.add("productImage");
		productImageClasses.add("image");
		if(thumbnail)
		{
			productImageClasses.add("small");
		} else {
			productImageClasses.add("large");
		}
		
		String returnHtml = Basic.createImageContainer(imageLink, productImageClasses);
		
		return returnHtml;
	}


	@Deprecated
	@Override
	public String getProductListing(String productCode) {
		String productListingHtml = null;
		
		//BUG - dead store TODO
		String fullContainer = getProductImageContainer(productCode, false);
		String thumbContainer = getProductImageContainer(productCode, true);
		
		//set onclick
		
		productListingHtml = "<div id='listing_" + productCode + "' class='productListingContainer'>";
		productListingHtml += "<a id='" + productCode + "' "
				+ "href='/french_ventures/view/product/getDetails/" + productCode + "'>";
		productListingHtml += "<span class='thumb'>" + thumbContainer + "</span>";
		productListingHtml += "</a>";
		productListingHtml += "<span class='details'>" + getProductListingDetails(productCode) + "</span>";
		productListingHtml += "</div>";
		
		
		return productListingHtml;
	}

	@Deprecated
	@Override
	public String getProductListingDetails(String productCode) {
		String productDetails = "test - " + productCode;
		return productDetails;
	}

}
