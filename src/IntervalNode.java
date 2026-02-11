enum Color {
    RED, BLACK
}

public class IntervalNode {
    int start;
    int end;
    int maxEnd;
    int taskId;

    Color color;

    IntervalNode left;
    IntervalNode right;
    IntervalNode parent;

    public IntervalNode(int start, int end, int taskId) {
        this.start = start;
        this.end = end;
        this.taskId = taskId;
        this.maxEnd = end;

        this.color = Color.RED;
    }

    @Override
    public String toString() {
        return "[Task " + taskId + " (" + start + "," + end + ")]";
    }
}
