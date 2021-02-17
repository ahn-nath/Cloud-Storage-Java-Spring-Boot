package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // to define order of tests
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static WebDriver driver;
	private SignupPage signupPage;
	private LoginPage loginPage;
	private HomePage homePage;

	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		signupPage = new SignupPage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@AfterEach
	public void afterEach() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Authentication
	public void userSignUpAndLogin() {
		String username = "pzastoup";
		String password = "whatabadpassword";

		// sign up new user
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.signup("Peter", "Zastoupil", username, password);

		// log in
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.login(username, password);

	}

	// Notes
	public void createNote(String title, String description) throws InterruptedException {
		homePage.createNote(driver, title, description);
	}

	
	public void updateNote(String title, String description) throws InterruptedException {
		homePage.editNote(driver, title, description);
	}

	
	// Credentials
	private void createCredential(String url, String username, String password) throws InterruptedException {
		homePage.createCredential(driver, url, username, password);
		
	}
	
	private void updateCredential(String url, String username, String password) throws InterruptedException {
		homePage.editCredential(driver, url, username, password);
		
	}


	@Test
	@Order((1))
	public void testNullUserAcess() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/result");
		assertEquals("Login", driver.getTitle());
	}
	
	
	
	@Test
	@Order((2))
	public void testSignupLoginAndSubmit() throws InterruptedException {

		// sign up and login
		userSignUpAndLogin();
		assertEquals("Home", driver.getTitle());

		// log out user
		homePage.logOut(driver);

		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(driver -> driver.getTitle().equals("Login"));

		// go to home page
		driver.get("http://localhost:" + this.port + "/home");
		assertEquals("Login", driver.getTitle());

	}

	
	@Test
	@Order((3))
	public void testNoteCreate() throws InterruptedException {
		// sign up and login first
		userSignUpAndLogin();

		String title = "Life";
		String description = "Life is beautiful if you try harder";

		// create
		createNote(title, description);
		
		// read
		Note note = homePage.getFirstNote();
		assertEquals(title, note.getNoteTitle());
		assertEquals(description, note.getNoteDescription());
	}
	
	
	@Test
	@Order((4))
	public void testNoteUpdate() throws InterruptedException {
		// sign up and login first
		userSignUpAndLogin();

		String title = "Life [updated]";
		String description = "Life is beautiful if you try harder [updated]";

		// create
		updateNote(title, description);
		
		// read
		Note note = homePage.getFirstNote();
		assertEquals(title, note.getNoteTitle());
		assertEquals(description, note.getNoteDescription());
		
		
	}
	
	
	@Test
	@Order((5))
	public void testNoteDelete() throws InterruptedException {

		// sign up and login first
		userSignUpAndLogin();
		
		// delete
		homePage.deleteNote(driver);
	    Thread.sleep(5000);	
	   
		List<WebElement> deleteNoteButtons = driver.findElements(By.id("delete-note"));
		Assertions.assertEquals(0, deleteNoteButtons.size());
		
	}
	

	
	@Test
	@Order((6))
	public void testCredentialCreate() throws InterruptedException {
		// sign up and login first
		userSignUpAndLogin();

		String url = "http://localhost:8080/login";
		String username = "ahnath";
		String password = "12345";

		// create
		createCredential(url, username, password);
		
		// read
		Credential credential = homePage.getFirstCredential();
		assertEquals(url, credential.getUrl());
		assertEquals(username, credential.getUsername());
				
	}
	

	@Test
	@Order((7))
	public void testCredentialUpdate() throws InterruptedException {
		// sign up and login first
		userSignUpAndLogin();

		String url = "http://localhost:8080/home";
		String username = "ahnath-updated";
		String password = "123456";

		// create
		updateCredential(url, username, password);
		
		// read
		Credential credential = homePage.getFirstCredential();
		assertEquals(url, credential.getUrl());
		assertEquals(username, credential.getUsername());
	
		
		
	}
	

	@Test
	@Order((8))
	public void testCredentialDelete() throws InterruptedException {

		// sign up and login first
		userSignUpAndLogin();
		
		// delete
		homePage.deleteCredential(driver);
	    Thread.sleep(5000);	
	   
		List<WebElement> deleteCredentialButtons = driver.findElements(By.id("delete-credential"));
		Assertions.assertEquals(0, deleteCredentialButtons.size());
		
	}


}
