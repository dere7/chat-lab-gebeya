import java.io.IOException;
import java.util.Scanner;

public class FriendsList extends FileHandler<Friend> {
    public FriendsList(String path) {
        super(path, 4); // one friend data is in 4 lines
    }

    @Override
    public Friend decode(String data) {
        Scanner scanner = new Scanner(data);
        Friend friend = new Friend();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("<")) {
                friend.setUsername(line.substring(1, line.length() - 1));
            } else if (line.startsWith("[")) {
                String[] pair = line.split("]");
                String key = pair[0].substring(1);
                String value = pair.length == 1 ? "" : pair[1]; // in case the value is empty
             try {
                switch (key) {
                    case "FULLNAME":
                        friend.setFullName(value);
                        break;
                    case "LASTIP":
                        friend.setLastIp(value);
                        break;
                    case "IMAGE":
                        friend.setImage(value);
                        break;
                    default:
                        throw new RuntimeException("Invalid key: " + key);
                }}
             // I created an instance of the SystemExceptionHandler class.
             catch (RuntimeException re) {
                 SystemExceptionHandler handler = new SystemExceptionHandler();
                 handler.handleException(re);
             }


            } else {
                throw new RuntimeException("Invalid input: friend data should either start with < or [");

            }

        }
        return friend;
    }

    @Override
    public String encode(Friend data) {
        return String.format(
                "<%s>\n[FULLNAME]%s\n[LASTIP]%s\n[IMAGE]%s\n",
                data.getUsername(),
                data.getFullName(),
                data.getLastIp(),
                data.getImage()
        );
    }

    public void printMenu(Scanner scanner) {
        while(true) {
            System.out.println();
            System.out.println("Friends List");
            System.out.println("-----------------");
            System.out.println("1. View All Friends");
            System.out.println("2. Add friend");
            System.out.println("3. Go back");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("Friends List");
                    System.out.println("----------------");
                    super.printData();
                    break;
                case 2:
                    try {
                        Friend friend = new Friend();
                        System.out.print("Username: ");
                        friend.setUsername(scanner.nextLine());
                        System.out.print("Full name: ");
                        friend.setFullName(scanner.nextLine());
                        System.out.print("Last IP: ");
                        friend.setLastIp(scanner.nextLine());
                        System.out.print("Image URL: ");
                        friend.setImage(scanner.nextLine());
                        this.appendData(friend);
                        System.out.println("Successfully added new friend");
                    } catch (IOException e) {
                        System.out.println("Unable to add new friend: " + e.getMessage());
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
}
