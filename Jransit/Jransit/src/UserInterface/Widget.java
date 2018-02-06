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
	VBox widget;
	
	public Widget(String title, String description, double x, double y, Pane root)
	{
		this.title = title;
		this.description = description;
		this.x = x;
		this.y = y;
		this.widget = new VBox();
		
		String cssLayout ="-fx-border-insets: 0;\n" + "-fx-border-width: 2;\n" + "-fx-border-radius: 5;\n" + 
		"-fx-padding: 5; \n" + "-fx-background-radius: 5;\n" + "-fx-background-color: #e6e6e6; \n";
		String titleLayout ="-fx-font: 15px Lato;\n" + "-fx-stroke-width: 2;\n";
		String descLayout ="-fx-font: 12px Lato;\n" + "-fx-stroke-width: 1;\n";
		
		this.widget.setStyle(cssLayout);
		Text t = new Text(title);
		t.setStyle(titleLayout);
		Text d = new Text(description);
		d.setStyle(descLayout);
		
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
	
	public void addRow(String xtra)
	{
		String extraLayout ="";
		Text extra = new Text(xtra);
		
		this.widget.getChildren().add(extra);
	}
}
