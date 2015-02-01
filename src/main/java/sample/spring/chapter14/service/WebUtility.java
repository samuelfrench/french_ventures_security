/**
 * 
 */
package sample.spring.chapter14.service;

import org.apache.log4j.Logger;

/**
 * @author Sam French
 *
 */
public interface WebUtility {
	Logger log = Logger.getLogger(WebUtility.class);
	
	Integer safeInteger(String string);
	
	Integer safeString(String string);
}
