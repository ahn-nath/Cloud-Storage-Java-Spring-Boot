package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

	// Defining element selectors
	@FindBy(id = "inputFirstName")
	private WebElement firstnameInput;

	@FindBy(id = "inputLastName")
	private WebElement lastnameInput;

	@FindBy(id = "inputUsername")
	private WebElement usernameInput;

	@FindBy(id = "inputPassword")
	private WebElement passwordInput;

	@FindBy(id = "submit-button")
	private WebElement submitButton;

	// Initializing elements in the constructor
	public SignupPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// creating helper methods
	public void signup(String firstname, String lastname, String username, String password) {
		firstnameInput.sendKeys(firstname);
		lastnameInput.sendKeys(lastname);
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submitButton.click();
	}

}
