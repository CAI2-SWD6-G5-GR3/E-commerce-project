package com.demoblaze.pages;

import org.openqa.selenium.support.ui.WebDriverWait;
import com.demoblaze.base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePage {

    // Locators for elements on the Home Page
    private  final By logo = By.id("nava");  // Locator for the website logo
    private  final By homeButton = By.cssSelector("a.nav-link[href='index.html']");  // Locator for the Home button
    private  final By contactButton = By.cssSelector("a.nav-link[data-target='#exampleModal']");  // Locator for the Contact button
    private  final By aboutUsButton = By.cssSelector("a.nav-link[data-target='#videoModal']");  // Locator for the About Us button
    private  final By cartButton = By.id("cartur");  // Locator for the Cart button
    private final By loginButton = By.id("login2");  // Locator for the Login button
    private final By signupButton = By.id("signin2");  // Locator for the Signup button
    private final By phoneCategory = By.xpath("/html/body/div[5]/div/div[1]/div/a[2]");  // Locator for the Phone category
    private final By laptopCategory = By.xpath("/html/body/div[5]/div/div[1]/div/a[3]");  // Locator for the Laptop category
    private final By monitorsCategory = By.xpath("/html/body/div[5]/div/div[1]/div/a[4]");  // Locator for the Monitors category
    private final By firstItem = By.cssSelector("a[href='prod.html?idp_=1']");  // Locator for the first item in products
    private final By nextButtonLocator = By.cssSelector("button.next");  // Adjust the selector as needed
    private final By productNamesLocator = By.cssSelector(".card-title .hrefch");
    private final By productDescriptionsLocator = By.cssSelector(".card-text");
    private final By productPricesLocator = By.cssSelector(".card-block > h5");
    private By firstItemImage = By.cssSelector("img.card-img-top.img-fluid");  // First item image
    private By welcomeUser = By.id("nameofuser");  // Welcome user element (username)
    private By logOutButton = By.id("logout2");  // Log Out button
    private By prevButton = By.id("prev2");  // Previous button
    private By nextButton = By.id("next2");  // Next button


    // Getter methods for locators
    public By getLoginButtonLocator() {
        return loginButton;
    }
    public By getPhoneCategoryLocator() {
        return phoneCategory;
    }
    public By getLaptopCategoryLocator() {
        return laptopCategory;
    }
    public By getMonitorsCategoryLocator() {
        return monitorsCategory;
    }
    public By getFirstItemImageLocator() {
        return firstItemImage;
    }
    public By getWelcomeUserLocator() {
        return welcomeUser;
    }
    public By getLogOutButtonLocator() {
        return logOutButton;
    }

    // Getter method for productPricesLocator
    public By getProductPricesLocator() {
        return productPricesLocator;
    }

    // Method to click on the logo
    public void clickLogo() {
        click(logo);  // Click the logo
    }

    // Method to click on the Home button
    public void clickHomeButton() {
        click(homeButton);  // Click the Home button
    }

    // Method to click on the Contact button (opens the contact modal)
    public void clickContactButton() {
        click(contactButton);  // Click the Contact button
    }

    // Method to click on the About Us button (opens the About Us modal)
    public void clickAboutUsButton() {
        click(aboutUsButton);  // Click the About Us button
    }

    // Method to click on the Cart button
    public void clickCartButton() {
        click(cartButton);  // Click the Cart button
    }

    // Method to click on the Login button (opens the Login modal)
    public void clickLoginButton() {
        click(loginButton);  // Click the Login button
    }

    // Method to click on the Signup button (opens the Signup modal)
    public void clickSignupButton() {
        click(signupButton);  // Click the Signup button
    }

    // Method to click on the first product item in the product list
    public void clickFirstItem() {
        click(firstItem);  // Click the first product (Samsung galaxy s6)
    }


    // Method to retrieve the displayed username from the "Welcome User" element
    public String getWelcomeUser() {
        return find(welcomeUser).getText();  // Get the text (username) displayed in the welcome element
    }

    // Method to click the Log Out button
    public void clickLogOutButton() {
        click(logOutButton);  // Click the Log Out button
    }

    // Method to click the "Previous" button for pagination
    public void clickPrevButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement prevBtn = wait.until(ExpectedConditions.elementToBeClickable(prevButton));  // Wait for the prev button to be clickable
        prevBtn.click();  // Click the "Previous" button
    }

    // Method to click the "Next" button for pagination
    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));  // Wait for the next button to be clickable
        nextBtn.click();  // Click the "Next" button
    }

    // Wait for the "Next" button to be clickable
    public void waitForNextButtonToBeClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));  // Wait for the "Next" button to be clickable
    }

    // Wait for the "Previous" button to be clickable
    public void waitForPrevButtonToBeClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(prevButton));  // Wait for the "Previous" button to be clickable
    }


    // Method to check if the Next button is visible on the page
    public boolean isNextButtonVisible() {
        List<WebElement> nextButton = findAll(nextButtonLocator);
        return !nextButton.isEmpty();
    }

    // Method to wait for the product names to become visible on the page
    public void waitForProductNamesToBeVisible() {
        waitForElementToBeVisible(productNamesLocator, 20); // Adjust the timeout as needed
    }

    // Method to apply a category filter by clicking on the category (Phone, Laptop, or Monitor)
    public void applyCategoryFilter(By categoryLocator) {
        waitForProductNamesToBeVisible();
        clickHomeButton(); // Navigate back to the home page (if needed)
        click(categoryLocator); // Click on the category using the provided locator
        waitForProductNamesToBeVisible();
    }

    // Method to navigate through the pages (Next or Previous) and verify the products
    public void navigateAndVerifyPage(String direction) {
        if (direction.equalsIgnoreCase("next")) {
            waitForNextButtonToBeClickable();
            clickNextButton();
        } else if (direction.equalsIgnoreCase("previous")) {
            waitForPrevButtonToBeClickable();
            clickPrevButton();
        }
        waitForProductNamesToBeVisible();
    }

    // Method to get the list of product names while navigating through the pages
    public List<String> navigateAndGetProductNames(String direction) {
        if (direction.equalsIgnoreCase("previous")) {
            clickPrevButton();
        }
        waitForProductNamesToBeVisible();
        return getVisibleProductNames();
    }

    // Method to check if the products belong to the specified category
    public boolean areProductsFromCategory(String category) {
        // Use the waitForElementToBeVisible method from BasePage
        waitForProductNamesToBeVisible();

        // Re-locate the elements inside the loop to avoid stale element references
        List<WebElement> productNames = findAll(productNamesLocator);
        List<WebElement> productDescriptions = findAll(productDescriptionsLocator);

        List<String> excludedCategories = new ArrayList<>();
        if (category.equalsIgnoreCase("phone")) {
            excludedCategories.add("laptop");
            excludedCategories.add("monitor");
        } else if (category.equalsIgnoreCase("laptop")) {
            excludedCategories.add("phone");
            excludedCategories.add("monitor");
        } else if (category.equalsIgnoreCase("monitor")) {
            excludedCategories.add("phone");
            excludedCategories.add("laptop");
        }

        // Iterate through the products and check their names and descriptions
        while (true) {
            // Re-locate the product elements in case of stale element references
            productNames = findAll(productNamesLocator);
            productDescriptions = findAll(productDescriptionsLocator);

            // Loop through the current page's products
            for (int i = 0; i < productNames.size(); i++) {
                try {
                    // Get product name and description
                    String productName = productNames.get(i).getText().toLowerCase();
                    String productDescription = productDescriptions.get(i).getText().toLowerCase();

                    // Check if the product matches the excluded categories
                    for (String excludedCategory : excludedCategories) {
                        if (productName.contains(excludedCategory) || productDescription.contains(excludedCategory)) {
                            System.out.println("Invalid product found: ");
                            System.out.println("Name: " + productName);
                            System.out.println("Description: " + productDescription);
                            return false;
                        }
                    }
                } catch (StaleElementReferenceException e) {
                    // If stale reference occurs, re-locate the elements and retry the operation
                    productNames = findAll(productNamesLocator);
                    productDescriptions = findAll(productDescriptionsLocator);
                    // Retry the current iteration
                    i--;
                }
            }

            // Check if the "Next" button is available and click it, else break the loop
            if (isNextButtonVisible()) {
                clickNextButton();  // Click the Next button
                waitForProductNamesToBeVisible();
            } else {
                break;  // No "Next" button, exit the loop
            }
        }
        return true;  // All products are valid
    }

    // Method to check if the original page is displayed
    public boolean isOriginalPageDisplayed() {
        // You can verify if the first product name matches the first product on the original page
        List<WebElement> productTitles = findAll(productNamesLocator);
        String firstProductName = productTitles.get(0).getText().toLowerCase();

        return firstProductName.equalsIgnoreCase("original product name");  // Replace with the original product name
    }


    // Method to get the updated list of visible product names
    public List<String> getVisibleProductNames() {
        waitForProductNamesToBeVisible();
        List<WebElement> products = findAll(productNamesLocator);
        List<String> productNames = new ArrayList<>();

        for (WebElement product : products) {
            productNames.add(product.getText().trim());  // Collect product names
        }
        return productNames;
    }

    // Method to get the visible product descriptions
    public List<String> getVisibleProductDescriptions() {
        List<WebElement> descriptionElements = findAll(productDescriptionsLocator);
        List<String> descriptions = new ArrayList<>();
        for (WebElement description : descriptionElements) {
            descriptions.add(description.getText().trim()); // Collect product descriptions
        }
        return descriptions;
    }

    // Method to get the visible product prices
    public List<String> getVisibleProductPrices() {
        List<WebElement> priceElements = findAll(productPricesLocator);
        List<String> prices = new ArrayList<>();
        for (WebElement price : priceElements) {
            prices.add(price.getText().trim());
        }
        return prices;
    }


    // Method to check if the first item image is visible
    public boolean isFirstItemImageVisible() {
        WebElement itemImage = driver.findElement(firstItemImage);  // Use the By selector for the first item image
        return itemImage.isDisplayed();  // Check if the element is visible
    }


}
