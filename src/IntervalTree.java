public class IntervalTree {

    private final IntervalNode NIL;
    private IntervalNode root;

    public IntervalTree() {
        NIL = new IntervalNode(0, 0, -1);
        NIL.color = Color.BLACK;
        NIL.maxEnd = Integer.MIN_VALUE;
        NIL.left = NIL.right = NIL.parent = NIL;

        root = NIL;
    }

    // Insert
    public boolean insert(int start, int end, int taskId) {
        if (hasConflict(start, end)) return false;

        IntervalNode node = new IntervalNode(start, end, taskId);
        node.left = node.right = node.parent = NIL;

        bstInsert(node);
        fixInsert(node);
        return true;
    }

    private void bstInsert(IntervalNode z) {
        IntervalNode y = NIL;
        IntervalNode x = root;

        while (x != NIL) {
            y = x;
            if (z.start < x.start)
                x = x.left;
            else
                x = x.right;
        }

        z.parent = y;

        if (y == NIL)
            root = z;
        else if (z.start < y.start)
            y.left = z;
        else
            y.right = z;

        updateMaxUpwards(z);
    }

    // Fix red black tree
    private void fixInsert(IntervalNode z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                IntervalNode y = z.parent.parent.right;

                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                IntervalNode y = z.parent.parent.left;

                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    // Rotations
    private void leftRotate(IntervalNode x) {
        IntervalNode y = x.right;
        x.right = y.left;

        if (y.left != NIL)
            y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == NIL)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;

        recompute(x);
        recompute(y);
    }

    private void rightRotate(IntervalNode y) {
        IntervalNode x = y.left;
        y.left = x.right;

        if (x.right != NIL)
            x.right.parent = y;

        x.parent = y.parent;

        if (y.parent == NIL)
            root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        x.right = y;
        y.parent = x;

        recompute(y);
        recompute(x);
    }

    // Augmentation
    private void recompute(IntervalNode n) {
        n.maxEnd = Math.max(
            n.end,
            Math.max(n.left.maxEnd, n.right.maxEnd)
        );
    }

    private void updateMaxUpwards(IntervalNode n) {
        while (n != NIL) {
            recompute(n);
            n = n.parent;
        }
    }

    // Search
    public boolean hasConflict(int start, int end) {
        return conflictSearch(root, start, end) != NIL;
    }

    public IntervalNode conflictSearch(int start, int end) {
        return conflictSearch(root, start, end);
    }

    private IntervalNode conflictSearch(IntervalNode x, int start, int end) {
        while (x != NIL && !overlap(x, start, end)) {
            if (x.left != NIL && x.left.maxEnd >= start)
                x = x.left;
            else
                x = x.right;
        }
        return x;
    }

    private boolean overlap(IntervalNode n, int s, int e) {
        return s <= n.end && n.start <= e;
    }

    // Access
    public IntervalNode getRoot() {
        return root;
    }

    public IntervalNode getNil() {
        return NIL;
    }

    // Print
    public void print() {
        IntervalTreePrinter printer = new IntervalTreePrinter();
        printer.print(root, NIL);
    }

}
