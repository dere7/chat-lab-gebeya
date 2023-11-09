import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class
ChatApplication {
    public static void main(String[] args) {
        Chat privateChat = new Chat("files/Donut[AFK].log", "Private Chat");
        Chat publicChat = new Chat("files/Eurakarte.log", "Public Chat");
        FriendsList friendsList = new FriendsList("files/friends.list");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\nChat App");
            System.out.println("==================");
            System.out.println("1. Private chat");
            System.out.println("2. Public chat");
            System.out.println("3. Manage friends list");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    privateChat.printMenu(scanner);
                    break;
                case 2:
                    publicChat.printMenu(scanner);
                    break;
                case 3:
                    friendsList.printMenu(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid Choice. Please try again.");
            }
        }
    }
}
/* A  CustomExceptionHandler class that catch
 all types of exceptions and writes them in system.log file */
class SystemExceptionHandler {
    public void handleException(Exception e) {
        try (FileWriter fileWriter = new FileWriter("system.log", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(new java.util.Date().toString() + " - " + e.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }}