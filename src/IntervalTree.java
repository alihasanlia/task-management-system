public class IntervalTree {

    private IntervalNode root;

    // Insert
    public boolean insert(int start, int end, int taskId) {
        if (hasConflict(start, end)) {
            return false; // conflict exists
        }
        root = insert(root, start, end, taskId);
        return true;
    }

    private IntervalNode insert(IntervalNode node, int start, int end, int taskId) {
        if (node == null)
            return new IntervalNode(start, end, taskId);

        if (start < node.start)
            node.left = insert(node.left, start, end, taskId);
        else
            node.right = insert(node.right, start, end, taskId);

        node.maxEnd = Math.max(node.maxEnd, end);
        return node;
    }

    // Conflict
    public boolean hasConflict(int start, int end) {
        return conflictSearch(root, start, end) != null;
    }

    public IntervalNode conflictSearch(int start, int end) {
        return conflictSearch(root, start, end);
    }

    private IntervalNode conflictSearch(IntervalNode node, int start, int end) {
        if (node == null)
            return null;

        if (overlap(node, start, end))
            return node;

        if (node.left != null && node.left.maxEnd >= start)
            return conflictSearch(node.left, start, end);

        return conflictSearch(node.right, start, end);
    }

    private boolean overlap(IntervalNode node, int start, int end) {
        return start <= node.end && node.start <= end;
    }

    // Print
    public void print() {
        IntervalTreePrinter printer = new IntervalTreePrinter();
        printer.print(root);
    }

}
