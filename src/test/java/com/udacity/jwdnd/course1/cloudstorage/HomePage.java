package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

public class HomePage {
	//Defining element selectors
    @FindBy(id = "logOut")
    private WebElement logOutButton;

    // Notes
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;
    
    @FindBy(id = "add-note")
    private WebElement addNoteButton;

    @FindBy(id = "delete-note")
    private WebElement deleteNoteButton;

    @FindBy(id = "edit-note")
    private WebElement editNoteButton;
    
    @FindBy(name = "noteTitle")
    private WebElement noteTitleInput;
    
    @FindBy(name = "noteDescription")
    private WebElement noteDescriptionInput;
    
    @FindBy(id = "submit-note")
    private WebElement submitNoteButton;
    
    @FindBy(id = "note-title")
    private WebElement noteTitle;
    
    @FindBy(id = "note-description")
    private WebElement noteDescription;
    
    //Credentials
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;
    
    @FindBy(id = "add-credential")
    private WebElement addCredentialButton;

    @FindBy(id = "delete-credential")
    private WebElement deleteCredentialButton;

    @FindBy(id = "edit-credential")
    private WebElement editCredentialButton;
    
    @FindBy(name = "url")
    private WebElement credentialUrlInput;
    
    @FindBy(name = "username")
    private WebElement credentialUsernameInput;
    
    @FindBy(name = "password")
    private WebElement credentialPasswordInput;
      
    @FindBy(id = "submit-credential")
    private WebElement submitCredentialButton;
    
    @FindBy(id = "credential-url")
    private WebElement credentialUrl;
    
    @FindBy(id = "credential-username")
    private WebElement credentialUsername;
    
    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    //Initializing elements in the constructor
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // creating helper methods
    public void logOut(WebDriver driver) throws InterruptedException { //log user out
    	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
	    Thread.sleep(5000);
    }
    
    
    
    //creating helper methods: create,view, edit, and delete for each case (switch)
    public void createNote(WebDriver driver, String title, String description) throws InterruptedException { //submit chat form
    	
    	// go to notes tab and open modal
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", notesTab);
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();
        Thread.sleep(5000);
    	
        noteTitleInput.sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        submitNoteButton.click();
        
        // to verify note creation
        jse.executeScript("arguments[0].click()", notesTab);
        Thread.sleep(5000);
    }
    
    
  
    public void editNote(WebDriver driver, String title, String description) throws InterruptedException { //submit chat form
    	
    	// go to notes tab and open modal
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", notesTab);
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(editNoteButton)).click();
        Thread.sleep(5000);
    	
        noteTitleInput.sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        submitNoteButton.click();
        
        // to verify note creation
        jse.executeScript("arguments[0].click()", notesTab);
        Thread.sleep(5000);
    }
    
    public Note getFirstNote() {
        Note result = new Note();
        
        result.setNoteTitle(noteTitle.getText());
        result.setNoteDescription(noteDescription.getText());
      
        return result;
    }
    
    
    public void deleteNote(WebDriver driver) throws InterruptedException{
    	// go to notes tab to delete item
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", notesTab);
    	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(deleteNoteButton)).click();
	    Thread.sleep(5000);	
    }
    

    
    //creating helper methods: create,view, edit, and delete for each case (switch)
    public void createCredential(WebDriver driver, String url, String username, String password) throws InterruptedException { //submit chat form
    	
    	// go to notes tab and open modal
     	JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(addCredentialButton)).click();
        Thread.sleep(5000);
    	
        credentialUrlInput.sendKeys(url);
        credentialUsernameInput.sendKeys(username);
        credentialPasswordInput.sendKeys(password);
        submitCredentialButton.click();
        
        // to verify note creation
        jse.executeScript("arguments[0].click()", credentialsTab);
        Thread.sleep(5000);
    }
    
    
    //creating helper methods: create,view, edit, and delete for each case (switch)
    public void editCredential(WebDriver driver, String url, String username, String password) throws InterruptedException { //submit chat form
    	
    	// go to notes tab and open modal
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(editCredentialButton)).click();
        Thread.sleep(5000);
    	
        credentialUrlInput.sendKeys(url);
        credentialUsernameInput.sendKeys(username);
        credentialPasswordInput.sendKeys(password);
        submitCredentialButton.click();
        
        // to verify note creation
        jse.executeScript("arguments[0].click()", credentialsTab);
        Thread.sleep(5000);
    }
    
    public Credential getFirstCredential() {
    	Credential result = new Credential();
        
        result.setUrl(credentialUrl.getText());
        result.setUsername(credentialUsername.getText());
        result.setPassword(credentialPassword.getText());
      
        return result;
    }
    
    
    public void deleteCredential(WebDriver driver) throws InterruptedException{
    	// go to notes tab to delete item
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", credentialsTab);
    	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(deleteCredentialButton)).click();
	    Thread.sleep(5000);	
    }
}
