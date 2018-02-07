package UserInterface;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Widget 
{
	 String title;
	 String description; //im not 100% sure but these should be private
	 double x;
	 double y;			//if you ever need to refrence these use getter methods
	 Pane parent;		//getPane(){return pane}; <<---- like that
	 VBox widget;
	
	public Widget(String title, String description, double x, double y, Pane root)
	{
		this.title = title;
		this.description = description;
		this.x = x;
		this.y = y;
		this.widget = new VBox();
		Font lato = Font.loadFont(getClass().getResourceAsStream("/fonts/Lato-Regular.ttf"), 12);
		
		String cssLayout = "-fx-border-insets: 0;\n" + "-fx-border-width: 2;\n" + //border insets + width
		"-fx-border-radius: 5;\n" + "-fx-padding: 5; \n" + 						  // border radius + padding		// space these out next time just to make it more visible also comment 
		"-fx-background-radius: 5;\n" + "-fx-background-color: #e6e6e6; \n";	  // background raidus + paddingq	// each line is doing to make it easier
		
		String titleLayout ="-fx-font-size: 15px;\n" + "-fx-font-weight: bold;\n";
																						// why are there two font sizes, comment to make clearer and easier
		String descLayout ="-fx-font-size: 12px;\n";
		
		this.widget.setStyle(cssLayout);
		Text t = new Text(title);
		t.setFont(lato);
		t.setStyle(titleLayout);
		Text d = new Text(description);
		d.setStyle(descLayout);
		d.setFont(lato);
		
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
		String extraLayout ="-fx-font-size: 12px;\n";
		Text extra = new Text(xtra);
		extra.setStyle(extraLayout);
		this.widget.getChildren().add(extra);
	}
}
