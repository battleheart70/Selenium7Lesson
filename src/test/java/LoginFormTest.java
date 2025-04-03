import config.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class LoginFormTest {
  private WebDriver driver;
  private WebDriverWait wait;
  TestPropertiesConfig config =
      ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    driver.manage().window().maximize();
    driver.get(config.getBaseUrl());
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }

  @Test
  @DisplayName("Successfully login")
  void successLoginTest() {
    goToLoginForm();
    populateUsername(config.getUsername());
    populatePassword(config.getPassword());
    pressSubmitButton();

    WebElement successAlert =
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));

    Assertions.assertEquals("Login successful", successAlert.getText());
  }

  @Test
  @DisplayName("invalid credentials test")
  void invalidCredentialsTest() {
    goToLoginForm();
    populateUsername("invalidLogin");
    populatePassword("invalidPass");
    pressSubmitButton();

    WebElement dangerAlert =
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("invalid")));

    Assertions.assertEquals("Invalid credentials", dangerAlert.getText());
  }

  private void goToLoginForm() {
    driver.findElement(By.linkText("Login form")).click();
  }

  private void populateUsername(String username) {
    driver.findElement(By.id("username")).sendKeys(username);
  }

  private void populatePassword(String password) {
    driver.findElement(By.id("password")).sendKeys(password);
  }

  private void pressSubmitButton() {
    driver.findElement(By.cssSelector("button[type='submit']")).click();
  }
}
