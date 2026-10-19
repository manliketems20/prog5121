
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Registration ===");

        String username;
        do {
            System.out.print("Enter a username (must contain an underscore and be no more than five characters): ");
            username = scanner.nextLine();
            System.out.println(login.checkUserName(username) ? Login.USERNAME_SUCCESS : Login.USERNAME_FAIL);
        } while (!login.checkUserName(username));

        String password;
        do {
            System.out.print("Enter a password (min. 8 characters, a capital letter, a number and a special character): ");
            password = scanner.nextLine();
            System.out.println(login.checkPasswordComplexity(password) ? Login.PASSWORD_SUCCESS : Login.PASSWORD_FAIL);
        } while (!login.checkPasswordComplexity(password));

        String cellPhoneNumber;
        do {
            System.out.print("Enter your South African cell phone number (e.g. +27838968976): ");
            cellPhoneNumber = scanner.nextLine();
            System.out.println(login.checkCellPhoneNumber(cellPhoneNumber) ? Login.CELL_SUCCESS : Login.CELL_FAIL);
        } while (!login.checkCellPhoneNumber(cellPhoneNumber));

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        String registrationMessage = login.registerUser(firstName, lastName, username, password, cellPhoneNumber);
        System.out.println(registrationMessage);

        System.out.println();
        System.out.println("=== Login ===");
        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine();

        boolean success = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(success));


        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Send Message");
            System.out.println("2. See recently sent messages");
            System.out.println("3. Exit");
            choice = scanner.nextInt();
            switch (choice) {

                case 1: {
                    // Recipient: keep asking until the number is valid
                    String recipient;
                    do {
                        System.out.print("Enter recipient cell number (e.g. +27838968976): ");
                        recipient = scanner.nextLine();
                        if (!login.checkCellPhoneNumber(recipient)) {
                            System.out.println(Login.CELL_FAIL);
                        }
                    } while (!login.checkCellPhoneNumber(recipient));

                    // Message text: keep asking until it isn't empty
                    String text;
                    do {
                        System.out.print("Enter your message: ");
                        text = scanner.nextLine();
                        if (text.trim().isEmpty()) {
                            System.out.println("Message cannot be empty.");
                        }
                    } while (text.trim().isEmpty());

                    // Create the Message object (generates the ID and hash)
                    Message message = new Message(recipient, text);

                    System.out.println("\nMessage sent successfully.");
                    System.out.println("Message ID:   " + message.getMessageID());
                    System.out.println("Recipient:    " + message.getRecipient());
                    System.out.println("Message:      " + message.getMessage());
                    System.out.println("Message Hash: " + message.getMessageHash());
                    break;
                }
                case 2:
                    System.out.println("Feature not avalible yet. Coming soon.");

                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1, 2 or 3.");

            }

        }while (choice !=3);
        scanner.close();
    }
}