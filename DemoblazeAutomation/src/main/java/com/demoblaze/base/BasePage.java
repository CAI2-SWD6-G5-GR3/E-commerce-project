package com.demoblaze.base;

import graphql.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public static WebDriver driver;



    // Sets the WebDriver instance for the class
    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    // Finds a single element based on the provided locator
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    // Finds all elements that match the given locator and returns them as a list
    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }


    // Clears the text of an input field and sets new text
    public void set(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Clicks on the element identified by the given locator
    public void click(By locator) {
        find(locator).click();
    }


    // Waits for an element to become clickable within the specified timeout period
    public void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Waits for an element to be visible within the specified timeout period
    public void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Waits for an element to no longer be visible within the specified timeout period
    public void waitForElementToBeNotVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Checks if an alert is present, retrieves its text, accepts it, and returns the alert text
    public String getAlertTextIfPresentAndAccept() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;

        } catch (NoAlertPresentException e) {
            return null;
        } catch (Exception e) {
            System.out.println("Exception while handling alert: " + e.getMessage());
            return null;
        }
    }


    // Method to wait for the element count to change (decrease in this case)
    public void waitForElementCountChange(By locator, int initialCount, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(driver -> {
            int currentCount = driver.findElements(locator).size();  // Get the current count of elements
            return currentCount < initialCount;  // Wait for the count to decrease
        });
    }


    // Verifies that the alert text matches the expected text

    public void verifyAlertText(String expectedText) {
        String alertText = getAlertTextIfPresentAndAccept();
        Assert.assertNotNull(alertText, "No alert appeared.");
        Assert.assertTrue(alertText.contains(expectedText), "Unexpected alert text: " + alertText);
    }

    // Waits for an alert to appear, then accepts it within the specified timeout period

    public void waitForAlertAndAccept(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.alertIsPresent());  // Wait for the alert to appear

        Alert alert = driver.switchTo().alert();  // Switch to the alert
        alert.accept();  // Accept the alert
    }

    // Scrolls to the bottom of the page by moving the view to the footer
    public void scrollToBottom() {
        Actions actions = new Actions(driver);
        WebElement footer = driver.findElement(By.cssSelector("footer"));
        actions.moveToElement(footer).perform();  // Scroll to the footer (or bottom)
    }


}
