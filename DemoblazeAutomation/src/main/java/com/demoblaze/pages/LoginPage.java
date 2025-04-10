package com.demoblaze.pages;

import com.demoblaze.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Locators for elements in the Login Modal
    private final By loginUsernameInput = By.id("loginusername");
    private final By loginPasswordInput = By.id("loginpassword");
    private final By loginSubmitButton = By.cssSelector("button[onclick='logIn()'][class='btn btn-primary']");

    // Method to set the username in the login input field

    public void setLoginUsername(String username) {
        set(loginUsernameInput, username);
    }

    // Method to set the password in the login input field
    public void setLoginPassword(String password) {
        set(loginPasswordInput, password);
    }

    // Method to login by setting both username and password, then submitting the form
    public void login(String username, String password) {
        setLoginUsername(username);
        setLoginPassword(password);
        clickLoginSubmitButton();
    }

    // Method to click the login submit button
    public void clickLoginSubmitButton() {
        click(loginSubmitButton);
    }


}
