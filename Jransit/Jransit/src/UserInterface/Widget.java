package UserInterface;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Widget 
{
	String title;
	String description;
	double x;
	double y;
	Pane parent;
	
	public Widget(String title, String description, double x, double y, Pane root)
	{
		this.title = title;
		this.description = description;
		this.x = x;
		this.y = y;
		VBox widget = new VBox();
		Text t = new Text(title);
		Text d = new Text(description);
		widget.setTranslateX(x);
		widget.setTranslateY(y);
		root.getChildren().add(widget);
		widget.getChildren().addAll(t,d);
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
