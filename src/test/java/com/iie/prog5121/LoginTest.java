package com.iie.prog5121;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests built around the exact test data supplied in the PoE brief.
 */
class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
    }

    // ---------------- checkUserName ----------------

    @Test
    void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    // ---------------- checkPasswordComplexity ----------------

    @Test
    void testPasswordMeetsComplexityRules() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexityRules() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ---------------- checkCellPhoneNumber ----------------

    @Test
    void testCellPhoneNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testCellPhoneNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---------------- registerUser (assertEquals on messages) ----------------

    @Test
    void testRegisterUser_usernameFailMessage() {
        String result = login.registerUser("Kyle", "Daniels", "kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(Login.USERNAME_FAIL, result);
    }

    @Test
    void testRegisterUser_passwordFailMessage() {
        String result = login.registerUser("Kyle", "Daniels", "kyl_1", "password", "+27838968976");
        assertEquals(Login.PASSWORD_FAIL, result);
    }

    @Test
    void testRegisterUser_cellNumberFailMessage() {
        String result = login.registerUser("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(Login.CELL_FAIL, result);
    }

    @Test
    void testRegisterUser_success() {
        String result = login.registerUser("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(Login.REGISTRATION_SUCCESS, result);
        assertTrue(login.isRegistered());
    }

    // ---------------- loginUser / returnLoginStatus ----------------

    @Test
    void testLoginSuccessful() {
        login.registerUser("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");

        boolean loggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");

        assertTrue(loggedIn);
        assertEquals("Welcome Kyle, Daniels it is great to see you again.", login.returnLoginStatus(loggedIn));
    }

    @Test
    void testLoginFailed() {
        login.registerUser("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");

        boolean loggedIn = login.loginUser("kyl_1", "wrongPassword");

        assertFalse(loggedIn);
        assertEquals(Login.LOGIN_FAIL, login.returnLoginStatus(loggedIn));
    }
}
