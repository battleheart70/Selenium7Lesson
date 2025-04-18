package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Страница со Shadow DOM элементами.
 */
public class ShadowDOMPage extends BasePage {

  @FindBy(id = "content")
  private WebElement contentElement;

  private static final By PARAGRAPH = By.cssSelector("p");

  public ShadowDOMPage(WebDriver driver) {
    super(driver);
  }

  @Step("Получить контейнер Shadow DOM")
  public WebElement getContentElement() {
    wait.until(ExpectedConditions.visibilityOf(contentElement));
    return contentElement;
  }

  @Step("Получить текст параграфа внутри Shadow DOM")
  public String getParagraphInsideShadowRootText() {
    WebElement host = getContentElement();
    SearchContext shadowRoot = host.getShadowRoot();
    WebElement paragraph = shadowRoot.findElement(PARAGRAPH);
    wait.until(ExpectedConditions.visibilityOf(paragraph));
    return paragraph.getText();
  }

  @Step("Получить текст параграфа вне Shadow DOM")
  public String getParagraphOutsideShadowRootText() {
    WebElement host = getContentElement();
    WebElement paragraph = host.findElement(PARAGRAPH);
    wait.until(ExpectedConditions.visibilityOf(paragraph));
    return paragraph.getText();
  }
}
