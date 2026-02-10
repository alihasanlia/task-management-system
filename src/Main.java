import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();

            if (input.equals("exit")) {
                break;
            }   

            switch (input) {
                case "InsertTask":
                    // +Find overlaps
                    break;
                    
                case "DeleteTask":
                  
                    break;
        
                case "UpdateTask":
                    
                    break;

                case "QueryTaskId":

                    break;

                case "QueryTaskSum":

                    break;

                case "PrintTrees":

                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
        scanner.close();
    }
}
