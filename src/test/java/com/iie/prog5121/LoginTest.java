package com.iie.prog5121;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;


class LoginTest {

    private Login login;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    void setUp() {
        login = new Login();
    }

    @AfterEach
    public void tearDown() throws Exception {
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

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);

        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);

        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellPhoneNumber = "";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);

        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String firstName = "";
        String lastName = "";
        String username = "";
        String password = "";
        String cellPhoneNumber = "";
        Login instance = new Login();
        String expResult = "";
        String result = instance.registerUser(firstName, lastName, username, password, cellPhoneNumber);
        assertEquals(expResult, result);
   
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "";
        String password = "";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.loginUser(username, password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean loginSuccessful = false;
        Login instance = new Login();
        String expResult = "";
        String result = instance.returnLoginStatus(loginSuccessful);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Login.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Login instance = new Login();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Login.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Login instance = new Login();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class Login.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Login instance = new Login();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCellPhoneNumber method, of class Login.
     */
    @Test
    public void testGetCellPhoneNumber() {
        System.out.println("getCellPhoneNumber");
        Login instance = new Login();
        String expResult = "";
        String result = instance.getCellPhoneNumber();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRegistered method, of class Login.
     */
    @Test
    public void testIsRegistered() {
        System.out.println("isRegistered");
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.isRegistered();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    private void fail(String the_test_case_is_a_prototype) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
