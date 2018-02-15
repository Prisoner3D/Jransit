package info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import api.MTAApi;
import api.MTAApiStaticFactory;
import api.TrainFeed;

import java.util.TreeMap;

/**
 * Contains train information that stays the same
 * 
 * @author
 *
 */
public class LineInfoStaticFactory {
	private static final String LINES = "123456SACENQRWBDFMLGJZ";
	private static final String sNUM_S = "123456S";
	private static final String sBLUE = "ACE";
	private static final String sYELLOW = "NQRW";
	private static final String sORANGE = "BDFM";
	private static final String sGRAY = "L";
	private static final String sGREEN = "G";
	private static final String sBROWN = "JZ";

	// private static List<LineInfo> lineMap = new ArrayList<>();
	private static Map<String, LineInfo> lineMap = new TreeMap<String, LineInfo>();

	public static Map<String, LineInfo> getLines() {
		update();
		return lineMap;
	}

	private static void update() {
		Map<TrainFeed, MTAApi> apis = MTAApiStaticFactory.getApis();
		for (Entry<TrainFeed, MTAApi> entry : apis.entrySet()) {
			TrainFeed key = entry.getKey();
			MTAApi api = entry.getValue();
			try {
				api.updateFeed();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			switch (key) {
			case BLUE:
				for (int i = 0; i < LineInfoStaticFactory.sBLUE.length(); i++) {
					try {
						lineMap.put(LineInfoStaticFactory.sBLUE.substring(i, i + 1),
								new LineInfo(api, LineInfoStaticFactory.sBLUE.substring(i, i + 1)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			case NUM_S:
				for (int i = 0; i < LineInfoStaticFactory.sNUM_S.length(); i++) {
					try {
						lineMap.put(LineInfoStaticFactory.sNUM_S.substring(i, i + 1),
								new LineInfo(api, LineInfoStaticFactory.sNUM_S.substring(i, i + 1)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			case YELLOW:
				for (int i = 0; i < LineInfoStaticFactory.sYELLOW.length(); i++) {
					try {
						lineMap.put(LineInfoStaticFactory.sYELLOW.substring(i, i + 1),
								new LineInfo(api, LineInfoStaticFactory.sYELLOW.substring(i, i + 1)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			case ORANGE:
				for (int i = 0; i < LineInfoStaticFactory.sORANGE.length(); i++) {
					try {
						lineMap.put(LineInfoStaticFactory.sORANGE.substring(i, i + 1),
								new LineInfo(api, LineInfoStaticFactory.sORANGE.substring(i, i + 1)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			case GRAY:
				for (int i = 0; i < LineInfoStaticFactory.sGRAY.length(); i++) {
					try {
						lineMap.put(LineInfoStaticFactory.sGRAY.substring(i, i + 1),
								new LineInfo(api, LineInfoStaticFactory.sGRAY.substring(i, i + 1)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			case GREEN:
				for (int i = 0; i < LineInfoStaticFactory.sGREEN.length(); i++) {
					try {
						lineMap.put(LineInfoStaticFactory.sGREEN.substring(i, i + 1),
								new LineInfo(api, LineInfoStaticFactory.sGREEN.substring(i, i + 1)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			case BROWN:
				for (int i = 0; i < LineInfoStaticFactory.sBROWN.length(); i++) {
					try {
						lineMap.put(LineInfoStaticFactory.sBROWN.substring(i, i + 1),
								new LineInfo(api, LineInfoStaticFactory.sBROWN.substring(i, i + 1)));
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
