package french_ventures.spring.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class OpenExchangeRatesJsonModel {
	private String disclaimer;
	private String license;
	private String timestamp;
	private String base;
	private Map<String,String> rates;
	
	public OpenExchangeRatesJsonModel(){
	}
	
	public OpenExchangeRatesJsonModel(String disclaimer,
			String license, String timestamp, String base,
			Map<String,String> rates)
			{
		this.disclaimer = disclaimer;
		this.license = license;
		this.timestamp = timestamp;
		this.base = base;
		this.rates = rates;
			}
	
	/**
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}
	/**
	 * @param disclaimer the disclaimer to set
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}
	/**
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}
	/**
	 * @return the rates
	 */
	public Map<String, String> getRates() {
		return rates;
	}
	/**
	 * @param rates the rates to set
	 */
	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}
}
