package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PlaceOrderPage extends BasePage {
    //  Locators for elements inside the Place Order modal
    private final By OrderModal = By.id("orderModal");
    private  final By nameInput = By.id("name");  // Locator for the name input field
    private  final By countryInput = By.id("country");  // Locator for the country input field
    private  final By cityInput = By.id("city");   // Locator for the city input field
    private  final By cardInput = By.id("card");  // Locator for the credit card input field
    private  final By monthInput = By.id("month");  // Locator for the month input field
    private  final By yearInput = By.id("year");  // Locator for the year input field
    private  final By purchaseButton = By.cssSelector("button[onclick='purchaseOrder()']");  // Locator for the Purchase button

    // Getter methods for locators
    public By getOrderModalLocator() {
        return OrderModal;
    }

    // Method to fill in the name field
    public void setName(String name) {
        set(nameInput, name);  // Set the name in the input field
    }

    // Method to fill in the country field
    public void setCountry(String country) {
        set(countryInput, country);  // Set the country in the input field
    }

    // Method to fill in the city field
    public void setCity(String city) {
        set(cityInput, city);  // Set the city in the input field
    }

    // Method to fill in the credit card number field
    public void setCard(String card) {
        set(cardInput, card);  // Set the credit card number in the input field
    }

    // Method to fill in the expiration month of the credit card
    public void setMonth(String month) {
        set(monthInput, month);  // Set the expiration month in the input field
    }

    // Method to fill in the expiration year of the credit card
    public void setYear(String year) {
        set(yearInput, year);  // Set the expiration year in the input field
    }


    // Method to click the Purchase button and complete the order
    public void clickPurchaseButton() {
        waitForElementToBeClickable(purchaseButton, 20);  // Wait for the Purchase button to be clickable
        click(purchaseButton);  // Click the Purchase button
    }

    // Method to check if the Order modal is visible
    public boolean isOrderModalVisible() {
        try {
            WebElement modal = find(OrderModal);  // 'OrderModal' is your By.id("orderModal")
            return modal.isDisplayed();  // Check if the modal is displayed
        } catch (NoSuchElementException e) {
            return false;  // If the modal is not found, return false (not visible)
        }
    }

}
