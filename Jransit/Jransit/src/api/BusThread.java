package api;

import java.io.File;
import java.util.List;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import UserInterface.JavaFXExample;
import csv.CSVUtilities;
import csv.HistoryRecorder;
import mapObjects.Bus;
import mapObjects.BusFactory;

public class BusThread extends Thread {
	private Map map;
	private Icon icon;
	private BusFactory busFac;
	private HistoryRecorder histRec;
	private boolean firstRun = true;
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

	public HistoryRecorder getHistRec() {
		return histRec;
	}

	
	public BusFactory getBusFac() {
		return busFac;
	}

	@Override
	public void run() {
		while (true) {
			if (!firstRun) {
				JavaFXExample.slider.addTick();
				JavaFXExample.slider.addTick();
			}
			if (firstRun) {
				firstRun = false;
			}
			//TODO: lock on run
			double sliderState = JavaFXExample.slider.getSlider().getValue();
			if (sliderState == 1) {
				List<Bus> busses = busFac.placeBusses(map, icon, true);
				this.histRec.write(busses);
			}
			
			
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
