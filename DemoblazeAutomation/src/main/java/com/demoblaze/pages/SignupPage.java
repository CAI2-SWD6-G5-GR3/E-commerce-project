package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignupPage extends BasePage {

    // Locators for elements in the Signup Modal
    private final By signupUsernameInput = By.id("sign-username");
    private final By signupPasswordInput = By.id("sign-password");
    private final By signupSubmitButton = By.cssSelector("button[onclick='register()'][class='btn btn-primary']");


    // Method to get the username input value
    public String getSignupUsernameInput() {
        WebElement usernameInput = find(signupUsernameInput);
        return usernameInput.getAttribute("value");  // Get the value of the username input field
    }

    // Method to get the password input value
    public String getSignupPasswordInput() {
        WebElement passwordInput = find(signupPasswordInput);
        return passwordInput.getAttribute("value");  // Get the value of the password input field
    }

    // Method to set the username input field with a provided username
    public void setSignupUsername(String username) {
        set(signupUsernameInput, username);
    }

    // Method to set the password input field with a provided password
    public void setSignupPassword(String password) {
        set(signupPasswordInput, password);
    }

    // Method to click the submit button to register the user
    public void clickSignupSubmitButton() {
        click(signupSubmitButton);
    }

    // Method to perform the signup process by providing both username and password
    public void signup(String username, String password) {
        setSignupUsername(username);
        setSignupPassword(password);
        clickSignupSubmitButton();
    }

}
