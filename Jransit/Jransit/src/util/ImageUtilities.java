package util;

public class ImageUtilities 

/**
 * Image utilities to display images.
 */
{
	/**
	 * Location of Train Icons
	 * @param train 
	 * @return the relative path of the train image
	 */
	public static String getTrainIcon(String train)
	{
		return "src/" +  train + ".png";
	}
}
