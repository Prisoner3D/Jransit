import java.io.IOException;
import java.util.Map;

public class Test {
	public static void main(String[] args) throws IOException {
		Map<TrainFeed, MTAApi> apis = MTAApiStaticFactory.getApis();
		System.out.println(LineInfoStaticFactory.getLines());
	}
}
