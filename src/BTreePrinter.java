import java.util.ArrayList;
import java.util.List;

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
        System.out.println("\n" + ANSI_BOLD + "=== B-Tree Visualization ===" + ANSI_RESET);
        printEnhanced(root, 0, 0);
        System.out.println(ANSI_BOLD + "============================\n" + ANSI_RESET);
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

    // Print B-tree by levels with color
    public void printByLevels(BTreeNode root) {
        System.out.println(ANSI_BOLD + "\n=== B-Tree (by Levels) ===" + ANSI_RESET);
        
        if (root == null) {
            System.out.println("(Empty Tree)");
            return;
        }
        
        // Create lists for each level
        List<List<BTreeNode>> levels = new ArrayList<>();
        buildLevels(root, 0, levels);
        
        // Print each level
        for (int i = 0; i < levels.size(); i++) {
            String levelColor = getLevelColor(i);
            System.out.print(levelColor + "Level " + i + ": " + ANSI_RESET);
            
            List<BTreeNode> levelNodes = levels.get(i);
            for (int j = 0; j < levelNodes.size(); j++) {
                BTreeNode node = levelNodes.get(j);
                                
                // Print node
                System.out.print(ANSI_BLACK + "[" + ANSI_RESET);
                for (int k = 0; k < node.n; k++) {
                    System.out.print(node.keys[k] + ANSI_RESET);
                    if (k < node.n - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.print(ANSI_BLACK + "]" + ANSI_RESET);
                
                // Add separator between nodes (but not after last one)
                if (j < levelNodes.size() - 1) {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        
        System.out.println(ANSI_BOLD + "==========================" + ANSI_RESET + "\n");
    }

    // Build levels list
    private void buildLevels(BTreeNode node, int level, List<List<BTreeNode>> levels) {
        if (node == null) return;
        
        // Ensure level list exists
        if (levels.size() <= level) {
            levels.add(new ArrayList<>());
        }
        
        // Add current node to its level
        levels.get(level).add(node);
        
        // Add children to next level
        if (!node.leaf) {
            for (int i = 0; i <= node.n; i++) {
                buildLevels(node.children[i], level + 1, levels);
            }
        }
    }

    // Get color for level header
    private String getLevelColor(int level) {
        if (level < LEVEL_COLORS.length) {
            return LEVEL_COLORS[level];
        }
        // Cycle colors if more levels than defined colors
        return LEVEL_COLORS[level % LEVEL_COLORS.length];
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
