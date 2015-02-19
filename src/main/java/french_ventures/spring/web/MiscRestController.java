package french_ventures.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import french_ventures.spring.domain.ExchangeRate;
import french_ventures.spring.domain.ExchangeRate.Currency;
import french_ventures.spring.service.ExchangeRateService;

@Controller
public class MiscRestController {
	
	
	final Currency BASE_CURRENCY = Currency.USD;
	
	@Autowired
	ExchangeRateService exchangeRateService;
	
	@RequestMapping(value = "/misc/exchangeRate", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ExchangeRate getRate()
	{
		return exchangeRateService.getExchangeRate(BASE_CURRENCY);
	}
}
