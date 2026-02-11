public class IntervalTreePrinter {

    private static final String ANSI_RED   = "\u001B[31m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RESET = "\u001B[0m";

    public void print(IntervalNode root, IntervalNode nil) {
        System.out.println("\n" + "=== Interval Tree (RB) ===" + ANSI_RESET);
        if (root == nil) {
            System.out.println("(Empty Tree)");
        } else {
            printTreeHelper(root.right, nil, "    ", false);
            
            // Print root with special formatting
            System.out.print("─── ");
            if (root.color == Color.RED) {
                System.out.println(ANSI_RED + root.toString() + ANSI_RESET);
            } else {
                System.out.println(ANSI_BLACK + root.toString() + ANSI_RESET);
            }
            
            printTreeHelper(root.left, nil, "    ", true);
        }
        System.out.println("==========================\n");
    }

    private void printTreeHelper(IntervalNode node, IntervalNode nil, String indent, boolean isLeft) {
        if (node != nil) {
            // Print right subtree first
            printTreeHelper(node.right, nil, indent + (isLeft ? "│   " : "    "), false);
            
            // Print current node
            System.out.print(indent);
            System.out.print(isLeft ? "└── " : "┌── ");
            
            if (node.color == Color.RED) {
                System.out.println(ANSI_RED + node.toString() + ANSI_RESET);
            } else {
                System.out.println(ANSI_BLACK + node.toString() + ANSI_RESET);
            }
            
            // Print left subtree
            printTreeHelper(node.left, nil, indent + (isLeft ? "    " : "│   "), true);
        }
    }
    
}
