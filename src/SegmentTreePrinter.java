public class SegmentTreePrinter {

    // Color constants
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BOLD = "\u001B[1m";

    public void print(int[] tree, int size) {
        System.out.println(ANSI_BOLD + "\n=== Segment Tree ===" + ANSI_RESET);
        if (tree[1] == 0) {
            System.out.println("(Empty Tree)");
        } else {
            printHelper(tree, 1, 0, size - 1, "", false);
        }
        System.out.println(ANSI_BOLD + "====================\n" + ANSI_RESET);
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
            System.out.println(ANSI_CYAN + "[" + start + "] = " + tree[node] + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "[" + start + " - " + end + "]" + ANSI_YELLOW + " sum=" + tree[node] + ANSI_RESET);
        }

        // LEFT subtree (goes DOWN)
        if (start != end) {
            printHelper(tree, node * 2, start, mid, indent + (isLeft ? "    " : "│   "), true);
        }
    }
}
