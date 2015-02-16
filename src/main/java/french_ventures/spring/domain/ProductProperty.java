package french_ventures.spring.domain;

public class ProductProperty {
	
	//database columns
	private Integer productPropertyId;
	private Double lengthCm;
	private Double widthCm;
	private Double thicknessMm;
	private Double weight1000;
	
	
	public ProductProperty(){};
	
	public ProductProperty(Integer productPropertyId, Double lengthCm, Double widthCm, Double thicknessMm, Double weight1000)
	{
		this.productPropertyId = productPropertyId;
		this.lengthCm = lengthCm;
		this.widthCm = widthCm;
		this.thicknessMm = thicknessMm;
		this.weight1000 = weight1000;
	}
	/**
	 * @return the productPropertyId
	 */
	public Integer getProductPropertyId() {
		return productPropertyId;
	}
	/**
	 * @param productPropertyId the productPropertyId to set
	 */
	public void setProductPropertyId(Integer productPropertyId) {
		this.productPropertyId = productPropertyId;
	}
	/**
	 * @return the lengthCm
	 */
	public Double getLengthCm() {
		return lengthCm;
	}
	/**
	 * @param lengthCm the lengthCm to set
	 */
	public void setLengthCm(Double lengthCm) {
		this.lengthCm = lengthCm;
	}
	/**
	 * @return the widthCm
	 */
	public Double getWidthCm() {
		return widthCm;
	}
	/**
	 * @param widthCm the widthCm to set
	 */
	public void setWidthCm(Double widthCm) {
		this.widthCm = widthCm;
	}
	/**
	 * @return the thicknessMm
	 */
	public Double getThicknessMm() {
		return thicknessMm;
	}
	/**
	 * @param thicknessMm the thicknessMm to set
	 */
	public void setThicknessMm(Double thicknessMm) {
		this.thicknessMm = thicknessMm;
	}
	/**
	 * @return the weight1000
	 */
	public Double getWeight1000() {
		return weight1000;
	}
	/**
	 * @param weight1000 the weight1000 to set
	 */
	public void setWeight1000(Double weight1000) {
		this.weight1000 = weight1000;
	}
}
