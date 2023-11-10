import java.io.*;
import java.util.Scanner;

public class Chat extends FileHandler<Message> {
    private final String username = "Eukarte";
    private final String header;

    public Chat(String path, String header) {
        super(path);
        this.header = header;
    }

    public void sendMessage(String content) throws IOException {
        super.appendData(new Message(this.username, content));
    }

    public void printMenu(Scanner scanner) {
        while(true) {
            System.out.println();
            System.out.println(header);
            System.out.println("-----------------");
            System.out.println("1. View messages");
            System.out.println("2. Send Message");
            System.out.println("3. Go back");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    super.printData();
                    break;
                case 2:
                    try {
                        System.out.print("Message: ");
                        String content = scanner.nextLine();
                        this.sendMessage(content);
                        System.out.println("Successfully sent message");
                    } catch (IOException e) {
                        System.out.println("Unable to send message: " + e.getMessage());
                        // I created an instance of the SystemExceptionHandler class.
                            SystemExceptionHandler handler = new SystemExceptionHandler();
                            handler.handleException(e);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public Message decode(String data) {
        String[] content = data.split(" ",2);
        content[0] = content[0].substring(1, content[0].length() - 1); // remove enclosing []
        content[1] = content[1].substring(0, content[1].length() - 1); // exclude new line
        return new Message(content[0], content[1]);
    }

    @Override
    public String encode(Message message) {
        return String.format("<%s> %s", this.username, message.getContent());
    }
}
