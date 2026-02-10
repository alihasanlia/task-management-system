public class SegmentTree {

    private int[] tree;
    private int size;

    public SegmentTree(int maxId) {
        this.size = maxId + 1;
        tree = new int[4 * size];
    }

    // Build
    public void build(int[] values) {
        build(1, 0, size - 1, values);
    }

    private void build(int node, int start, int end, int[] values) {
        if (start == end) {
            tree[node] = values[start];
        } else {
            int mid = (start + end) / 2;
            build(node * 2, start, mid, values);
            build(node * 2 + 1, mid + 1, end, values);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    // Update
    public void update(int index, int newValue) {
        update(1, 0, size - 1, index, newValue);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;

            if (index <= mid)
                update(node * 2, start, mid, index, value);
            else
                update(node * 2 + 1, mid + 1, end, index, value);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    // Query
    public int query(int left, int right) {
        return query(1, 0, size - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left)
            return 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right)
             + query(node * 2 + 1, mid + 1, end, left, right);
    }

    // Print
    public void print() {
        System.out.println("Segment Tree:");
        for (int i = 1; i < 2 * size; i++) {
            if (tree[i] != 0)
                System.out.println("Node " + i + " -> " + tree[i]);
        }
    }
}
