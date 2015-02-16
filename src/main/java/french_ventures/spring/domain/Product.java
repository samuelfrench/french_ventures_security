package french_ventures.spring.domain;

import org.springframework.web.multipart.MultipartFile;

public class Product {
	private Integer productId;
	private String resourceURL;
	private String description;
	private Integer qtyPerUnit;
	private Integer unitOnHand;
	private Double weightInGrams;
	private Double retailPriceUSD;
	private Double supplyCostUSD;
	private Double length;
	private Double width;
	private Double thickness;
	
	//system
	private Boolean archive;
	
	//transient placeholders
	private String imageHtml;
	private String cost;
	private String inStock;
	private MultipartFile image;
	
	@Deprecated
	private String largeImageDiv;
	//SAFE_UNIQUE_ID CODE
	private String productCode;
	
	
	/**
	 * @return the productCode
	 */
	//@Column(unique=true)
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * Default Constructor
	 */
	
	public Product()
	{
		setProductId(Integer.valueOf(-1));
	}
	
	public Product(Integer productId, String resourceURL, String description, Integer qtyPerUnit, Integer unitOnHand, 
			Double weightInGrams, Double retailPriceUSD, Double supplyCostUSD){
		this.productId = productId;
		this.resourceURL = resourceURL;
		this.description = description;
		this.qtyPerUnit = qtyPerUnit;
		this.unitOnHand = unitOnHand;
		this.weightInGrams = weightInGrams;
		this.retailPriceUSD = retailPriceUSD;
		this.supplyCostUSD = supplyCostUSD;
	}
	
	public Product(Integer productId, String resourceURL, String description, Integer qtyPerUnit, Integer unitOnHand, 
			Double weightInGrams, Double retailPriceUSD, Double supplyCostUSD, Double length, Double width, Double thickness, Boolean archive){
		this.productId = productId;
		this.resourceURL = resourceURL;
		this.description = description;
		this.qtyPerUnit = qtyPerUnit;
		this.unitOnHand = unitOnHand;
		this.weightInGrams = weightInGrams;
		this.retailPriceUSD = retailPriceUSD;
		this.supplyCostUSD = supplyCostUSD;
		this.length = length;
		this.width = width;
		this.thickness = thickness;
		this.archive = archive;
	}
	
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the resourceURL
	 */
	public String getResourceURL() {
		return resourceURL;
	}
	/**
	 * @param resourceURL the resourceURL to set
	 */
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the qtyPerUnit
	 */
	public Integer getQtyPerUnit() {
		return qtyPerUnit;
	}
	/**
	 * @param qtyPerUnit the qtyPerUnit to set
	 */
	public void setQtyPerUnit(Integer qtyPerUnit) {
		this.qtyPerUnit = qtyPerUnit;
	}
	/**
	 * @return the unitOnHand
	 */
	public Integer getUnitOnHand() {
		return unitOnHand;
	}
	/**
	 * @param unitOnHand the unitOnHand to set
	 */
	public void setUnitOnHand(Integer unitOnHand) {
		this.unitOnHand = unitOnHand;
	}
	/**
	 * @return the weightInGrams
	 */
	public Double getWeightInGrams() {
		return weightInGrams;
	}
	/**
	 * @param weightInGrams the weightInGrams to set
	 */
	public void setWeightInGrams(Double weightInGrams) {
		this.weightInGrams = weightInGrams;
	}
	/**
	 * @return the retailPriceUSD
	 */
	public Double getRetailPriceUSD() {
		return retailPriceUSD;
	}
	/**
	 * @param retailPriceUSD the retailPriceUSD to set
	 */
	public void setRetailPriceUSD(Double retailPriceUSD) {
		this.retailPriceUSD = retailPriceUSD;
	}
	/**
	 * @return the supplyCostUSD
	 */
	public Double getSupplyCostUSD() {
		return supplyCostUSD;
	}
	/**
	 * @param supplyCostUSD the supplyCostUSD to set
	 */
	public void setSupplyCostUSD(Double supplyCostUSD) {
		this.supplyCostUSD = supplyCostUSD;
	}

	/**
	 * @return the imageHtml
	 * Transient
	 */
	public String getImageHtml() {
		return imageHtml;
	}

	/**
	 * @param imageHtml the imageHtml to set
	 * Transient
	 */
	public void setImageHtml(String imageHtml) {
		this.imageHtml = imageHtml;
	}

	/**
	 * @return the largeImageDiv
	 * @deprecated
	 */
	@Deprecated
	public String getLargeImageDiv() {
		return largeImageDiv;
	}

	/**
	 * @param largeImageDiv the largeImageDiv to set
	 * @deprecated
	 */
	@Deprecated
	public void setLargeImageDiv(String largeImageDiv) {
		this.largeImageDiv = largeImageDiv;
	}

	/**
	 * @return the cost
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}

	/**
	 * @return the inStock
	 */
	public String getInStock() {
		return inStock;
	}

	/**
	 * @param inStock the inStock to set
	 */
	public void setInStock(String inStock) {
		this.inStock = inStock;
	}

	/**
	 * @return the thickness
	 */
	public Double getThickness() {
		return thickness;
	}

	/**
	 * @param thickness the thickness to set
	 */
	public void setThickness(Double thickness) {
		this.thickness = thickness;
	}

	/**
	 * @return the width
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	/**
	 * @return the length
	 */
	public Double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Double length) {
		this.length = length;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
