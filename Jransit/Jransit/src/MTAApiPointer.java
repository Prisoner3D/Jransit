
public class MTAApiPointer implements Pointer {
	private MTAApi api;
	public MTAApiPointer(MTAApi api) {
		this.api = api;
	}
	
	public MTAApiPointer() {
		
	}
	
	@Override
	public MTAApi dereference() {
		return api;
	}
	
	@Override
	public void reference(Object api) {
		if (!(api instanceof MTAApi)) {
			throw new ClassCastException ("Must be of class MTAApi");
		}
		this.api = (MTAApi) api;
	}
}
