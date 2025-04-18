package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfiniteScrollPage extends BasePage {


  @FindBy(css = "p.lead")
  private List<WebElement> paragraphs;

  public InfiniteScrollPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ожидать загрузки хотя бы одного параграфа")
  public void waitUntilParagraphsLoad() {
    wait.until(driver -> !paragraphs.isEmpty());
  }

  @Step("Ожидать загрузки более {numberOfParagraphs} параграфов")
  public void waitUntilParagraphsLoad(int numberOfParagraphs) {
    wait.until(driver -> paragraphs.size() > numberOfParagraphs);
  }

  @Step("Прокрутить страницу {n} раз и проверить загрузку новых элементов")
  public void assertScrollForNTimes(int n, JavascriptExecutor js) {
    for (int i = 0; i < n; i++) {
      int beforeScrollCount = paragraphs.size();
      js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
      waitUntilParagraphsLoad(beforeScrollCount);
      int afterScrollCount = paragraphs.size();
      assertTrue(
              afterScrollCount > beforeScrollCount,
              "Элементы больше не загружаются после " + (i+1) + " скролла"
      );
    }
  }
}
