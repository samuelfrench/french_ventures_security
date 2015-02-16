/**
 * 
 */
package french_ventures.spring.service;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Sam French
 *
 */
public interface WebUtility {
	static Logger log = Logger.getLogger(WebUtility.class);
	
	@PreAuthorize("permitAll")
	Integer safeInteger(String string);
	
	@PreAuthorize("permitAll")
	String safeString(String string);
	
	@PreAuthorize("permitAll")
	Boolean isDescending(String string);
	
	@PreAuthorize("permitAll")
	Double safeDouble(String string);
}
