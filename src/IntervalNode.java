public class IntervalNode {
    int start;
    int end;
    int maxEnd;
    int taskId;

    IntervalNode left;
    IntervalNode right;

    public IntervalNode(int start, int end, int taskId) {
        this.start = start;
        this.end = end;
        this.taskId = taskId;
        this.maxEnd = end;
    }
}
