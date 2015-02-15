package french_ventures.spring.service;

import java.util.List;

@Deprecated
public class Basic {
	@Deprecated
	public static String createImageContainer(String path, List<String> classNames)
	{
		String imageContainer = null;
		Boolean hasClasses;
		
		
		try {
			if(classNames.size() > 0)
			{
				hasClasses = true;
			} else {
				hasClasses = false;
			}
		} catch (NullPointerException e)
		{
			hasClasses = false;
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<img src='").append(path).append("'");
		if(hasClasses)
		{
			buf.append(" class='");
			for(int x = 0; x < classNames.size(); x++)
			{
				buf.append(classNames.get(x)).append(" ");
			}
			buf.append("' ");
		}
		buf.append("/>");
		
		
		
		return buf.toString();
	}
}
