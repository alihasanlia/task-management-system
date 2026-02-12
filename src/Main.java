import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            if (!scanner.hasNext())
                break;

            String command = scanner.next();

            if (command.equalsIgnoreCase("exit"))
                break;

            try {
                switch (command) {

                    case "InsertTask": {
                        int id = readInt(scanner);
                        int start = readInt(scanner);
                        int end = readInt(scanner);
                        int value = readInt(scanner);

                        taskManager.insert(new Task(id, start, end, value));
                        break;
                    }

                    case "DeleteTask": {
                        int id = readInt(scanner);
                        taskManager.delete(id);
                        break;
                    }

                    case "UpdateTask": {
                        int id = readInt(scanner);
                        int start = readInt(scanner);
                        int end = readInt(scanner);
                        int value = readInt(scanner);

                        taskManager.update(id, start, end, value);
                        break;
                    }

                    case "QueryTaskId": {
                        int id = readInt(scanner);
                        taskManager.search(id);
                        break;
                    }

                    case "QueryTaskSum": {
                        int id1 = readInt(scanner);
                        int id2 = readInt(scanner);
                        taskManager.search(id1, id2);
                        break;
                    }

                    case "PrintTrees":
                        taskManager.printTrees();
                        break;

                    default:
                        System.out.println("Invalid command");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
                scanner.nextLine(); // discard rest of line
            }
        }

        scanner.close();
    }

    // Helper
    private static int readInt(Scanner scanner) {
        if (!scanner.hasNextInt())
            throw new IllegalArgumentException("Expected integer");

        return scanner.nextInt();
    }
}
