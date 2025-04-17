package sit707_week4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Tests functions in LoginForm using various testing techniques to achieve maximum code coverage.
 * @author Ahsan Habib
 */
public class LoginFormTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
    public void tearDownStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "S223979728";
        Assert.assertNotNull("Student ID is null", studentId);
        System.out.println("Student ID: " + studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Saumil Patel";
        Assert.assertNotNull("Student name is null", studentName);
        System.out.println("Student Name: " + studentName);
    }

    /**
     * USERNAME TESTS
     */
    
    // Test Case 1: Null username with valid password
    @Test
    public void testNullUsername() {
        LoginStatus status = LoginForm.login(null, "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
        System.out.println("Test: Null Username - " + status);
    }
    
    // Test Case 2: Empty username with valid password
    @Test
    public void testEmptyUsername() {
        LoginStatus status = LoginForm.login("", "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
        System.out.println("Test: Empty Username - " + status);
    }
    
    // Test Case 3: Invalid username with valid password
    @Test
    public void testInvalidUsername() {
        LoginStatus status = LoginForm.login("wrong_user", "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("Test: Invalid Username - " + status);
    }
    
    /**
     * PASSWORD TESTS
     */
    
    // Test Case 4: Valid username with null password
    @Test
    public void testNullPassword() {
        LoginStatus status = LoginForm.login("ahsan", null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Password", status.getErrorMsg());
        System.out.println("Test: Null Password - " + status);
    }
    
    // Test Case 5: Valid username with empty password
    @Test
    public void testEmptyPassword() {
        LoginStatus status = LoginForm.login("ahsan", "");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Password", status.getErrorMsg());
        System.out.println("Test: Empty Password - " + status);
    }
    
    // Test Case 6: Valid username with invalid password
    @Test
    public void testInvalidPassword() {
        LoginStatus status = LoginForm.login("ahsan", "wrong_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("Test: Invalid Password - " + status);
    }
    
    /**
     * COMBINED USERNAME AND PASSWORD TESTS
     */
    
    // Test Case 7: Null username and null password
    @Test
    public void testNullUsernameNullPassword() {
        LoginStatus status = LoginForm.login(null, null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
        System.out.println("Test: Null Username, Null Password - " + status);
    }
    
    // Test Case 8: Empty username and empty password
    @Test
    public void testEmptyUsernameEmptyPassword() {
        LoginStatus status = LoginForm.login("", "");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
        System.out.println("Test: Empty Username, Empty Password - " + status);
    }
    
    // Test Case 9: Invalid username and invalid password
    @Test
    public void testInvalidUsernameInvalidPassword() {
        LoginStatus status = LoginForm.login("wrong_user", "wrong_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("Test: Invalid Username, Invalid Password - " + status);
    }
    
    /**
     * VALIDATION CODE TESTS
     */
    
    // Test Case 10: Valid validation code
    @Test
    public void testValidValidationCode() {
        boolean result = LoginForm.validateCode("123456");
        Assert.assertTrue(result);
        System.out.println("Test: Valid Validation Code - Result: " + result);
    }
    
    // Test Case 11: Null validation code
    @Test
    public void testNullValidationCode() {
        boolean result = LoginForm.validateCode(null);
        Assert.assertFalse(result);
        System.out.println("Test: Null Validation Code - Result: " + result);
    }
    
    // Test Case 12: Empty validation code
    @Test
    public void testEmptyValidationCode() {
        boolean result = LoginForm.validateCode("");
        Assert.assertFalse(result);
        System.out.println("Test: Empty Validation Code - Result: " + result);
    }
    
    // Test Case 13: Invalid validation code
    @Test
    public void testInvalidValidationCode() {
        boolean result = LoginForm.validateCode("wrong_code");
        Assert.assertFalse(result);
        System.out.println("Test: Invalid Validation Code - Result: " + result);
    }
    
    /**
     * SUCCESSFUL LOGIN TEST
     */
    
    // Test Case 14: Successful login with valid credentials
    @Test
    public void testSuccessfulLogin() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        Assert.assertEquals("123456", status.getErrorMsg());
        System.out.println("Test: Successful Login - " + status);
    }

    /**
     * FULL FLOW TESTS
     */
    
    // Test Case 15: Successful login followed by successful validation
    @Test
    public void testSuccessfulLoginAndValidation() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        
        boolean validationResult = LoginForm.validateCode(status.getErrorMsg());
        Assert.assertTrue(validationResult);
        System.out.println("Test: Successful Login + Validation - Login: " + status + ", Validation: " + validationResult);
    }
    
    // Test Case 16: Failed login followed by attempted validation
    @Test
    public void testFailedLoginAndValidation() {
        LoginStatus status = LoginForm.login("wrong_user", "wrong_pass");
        Assert.assertFalse(status.isLoginSuccess());
        
        boolean validationResult = LoginForm.validateCode(status.getErrorMsg());
        Assert.assertFalse(validationResult);
        System.out.println("Test: Failed Login + Validation - Login: " + status + ", Validation: " + validationResult);
    }
    
    /**
     * LOGINSTATUS CLASS TESTS
     */
    
    // Test Case 17: LoginStatus constructor and getters
    @Test
    public void testLoginStatusConstructorAndGetters() {
        LoginStatus status = new LoginStatus(true, "Test Message");
        Assert.assertTrue(status.isLoginSuccess());
        Assert.assertEquals("Test Message", status.getErrorMsg());
        System.out.println("Test: LoginStatus Constructor and Getters - " + status);
    }
    
    // Test Case 18: LoginStatus toString method
    @Test
    public void testLoginStatusToString() {
        LoginStatus status = new LoginStatus(true, "Test Message");
        String toString = status.toString();
        Assert.assertTrue(toString.contains("loginSuccess=true"));
        Assert.assertTrue(toString.contains("errorMsg=Test Message"));
        System.out.println("Test: LoginStatus toString - " + toString);
    }
    
    /**
     * EDGE CASE TESTS
     */
    
    // Test Case 19: Unicode characters in username/password
    @Test
    public void testUnicodeCharactersInCredentials() {
        LoginStatus status = LoginForm.login("αβγδε", "оПрСт");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("Test: Unicode Characters in Credentials - " + status);
    }
    
    // Test Case 20: Very long username/password
    @Test
    public void testVeryLongCredentials() {
        StringBuilder longStr = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longStr.append("a");
        }
        LoginStatus status = LoginForm.login(longStr.toString(), longStr.toString());
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("Test: Very Long Credentials - " + status);
    }
    
    // Test Case 21: Multiple login attempts in sequence
    @Test
    public void testMultipleLoginAttempts() {
        // First attempt - invalid credentials
        LoginStatus status1 = LoginForm.login("wrong_user", "wrong_pass");
        Assert.assertFalse(status1.isLoginSuccess());
        
        // Second attempt - still invalid
        LoginStatus status2 = LoginForm.login("ahsan", "wrong_pass");
        Assert.assertFalse(status2.isLoginSuccess());
        
        // Third attempt - successful
        LoginStatus status3 = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status3.isLoginSuccess());
        
        System.out.println("Test: Multiple Login Attempts - Results: " + status1 + ", " + status2 + ", " + status3);
    }
    
    // Test Case 22: Attempt to validate with null right after successful login
    @Test
    public void testValidateNullAfterSuccessfulLogin() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        
        boolean validationResult = LoginForm.validateCode(null);
        Assert.assertFalse(validationResult);
        System.out.println("Test: Validate Null After Successful Login - Result: " + validationResult);
    }

    // Test Case 23: Attempt to validate with empty string right after successful login
    @Test
    public void testValidateEmptyAfterSuccessfulLogin() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        
        boolean validationResult = LoginForm.validateCode("");
        Assert.assertFalse(validationResult);
        System.out.println("Test: Validate Empty After Successful Login - Result: " + validationResult);
    }

    // Test Case 24: Try every possible combination of null/empty for username and password
    @Test
    public void testAllCombinationsOfNullAndEmpty() {
        String[] testValues = {null, "", "valid"};
        
        for (String username : testValues) {
            for (String password : testValues) {
                if ("valid".equals(username)) {
                    username = "ahsan";
                }
                
                if ("valid".equals(password)) {
                    password = "ahsan_pass";
                }
                
                LoginStatus status = LoginForm.login(username, password);
                System.out.println("Test: Combination - Username: " + (username == null ? "null" : "'" + username + "'") + 
                                   ", Password: " + (password == null ? "null" : "'" + password + "'") + 
                                   " - Result: " + status);
            }
        }
    }
    
    // Test Case 25: Testing the original sample case
    @Test
    public void testFailEmptyUsernameAndEmptyPasswordAndDontCareValCode() {
        LoginStatus status = LoginForm.login(null, null);
        Assert.assertTrue(status.isLoginSuccess() == false);
        System.out.println("Test: Original Sample Case - " + status);
    }
    
    /**
     * MAIN CLASS TESTING - NEW TEST CASES FOR COVERAGE IMPROVEMENT
     */
    
    // Test Case 26: Test Main's execution path directly
    @Test
    public void testMainMethod() {
        // This directly executes the main method to cover all its paths
        Main.main(new String[]{});
        
        // We don't need to assert anything specific here as this is just for coverage
        Assert.assertTrue(true);
        
        // The output will contain all the console outputs from Main.main()
        String output = outContent.toString();
        Assert.assertTrue(output.contains("[Empty username, empty password]"));
        Assert.assertTrue(output.contains("[Correct username, Correct password]"));
    }
    
    // Test Case 27: Test specific scenarios from Main in isolation
    @Test
    public void testMainScenarioEmptyUsernameWrongPassword() {
        LoginStatus status = LoginForm.login(null, "xyz");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
        System.out.println("[Empty username, wrong password] >> " + status);
    }
    
    // Test Case 28: Test specific scenarios from Main in isolation
    @Test
    public void testMainScenarioWrongUsernameWrongPassword() {
        LoginStatus status = LoginForm.login("abc", "xyz");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("[Wrong username, wrong password] >> " + status);
    }
    
    // Test Case 29: Test specific scenarios from Main in isolation
    @Test
    public void testMainScenarioCorrectUsernameEmptyPassword() {
        LoginStatus status = LoginForm.login("ahsan", null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Password", status.getErrorMsg());
        System.out.println("[Correct username, empty password] >> " + status);
    }
    
    // Test Case 30: Test specific scenarios from Main in isolation
    @Test
    public void testMainScenarioCorrectUsernameWrongPassword() {
        LoginStatus status = LoginForm.login("ahsan", "xyz");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("[Correct username, wrong password] >> " + status);
    }
    
    // Test Case 31: Test specific scenarios from Main in isolation
    @Test
    public void testMainScenarioWrongUsernameCorrectPassword() {
        LoginStatus status = LoginForm.login("abc", "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
        System.out.println("[Wrong username, Correct password] >> " + status);
    }
    
    // Test Case 32: Test Main's validation code execution path
    @Test
    public void testMainValidationCodeScenarios() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        
        // Test all validation code scenarios from main
        Assert.assertFalse(LoginForm.validateCode(null));
        Assert.assertFalse(LoginForm.validateCode("abcd"));
        Assert.assertTrue(LoginForm.validateCode("123456"));
        
        System.out.println("Test: Main Validation Code Scenarios");
    }
}