package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IFramesPage extends BasePage {

  @FindBy(id = "my-iframe")
  private WebElement iframe;

  @FindBy(css = "p.lead")
  private WebElement paragraphElement;

  public IFramesPage(WebDriver driver) {
    super(driver);
  }

  @Step("Переключиться на iFrame")
  public void switchToIframe() {
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
  }

  @Step("Переключиться обратно в основной контент")
  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }

  @Step("Получить первый параграф внутри iFrame")
  public String getFirstParagraphText() {
    wait.until(ExpectedConditions.visibilityOf(paragraphElement));
    return paragraphElement.getText();
  }
}
