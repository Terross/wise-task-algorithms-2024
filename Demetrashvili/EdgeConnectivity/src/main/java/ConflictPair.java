import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ConflictPair {

    private Interval left;
    private Interval right;

    public void swapIntervals() {
        var tmp = this.left;
        this.left = this.right;
        this.right = tmp;
    }
}
