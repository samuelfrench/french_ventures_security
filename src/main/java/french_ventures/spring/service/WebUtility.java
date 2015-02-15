/**
 * 
 */
package french_ventures.spring.service;

import org.apache.log4j.Logger;

/**
 * @author Sam French
 *
 */
public interface WebUtility {
	Logger log = Logger.getLogger(WebUtility.class);
	
	Integer safeInteger(String string);
	
	String safeString(String string);
	
	Boolean isDescending(String string);
}
