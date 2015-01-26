package sample.spring.chapter14.domain;

public interface ProductDetails {
	
	public final static String REQUESTED_DATA_NOT_AVAILABLE = "Data unavailable"; //change to 404
	public final static String IMAGE_BASE_PATH = "/french_ventures_secure/resources/image";
	public final static String PRODUCT_IMAGE_COMPRESSED_PATH = "/product/compressed/";
	public final static String PRODUCT_IMAGE_ORIGINAL_PATH = "/product/original/";
	public final static String PRODUCT_IMAGE_THUMBNAIL_PATH = "/product/thumb/";
	
	public String getProductLink(String imgCode, boolean thumbnail);
	public String getProductImageContainer(String imgCode, boolean thumbnail);
	public String getProductListing(String productCode);
	public String getProductListingDetails(String productCode);
}

