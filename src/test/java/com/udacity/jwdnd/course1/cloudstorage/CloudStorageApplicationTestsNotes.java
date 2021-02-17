package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // to define order of tests
class CloudStorageApplicationTestsNotes {

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

	
	public void updateNote(String title, String description, boolean clear) throws InterruptedException {
		homePage.editNote(driver, title, description, clear);
	}

	
	@Test
	@Order((1))
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
	@Order((2))
	public void testNoteUpdate() throws InterruptedException {
		// sign up and login first
		userSignUpAndLogin();

		String title = "Life [updated]";
		String description = "Life is beautiful if you try harder [updated]";

		// update
		updateNote(" [updated]", " [updated]", false);
		
		// read
		Note note = homePage.getFirstNote();
		assertEquals(title, note.getNoteTitle());
		assertEquals(description, note.getNoteDescription());
		
		
	}
	
	
	@Test
	@Order((3))
	public void testNoteDelete() throws InterruptedException {

		// sign up and login first
		userSignUpAndLogin();
		
		// delete
		homePage.deleteNote(driver);
	    Thread.sleep(5000);	
	   
		List<WebElement> deleteNoteButtons = driver.findElements(By.id("delete-note"));
		Assertions.assertEquals(0, deleteNoteButtons.size());
		
	}
	

}
