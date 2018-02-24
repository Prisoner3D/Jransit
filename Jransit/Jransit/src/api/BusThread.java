package api;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import UserInterface.MapsApp;
import csv.CSVUtilities;
import csv.HistoryRecorder;
import main.Main;
import mapObjects.Bus;
import mapObjects.BusFactory;

/**
 * A thread that places the busses, reads and writes the bus history.
 *
 */
public class BusThread extends Thread {
    private Map map;
    private Icon icon;
    private BusFactory busFac;
    private HistoryRecorder histRec;
    private boolean firstRun = true;

    public BusThread(Map map, Icon icon) {
        this.map = map;
        this.icon = icon;
        this.busFac = (new BusFactory());
        String basePath = (new File("").getAbsolutePath());
        File file = new File(basePath + "\\Jransit\\src\\csv\\bus history.txt");
        this.histRec = new HistoryRecorder(new CSVUtilities(file, "|"));
    }

    public void clearFile() {
        this.histRec.resetFile();
    }

    public BusFactory getBusFac() {
        return busFac;
    }

    public HistoryRecorder getHistRec() {
        return histRec;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // TODO: lock on run
            double sliderState = MapsApp.slider.getSlider().getValue();
            MapsApp.slider.getSlider().setDisable(true);
            List<Bus> busses;
            if (sliderState == 0) {
            	busses = busFac.placeBusses(map, true);
            } else {
                busses = busFac.placeBusses(map, false);
            }
            this.histRec.write(busses);
            MapsApp.slider.getSlider().setDisable(false);

            MapsApp.setTimer(new AtomicInteger(30));
            synchronized(this) {
            	try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            /*try {
                BusThread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            MapsApp.slider.addTick();
        }
    }
}
