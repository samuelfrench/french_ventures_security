package french_ventures.spring.domain;

import java.util.Date;

public class ExchangeRate {
	public enum Currency {
	    USD, CNY, GBP, EUR, CAD
	}
	
	private Currency baseCurrency;
	private Double USDRate;
	private Double CNYRate;
	private Double GBPRate;
	private Double EURRate;
	private Double CADRate;
	private Long updated;
	private String displayUpdated;
	
	public ExchangeRate(){}
	public ExchangeRate(String baseCurrency, Long updated)
	{
		switch(baseCurrency)
		{
			case("USD"):
			{
				this.setBaseCurrency(Currency.USD);
				break;
			}
			case("CNY"):
			{
				this.setBaseCurrency(Currency.CNY);
				break;
			}
			case("GBP"):
			{
				this.setBaseCurrency(Currency.GBP);
				break;
			}
			case("EUR"):
			{
				this.setBaseCurrency(Currency.EUR);
				break;
			}
			case("CAD"):
			{
				this.setBaseCurrency(Currency.CAD);
				break;
			}
			default:
			{
				this.setBaseCurrency(Currency.USD);
			}
		}
		System.out.println("in class up: " + updated); //DEBUG TODO
		this.setUpdated(updated);
	}
	
	
	

	public Currency getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}




	public Double getUSDRate() {
		return USDRate;
	}
	public void setUSDRate(Double uSDRate) {
		USDRate = uSDRate;
	}

	public Double getCNYRate() {
		return CNYRate;
	}
	public void setCNYRate(Double cNYRate) {
		CNYRate = cNYRate;
	}

	public Double getGBPRate() {
		return GBPRate;
	}
	public void setGBPRate(Double gBPRate) {
		GBPRate = gBPRate;
	}
	
	public Double getEURRate() {
		return EURRate;
	}
	public void setEURRate(Double eURRate) {
		EURRate = eURRate;
	}
	
	public Double getCADRate() {
		return CADRate;
	}
	public void setCADRate(Double cADRate) {
		CADRate = cADRate;
	}

	public Long getUpdated() {
		return updated;
	}
	public void setUpdated(Long updated) {
		this.updated = updated;
		setDisplayUpdated();
	}
	public void setDisplayUpdated()
	{
		Date date = new Date(this.updated*1000);
		System.out.println("date: " + date.getTime());
		this.displayUpdated = date.toString();
		System.out.println("date str: " + date.toString());
	}
	public String getDisplayUpdated() {
		return displayUpdated;
	}
	public void setDisplayUpdated(String displayUpdated) {
		this.displayUpdated = displayUpdated;
	}
}