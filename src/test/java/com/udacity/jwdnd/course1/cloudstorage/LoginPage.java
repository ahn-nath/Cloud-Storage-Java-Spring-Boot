package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	// Defining element selectors
	@FindBy(id = "inputUsername")
	private WebElement usernameInput;

	@FindBy(id = "inputPassword")
	private WebElement passwordInput;

	@FindBy(id = "submit-button")
	private WebElement submitButton;

	// Initializing elements in the constructor
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void login(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submitButton.click();

	}

}
