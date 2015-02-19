package french_ventures.spring.service;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;

import french_ventures.spring.domain.ExchangeRate;
import french_ventures.spring.domain.ExchangeRate.Currency;

public interface ExchangeRateService {
	static Logger log = Logger.getLogger(ExchangeRateService.class);
	
	@PreAuthorize("permitAll")
	public ExchangeRate getExchangeRate(Currency baseCurrency);
}
