package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDOMPage extends BasePage {
  private final By contentElement = By.id("content");
  private final By paragraph = By.cssSelector("p");

  public ShadowDOMPage(WebDriver driver) {
    super(driver);
  }

  @Step("Получить элемент с id 'content'")
  public WebElement getContentElement() {
    return getElementByLocator(contentElement);
  }

  @Step("Получить параграф внутри Shadow DOM")
  public WebElement getParagraphInsideShadowRoot() {
    WebElement shadowHost = getContentElement();
    SearchContext shadowRoot = shadowHost.getShadowRoot();
    return shadowRoot.findElement(paragraph);
  }

  @Step("Получить параграф вне Shadow DOM")
  public WebElement getParagraphOutsideShadowRoot() {
    WebElement shadowHost = getContentElement();
    return shadowHost.findElement(paragraph);
  }
}
