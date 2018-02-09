package api;


/**
 * Class of constants used to access different feeds in GTFS directly through
 * http://datamine.mta.info/mta_esi.php?key={key}&feed_id=[TrainFeed]
 * @author alex
 *
 */
public enum TrainFeed {
	/**
	 * Feed Access to Trains : 1 2 3 4 5 6 S
	 */
	NUM_S(1),
	/**
	 * Feed Access to Trains : A C E
	 */
	BLUE(26),
	/**
	 * Feed Access to Trains : N Q R W
	 */
	YELLOW(16),
	/**
	 * Feed Access to Trains : B D F M
	 */
	ORANGE(21), 
	/**
	 * Feed Access to Trains : L
	 */
	GRAY(2),
	/**
	 * Feed Access to Trains : SIR
	 */
	SIR(11),
	/**
	 * Feed Access to Trains : G
	 */
	GREEN(31),
	/**
	 * Feed Access to Trains : J Z
	 */
	BROWN(36)
	;
	
	private final int id;
	private TrainFeed(int id)
    {
        this.id = id;
    }
	
	@Override
	public String toString() {
		return String.valueOf(this.id);
	}
	
	public static String getLetters(TrainFeed tf) {
		switch(tf) {
			case BLUE:
				return "ACE";
			case NUM_S:
				return "123456S";
			case YELLOW:
				return "NQRW";
			case ORANGE:
				return "BDFM";
			case GRAY:
				return "L";
			case SIR:
				return "SIR";
			case GREEN:
				return "G";
			case BROWN:
				return "JZ";
				
			default:
				return null;
		}
	}
}
