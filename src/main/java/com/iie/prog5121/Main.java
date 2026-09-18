package com.iie.prog5121;

import java.util.Scanner;

/**
 * Console entry point for the Part 1 registration and login feature.
 * No GUI / JOptionPane is used, as required by the brief.
 */
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

        scanner.close();
    }
}
