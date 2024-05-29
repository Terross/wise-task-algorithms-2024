import java.util.UUID;

public class Interval {
    Pair<UUID, UUID> low;
    Pair<UUID, UUID> high;

    public Interval() {
        this.low = null;
        this.high = null;
    }

    public Interval(Pair<UUID, UUID> low, Pair<UUID, UUID> high) {
        this.low = low;
        this.high = high;
    }

    public boolean isEmpty() {
        return low == null && high == null;
    }

    public Pair<UUID, UUID> getLow() {
        return this.low;
    }

    public Pair<UUID, UUID> getHigh() {
        return this.high;
    }

    public void setLow(Pair<UUID, UUID> low) {
        this.low = low;
    }

    public void setHigh(Pair<UUID, UUID> high) {
        this.high = high;
    }
}