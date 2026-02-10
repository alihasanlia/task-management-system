public class BTreeNode {
    int t;                     // minimum degree
    int n;                     // current number of keys
    int[] keys;                // task ids
    Task[] values;             // tasks
    BTreeNode[] children;
    boolean leaf;

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.values = new Task[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    // Search by id
    Task search(int key) {
        int i = 0;
        while (i < n && key > keys[i]) i++;

        if (i < n && keys[i] == key)
            return values[i];

        if (leaf)
            return null;

        return children[i].search(key);
    }
}
