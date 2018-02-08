
public class LineInfoPointer implements Pointer {
	private LineInfo info;
	public LineInfoPointer(LineInfo info) {
		this.info = info;
	}
	
	public LineInfoPointer() {
		
	}
	
	@Override
	public LineInfo dereference() {
		return this.info;
	}

	@Override
	public void reference(Object info) {
		if (!(info instanceof LineInfo)) {
			throw new ClassCastException ("Must be of class MTAApi");
		}
		this.info = (LineInfo) info;
	}

}
