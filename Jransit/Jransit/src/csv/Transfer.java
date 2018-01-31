package csv;

public class Transfer {
	private String fromStopId;
	private String toStopId;
	private String transferType;
	private String transferTime;
	public Transfer(String fromStopId, String toStopId, String transferType, String transferTime) {
		this.fromStopId = fromStopId;
		this.toStopId = toStopId;
		this.transferType = transferType;
		this.transferTime = transferTime;
	}

}
