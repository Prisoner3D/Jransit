package mapObjects;

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
