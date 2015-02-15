package french_ventures.spring.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;

import french_ventures.spring.domain.Product;

public interface TableUtility {
	static Logger log = Logger.getLogger(WebUtility.class);
	
	public enum filterTypeEnum {
	    CURRENCY, INTEGERRANGE
	}
	
	@PreAuthorize("permitAll")
	public List<Product> applyFilter(List<Product> list, Boolean descOrder, filterTypeEnum filterType);
	
	@PreAuthorize("permitAll")
	public List<Product> isolateCurrentPage(List<Product> list, Integer start, Integer length);
}
