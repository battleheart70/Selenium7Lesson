package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InfiniteScrollPage extends BasePage {
  private final By paragraph = By.cssSelector("p.lead");

  public InfiniteScrollPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ожидание загрузки параграфов")
  public void waitUntilParagraphsLoad() {
    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(paragraph, 0));
  }

  @Step("Ожидание загрузки более {numberOfParagraphs} параграфов")
  public void waitUntilParagraphsLoad(int numberOfParagraphs) {
    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(paragraph, numberOfParagraphs));
  }

  @Step("Прокрутить страницу {n} раз и проверить загрузку новых элементов")
  public void assertScrollForNTimes(int n, JavascriptExecutor js) {
    for (int i = 0; i < n; i++) {
      int beforeScrollCount = driver.findElements(paragraph).size();
      js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
      waitUntilParagraphsLoad(beforeScrollCount);
      int afterScrollCount = driver.findElements(paragraph).size();
      assertTrue(
              afterScrollCount > beforeScrollCount,
              "Элементы больше не загружаются после " + i + " скролла");
    }
  }
}
