package UserInterface;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MapOverlay extends Application 
{
	private ArrayList<Widget> widgets = new ArrayList<Widget>();
	@Override
	public void start(Stage primaryStage) 
	{
		Widget theWidget = newWidget("station","station description");
		try 
		{
			VBox widget = new VBox();
			String title = theWidget.title;
			String description = theWidget.description;
			Text t = new Text(title);
			Text d = new Text(description);
			widget.getChildren().addAll(t,d);
			Scene scene = new Scene(widget,1900,1000);
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public Widget newWidget(String title, String description)
	{
		Widget widget = new Widget(title, description);
		return widget;
	}
}
