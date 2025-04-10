package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    // Locators for elements on the Cart Page
    private  final By totalPrice = By.id("totalp");  // Total price displayed
    private  final By placeOrderButton = By.cssSelector("button[data-toggle='modal'][data-target='#orderModal']");  // Place order button
    private  final By firstDeleteButton = By.xpath("//tbody[@id='tbodyid']//tr[1]//a[text()='Delete']");
    private  final By cartItemsRows = By.xpath("//tbody[@id='tbodyid']//tr[@class='success']");
    private  final By cartItemsLocator = By.cssSelector("tr.success");
    private  final By itemPriceLocator = By.cssSelector("td:nth-child(3)");


    // Getter method to access the totalPrice locator
    public By getTotalPriceLocator() {
        return totalPrice;
    }



    // Method to retrieve the total price text displayed on the cart page
    public String getTotalPrice() {
        return find(totalPrice).getText();  // Get the text of the total price
    }

    // Method to click on the Place Order button to proceed with the checkout
    public void clickPlaceOrderButton() {
        waitForElementToBeClickable(placeOrderButton, 10);  // Wait for the Place Order button to be clickable
        click(placeOrderButton);  // Click the Place Order button
    }


    // Method to click the delete button of the first item in the cart
    public void clickFirstDeleteButton() {
        click(firstDeleteButton);
    }

    // Method to retrieve the total price as an integer value (without decimal part)
    public int getTotalPriceInt() {
        String totalText = find(totalPrice).getText();
        return Integer.parseInt(totalText);
    }


    // Method to count the number of items in the cart by counting the rows in the cart table
    public int getCartItemsCount() {
        List<WebElement> items = findAll(cartItemsRows);
        return items.size();
    }

    // Method to wait for an item to be removed from the cart (based on the initial item count)
    public void waitForItemToBeRemoved(int initialCount) {
        // Call the method from BasePage to wait for the element count to change
        waitForElementCountChange(cartItemsRows, initialCount, Duration.ofSeconds(20));
    }


    // Method to check if the cart is empty based on the visibility of the total price element
    public boolean isCartEmpty() {
        try {
            WebElement totalElement = find(totalPrice);
            return !totalElement.isDisplayed();  // Return true if totalp is not visible, meaning the cart is empty
        } catch (NoSuchElementException e) {
            // If the element is not found at all, consider the cart as empty
            return true;
        }
    }

    // Method to check if the total price is empty or zero
    public boolean isTotalPriceEmptyOrZero() {
        try {
            WebElement totalElement = find(totalPrice);
            String totalText = totalElement.getText().trim();
            return totalText.isEmpty() || totalText.equals("0");
        } catch (NoSuchElementException e) {
            // If the element is not found at all, assume the total price is empty/zero
            return true;
        }
    }

    // Method to calculate the total price by parsing the displayed total price
    public double calculateTotalPrice() {
        String totalPriceText = getTotalPrice();
        String numericTotalPriceText = totalPriceText.replaceAll("[^0-9.]", ""); // Clean the price string
        return Double.parseDouble(numericTotalPriceText);
    }


    // Helper Method to calculate the total price by summing up the prices of all items in the cart
    public double calculateCartTotal() {
        double totalPrice = 0.0;

        // Get all items in the cart (returns a list of cart item elements)
        List<WebElement> cartItems = getCartItems();
        for (WebElement item : cartItems) {
            // Get the price for each item (assuming the method retrieves item price text)
            String itemPriceText = getItemPrice(item);
            String numericPriceText = itemPriceText.replaceAll("[^0-9.]", ""); // Clean the price string
            double itemPrice = Double.parseDouble(numericPriceText);
            totalPrice += itemPrice; // Accumulate the total price
        }

        return totalPrice; // Return the total price of all items in the cart
    }


    // Method to get all items in the cart (represented by rows with class 'success')
    public List<WebElement> getCartItems() {
        // Use the findAll() method from BasePage to find all cart items using the locator
        return findAll(cartItemsLocator);
    }

    // Method to get the price of a specific item in the cart
    public String getItemPrice(WebElement item) {
        // Find the price element (third <td> in each <tr> element) and return the price text
        WebElement priceElement = item.findElement(itemPriceLocator);
        return priceElement.getText(); // Return the price of the item as text
    }


}




