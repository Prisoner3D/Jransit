package UserInterface;

public class Widget 
{
	private String title;
	private String description;
	
	public Widget(String title, String description)
	{
		this.title = title;
		this.description = description;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getDescription()
	{
		return this.description;
	}
}
