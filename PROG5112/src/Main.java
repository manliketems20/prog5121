
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
 System.out.println("2. Disregard Message");
 System.out.println("3. Store Message");
 System.out.println("4. Stored Messages Menu");
 System.out.println("5. Exit");
 choice = sc.nextInt();
 sc.nextLine();

 switch (choice) {
 case 1: handleMessage(sc, "send"); break;
 case 2: handleMessage(sc, "discard"); break;
 case 3: handleMessage(sc, "store"); break;
 case 4: storedMessagesMenu(sc); break;
 case 5: System.out.println("Exiting..."); break;
 default: System.out.println("Invalid choice.");
 }
 } while (choice != 5);
 }

 private static void handleMessage(Scanner sc, String option) {
 System.out.println("Enter Recipient number:");
 String recipient = sc.nextLine();
 System.out.println("Enter Message:");
 String text = sc.nextLine();

 Message msg = new Message(recipient, text);
 System.out.println(msg.sendMessage(option));
System.out.println(msg.printMessage());
 }

 private static void storedMessagesMenu(Scanner sc) {
 System.out.println("\n--- STORED MESSAGES MENU ---");
 System.out.println("1. Display all stored messages");
 System.out.println("2. Display longest stored message");
 System.out.println("3. Search by Message ID");
System.out.println("4. Search by Recipient");
 System.out.println("5. Delete by Hash");
 System.out.println("6. Display full report");

 int option = sc.nextInt();
 sc.nextLine();

 switch (option) {
 case 1: Message.displayStoredMessages(); break;
 case 2: Message.displayLongestMessage(); break;
 case 3:
 System.out.println("Enter Message ID:");
 Message.searchByMessageID(sc.nextLine());
break;
 case 4:
 System.out.println("Enter Recipient:");
 Message.searchByRecipient(sc.nextLine());
 break;
 case 5:
System.out.println("Enter Message Hash:");
 Message.deleteByHash(sc.nextLine());
 break;
 case 6: Message.displayReport(); break;
 default: System.out.println("Invalid option.");
 }
 }
}
