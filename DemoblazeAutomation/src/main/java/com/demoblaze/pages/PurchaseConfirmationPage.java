package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PurchaseConfirmationPage extends BasePage {
    // Locator for the confirmation modal
    private  final By confirmModal = By.cssSelector(".sweet-alert");

    // Getter method to access the confirmModal locator
    public  By getConfirmModalLocator() {
        return confirmModal;
    }


    // Method to check if the confirmation modal is visible
    public boolean isconfirmModalVisible() {
        try {
            WebElement modal = find(confirmModal);  // 'OrderModal' is your By.id("orderModal")
            return modal.isDisplayed();  // Check if the modal is displayed
        } catch (NoSuchElementException e) {
            return false;  // If the modal is not found, return false (not visible)
        }
    }


}
