
public enum Direction {
	NORTH,SOUTH;
	
	public Direction getDirection(char x) {
		if(x =='S') {
			return SOUTH;
		}
		else if(x == 'N') {
			return NORTH;
		}
		else 
			return null;
	}
}
