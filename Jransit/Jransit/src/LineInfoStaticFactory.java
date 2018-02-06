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
	
	public static List<LineInfo> getLines() {
		return lineArray;
	}
	private static List<LineInfo> lineArray = new ArrayList<>();
	static {
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
		    		for (int i = 0; i < LineInfoStaticFactory.NUM_S.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.NUM_S.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    	case YELLOW:
		    		for (int i = 0; i < LineInfoStaticFactory.YELLOW.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.YELLOW.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    	case ORANGE:
		    		for (int i = 0; i < LineInfoStaticFactory.ORANGE.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.ORANGE.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    	case GRAY:
		    		for (int i = 0; i < LineInfoStaticFactory.GRAY.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.GRAY.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    	case GREEN:
		    		for (int i = 0; i < LineInfoStaticFactory.GREEN.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.GREEN.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    	case BROWN:
		    		for (int i = 0; i < LineInfoStaticFactory.BROWN.length(); i++) {
		    			try {
							lineArray.add(new LineInfo(api, LineInfoStaticFactory.BROWN.substring(i, i+1)));
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
				default:
					break;
		    }
		}
	}

}
