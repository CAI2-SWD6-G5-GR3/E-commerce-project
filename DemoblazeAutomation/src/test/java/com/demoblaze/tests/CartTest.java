package com.demoblaze.tests;

import com.demoblaze.pages.*;
import com.demoblaze.base.BaseTest;
import com.demoblaze.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {
    // Page objects for testing
    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    PlaceOrderPage orderPage = new PlaceOrderPage();
    PurchaseConfirmationPage confirmPage = new PurchaseConfirmationPage();
    // Initialize the Logger
    private static final Logger logger = LogManager.getLogger(LoginInTest.class);


    // Verify adding product to cart by checking the success message
    @Test(priority = 1)
    public void verify_adding_product_to_cart_by_message() {
        logger.info("Starting test: verify_adding_product_to_cart_by_message");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear (indicating successful login)
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Click on the first product and add it to the cart
        logger.info("Clicking the first product to add to the cart");
        homePage.clickFirstItem();
        logger.info("Clicking the 'Add to Cart' button for the selected product");
        productPage.clickAddToCartButton();

        // Capture and validate the success message from the alert
        logger.info("Capturing the success alert message after adding product to cart");
        String alertText = homePage.getAlertTextIfPresentAndAccept();
        logger.info("Alert text received: " + alertText);
        Assert.assertNotNull(alertText, "No alert appeared after adding product to the cart.");
        Assert.assertTrue(alertText.contains("Product added."), "Unexpected alert text: " + alertText);

        logger.info("Test verify_adding_product_to_cart_by_message completed successfully");
    }




    // Verify adding product to cart by checking the total price in the cart
    @Test(priority = 2)
    public void verify_adding_product_to_cart_by_cart() {
        logger.info("Starting test: verify_adding_product_to_cart_by_cart");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Click on the first product and add it to the cart
        logger.info("Clicking the first product to add to the cart");
        homePage.clickFirstItem();
        logger.info("Clicking the 'Add to Cart' button for the selected product");
        productPage.clickAddToCartButton();

        // Accept the alert after adding the product to the cart
        logger.info("Accepting the alert after adding the product to the cart");
        homePage.getAlertTextIfPresentAndAccept();

        // Navigate to the cart and wait for the total price to be visible
        logger.info("Navigating to the cart and waiting for the total price to appear");
        productPage.clickCartButton();
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 20);

        // Calculate the total price and assert that it's greater than 0
        logger.info("Calculating the total price in the cart");
        double calculatedTotalPrice = cartPage.calculateTotalPrice();
        logger.info("Calculated total price: " + calculatedTotalPrice);
        Assert.assertTrue(calculatedTotalPrice > 0, "Total price in the cart should be greater than 0. Actual price: " + calculatedTotalPrice);

        logger.info("Test verify_adding_product_to_cart_by_cart completed successfully");
    }




    // Verify deleting an item from the cart
    @Test(priority = 3)
    public void verify_delete_item_from_cart() {
        logger.info("Starting test: verify_delete_item_from_cart");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Add two items to the cart
        logger.info("Clicking the first product to add to the cart");
        homePage.clickFirstItem();
        logger.info("Clicking the 'Add to Cart' button for the selected product");
        productPage.clickAddToCartButton();
        logger.info("Accepting the alert after adding the product to the cart");
        homePage.getAlertTextIfPresentAndAccept();

        logger.info("Waiting for the welcome message after adding the first item");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        logger.info("Clicking the second product to add to the cart");
        productPage.clickAddToCartButton();
        logger.info("Accepting the alert after adding the second product to the cart");
        homePage.getAlertTextIfPresentAndAccept();

        // Navigate to the cart page
        logger.info("Navigating to the cart page");
        homePage.clickCartButton();
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 30);

        // Get initial item count and total price
        logger.info("Getting the initial item count and total price from the cart");
        int initialItemCount = cartPage.getCartItemsCount();
        int initialTotal = cartPage.getTotalPriceInt();

        // Delete the first item and wait for the item to be removed
        logger.info("Deleting the first item from the cart");
        cartPage.clickFirstDeleteButton();
        logger.info("Waiting for the item to be removed from the cart");
        cartPage.waitForItemToBeRemoved(initialItemCount);

        // Get updated count and total
        logger.info("Getting the updated item count and total price from the cart");
        int updatedItemCount = cartPage.getCartItemsCount();
        int updatedTotal = cartPage.getTotalPriceInt();

        // Assertions to check if item count and total price decreased
        logger.info("Verifying the item count and total price after deletion");
        Assert.assertTrue(updatedItemCount < initialItemCount, "Item count should decrease after deletion.");
        Assert.assertTrue(updatedTotal < initialTotal, "Total price should decrease after deletion.");

        logger.info("Test verify_delete_item_from_cart completed successfully");
    }


    // Verify total price calculation in the cart
    @Test(priority = 4)
    public void verify_total_price_calculation_in_cart() {
        logger.info("Starting test: verify_total_price_calculation_in_cart");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Add two items to the cart
        logger.info("Clicking the first product to add to the cart");
        homePage.clickFirstItem();
        logger.info("Clicking the 'Add to Cart' button for the selected product");
        productPage.clickAddToCartButton();
        logger.info("Accepting the alert after adding the product to the cart");
        homePage.getAlertTextIfPresentAndAccept();

        logger.info("Waiting for the welcome message after adding the first item");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        logger.info("Clicking the second product to add to the cart");
        productPage.clickAddToCartButton();
        logger.info("Accepting the alert after adding the second product to the cart");
        homePage.getAlertTextIfPresentAndAccept();

        // Navigate to the cart
        logger.info("Navigating to the cart");
        homePage.clickCartButton();
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 20);

        // Calculate the total price using the helper method
        logger.info("Calculating the total price using the helper method");
        double calculatedTotalPrice = cartPage.calculateCartTotal();

        // Get the total price from the cart page
        logger.info("Getting the total price from the cart page");
        String totalPriceText = cartPage.getTotalPrice();
        String numericTotalPriceText = totalPriceText.replaceAll("[^0-9.]", "");
        double displayedTotalPrice = Double.parseDouble(numericTotalPriceText);

        // Assert that the calculated total price equals the displayed total price
        logger.info("Verifying that the calculated total price equals the displayed total price");
        Assert.assertEquals(calculatedTotalPrice, displayedTotalPrice, "The total price is incorrect in the cart!");

        logger.info("Test verify_total_price_calculation_in_cart completed successfully");
    }



    // Verify that the cart is empty when no item is added
    @Test(priority = 5)
    public void Verify_Empty_Cart_no_item_added() {
        logger.info("Starting test: Verify_Empty_Cart_no_item_added");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser2 and password: depiuser2");
        loginPage.login("depiuser2", "depiuser2");

        // Wait for the welcome message to appear
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Navigate to the cart
        logger.info("Navigating to the cart");
        homePage.clickCartButton();

        // Assert that the cart is empty
        logger.info("Verifying that the cart is empty");
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty!");
        logger.info("Cart is empty as expected");

        logger.info("Verifying that the total price is empty or zero");
        Assert.assertTrue(cartPage.isTotalPriceEmptyOrZero(), "Total price is not empty or zero!");
        logger.info("Total price is empty or zero as expected");

        logger.info("Test Verify_Empty_Cart_no_item_added completed successfully");
    }



    // Verify that the cart's content persists after refreshing the page
    @Test(priority = 6)
    public void verify_cart_persistence_after_refresh() {
        logger.info("Starting test: verify_cart_persistence_after_refresh");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Add an item to the cart
        logger.info("Clicking the first product to add to the cart");
        homePage.clickFirstItem();
        logger.info("Clicking the 'Add to Cart' button for the selected product");
        productPage.clickAddToCartButton();
        logger.info("Accepting the alert after adding the product to the cart");
        homePage.getAlertTextIfPresentAndAccept();

        // Navigate to the cart page
        logger.info("Navigating to the cart page");
        productPage.clickCartButton();
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 20);

        // Refresh the page and check if the cart's total price persists
        logger.info("Refreshing the page to check if the cart's total price persists");
        driver.navigate().refresh();
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 20);

        logger.info("Calculating the total price after page refresh");
        double calculatedTotalPrice = cartPage.calculateTotalPrice();
        logger.info("Calculated total price: " + calculatedTotalPrice);

        Assert.assertTrue(calculatedTotalPrice > 0, "Total price in the cart should be greater than 0. Actual price: " + calculatedTotalPrice);

        logger.info("Test verify_cart_persistence_after_refresh completed successfully");
    }


    // Verify that the cart's content persists after logging out and logging back in
    @Test(priority = 7)
    public void verify_cart_persistence_after_logout() {
        logger.info("Starting test: verify_cart_persistence_after_logout");

        // Log in to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Navigate to the cart and get the total price before logout
        logger.info("Navigating to the cart to get the total price before logout");
        homePage.clickCartButton();
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 20);

        logger.info("Capturing the total price before logout");
        String totalPriceText = cartPage.getTotalPrice();
        String numericTotalPriceText = totalPriceText.replaceAll("[^0-9.]", "");
        double totalPrice = Double.parseDouble(numericTotalPriceText);

        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Log out and log back in
        logger.info("Logging out of the application");
        homePage.clickLogOutButton();

        logger.info("Clicking the login button again");
        homePage.clickLoginButton();
        loginPage.login("depiuser", "depiuser");

        // Wait for the welcome message to appear and navigate to the cart again
        logger.info("Waiting for the welcome message after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        logger.info("Navigating to the cart again after login");
        homePage.clickCartButton();
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 20);

        // Get the total price after logging back in
        logger.info("Capturing the total price after login");
        String totalPriceTextAfter = cartPage.getTotalPrice();
        String numericTotalPriceTextAfter = totalPriceText.replaceAll("[^0-9.]", "");
        double totalPriceAfter = Double.parseDouble(numericTotalPriceText);
        loginPage.waitForElementToBeVisible(cartPage.getTotalPriceLocator(), 20);


        // Assert that the total price is the same before and after logout/login
        logger.info("Verifying that the total price remains the same after logout and login");
        Assert.assertEquals(totalPrice, totalPriceAfter, "Total price changed after logout and login!");

        logger.info("Test verify_cart_persistence_after_logout completed successfully");

    }


    // Verify forbidding checkout if the cart is empty
    @Test(priority = 8)
    public void verify_forbid_checkout_if_cart_is_empty() {
        logger.info("Starting test: verify_forbid_checkout_if_cart_is_empty");

        // Login to the application
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser2 and password: depiuser2");
        loginPage.login("depiuser2", "depiuser2");

        // Attempt to click on 'cart' button
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);
        logger.info("Navigating to the cart");
        homePage.clickCartButton();

        // Attempt to click on 'Place Order' button
        logger.info("Attempting to click the 'Place Order' button to proceed to checkout");
        cartPage.clickPlaceOrderButton(); // Ensure this clicks the button to proceed to checkout

        // Assert that the order modal is not visible, preventing checkout
        logger.info("Waiting for the order modal to appear (this should not be visible)");
        orderPage.waitForElementToBeVisible(orderPage.getOrderModalLocator(), 20);
        logger.info("Verifying that the order modal is not visible");
        Assert.assertFalse(orderPage.isOrderModalVisible(), "Order Modal is not visible!");

        logger.info("Test verify_forbid_checkout_if_cart_is_empty completed successfully");
    }


    // Test to verify that the user can proceed to checkout from the cart
    @Test(priority = 9)
    public void verify_proceeding_to_checkout() {
        logger.info("Starting test: verify_proceeding_to_checkout");

        // Log in to the application and wait for the welcome message
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Navigate to the cart and click on 'Place Order' to begin the checkout process
        logger.info("Navigating to the cart");
        homePage.clickCartButton();
        logger.info("Clicking the 'Place Order' button to proceed to checkout");
        cartPage.clickPlaceOrderButton();

        // Wait for the order modal to appear and verify it's visible
        logger.info("Waiting for the order modal to appear after clicking 'Place Order'");
        orderPage.waitForElementToBeVisible(orderPage.getOrderModalLocator(), 20);
        logger.info("Verifying that the order modal is visible");
        Assert.assertTrue(orderPage.isOrderModalVisible(), "Order Modal is not visible!");

        logger.info("Test verify_proceeding_to_checkout completed successfully");
    }



    // Test to verify that the order can be placed with valid details
    @Test(priority = 10)
    public void verify_order_placement_with_valid_details() {
        logger.info("Starting test: verify_order_placement_with_valid_details");

        // Log in to the application and wait for the welcome message
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Navigate to the cart and proceed to checkout
        logger.info("Navigating to the cart");
        homePage.clickCartButton();
        logger.info("Clicking the 'Place Order' button to proceed to checkout");
        cartPage.clickPlaceOrderButton();

        // Wait for the order modal to appear
        logger.info("Waiting for the order modal to appear");
        orderPage.waitForElementToBeVisible(orderPage.getOrderModalLocator(), 20);

        // Enter valid order details in the modal
        logger.info("Entering valid order details");
        orderPage.setName("Team");
        orderPage.setCountry("Egypt");
        orderPage.setCity("Cairo");
        orderPage.setCard("78154214521");
        orderPage.setMonth("4");
        orderPage.setYear("2025");

        // Submit the order by clicking the Purchase button
        logger.info("Clicking the 'Purchase' button to submit the order");
        orderPage.clickPurchaseButton();

        // Wait for the confirmation modal to appear and verify it
        logger.info("Waiting for the confirmation modal to appear");
        orderPage.waitForElementToBeVisible(confirmPage.getConfirmModalLocator(), 20);
        logger.info("Verifying that the confirmation modal is visible");
        Assert.assertTrue(confirmPage.isconfirmModalVisible(), "Confirm Modal is not visible!");

        logger.info("Test verify_order_placement_with_valid_details completed successfully");
    }


    // Test to verify that the order cannot be placed with an invalid credit card
    @Test(priority = 11)
    public void verify_order_placement_with_invalid_credit_card() {
        logger.info("Starting test: verify_order_placement_with_invalid_credit_card");

        // Log in to the application and wait for the welcome message
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Navigate to the cart and proceed to checkout
        logger.info("Navigating to the cart");
        homePage.clickCartButton();
        logger.info("Clicking the 'Place Order' button to proceed to checkout");
        cartPage.clickPlaceOrderButton(); // Ensure this clicks the button to proceed to checkout

        // Wait for the order modal to appear
        logger.info("Waiting for the order modal to appear");
        orderPage.waitForElementToBeVisible(orderPage.getOrderModalLocator(), 20);

        // Enter invalid credit card details
        logger.info("Entering invalid credit card details");
        orderPage.setName("Team");
        orderPage.setCountry("Egypt");
        orderPage.setCity("Cairo");
        orderPage.setCard("777"); // Invalid card number
        orderPage.setMonth("4");
        orderPage.setYear("2025");

        // Attempt to submit the order by clicking the Purchase button
        logger.info("Attempting to submit the order with invalid credit card details");
        orderPage.clickPurchaseButton();

        // Wait for the confirmation modal and verify that it's not visible due to invalid credit card
        logger.info("Waiting for the confirmation modal to appear (should not be visible)");
        orderPage.waitForElementToBeVisible(confirmPage.getConfirmModalLocator(), 20);
        logger.info("Verifying that the confirmation modal is not visible due to invalid credit card");
        Assert.assertFalse(confirmPage.isconfirmModalVisible(), "Confirm Modal is not visible!");

        logger.info("Test verify_order_placement_with_invalid_credit_card completed successfully");
    }



    // Test to verify that the order cannot be placed with missing details
    @Test(priority = 12)
    public void verify_order_placement_with_missing_details() {
        logger.info("Starting test: verify_order_placement_with_missing_details");

        // Log in to the application and wait for the welcome message
        logger.info("Clicking the login button");
        homePage.clickLoginButton();
        logger.info("Performing login with username: depiuser and password: depiuser");
        loginPage.login("depiuser", "depiuser");
        logger.info("Waiting for the welcome message to appear after login");
        loginPage.waitForElementToBeVisible(homePage.getWelcomeUserLocator(), 20);

        // Navigate to the cart and proceed to checkout
        logger.info("Navigating to the cart");
        homePage.clickCartButton();
        logger.info("Clicking the 'Place Order' button to proceed to checkout");
        cartPage.clickPlaceOrderButton(); // Ensure this clicks the button to proceed to checkout

        // Wait for the order modal to appear
        logger.info("Waiting for the order modal to appear");
        orderPage.waitForElementToBeVisible(orderPage.getOrderModalLocator(), 20);

        // Enter incomplete order details (missing card information)
        logger.info("Entering incomplete order details (missing card information)");
        orderPage.setName("Test");
        orderPage.setCard("Test");
        logger.info("Clicking the 'Purchase' button to submit the order with missing details");
        orderPage.clickPurchaseButton();

        // Wait for the confirmation modal and verify that it's not visible due to missing details
        logger.info("Waiting for the confirmation modal to appear (should not be visible)");
        orderPage.waitForElementToBeVisible(confirmPage.getConfirmModalLocator(), 20);
        logger.info("Verifying that the confirmation modal is not visible due to missing details");
        Assert.assertFalse(confirmPage.isconfirmModalVisible(), "Confirm Modal is not visible!");

        logger.info("Test verify_order_placement_with_missing_details completed successfully");
    }

}
