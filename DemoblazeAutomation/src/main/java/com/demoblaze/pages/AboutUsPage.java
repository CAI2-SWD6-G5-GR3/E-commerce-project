package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class AboutUsPage extends BasePage {
    // Locator for the About Us modal
    private final By aboutUsModal = By.id("videoModal");
    private final By aboutUsVideo = By.xpath(" //*[@id=\"example-video\"]/div[5]/div");
    private final By closeAboutUsModalButton = By.xpath("//*[@id=\"videoModal\"]/div/div/div[3]/button");


    // Getter methods for locators
    public By getAboutUsModalLocator() {
        return aboutUsModal;
    }


    // Click to close the "About Us" modal
    public void closeAboutUsModal() {
        click(closeAboutUsModalButton);  // Close the About Us modal
    }

    // Check if the About Us modal is visible on the page
    public boolean isContactModalVisible() {
        try {
            WebElement modal = find(aboutUsModal);   // 'OrderModal' is your By.id("orderModal")
            return modal.isDisplayed();  // Check if the modal is displayed
        } catch (NoSuchElementException e) {
            return false;  // If the modal is not found, return false (not visible)
        }
    }

    // Check if the modal dialog content contains an error message related to media load failure
    public boolean isMediaLoadErrorPresent() {
        WebElement modalDialogContent = find(aboutUsVideo);
        return modalDialogContent.getText().toLowerCase().contains("failed");
    }


}
