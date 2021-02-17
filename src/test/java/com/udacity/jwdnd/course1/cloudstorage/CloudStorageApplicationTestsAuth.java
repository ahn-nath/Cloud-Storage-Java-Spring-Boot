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
class CloudStorageApplicationTestsAuth {

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

}
