package com.iie.prog5121;

import java.util.regex.Pattern;


public class Login {

    // ---- Standard system messages (wording taken from the brief / test tables) ----
    public static final String USERNAME_SUCCESS =
            "Username successfully captured.";
    public static final String USERNAME_FAIL =
            "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

    public static final String PASSWORD_SUCCESS =
            "Password successfully captured.";
    public static final String PASSWORD_FAIL =
            "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

    public static final String CELL_SUCCESS =
            "Cell number successfully captured.";
    public static final String CELL_FAIL =
            "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";

    public static final String REGISTRATION_SUCCESS =
            "Username successfully captured. Password successfully captured. Cell number successfully captured. Registration successful, you may now log in.";

    public static final String LOGIN_FAIL =
            "Username or password incorrect, please try again.";

    private static final Pattern HAS_UPPER = Pattern.compile("[A-Z]");
    private static final Pattern HAS_DIGIT = Pattern.compile("[0-9]");
    private static final Pattern HAS_SPECIAL = Pattern.compile("[^A-Za-z0-9]");

    // ---- Stored details for the currently registered user ----
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;
    private boolean registered = false;

    public Login() {
    }

    /**
     * Checks that a username contains an underscore and is no more than
     * five characters long.
     */
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks that a password is at least eight characters long and
     * contains a capital letter, a number and a special character.
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        return HAS_UPPER.matcher(password).find()
                && HAS_DIGIT.matcher(password).find()
                && HAS_SPECIAL.matcher(password).find();
    }

    /**
     * Checks that a cell phone number starts with a '+' international
     * dialling code followed by a number that is no more than ten digits
     * long (South African mobile numbers use the +27 country code followed
     * by nine digits, e.g. "+27838968976").
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null || !cellPhoneNumber.startsWith("+")) {
            return false;
        }
        String digits = cellPhoneNumber.substring(1);
        if (digits.isEmpty() || !digits.matches("[0-9]+")) {
            return false;
        }
        if (digits.length() < 3) {
            return false;
        }
        // Treat the first two digits as the country code (e.g. "27") and
        // everything after that as the subscriber number.
        String number = digits.substring(2);
        return !number.isEmpty() && number.length() <= 10;
    }

    /**
     * Validates and, if everything is correct, stores a new user's
     * registration details. Checks are performed in order: username, then
     * password, then cell phone number, returning the message for the
     * first check that fails - or a success message if all three pass.
     */
    public String registerUser(String firstName, String lastName, String username,
                                String password, String cellPhoneNumber) {
        if (!checkUserName(username)) {
            return USERNAME_FAIL;
        }
        if (!checkPasswordComplexity(password)) {
            return PASSWORD_FAIL;
        }
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return CELL_FAIL;
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.registered = true;

        return REGISTRATION_SUCCESS;
    }

    /**
     * Verifies that the supplied username and password match the details
     * captured during registration.
     */
    public boolean loginUser(String username, String password) {
        return registered
                && this.username != null && this.username.equals(username)
                && this.password != null && this.password.equals(password);
    }

    /**
     * Returns the message that corresponds to the outcome of
     * {@link #loginUser(String, String)}.
     */
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return LOGIN_FAIL;
    }

    // ---- Simple getters, useful for the console app and for tests ----
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public boolean isRegistered() {
        return registered;
    }
}
