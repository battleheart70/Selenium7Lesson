package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

class InfiniteScrollTests extends BaseTest{

 
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
