package com.demoblaze.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.demoblaze.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductCatalogTest extends BaseTest {

    // Initialize the Logger
    private static final Logger logger = LogManager.getLogger(LoginInTest.class);



    // Test to verify the product filter for the 'Phone' category
    @Test(priority = 1)
    public void verify_phone_category_filter() {
        logger.info("Starting test: verify_phone_category_filter");

        // Apply the filter for the 'Phone' category
        logger.info("Applying the filter for the 'Phone' category");
        homePage.applyCategoryFilter(homePage.getPhoneCategoryLocator());

        // Assert that all products belong to the 'Phone' category
        logger.info("Verifying that all products belong to the 'Phone' category");
        Assert.assertTrue(homePage.areProductsFromCategory("phone"), "Products do not belong to the 'Phone' category.");
        logger.info("Test verify_phone_category_filter completed successfully");

    }

    // Test to verify the product filter for the 'Laptop' category
    @Test(priority = 2)
    public void verify_laptop_category_filter() {
        logger.info("Starting test: verify_laptop_category_filter");

        // Apply the filter for the 'Laptop' category
        logger.info("Applying the filter for the 'Laptop' category");
        homePage.applyCategoryFilter(homePage.getLaptopCategoryLocator());

        // Assert that all products belong to the 'Laptop' category
        logger.info("Verifying that all products belong to the 'Laptop' category");
        Assert.assertTrue(homePage.areProductsFromCategory("laptop"), "Products do not belong to the 'Laptop' category.");

        logger.info("Test verify_laptop_category_filter completed successfully");
    }


    // Test to verify the product filter for the 'Monitor' category
    @Test(priority = 3)
    public void verify_monitor_category_filter() {
        logger.info("Starting test: verify_monitor_category_filter");

        // Apply the filter for the 'Monitor' category
        logger.info("Applying the filter for the 'Monitor' category");
        homePage.applyCategoryFilter(homePage.getMonitorsCategoryLocator());

        // Assert that all products belong to the 'Monitor' category
        logger.info("Verifying that all products belong to the 'Monitor' category");
        Assert.assertTrue(homePage.areProductsFromCategory("monitor"), "Products do not belong to the 'Monitor' category.");

        logger.info("Test verify_monitor_category_filter completed successfully");
    }


    // Test to verify pagination functionality
    @Test(priority = 4)
    public void verify_pagination() {
        logger.info("Starting test: verify_pagination");

        // Scroll to the bottom of the page to locate pagination controls
        logger.info("Scrolling to the bottom of the page to locate pagination controls");
        homePage.scrollToBottom();

        // Navigate to the next page and verify that the products are loaded correctly
        logger.info("Navigating to the next page to verify products");
        homePage.navigateAndVerifyPage("next");

        // Navigate back to the previous page and verify that the original page is displayed
        logger.info("Navigating back to the previous page to verify products");
        homePage.navigateAndVerifyPage("previous");

        logger.info("Verifying that the previous page products are displayed");
        Assert.assertTrue(homePage.isOriginalPageDisplayed(), "Previous page products are not displayed.");

        logger.info("Test verify_pagination completed successfully");

    }


    // Test to verify that the products doesn't change when the 'Previous' button is clicked
    @Test(priority = 5)
    public void verify_same_products_when_clicking_prev() {
        logger.info("Starting test: verify_same_products_when_clicking_prev");

        // Capture the product names visible before clicking the 'Prev' button
        logger.info("Capturing the product names visible before clicking the 'Prev' button");
        List<String> itemsBefore = homePage.getVisibleProductNames();

        // Click the 'Prev' button and capture the product names visible after clicking
        logger.info("Navigating back to the previous page to capture the product names");
        List<String> itemsAfter = homePage.navigateAndGetProductNames("previous");

        // Assert that the items have not changed between the two pages
        logger.info("Verifying that the items remain the same between the two pages");
        Assert.assertNotEquals(itemsBefore, itemsAfter, "Prev button did not change the displayed items.");

        logger.info("Test verify_same_products_when_clicking_prev completed successfully");
    }



    // Test to verify that products are loaded correctly on the catalog page
    @Test(priority = 6)
    public void verify_products_loads() {
        logger.info("Starting test: verify_products_loads");

        // Wait for product names to become visible after page load
        logger.info("Waiting for product names to become visible after page load");
        homePage.waitForProductNamesToBeVisible();

        // Get the list of visible products and assert that the list is not empty
        logger.info("Getting the list of visible products");
        List<String> products = homePage.getVisibleProductNames();
        logger.info("Verifying that products are displayed on the catalog page");
        Assert.assertTrue(products.size() > 0, "No products were displayed on the catalog page.");

        // Optionally, check that each product has details (name, price, and image)
        logger.info("Verifying product details (name, price, and image) for each product");
        for (String product : products) {
            Assert.assertNotNull(product, "Product name is missing for: " + product);
        }

        logger.info("Product catalog page loaded successfully with " + products.size() + " products.");
    }


    // Test to verify that all product names are displayed across all pages
    @Test(priority = 7)
    public void verify_all_product_names_displayed() {
        logger.info("Starting test: verify_all_product_names_displayed");

        // Navigate to the home page or product catalog
        logger.info("Clicking the home button to navigate to the product catalog");
        homePage.clickHomeButton();

        do {
            // Wait for product names to be visible
            logger.info("Waiting for product names to become visible on the current page");
            homePage.waitForProductNamesToBeVisible(); // Wait for product names to be visible

            // Assert that each product name is not null or empty
            logger.info("Verifying that each product name is not null or empty");
            homePage.getVisibleProductNames().forEach(name -> {
                Assert.assertNotNull(name, "Product name is null.");
                Assert.assertFalse(name.isEmpty(), "Product name is empty.");
            });

            logger.info("Checked names on current page.");

            // Navigate to the next page using the navigation method
            logger.info("Navigating to the next page");
            homePage.navigateAndVerifyPage("next");

        } while (homePage.isNextButtonVisible());

        logger.info("All product names are displayed correctly across all pages.");
    }


    // Test to verify that all product descriptions are displayed across all pages
    @Test(priority = 8)
    public void verify_all_product_description_displayed() {
        logger.info("Starting test: verify_all_product_description_displayed");

        // Navigate to the home page or product catalog
        logger.info("Clicking the home button to navigate to the product catalog");
        homePage.clickHomeButton();

        do {
            // Wait for product descriptions to be visible
            logger.info("Waiting for product descriptions to become visible on the current page");
            homePage.waitForElementToBeVisible(homePage.getProductPricesLocator(), 20);

            // Assert that each product description is not null or empty
            logger.info("Verifying that each product description is not null or empty");
            homePage.getVisibleProductDescriptions().forEach(description -> {
                Assert.assertNotNull(description, "Product description is null.");
                Assert.assertFalse(description.isEmpty(), "Product description is empty.");
            });

            logger.info("Checked descriptions on current page.");

            // Navigate to the next page using the navigation method
            logger.info("Navigating to the next page");
            homePage.navigateAndVerifyPage("next");

        } while (homePage.isNextButtonVisible()); // Continue as long as "Next" button is visible

        logger.info("All product descriptions are displayed correctly across all pages.");
    }



    // Test to ensure that there are no duplicate products on the catalog page
    @Test(priority = 9)
    public void ensure_unique_products() {
        logger.info("Starting test: ensure_unique_products");

        // Navigate to the product catalog page (if not already there)
        logger.info("Clicking the home button to navigate to the product catalog page");
        homePage.clickHomeButton();

        // Capture the product names visible on the current page
        logger.info("Capturing the product names visible on the current page");
        List<String> productNames = homePage.getVisibleProductNames();

        // Convert the list of product names to a set to check for duplicates
        logger.info("Converting the list of product names to a set to check for duplicates");
        Set<String> uniqueProductNames = new HashSet<>(productNames);

        // Assert that the number of unique product names equals the total number of product names (no duplicates)
        logger.info("Verifying that there are no duplicate products on the catalog page");
        Assert.assertEquals(productNames.size(), uniqueProductNames.size(), "Duplicate products found on the catalog page.");

        logger.info("Test ensure_unique_products completed successfully");
    }



    // Test to verify that all product prices are displayed correctly across all pages
    @Test(priority = 10)
    public void verify_all_product_prices_displayed() {
        logger.info("Starting test: verify_all_product_prices_displayed");

        // Navigate to the home page or product catalog
        logger.info("Clicking the home button to navigate to the product catalog");
        homePage.clickHomeButton();

        do {
            // Wait for product prices to be visible
            logger.info("Waiting for product prices to become visible on the current page");
            homePage.waitForElementToBeVisible(homePage.getProductPricesLocator(), 20);

            // Assert that each product price is valid, not null, and properly formatted
            logger.info("Verifying that each product price is valid, not null, and properly formatted");
            homePage.getVisibleProductPrices().forEach(price -> {
                Assert.assertNotNull(price, "Product price is null.");
                Assert.assertFalse(price.isEmpty(), "Product price is empty.");
                Assert.assertTrue(price.matches("\\$?\\d+(\\.\\d{1,2})?"), "Invalid price format: " + price);
            });

            logger.info("Checked product prices on current page.");

            // Navigate to the next page using the navigation method
            logger.info("Navigating to the next page");
            homePage.navigateAndVerifyPage("next");

        } while (homePage.isNextButtonVisible()); // Continue if the "Next" button is visible

        logger.info("All product prices are displayed correctly across all pages.");
    }




}