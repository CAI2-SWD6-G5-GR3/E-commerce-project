package com.demoblaze.tests;

import com.demoblaze.pages.*;
import com.demoblaze.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NavigationTest extends BaseTest {
    ContactPage contactPage = new ContactPage();
    AboutUsPage aboutPage = new AboutUsPage();

    // Initialize the Logger
    private static final Logger logger = LogManager.getLogger(LoginInTest.class);



    @Test(priority = 1)
    public void verify_logo_button_click_functionality() {
        logger.info("Starting test: verify_logo_button_click_functionality");

        // Click on the logo to return to the homepage
        logger.info("Clicking the logo to return to the homepage");
        homePage.clickLogo();

        // Wait for the first item image to load and become visible
        logger.info("Waiting for the first item image to load and become visible");
        homePage.waitForElementToBeVisible(homePage.getFirstItemImageLocator(), 20); // Adjust timeout as needed

        // Assert that the first item image is visible
        logger.info("Verifying that the first item image is visible");
        Assert.assertTrue(homePage.isFirstItemImageVisible(), "Items are not visible after clicking the logo.");

        logger.info("Test verify_logo_button_click_functionality completed successfully");
    }


    @Test(priority = 2)
    public void verify_home_nav_button_click_functionality() {
        logger.info("Starting test: verify_home_nav_button_click_functionality");

        // Click on the logo to return to the homepage
        logger.info("Clicking the home button to navigate to the homepage");
        homePage.clickHomeButton();

        // Wait for the first item image to load and become visible
        logger.info("Waiting for the first item image to load and become visible");
        homePage.waitForElementToBeVisible(homePage.getFirstItemImageLocator(), 20); // Adjust timeout as needed

        // Assert that the first item image is visible
        logger.info("Verifying that the first item image is visible after navigating to the homepage");
        Assert.assertTrue(homePage.isFirstItemImageVisible(), "Items are not visible after clicking the Home.");

        logger.info("Test verify_home_nav_button_click_functionality completed successfully");
    }



    @Test(priority = 3)
    public void verify_contact_nav_button_click_functionality() {
        logger.info("Starting test: verify_contact_nav_button_click_functionality");

        // Click on the logo to return to the homepage
        logger.info("Clicking the contact button to navigate to the contact page");
        homePage.clickContactButton();

        // Wait for the contact modal to load and become visible
        logger.info("Waiting for the contact modal to become visible");
        homePage.waitForElementToBeVisible(contactPage.getContactModalLocator(), 20); // Adjust timeout as needed

        // Assert that the contact modal is visible
        logger.info("Verifying that the contact modal is visible after clicking the Contact button");
        Assert.assertTrue(contactPage.isContactModalVisible(), "Contact Modal is not visible after clicking the Contact.");

        logger.info("Test verify_contact_nav_button_click_functionality completed successfully");
    }


    @Test(priority = 4)
    public void verify_Forbid_submitting_contact_form_with_missing_details() {
        logger.info("Starting test: verify_Forbid_submitting_contact_form_with_missing_details");

        // Click on the logo to return to the homepage
        logger.info("Clicking the contact button to navigate to the contact page");
        homePage.clickContactButton();

        // Wait for the contact modal to be visible
        logger.info("Waiting for the contact modal to become visible");
        homePage.waitForElementToBeVisible(contactPage.getContactModalLocator(), 20); // Adjust timeout as needed

        // Click the 'Send Message' button
        logger.info("Clicking the 'Send Message' button with missing details");
        contactPage.clickSendMessageButton();

        // Capture and validate the alert message after attempting to submit with missing details
        logger.info("Capturing the alert message after clicking 'Send Message'");
        String alertText = homePage.getAlertTextIfPresentAndAccept();  // 👈 safer method
        logger.info("Alert text received: " + alertText);

        // Assert that the alert text is not the success message
        logger.info("Verifying the alert message text");
        Assert.assertNotNull(alertText, "No alert appeared after Send Message.");
        Assert.assertFalse(alertText.contains("Thanks for the message!!"), "Unexpected alert text: " + alertText);

        logger.info("Test verify_Forbid_submitting_contact_form_with_missing_details completed successfully");
    }


    @Test(priority = 5)
    public void verify_about_us_button_click_functionality() {
        logger.info("Starting test: verify_about_us_button_click_functionality");

        // Click on the logo to return to the homepage
        logger.info("Clicking the About Us button to open the About Us modal");
        homePage.clickAboutUsButton();

        // Wait for the About Us modal to load and become visible
        logger.info("Waiting for the About Us modal to become visible");
        homePage.waitForElementToBeVisible(aboutPage.getAboutUsModalLocator(), 20); // Adjust timeout as needed

        // Assert that the About Us modal is visible
        logger.info("Verifying that the About Us modal is visible after clicking the About Us button");
        Assert.assertTrue(aboutPage.isContactModalVisible(), "Contact Modal is not visible after clicking the About Us button.");

        logger.info("Test verify_about_us_button_click_functionality completed successfully");
    }



    @Test(priority = 6)
    public void verify_closing_about_us_modal() {
        logger.info("Starting test: verify_closing_about_us_modal");

        // Click on the logo to return to the homepage
        logger.info("Clicking the About Us button to open the About Us modal");
        homePage.clickAboutUsButton();

        // Wait for the About Us modal to load and become visible
        logger.info("Waiting for the About Us modal to become visible");
        homePage.waitForElementToBeVisible(aboutPage.getAboutUsModalLocator(), 20); // Adjust timeout as needed

        // Close the About Us modal
        logger.info("Closing the About Us modal");
        aboutPage.closeAboutUsModal();

        // Wait for the About Us modal to be closed and become invisible
        logger.info("Waiting for the About Us modal to become invisible after closing");
        homePage.waitForElementToBeNotVisible(aboutPage.getAboutUsModalLocator(), 20); // Adjust timeout as needed

        // Assert that the About Us modal is no longer visible
        logger.info("Verifying that the About Us modal is not visible after closing it");
        Assert.assertFalse(aboutPage.isContactModalVisible(), "Contact Modal is not visible after clicking the About Us button.");

        logger.info("Test verify_closing_about_us_modal completed successfully");
    }



    @Test(priority = 7)
    public void verify_media_load__in_about_us() {
        logger.info("Starting test: verify_media_load__in_about_us");

        // Click on the About Us button
        logger.info("Clicking the About Us button to open the About Us modal");
        homePage.clickAboutUsButton();

        // Wait for the About Us modal to load and become visible
        logger.info("Waiting for the About Us modal to become visible");
        homePage.waitForElementToBeVisible(aboutPage.getAboutUsModalLocator(), 20); // Adjust timeout as needed

        // Call the helper method to check if the "failed" text is present
        logger.info("Checking if the 'failed' text is present in the About Us modal");
        boolean isErrorPresent = aboutPage.isMediaLoadErrorPresent();

        // Assert that the "failed" text is not found
        logger.info("Verifying that the 'failed' text is not found in the About Us modal");
        Assert.assertFalse(isErrorPresent, "The media load error message was found in the modal content.");

        logger.info("Test verify_media_load__in_about_us completed successfully");
    }



}
