package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    // Locators for elements on the Product Page
    private  final By addToCartButton = By.cssSelector("a.btn.btn-success.btn-lg");  // Add to cart button
    private  final By cartButton = By.id("cartur");  // Cart button


    // Method to click the "Add to Cart" button
    public void clickAddToCartButton() {
        waitForElementToBeClickable(addToCartButton, 10);  // Wait for the Add to Cart button to be clickable
        click(addToCartButton);  // Click the Add to Cart button
    }

    // Method to click the Cart button
    public void clickCartButton() {
        click(cartButton);  // Click the Cart button
    }
}
