class ConflictPair {
    Interval left;
    Interval right;

    public ConflictPair(Interval left, Interval right) {
        this.left = left;
        this.right = right;
    }

    public void swapIntervals() {
        Interval tmp = this.left;
        this.left = this.right;
        this.right = tmp;
    }

    public Interval getLeft() {
        return this.left;
    }

    public Interval getRight() {
        return this.right;
    }

    public void setLeft(Interval left) {
        this.left = left;
    }

    public void setRight(Interval right) {
        this.right = right;
    }
}