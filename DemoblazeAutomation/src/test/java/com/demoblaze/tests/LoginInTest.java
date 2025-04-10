package com.demoblaze.tests;
import com.demoblaze.utils.ExcelDataProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;
import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginInTest extends BaseTest {
    // Initialize the LoginPage object to interact with login functionality
    LoginPage loginPage = new LoginPage();

    // Initialize the Logger
    private static final Logger logger = LogManager.getLogger(LoginInTest.class);



    // Test to verify successful login with data from ExcelDataProvider
    @Test(priority = 1, dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class)
    public void verify_successful_login(String username, String password) {
        logger.info("Starting test: verify_successful_login");

        // Step 1: Click the login button to open the login modal
        clickLoginButton();

        // Step 2: Perform login with dynamic credentials provided by DataProvider
        performLogin(username, password);

        // Step 3: Wait for the welcome message indicating a successful login
        waitForWelcomeMessage();

        // Step 4: Validate the success of the login by checking the displayed welcome message
        validateWelcomeMessage(username);

        logger.info("Test verify_successful_login completed successfully");
    }

    public void clickLoginButton() {
        homePage.clickLoginButton();
    }

    public void performLogin(String username, String password) {
        loginPage.login(username, password);
    }

    public void waitForWelcomeMessage() {
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);
    }

    public void validateWelcomeMessage(String username) {
        String expectedMessage = "Welcome " + username;
        String actualMessage = homePage.getWelcomeUser();
        Assert.assertEquals(actualMessage, expectedMessage, "Login failed!");
    }

    public String attachLoginCredentials(String username, String password) {
        return "Username: " + username + "\nPassword: " + password;
    }


    // Test to verify session persistence after page refresh
    @Test(priority = 2)
    public void Verify_Session_Persistence() {
        logger.info("Starting test: Verify_Session_Persistence");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear after login
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Refresh the page to simulate session persistence
        logger.info("Refreshing the page to simulate session persistence");
        driver.navigate().refresh();

        // Wait for the welcome message to appear again after page reload
        logger.info("Waiting for the welcome message to appear after page reload");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Validate that the session is persistent by checking the welcome message again
        String expectedMessage = "Welcome depiuser";
        String actualMessage = homePage.getWelcomeUser();
        logger.info("Verifying the welcome message: expected = " + expectedMessage + ", actual = " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage, "Login failed!");

        logger.info("Test Verify_Session_Persistence completed successfully");
    }


    // Test to verify successful logout and Sign In button visibility
    @Test(priority = 3)
    public void verify_successful_logout() {
        logger.info("Starting test: verify_successful_logout");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");

        // Wait for the log-out button to appear after successful login
        logger.info("Waiting for the log-out button to appear after successful login");
        homePage.waitForElementToBeVisible(homePage.getLogOutButtonLocator(), 20);

        // Click on the log-out button
        logger.info("Clicking on the log-out button");
        homePage.clickLogOutButton();

        // Refresh the page to ensure elements reload correctly
        logger.info("Refreshing the page after logout");
        driver.navigate().refresh();

        // Verify that the Sign In button is visible after log-out (indicating successful logout)
        logger.info("Verifying the visibility of the Sign In button after logout");
        homePage.waitForElementToBeVisible(homePage.getLoginButtonLocator(), 20);
        Assert.assertTrue(homePage.find(homePage.getLoginButtonLocator()).isDisplayed(), "Sign In button is not visible after logout.");

        logger.info("Test verify_successful_logout completed successfully");
    }


    // Test to verify login with incorrect password
    @Test(priority = 4)
    public void verify_login_with_incorrect_password() {
        logger.info("Starting test: verify_login_with_incorrect_password");

        // Log in with an invalid password
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: magdyali and password: bbbbb");
        loginPage.login("magdyali", "bbbbb");

        // Capture and validate the alert message displayed after login failure
        logger.info("Capturing the alert message after login failure");
        String alertText = homePage.getAlertTextIfPresentAndAccept();
        logger.info("Alert text received: " + alertText);

        Assert.assertNotNull(alertText, "No alert appeared after login with incorrect password.");
        Assert.assertTrue(alertText.contains("Wrong password."), "Unexpected alert text: " + alertText);

        logger.info("Test verify_login_with_incorrect_password completed successfully");
    }



    // Test to verify login with an unregistered username
    @Test(priority = 5)
    public void test_login_with_unregistered_username() {
        logger.info("Starting test: test_login_with_unregistered_username");

        // Log in with an unregistered username
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: bbb7854bb and password: bbbbb");
        loginPage.login("bbb7854bb", "bbbbb");

        // Capture and validate the alert message displayed after login failure
        logger.info("Capturing the alert message after login failure");
        String alertText = homePage.getAlertTextIfPresentAndAccept();
        logger.info("Alert text received: " + alertText);

        Assert.assertNotNull(alertText, "No alert appeared after login with unregistered username.");
        Assert.assertTrue(alertText.contains("User does not exist."), "Unexpected alert text: " + alertText);

        logger.info("Test test_login_with_unregistered_username completed successfully");
    }



    // Test to verify login with empty username and password fields
    @Test(priority = 6)
    public void verify_login_with_empty_fields() {
        logger.info("Starting test: verify_login_with_empty_fields");

        // Log in with empty fields for both username and password
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with empty username and password");
        loginPage.login("", "");

        // Capture and validate the alert message displayed after login failure
        logger.info("Capturing the alert message after login with empty fields");
        String alertText = homePage.getAlertTextIfPresentAndAccept();
        logger.info("Alert text received: " + alertText);

        Assert.assertNotNull(alertText, "No alert appeared after login with empty fields.");
        Assert.assertTrue(alertText.contains("Please fill out Username and Password."), "Unexpected alert text: " + alertText);

        logger.info("Test verify_login_with_empty_fields completed successfully");
    }



    // Test to verify login with SQL injection attempts
    @Test(priority = 7)
    public void verify_multiple_SQL_injection_attempts() {
        logger.info("Starting test: verify_multiple_SQL_injection_attempts");

        // Attempt SQL injection with a single quote
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with SQL injection: username = ', password = depiuser");
        loginPage.login("'", "depiuser");

        // SoftAssert allows multiple assertions without failing the test immediately
        SoftAssert softAssert = new SoftAssert();

        // Capture and validate the alert message after the first injection attempt
        logger.info("Capturing the alert message after first SQL injection attempt");
        String alertText = homePage.getAlertTextIfPresentAndAccept();
        logger.info("Alert text received: " + alertText);
        softAssert.assertNotNull(alertText, "No alert appeared after login with ' .");
        softAssert.assertTrue(alertText.contains("Wrong password."), "Unexpected alert text: " + alertText);

        // Attempt SQL injection with a basic condition that always evaluates true
        logger.info("Performing login with SQL injection: username = 1=1;, password = depiuser");
        loginPage.login("1=1;", "depiuser");

        // Capture and validate the alert message after the second injection attempt
        logger.info("Capturing the alert message after second SQL injection attempt");
        String alertText2 = homePage.getAlertTextIfPresentAndAccept();
        logger.info("Alert text received: " + alertText2);
        softAssert.assertNotNull(alertText2, "No alert appeared after login with 1=1.");
        softAssert.assertTrue(alertText2.contains("User does not exist."), "Unexpected alert text: " + alertText2);

        // Assert all conditions to report any failed checks
        softAssert.assertAll();

        logger.info("Test verify_multiple_SQL_injection_attempts completed successfully");
    }


}