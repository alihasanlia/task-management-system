public class SegmentTreePrinter {

    public void print(int[] tree, int size) {
        System.out.println("\n=== Segment Tree ===");
        if (tree[1] == 0) {
            System.out.println("(Empty Tree)");
        } else {
            printHelper(tree, 1, 0, size - 1, "", false);
        }
        System.out.println("====================\n");
    }

    private void printHelper(int[] tree, int node, int start, int end, String indent, boolean isLeft) {
        if (node >= tree.length) return;

        int mid = (start + end) / 2;

        // RIGHT subtree first (goes UP)
        if (start != end) {
            printHelper(tree, node * 2 + 1, mid + 1, end, indent + (isLeft ? "│   " : "    "), false);
        }

        // Print current node
        System.out.print(indent);
        System.out.print(isLeft ? "└── " : "┌── ");

        if (start == end) {
            System.out.println("[" + start + "] = " + tree[node]);
        } else {
            System.out.println("[" + start + " - " + end + "] sum=" + tree[node]);
        }

        // LEFT subtree (goes DOWN)
        if (start != end) {
            printHelper(tree, node * 2, start, mid, indent + (isLeft ? "    " : "│   "), true);
        }
    }
}
