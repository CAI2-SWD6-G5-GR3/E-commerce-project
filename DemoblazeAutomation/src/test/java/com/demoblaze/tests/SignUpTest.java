package com.demoblaze.tests;

import com.demoblaze.utils.Utility;
import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.SignupPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SignUpTest extends BaseTest {
    // Initialize the SignupPage object to interact with signup functionality
    SignupPage signupPage = new SignupPage();

    // Initialize the Logger
    private static final Logger logger = LogManager.getLogger(LoginInTest.class);


    // Generate random username and password using the Utility class
    String username = Utility.generateRandomUsername();  // Generates a random username
    String password = Utility.generateRandomPassword();  // Generates a random password


    // Test to verify successful signup with a random username and password
    @Test(priority = 1)
    public void verify_successful_signup() {
        logger.info("Starting test: verify_successful_signup");

        // Click on the signup button to open the signup modal
        logger.info("Clicking the signup button to open the signup modal");
        homePage.clickSignupButton();

        // Perform the signup with generated random username and password
        logger.info("Performing the signup with a generated random username and password");
        signupPage.signup(username, password);

        // Verify that the alert text indicates a successful signup
        logger.info("Verifying the alert text indicating successful signup");
        homePage.verifyAlertText("Sign up successful.");

        logger.info("Test verify_successful_signup completed successfully");
    }



    // Test to verify signup with an existing username
    @Test(priority = 2)
    public void verify_signup_with_existing_username() {
        logger.info("Starting test: verify_signup_with_existing_username");

        // Click on the signup button
        logger.info("Clicking the signup button to open the signup modal");
        homePage.clickSignupButton();

        // Attempt to sign up with an already existing username ("depiuser")
        logger.info("Attempting to sign up with an already existing username ('depiuser')");
        signupPage.signup("depiuser", "depiuser");

        // Verify that the alert text indicates the username already exists
        logger.info("Verifying the alert text indicating the username already exists");
        homePage.verifyAlertText("This user already exist.");

        logger.info("Test verify_signup_with_existing_username completed successfully");
    }


    // Test to verify signup with empty username and password fields
    @Test(priority = 3)
    public void verify_signup_with_empty_fields() {
        logger.info("Starting test: verify_signup_with_empty_fields");

        // Click on the signup button
        logger.info("Clicking the signup button to open the signup modal");
        homePage.clickSignupButton();

        // Attempt to sign up with empty username and password fields
        logger.info("Attempting to sign up with empty username and password fields");
        signupPage.signup("", "");

        // Verify that the alert text prompts the user to fill out username and password
        logger.info("Verifying the alert text prompts the user to fill out username and password");
        homePage.verifyAlertText("Please fill out Username and Password.");

        logger.info("Test verify_signup_with_empty_fields completed successfully");
    }



    // Verify that the username and password fields are empty after successful signup
    @Test(priority = 4)
    public void verify_signup_fields_are_empty_after_successful_signup() {
        logger.info("Starting test: verify_signup_fields_are_empty_after_successful_signup");

        // Click on the signup button
        logger.info("Clicking the signup button to open the signup modal");
        homePage.clickSignupButton();

        // Perform the signup with generated random username and password
        logger.info("Performing the signup with a generated random username and password");
        signupPage.signup(username, password);

        // Wait for the alert to appear and accept it (successful signup alert)
        logger.info("Waiting for the alert to appear and accepting it (successful signup alert)");
        homePage.waitForAlertAndAccept(10);

        // Get the values of the username and password input fields after signup
        logger.info("Getting the values of the username and password input fields after signup");
        String enteredUsername = signupPage.getSignupUsernameInput();  // Get the value of the username input
        String enteredPassword = signupPage.getSignupPasswordInput();  // Get the value of the password input

        // Assert that both the username and password fields are empty after signup
        logger.info("Verifying that both the username and password fields are empty after signup");
        Assert.assertTrue(enteredUsername.isEmpty() && enteredPassword.isEmpty(), "Both the username and password fields should be empty after signup.");

        logger.info("Test verify_signup_fields_are_empty_after_successful_signup completed successfully");
    }


}



