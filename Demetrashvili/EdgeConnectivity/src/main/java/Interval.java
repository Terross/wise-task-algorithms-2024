import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interval {

    private Pair<UUID, UUID> low;
    private Pair<UUID, UUID> high;

    public boolean isEmpty() {
        return low == null && high == null;
    }

}
