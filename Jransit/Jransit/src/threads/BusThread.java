package threads;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import mapObjects.BusFactory;

/**
 * Manages runtime used to display bus icons.
 * 
 * @author
 *
 */
public class BusThread extends Thread {
    private Map map;
    private Icon icon;
    private BusFactory busFac;

    /**
     * BusThread Constructor
     * 
     * @param map
     *            to be placed on
     * @param icon
     *            to be placed
     */
    public BusThread(Map map, Icon icon) {
        this.map = map;
        this.icon = icon;
        this.busFac = (new BusFactory());
    }

    /**
     * Places bus icon on map and waits 30s.
     */
    @Override
    public void run() {
        while (true) {
            busFac.placeBusses(map, true);
            try {
                this.sleep(30000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
