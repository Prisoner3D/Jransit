package mapObjects;

import java.util.List;

import UserInterface.JavaFXExample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class TimelineSlider {
	private Slider slider;
	private double start;
	private double end;
	private double majorTickUnit;
	private int numOfTicks = 0;
	private int currentTick = 1;
	
	public TimelineSlider() {
		this.start = this.end = this.majorTickUnit = 1; //TODO  this  better not be by reference
		slider = new Slider(start, end, end);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(this.majorTickUnit);
        slider.setBlockIncrement(this.majorTickUnit);
        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        TimelineSlider self = this;
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number previous,
                    Number now) {
                if (!slider.isValueChanging()
                      || now.doubleValue() == slider.getMax()
                      || now.doubleValue() == slider.getMin()) {
                	int numOfStatesBehind = self.getNumOfTicks() - self.getCurrentTick();
        			List<String> busData = JavaFXExample.busThread.getHistRec().get(numOfStatesBehind);
        			System.out.println(busData.size());
        			BusFactory busFac = JavaFXExample.busThread.getBusFac();
        			busFac.placeBusses(busData);
                }
            }
        });
	}

	public Slider getSlider() {
		return slider;
	}
	
	public int getCurrentTick() {
		double val = this.getSlider().getValue();
		return (int) (numOfTicks * val);
	}
	
	public int getNumOfTicks() {
		return numOfTicks;
	}

	public void addTick() {
		if (numOfTicks > 3) {
			this.slider.setDisable(false);
		}
		if (numOfTicks == 0) {
			this.start = 0;
			slider.setMin(this.start);
			numOfTicks++;
		} else {
			numOfTicks++;
			this.majorTickUnit = 1 / (double) numOfTicks;
			slider.setMajorTickUnit(this.majorTickUnit);
			slider.setBlockIncrement(this.majorTickUnit);
		}
	}
}
