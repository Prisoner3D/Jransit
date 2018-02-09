package UserInterface;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MapOverlay extends Application 
{
	ArrayList<Widget> widgets = new ArrayList<Widget>();
	@Override
	public void start(Stage primaryStage) 
	{
		ArrayList<Long> timeStamps = new ArrayList<Long>();
		long time1 = 848656546;
		long time2 = 654864654;
		timeStamps.add(time1);
		timeStamps.add(time2);
		
		try 
		{
			Pane root = new Pane(); 
			Widget theWidget = new Widget("34th Street Herald Sq.","station description", 100, 265.2, root);
			theWidget.addRow("xtra");
			ArrivalTimeWidget timeWidget = new ArrivalTimeWidget("Times Sq. 42nd Street","station description", 200, 400, root);
			timeWidget.addTimeStamps(timeStamps);
			Scene scene = new Scene(root,1920,1000);
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
}