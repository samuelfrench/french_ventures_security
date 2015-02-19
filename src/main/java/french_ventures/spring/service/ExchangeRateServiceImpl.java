package french_ventures.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import french_ventures.spring.domain.ExchangeRate;
import french_ventures.spring.domain.OpenExchangeRatesJsonModel;
import french_ventures.spring.domain.ExchangeRate.Currency;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired
	WebUtility webUtility;
	
	@Override
	public ExchangeRate getExchangeRate(Currency baseCurrency) {
			RestTemplate restTemplate = new RestTemplate();
			OpenExchangeRatesJsonModel json  = restTemplate.getForObject("http://openexchangerates.org/api/latest.json?app_id=83e5b4d8f9654732aabe04b0c8dab340", OpenExchangeRatesJsonModel.class);
			ExchangeRate exchangeRate = new ExchangeRate("USD");
			
			return populateExchangeRate(json, exchangeRate);
	}
	
	private ExchangeRate populateExchangeRate(OpenExchangeRatesJsonModel source, ExchangeRate dest)
	{
		//dest.setBaseCurrency(baseCurrency);
		dest.setUSDRate(webUtility.safeDouble(source.getRates().get("USD")));
		dest.setCNYRate(webUtility.safeDouble(source.getRates().get("CNY")));
		//dest.setGBPRate(webUtility.safeDouble(source.getRates().get("GBP")));
		//dest.setEURRate(webUtility.safeDouble(source.getRates().get("EUR")));
		return dest;
	}
}
