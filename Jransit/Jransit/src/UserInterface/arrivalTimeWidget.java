package UserInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class arrivalTimeWidget extends Widget
{
	String title;
	String description;
	double x;
	double y;
	Pane parent;
	
	public arrivalTimeWidget(String title, String description, double x, double y, Pane root) 
	{
		super(title, description, x, y, root);
	}

	public String getTitle()
	{
		return this.title;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void addRow(String xtra)
	{
		Text extra = new Text(xtra);
		extra.setFont(Font.font("Lato", 13));
		this.widget.getChildren().add(extra);
	}
	
	public void addTimeStamps(ArrayList<Long> theTimeStamps)
	{
		for(int i = 0; i < theTimeStamps.size(); i++)
		{	
			Text timeStamp = new Text(convertTime(theTimeStamps.get(i)));
			timeStamp.setFont(Font.font("Lato", 13));
			this.widget.getChildren().add(timeStamp);
		}
	}
	
	public String convertTime(long time)
	{
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(time);
	    String milTime = cal.get(Calendar.HOUR_OF_DAY) + ":"+ cal.get(Calendar.MINUTE);
	    return (milTime);
	}
}