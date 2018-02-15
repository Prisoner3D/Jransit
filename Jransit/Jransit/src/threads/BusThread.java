package threads;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import mapObjects.BusFactory;

public class BusThread extends Thread {
	private Map map;
	private Icon icon;
	private BusFactory busFac;
	public BusThread(Map map, Icon icon) {
		 this.map = map;
		 this.icon = icon;
		 this.busFac = (new BusFactory());
	}

	@Override
	public void run() {
		while (true) {
			busFac.placeBusses(map, icon, true);
			try {
				this.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
