package sample.spring.chapter14.domain;

public class Product {
	private Integer productId;
	private String resourceURL;
	private String description;
	private Integer qtyPerUnit;
	private Integer unitOnHand;
	private Double weightInGrams;
	private Double retailPriceUSD;
	private Double supplyCostUSD;
	
	private String imageHtml;
	
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
}
