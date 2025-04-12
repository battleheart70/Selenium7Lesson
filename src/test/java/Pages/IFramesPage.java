package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IFramesPage extends BasePage {
  private final By iFrame = By.id("my-iframe");
  private final By paragraphElement = By.cssSelector("p.lead");

  public IFramesPage(WebDriver driver) {
    super(driver);
  }

  @Step("Подождать пока p.lead станет доступен")
  public WebElement getFirstParagraph() {
    return getElementByLocator(paragraphElement);
  }

  @Step("Переключится на iFrame")
  public void switchToIframe() {
    driver.switchTo().frame(getElementByLocator(iFrame));
  }

  @Step("Переключится в Default Content")
  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }
}
