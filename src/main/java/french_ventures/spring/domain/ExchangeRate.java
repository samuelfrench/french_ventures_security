package french_ventures.spring.domain;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
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
	private Timestamp updated;
	private Date date;
	
	public ExchangeRate(){}
	public ExchangeRate(String baseCurrency)
	{
		this.date = new Date();
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
				throw new InvalidParameterException();
			}
		}
		
		
		update();
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
		//update();
	}

	public Double getCNYRate() {
		return CNYRate;
	}
	public void setCNYRate(Double cNYRate) {
		CNYRate = cNYRate;
		update();
	}

	public Double getGBPRate() {
		return GBPRate;
	}
	public void setGBPRate(Double gBPRate) {
		GBPRate = gBPRate;
		update();
	}
	
	public Double getEURRate() {
		return EURRate;
	}
	public void setEURRate(Double eURRate) {
		EURRate = eURRate;
		update();
	}
	
	public Double getCADRate() {
		return CADRate;
	}
	public void setCADRate(Double cADRate) {
		CADRate = cADRate;
		update();
	}

	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	
	public void update()
	{
		try{
			if(this.date == null)
			{
				throw new IllegalStateException();
			}
		} catch (NullPointerException e)
		{
			throw new IllegalStateException();
		}
		
		this.setUpdated(new Timestamp(date.getTime()));
	}
}
