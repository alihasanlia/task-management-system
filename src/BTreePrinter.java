public class BTreePrinter {

    // Color constants
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_ORANGE = "\u001B[38;5;208m";
    private static final String ANSI_BOLD = "\u001B[1m";

    // Color array for levels
    private static final String[] LEVEL_COLORS = {
        ANSI_BOLD + ANSI_PURPLE,   // Level 0
        ANSI_BOLD + ANSI_BLUE,     // Level 1  
        ANSI_BOLD + ANSI_CYAN,     // Level 2
        ANSI_BOLD + ANSI_GREEN,    // Level 3
        ANSI_BOLD + ANSI_YELLOW,   // Level 4
        ANSI_BOLD + ANSI_RED,      // Level 5
        ANSI_BOLD + ANSI_WHITE     // Level 6+
    };

    // Alternative: Color each node differently within levels
    public void printEnhanced(BTreeNode root) {
        System.out.println("\n" + ANSI_RESET + "=== B-Tree Visualization ===");
        printEnhanced(root, 0, 0);
        System.out.println("===========================\n");
    }

    private void printEnhanced(BTreeNode node, int level, int siblingIndex) {
        if (node == null) return;

        // Get base color for this level
        String levelColor = getColorForLevel(level);
        
        // Print with indentation for better hierarchy visualization
        String indent = "  ".repeat(level);
        
        System.out.print(indent + "└─ " + ANSI_RESET);
        System.out.print(levelColor + "Level " + level + " " + ANSI_RESET);
        System.out.print(ANSI_BLACK + "[ " + ANSI_RESET);
        
        // Print keys with gradient colors
        for (int i = 0; i < node.n; i++) {
            // String keyColor = getKeyColor(i, node.n);
            System.out.print(node.keys[i] + " " + ANSI_RESET);
        }
        
        // Add node info
        System.out.print(ANSI_BLACK + "]" + ANSI_RESET);
        
        // Add metadata
        if (!node.leaf) {
            System.out.print(ANSI_ORANGE + " (" + (node.n + 1) + " children)" + ANSI_RESET);
        }
        
        System.out.println();
        
        // Print children
        if (!node.leaf) {
            for (int i = 0; i <= node.n; i++) {
                printEnhanced(node.children[i], level + 1, i);
            }
        }
    }

    // Utility methods for coloring
    private String getColorForLevel(int level) {
        if (level < LEVEL_COLORS.length) {
            return LEVEL_COLORS[level];
        }
        // Cycle through colors if we have more levels than defined colors
        return LEVEL_COLORS[level % LEVEL_COLORS.length];
    }
}
