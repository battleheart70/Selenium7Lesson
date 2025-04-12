package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DialogBoxesPage extends BasePage {

  public static final String ALERT_CONFIRM_TEXT = "Is this correct?";
  public static final String ALERT_HELLO_WORLD_TEXT = "Hello world!";
  public static final String ALERT_CONFIRM_ACCEPT_TEXT = "You chose: true";
  public static final String ALERT_CONFIRM_CANCEL_TEXT = "You chose: false";
  public static final String ALERT_PROMPT_TEXT = "Please enter your name";
  public static final String PROMPT_TEXT_CONFIRMATION = "You typed: ";
  public static final String MODAL_TITLE = "Modal title";
  public static final String MODAL_BODY = "This is the modal body";
  public static final String MODAL_CONFIRMATION_TEXT = "You chose: ";
  public static final String MODAL_SAVE_CHANGES = "Save changes";
  public static final String MODAL_CLOSE = "Close";

  private final By confirmText = By.id("confirm-text");
  private final By promptText = By.id("prompt-text");
  private final By modalTrigger = By.id("my-modal");
  private final By modalBody = By.cssSelector(".modal-body");
  private final By modalTitle = By.cssSelector(".modal-header");
  private final By modalConfirmation = By.id("modal-text");
  private final By alertButton = By.id("my-alert");
  private final By confirmButton = By.id("my-confirm");
  private final By promptButton = By.id("my-prompt");

  public DialogBoxesPage(WebDriver driver) {
    super(driver);
  }

  @Step("Открыть всплывающее сообщение (Alert)")
  public Alert openAlert() {
    return getAlert(alertButton);
  }

  @Step("Открыть подтверждение (Confirm)")
  public Alert openConfirm() {
    return getAlert(confirmButton);
  }

  @Step("Открыть ввод с подсказкой (Prompt)")
  public Alert openPrompt() {
    return getAlert(promptButton);
  }

  @Step("Нажать на кнопку, вызывающую модальное окно")
  public void clickModalTrigger() {
    getElementByLocator(modalTrigger).click();
  }

  @Step("Получить текст подтверждения в окне подтверждения")
  public WebElement getConfirmText() {
    return getElementByLocator(confirmText);
  }

  @Step("Получить текст подсказки в окне ввода")
  public WebElement getPromptText() {
    return getElementByLocator(promptText);
  }

  @Step("Получить текст модального окна")
  public WebElement getModalBody() {
    return getElementByLocator(modalBody);
  }

  @Step("Получить заголовок модального окна")
  public WebElement getModalTitle() {
    return getElementByLocator(modalTitle);
  }

  @Step("Получить текст подтверждения в модальном окне")
  public String getModalConfirmationText() {
    return getElementByLocator(modalConfirmation).getText();
  }

  @Step("Нажать на кнопку в модальном окне с текстом '{buttonText}'")
  public void clickModalButtonByText(String buttonText) {
    getElementByLocator(By.xpath("//button[normalize-space(text()) = '" + buttonText + "']"))
            .click();
  }
}
