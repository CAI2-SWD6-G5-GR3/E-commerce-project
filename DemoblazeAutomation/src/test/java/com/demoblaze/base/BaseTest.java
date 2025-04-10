
package com.demoblaze.base;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import com.demoblaze.pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import com.demoblaze.pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;


public class BaseTest {

    public WebDriver driver; // WebDriver instance for controlling the browser
    protected BasePage basePage; // Instance of the BasePage to access common page methods
    protected HomePage homePage; // Instance of the HomePage to access Home page methods
    private String DEMO_URL = "https://www.demoblaze.com/";  // URL of the demo application

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(@Optional("chrome") String browser) {  // Default to chrome if not specified
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.setPlatformName("Windows 10");  // Optional, but now matches node exactly
                System.out.println("Connecting to Chrome on Selenium Grid...");
                driver = new RemoteWebDriver(new URL("http://192.168.1.26:4444/wd/hub"), options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.setPlatformName("Windows 10");
                System.out.println("Connecting to Firefox on Selenium Grid...");
                driver = new RemoteWebDriver(new URL("http://192.168.1.26:4444/wd/hub"), options);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("RemoteWebDriver is ready for " + browser);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    // Before Every Test - load the application and initialize page objects before each test method
    @BeforeMethod
    public void loadApplication() {
        driver.manage().deleteAllCookies(); // Clear cookies to avoid interference between tests
        driver.get(DEMO_URL);  // Navigate to the demo application URL
        basePage = new BasePage(); // Initialize the BasePage object
        basePage.setDriver(driver);  // Set the WebDriver for the BasePage

        homePage = new HomePage(); // Initialize the HomePage object

    }

    // Once After All Tests - set up the WebDriver and initialize browser settings before running the tests
    @AfterClass
    public void tearDown() {
        driver.quit(); // Close the browser and quit the WebDriver session
    }


}