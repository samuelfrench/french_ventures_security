package sample.spring.chapter14.service;

import java.util.List;

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
		
		imageContainer = "<img src='" + path + "'";
		if(hasClasses)
		{
			imageContainer += " class='";
			for(int x = 0; x < classNames.size(); x++)
			{
				imageContainer += classNames.get(x) + " ";
			}
			imageContainer += "' ";
		}
		imageContainer += "/>";
		
		
		
		return imageContainer;
	}

	public static boolean evaluateBool(String booleanToEvaluate)
	{
		boolean getCompressed = true;
		try {
			if(booleanToEvaluate.trim().equals("0"))
			{
				getCompressed = false;
			}
		} catch (NullPointerException e){}
		return getCompressed;
	}
	
	public static boolean stringOK(String toEvaluate)
	{
		try {
			if(toEvaluate.length() < 1)
			{
				return false;
			}
		} catch (NullPointerException e)
		{
			return false;
		}
		return true;
	}
}
