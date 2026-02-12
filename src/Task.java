public class Task {
    int id;
    int startTime;
    int endTime;
    int value;

    public Task(int id, int startTime, int endTime, int value) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.value = value;
    }

    // Color constants
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_ORANGE = "\u001B[38;5;208m";
    private static final String ANSI_BOLD = "\u001B[1m";

    public void print() {
    String interval = String.format("[%2d - %2d]", startTime, endTime);
    String bar = "|".repeat(endTime - startTime + 1);

    System.out.println(
            ANSI_BOLD + "Task" + String.format("%2d", id) + ": " +  ANSI_RESET +
            ANSI_ORANGE + interval + " " + ANSI_RESET +
            ANSI_BOLD + "Value: " + ANSI_RESET +
            ANSI_ORANGE + String.format("%3d", value) + ANSI_RESET + " " +
            ANSI_GREEN + bar + ANSI_RESET
    );
}


}
