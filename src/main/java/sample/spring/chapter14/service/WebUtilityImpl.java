/**
 * 
 */
package sample.spring.chapter14.service;

import org.springframework.stereotype.Service;

/**
 * @author windows
 *
 */
@Service
public class WebUtilityImpl implements WebUtility {

	/* (non-Javadoc)
	 *
	 */
	@Override
	public Integer safeInteger(String string) {
		Integer integer = null;
		try{
			integer = Integer.parseInt(string);
		} catch(NumberFormatException e) {
			log.warn("The following was not an integer: " + string);
			integer = 0;
		} 
		return integer;
		
	}

	/* (non-Javadoc)
	 * 
	 */
	@Override
	public Integer safeString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
