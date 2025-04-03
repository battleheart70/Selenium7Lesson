import config.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShadowDOMTests {
  private WebDriver driver;
  private WebDriverWait wait;
  TestPropertiesConfig config =
      ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    driver.get(config.getBaseUrl());
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }

  @Test
  @DisplayName("Shadow DOM Тест")
  void shadowDOMTest() {
    driver.findElement(By.linkText("Shadow DOM")).click();
    WebElement shadowElement = getContentElement().getShadowRoot().findElement(By.cssSelector("p"));
    assertEquals("Hello Shadow DOM", shadowElement.getText(), "Текст внутри Shadow DOM неверен!");
  }

  @Test
  @DisplayName("Исключение при отсутствии getShadowRoot()")
  void shadowDOMThrowsExceptionTest() {
    driver.findElement(By.linkText("Shadow DOM")).click();
    Assertions.assertThrows(
        NoSuchElementException.class, () -> getContentElement().findElement(By.cssSelector("p")));
  }

  private WebElement getContentElement() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
  }
}
