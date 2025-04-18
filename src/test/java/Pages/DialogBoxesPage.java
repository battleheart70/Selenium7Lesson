package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DialogBoxesPage extends BasePage {

  @FindBy(id = "my-alert")
  private WebElement alertButton;

  @FindBy(id = "my-confirm")
  private WebElement confirmButton;

  @FindBy(id = "my-prompt")
  private WebElement promptButton;

  @FindBy(id = "confirm-text")
  private WebElement confirmTextElement;

  @FindBy(id = "prompt-text")
  private WebElement promptTextElement;

  @FindBy(id = "my-modal")
  private WebElement modalTrigger;

  @FindBy(css = ".modal-header")
  private WebElement modalTitleElement;

  @FindBy(css = ".modal-body")
  private WebElement modalBodyElement;

  @FindBy(id = "modal-text")
  private WebElement modalConfirmationElement;

  public DialogBoxesPage(WebDriver driver) {
    super(driver);
  }

  @Step("Открыть всплывающее сообщение (Alert)")
  public Alert openAlert() {
    return clickAndGetAlert(alertButton);
  }

  @Step("Открыть подтверждение (Confirm)")
  public Alert openConfirm() {
    return clickAndGetAlert(confirmButton);
  }

  @Step("Открыть ввод с подсказкой (Prompt)")
  public Alert openPrompt() {
    return clickAndGetAlert(promptButton);
  }

  @Step("Нажать на кнопку, вызывающую модальное окно")
  public void clickModalTrigger() {
    wait.until(ExpectedConditions.elementToBeClickable(modalTrigger)).click();
  }

  @Step("Получить текст подтверждения в окне подтверждения")
  public WebElement getConfirmText() {
    wait.until(ExpectedConditions.visibilityOf(confirmTextElement));
    return confirmTextElement;
  }

  @Step("Получить текст подсказки в окне ввода")
  public WebElement getPromptText() {
    wait.until(ExpectedConditions.visibilityOf(promptTextElement));
    return promptTextElement;
  }

  @Step("Получить заголовок модального окна")
  public WebElement getModalTitle() {
    wait.until(ExpectedConditions.visibilityOf(modalTitleElement));
    return modalTitleElement;
  }

  @Step("Получить тело модального окна")
  public WebElement getModalBody() {
    wait.until(ExpectedConditions.visibilityOf(modalBodyElement));
    return modalBodyElement;
  }

  @Step("Получить текст подтверждения в модальном окне")
  public String getModalConfirmationText() {
    wait.until(ExpectedConditions.visibilityOf(modalConfirmationElement));
    return modalConfirmationElement.getText();
  }

  @Step("Нажать на кнопку в модальном окне с текстом '{buttonText}'")
  public void clickModalButtonByText(String buttonText) {
    By locator = By.xpath("//button[normalize-space(text())='" + buttonText + "']");
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
  }
}
