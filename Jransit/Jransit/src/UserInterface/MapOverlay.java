package UserInterface;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MapOverlay extends Application 
{
	private ArrayList<Widget> widgets = new ArrayList<Widget>();
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Pane root = new Pane(); 
			Widget theWidget = new Widget("station","station description", 100, 265.2, root);
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
	
	public void addRow(Widget widget)
	{
		
	}
}