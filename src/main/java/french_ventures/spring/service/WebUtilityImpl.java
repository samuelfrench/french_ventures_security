/**
 * 
 */
package french_ventures.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author windows
 *
 */
@Service
public class WebUtilityImpl implements WebUtility {

	/*
	 * (non-Javadoc)
	 */
	@Override
	public Integer safeInteger(String string) {
		Integer integer = null;
		try {
			integer = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			log.warn("The following was not an integer: " + string);
			integer = 0;
		} catch (NullPointerException e) {
			log.error("Null pointer exception occurred parsing integer.");
			integer = 0;
		}
		return integer;

	}

	/*
	 * Has not been tested - TODO
	 */
	@Override
	public String safeString(String string) {
		try {
			string = string.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
			string = string.replaceAll("\\(", "& #40;").replaceAll("\\)",
					"& #41;");
			string = string.replaceAll("'", "& #39;");
			string = string.replaceAll("eval\\((.*)\\)", "");
			string = string.replaceAll(
					"[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
			string = string.replaceAll("script", "");
		} catch (NullPointerException e) {
			return "";
		}
		return string;
	}

	@Override
	public Boolean isDescending(String string) {
		try {
			if (string.trim().equalsIgnoreCase("desc")) {
				return true;
			}
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

}
