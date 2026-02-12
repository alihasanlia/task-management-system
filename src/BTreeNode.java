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
    public Task search(int key) {
        int i = 0;
        while (i < n && key > keys[i]) i++;

        if (i < n && keys[i] == key)
            return values[i];

        if (leaf)
            return null;

        return children[i].search(key);
    }

    // Update
    public boolean update(int key, Task newTask) {
        int i = 0;
        while (i < n && key > keys[i])
            i++;

        if (i < n && keys[i] == key) {
            values[i] = newTask;
            return true;
        }

        if (leaf)
            return false;

        return children[i].update(key, newTask);
    }

    // Delete
    public void delete(int key) {

        int idx = findKey(key);

        // Case 1: key found in this node
        if (idx < n && keys[idx] == key) {

            if (leaf)
                removeFromLeaf(idx);
            else
                removeFromNonLeaf(idx);

        } else {

            // Case 2: key not found here
            if (leaf)
                return; // key not present

            boolean flag = (idx == n);

            // Ensure child has at least t keys
            if (children[idx].n < t)
                fill(idx);

            if (flag && idx > n)
                children[idx - 1].delete(key);
            else
                children[idx].delete(key);
        }
    }

    public int findKey(int key) {
        int idx = 0;
        while (idx < n && keys[idx] < key)
            idx++;
        return idx;
    }

    public void removeFromLeaf(int idx) {
        for (int i = idx + 1; i < n; i++) {
            keys[i - 1] = keys[i];
            values[i - 1] = values[i];
        }
        n--;
    }

    private void removeFromNonLeaf(int idx) {

        int key = keys[idx];

        // Case 1: predecessor has >= t keys
        if (children[idx].n >= t) {
            BTreeNode cur = children[idx];
            while (!cur.leaf)
                cur = cur.children[cur.n];

            keys[idx] = cur.keys[cur.n - 1];
            values[idx] = cur.values[cur.n - 1];

            children[idx].delete(keys[idx]);
        }

        // Case 2: successor has >= t keys
        else if (children[idx + 1].n >= t) {
            BTreeNode cur = children[idx + 1];
            while (!cur.leaf)
                cur = cur.children[0];

            keys[idx] = cur.keys[0];
            values[idx] = cur.values[0];

            children[idx + 1].delete(keys[idx]);
        }

        // Case 3: both children have t-1 keys
        else {
            merge(idx);
            children[idx].delete(key);
        }
    }

    private void merge(int idx) {

        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];

        child.keys[t - 1] = keys[idx];
        child.values[t - 1] = values[idx];

        for (int i = 0; i < sibling.n; i++) {
            child.keys[i + t] = sibling.keys[i];
            child.values[i + t] = sibling.values[i];
        }

        if (!child.leaf) {
            for (int i = 0; i <= sibling.n; i++)
                child.children[i + t] = sibling.children[i];
        }

        for (int i = idx + 1; i < n; i++) {
            keys[i - 1] = keys[i];
            values[i - 1] = values[i];
        }

        for (int i = idx + 2; i <= n; i++)
            children[i - 1] = children[i];

        child.n += sibling.n + 1;
        n--;
    }

    private void fill(int idx) {

        if (idx != 0 && children[idx - 1].n >= t)
            borrowFromPrev(idx);

        else if (idx != n && children[idx + 1].n >= t)
            borrowFromNext(idx);

        else {
            if (idx != n)
                merge(idx);
            else
                merge(idx - 1);
        }
    }

    private void borrowFromPrev(int idx) {

        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx - 1];

        for (int i = child.n - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
            child.values[i + 1] = child.values[i];
        }

        if (!child.leaf) {
            for (int i = child.n; i >= 0; i--)
                child.children[i + 1] = child.children[i];
        }

        child.keys[0] = keys[idx - 1];
        child.values[0] = values[idx - 1];

        if (!child.leaf)
            child.children[0] = sibling.children[sibling.n];

        keys[idx - 1] = sibling.keys[sibling.n - 1];
        values[idx - 1] = sibling.values[sibling.n - 1];

        child.n++;
        sibling.n--;
    }

    private void borrowFromNext(int idx) {

        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];

        child.keys[child.n] = keys[idx];
        child.values[child.n] = values[idx];

        if (!child.leaf)
            child.children[child.n + 1] = sibling.children[0];

        keys[idx] = sibling.keys[0];
        values[idx] = sibling.values[0];

        for (int i = 1; i < sibling.n; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
            sibling.values[i - 1] = sibling.values[i];
        }

        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; i++)
                sibling.children[i - 1] = sibling.children[i];
        }

        child.n++;
        sibling.n--;
    }

}
