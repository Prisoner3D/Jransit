import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LineInfoStaticFactory {
	private static final String lines = "123456SACENQRWBDFMLGJZ";
	private static final String NUM_S = "123456S";
	private static final String BLUE = "ACE";
	private static final String YELLOW = "NQRW";
	private static final String ORANGE = "BDFM";
	private static final String GRAY = "L";
	private static final String GREEN = "G";
	private static final String BROWN = "JZ";
	private static List<LineInfo> lineArray = new ArrayList<>();
	public LineInfoStaticFactory() {
		Map<TrainFeed, MTAApi> apis = MTAApiStaticFactory.getApis();
		for (Entry<TrainFeed, MTAApi> entry : apis.entrySet()) {
			TrainFeed key = entry.getKey();
		    MTAApi api = entry.getValue();
		    switch (key) {
		    	case BLUE:
		    		for (int i = 0; i < LineInfoStaticFactory.BLUE.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.BLUE.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    	case NUM_S:
		    		for (int i = 0; i < LineInfoStaticFactory.BLUE.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.BLUE.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    }
		}
	}

}
