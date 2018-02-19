package csv;

public class Transfer implements EntityComposition {
    private final String fromStopID;
    private final Entity entity;
    private final String toStopID;
    private final String transferType;
    private final String minTransferTime;

    public Transfer(Entity entity) {
        this.fromStopID = entity.getPrimaryKey();
        this.entity = entity;
        this.toStopID = entity.getAttribute("to_stop_id");
        this.transferType = entity.getAttribute("transfer_type");
        this.minTransferTime = entity.getAttribute("min_transfer_time");
    }

    public Entity getEntity() {
        return entity;
    }

    public String getFromStopID() {
        return fromStopID;
    }

    public String getMinTransferTime() {
        return minTransferTime;
    }

    public String getToStopID() {
        return toStopID;
    }

    public String getTransferType() {
        return transferType;
    }

    @Override
    public String toString() {
        return String.valueOf(entity.getAttributes());
    }

}
