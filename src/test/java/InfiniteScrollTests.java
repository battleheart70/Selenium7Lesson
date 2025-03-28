import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InfiniteScrollTests {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    driver.get(BASE_URL);
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }

  @Test
  @DisplayName("Проверка бесконечного скролла")
  void infiniteScrollTest() {
    driver.findElement(By.linkText("Infinite scroll")).click();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String paragraphSelector = "p.lead";
    int maxScrolls = 5;

    wait.until(
        ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(paragraphSelector), 0));

    for (int i = 0; i < maxScrolls; i++) {
      int beforeScrollCount = driver.findElements(By.cssSelector(paragraphSelector)).size();
      js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
      wait.until(
          ExpectedConditions.numberOfElementsToBeMoreThan(
              By.cssSelector(paragraphSelector), beforeScrollCount));
      int afterScrollCount = driver.findElements(By.cssSelector(paragraphSelector)).size();
      assertTrue(
          afterScrollCount > beforeScrollCount,
          "Элементы больше не загружаются после " + i + " скролла");
    }
  }
}
