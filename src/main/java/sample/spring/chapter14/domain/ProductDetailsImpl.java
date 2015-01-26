package sample.spring.chapter14.domain;

import java.util.ArrayList;
import java.util.List;

import sample.spring.chapter14.dao.ProductImageDAO;
import sample.spring.chapter14.dao.ProductImageDAOImpl;
import sample.spring.chapter14.service.Basic;


public class ProductDetailsImpl implements ProductDetails {

	public ProductDetailsImpl() {
		// Auto-generated constructor stub
	}

	@Override
	public String getProductLink(String imgCode, boolean thumbnail) {

		Boolean foundImage = false;
		String returnValue = null;
		String imageSuffix = null;
		
		
		//check this stuff in controller
		if(imgCode != null) {
			//get image suffix for building the full path
			ProductImageDAO dao = null;
			try {
				dao = new ProductImageDAOImpl();
				imageSuffix = dao.getImagePathSuffix(imgCode);
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


	@Override
	public String getProductListing(String productCode) {
		String productListingHtml = null;
		
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

	@Override
	public String getProductListingDetails(String productCode) {
		String productDetails = "test - " + productCode;
		return productDetails;
	}
}
