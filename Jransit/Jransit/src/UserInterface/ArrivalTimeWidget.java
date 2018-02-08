package UserInterface;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ArrivalTimeWidget extends Widget
{
	String title;
	String description;
	double x;
	double y;
	Pane parent;
	
	// CAPITAL:IZEEE
	
	public ArrivalTimeWidget(StationInfo station, double x, double y, Pane root) 
	{
		super(station.getTrainName(),"Train time goes here", x, y, root);
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
		String extraLayout ="-fx-font-size: 12px;\n";
		Text extra = new Text(xtra);
		extra.setStyle(extraLayout);
		this.widget.getChildren().add(extra);
	}
	
	public void addTimeStamps(ArrayList<Long> theTimeStamps)
	{
		String timeLayout ="-fx-font-size: 12px;\n";
		for(int i = 0; i < theTimeStamps.size(); i++)
		{	
			Text timeStamp = new Text(convertTime(theTimeStamps.get(i)));
			timeStamp.setStyle(timeLayout);
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
