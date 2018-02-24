package mapObjects;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import UserInterface.MapsApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

/**
 * Slider that can be used to view history of buses on the map
 * 
 * @author
 *
 */
public class TimelineSlider {
    private Slider slider;
    private double start;
    private double end;
    private double majorTickUnit;
    private int numOfTicks = 0;
    private int currentTick = 1;

    /**
     * Create a slider that displays buses on the map over time
     */
    public TimelineSlider() {
        this.start = this.end = 0;
        this.majorTickUnit = 1;
        slider = new Slider(start, end, end);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(this.majorTickUnit);
        slider.setBlockIncrement(this.majorTickUnit);
        slider.setSnapToTicks(true);
        TimelineSlider self = this;
        // On slider change
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number previous, Number now) {
                if (!slider.isValueChanging() || now.doubleValue() == slider.getMax() || now.doubleValue() == slider.getMin()) {
                    //pause
                	MapsApp.timer.cancel();
                	
                	int numOfStatesBehind = self.getNumOfTicks() - self.getCurrentTick();
                    List<String> busData = MapsApp.busThread.getHistRec().get(numOfStatesBehind);
                    BusFactory busFac = MapsApp.busThread.getBusFac();
                    busFac.placeBusses(busData);
                    
                    //resume
                    MapsApp.setTimer(new AtomicInteger(30));
                }
            }
        });
    }
    /**
     * Adds a tick to the timeline slider based on some rules
     */
    public void addTick() {
        if (numOfTicks > 3) {
            this.slider.setDisable(false);
        }
        if (numOfTicks == 0) {
            this.end = 1;
            slider.setMax(this.end);
            numOfTicks++;
        } else {
            numOfTicks++;
            this.majorTickUnit = 1 / (double) numOfTicks;
            slider.setMajorTickUnit(this.majorTickUnit);
            slider.setBlockIncrement(this.majorTickUnit);
        }
    }

    public int getCurrentTick() {
        double val = this.getSlider().getValue();
        return (int) (numOfTicks * val);
    }

    public int getNumOfTicks() {
        return numOfTicks;
    }

    public Slider getSlider() {
        return slider;
    }
}
