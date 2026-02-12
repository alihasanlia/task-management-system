public class BTree {
    BTreeNode root;
    int t;

    public BTree(int t) {
        this.t = t;
        this.root = null;
    }

    // Search task by id
    public Task search(int id) {
        if (root == null) return null;
        return root.search(id);
    }

    // Insert task
    public void insert(Task task) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = task.id;
            root.values[0] = task;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                splitChild(s, 0, root);

                int i = 0;
                if (s.keys[0] < task.id)
                    i++;
                insertNonFull(s.children[i], task);

                root = s;
            } else {
                insertNonFull(root, task);
            }
        }
    }

    private void insertNonFull(BTreeNode node, Task task) {
        int i = node.n - 1;

        if (node.leaf) {
            while (i >= 0 && task.id < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                node.values[i + 1] = node.values[i];
                i--;
            }
            node.keys[i + 1] = task.id;
            node.values[i + 1] = task;
            node.n++;
        } else {
            while (i >= 0 && task.id < node.keys[i])
                i--;
            i++;

            if (node.children[i].n == 2 * t - 1) {
                splitChild(node, i, node.children[i]);

                if (task.id > node.keys[i])
                    i++;
            }
            insertNonFull(node.children[i], task);
        }
    }

    private void splitChild(BTreeNode parent, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
            z.values[j] = y.values[j + t];
        }

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.children[j] = y.children[j + t];
        }

        y.n = t - 1;

        for (int j = parent.n; j >= i + 1; j--)
            parent.children[j + 1] = parent.children[j];

        parent.children[i + 1] = z;

        for (int j = parent.n - 1; j >= i; j--) {
            parent.keys[j + 1] = parent.keys[j];
            parent.values[j + 1] = parent.values[j];
        }

        parent.keys[i] = y.keys[t - 1];
        parent.values[i] = y.values[t - 1];
        parent.n++;
    }

    // Delete
    public void delete(int id) {
        if (root == null) return;

        root.delete(id);

        if (root.n == 0) {
            if (root.leaf)
                root = null;
            else
                root = root.children[0];
        }
    }

    // Print tree
    public void printEnhanced() {
        BTreePrinter printer = new BTreePrinter();
        printer.printEnhanced(root);
    }

    public void printByLevels() {
        BTreePrinter printer = new BTreePrinter();
        printer.printByLevels(root);
    }
}
