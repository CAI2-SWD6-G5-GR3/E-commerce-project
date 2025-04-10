package com.demoblaze.utils;
import java.util.Random;
import com.demoblaze.base.BasePage ;
import org.openqa.selenium.WebDriver;

public class Utility {

    public static WebDriver driver;

    public static void setUtilityDriver() {
        driver = BasePage.driver;
    }

    // Method to generate a random number
    public static int generateRandomNumber(int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound); // Generates a random number between 0 and upperBound
    }

    // Method to generate a random username
    public static String generateRandomUsername() {
        return "depiuser" + generateRandomNumber(10000); // Generate a random username like depiuser1234
    }

    // Method to generate a random password
    public static String generateRandomPassword() {
        return "depiuser" + generateRandomNumber(10000); // Generate a random password like depiuser5678
    }



}