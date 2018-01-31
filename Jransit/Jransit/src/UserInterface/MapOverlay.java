package UserInterface;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
			BorderPane root = new BorderPane(); 
			VBox widget = new VBox();
			String title = theWidget.title;
			String description = theWidget.description;
			Text t = new Text(title);
			Text d = new Text(description);
			VBox widget1 = new VBox();
			String title1 = theWidget.title;
			String description1 = theWidget.description;
			Text t1 = new Text(title1);
			Text d1 = new Text(description1);
			widget.getChildren().addAll(t,d);
			widget1.getChildren().addAll(t1,d1);
			root.getChildren().addAll(widget,widget1);
			Scene scene = new Scene(root,1900,1000);
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