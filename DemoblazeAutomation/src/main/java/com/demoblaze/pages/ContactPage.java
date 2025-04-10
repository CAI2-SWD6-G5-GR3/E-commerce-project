package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage {
    // Locators for the Contact modal
    private final By contactModal = By.id("exampleModal");
    private final By messageTextarea = By.id("message-text");
    private final By sendMessageButton = By.cssSelector("button[onclick='send()'][class='btn btn-primary']");

    // Getter method for locators
    public By getContactModalLocator() {
        return contactModal;
    }

    // Method to set the message text in the message textarea
    public void setMessage(String message) {
        set(messageTextarea, message);
    } // Calls the 'set' method from BasePage to enter the message

    // Method to click on the send message button
    public void clickSendMessageButton() {
        click(sendMessageButton);
    }

    // Method to check if the Contact modal is visible on the page
    public boolean isContactModalVisible() {
        try {
            WebElement modal = find(contactModal);  // Find the modal element
            return modal.isDisplayed();  // Return true if the modal is visible, false otherwise
        } catch (NoSuchElementException e) {
            return false;  // If the modal is not found, return false (not visible)
        }
    }


}
