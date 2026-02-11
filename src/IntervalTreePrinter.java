public class IntervalTreePrinter {

    public void print(IntervalNode root) {
        System.out.println("\n" + "=== Interval Tree ===");
        if (root == null) {
            System.out.println("(Empty Tree)");
        } else {
            printHelper(root.right, "    ", false);
            printNode(root);
            printHelper(root.left, "    ", true);
        }
        System.out.println("=====================\n");
    }

    private void printHelper(IntervalNode node, String indent, boolean isLeft) {
        if (node != null) {
            // Print right subtree first
            printHelper(node.right, indent + (isLeft ? "│   " : "    "), false);
            
            // Print current node
            System.out.print(indent);
            System.out.print(isLeft ? "└── " : "┌── ");
            
            printNode(node);
            
            // Print left subtree
            printHelper(node.left, indent + (isLeft ? "    " : "│   "), true);
        }
    }

    private void printNode(IntervalNode node) {
        System.out.println("Task " + node.taskId +" (" + node.start + "," + node.end + ")");
    }
}
