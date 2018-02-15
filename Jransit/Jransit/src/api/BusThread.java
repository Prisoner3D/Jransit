package api;

import java.io.File;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import UserInterface.JavaFXExample;
import csv.CSVUtilities;
import csv.HistoryRecorder;
import mapObjects.BusFactory;

public class BusThread extends Thread {
	private Map map;
	private Icon icon;
	private BusFactory busFac;
	private HistoryRecorder histRec;
	public BusThread(Map map, Icon icon) {
		System.out.println("thread started");
		 this.map = map;
		 this.icon = icon;
		 this.busFac = (new BusFactory());
		 String basePath = (new File("").getAbsolutePath());
		 System.out.println(basePath);
		 File file = new File(basePath + "\\Jransit\\src\\csv\\bus history.txt");
		 System.out.println("Testt");
		 this.histRec = new HistoryRecorder(new CSVUtilities(file, "|"));
		 System.out.println("end of constructer");
	}

	@Override
	public void run() {
		while (true) {
			JavaFXExample.slider.addTick();
			double sliderState = JavaFXExample.slider.getSlider().getValue();
			if (sliderState != 1) {
				int numOfStatesBehind = JavaFXExample.slider.getNumOfTicks() - JavaFXExample.slider.getCurrentTick();
			}
			busFac.placeBusses(map, icon, true);
			this.histRec.write(busFac.getBusses());
			try {
				this.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void clearFile() {
		this.histRec.resetFile();
	}
}
