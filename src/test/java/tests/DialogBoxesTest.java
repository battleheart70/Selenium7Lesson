package tests;

import static org.example.Constants.LOREM_IPSUM;

import Pages.DialogBoxesPage;
import Pages.HomePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты диалоговых окон")
class DialogBoxesTest extends BaseTest {
  private DialogBoxesPage dialogBoxesPage;

  // moved from Page Object into test
  private static final String ALERT_HELLO_WORLD_TEXT    = "Hello world!";
  private static final String ALERT_CONFIRM_TEXT        = "Is this correct?";
  private static final String ALERT_CONFIRM_ACCEPT_TEXT = "You chose: true";
  private static final String ALERT_CONFIRM_CANCEL_TEXT = "You chose: false";
  private static final String ALERT_PROMPT_TEXT         = "Please enter your name";
  private static final String PROMPT_TEXT_CONFIRMATION  = "You typed: ";
  private static final String MODAL_BODY                = "This is the modal body";
  private static final String MODAL_TITLE               = "Modal title";
  private static final String MODAL_CONFIRMATION_TEXT   = "You chose: ";
  private static final String MODAL_SAVE_CHANGES        = "Save changes";
  private static final String MODAL_CLOSE               = "Close";

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    dialogBoxesPage = new HomePage(driver).openDialogBoxesPage();
  }

  @Test
  @DisplayName("Текст Alert должен быть корректным")
  void alertTextShouldBeCorrect() {
    Alert alert = dialogBoxesPage.openAlert();
    assertEquals(ALERT_HELLO_WORLD_TEXT, alert.getText());
    alert.accept();
  }

  @Test
  @DisplayName("Текст Confirm должен быть корректным")
  void confirmAlertTextShouldBeCorrect() {
    Alert alert = dialogBoxesPage.openConfirm();
    assertEquals(ALERT_CONFIRM_TEXT, alert.getText());
    alert.accept();
  }

  @Test
  @DisplayName("При подтверждении Confirm отображается правильный текст")
  void confirmAcceptShouldProduceCorrectText() {
    Alert alert = dialogBoxesPage.openConfirm();
    alert.accept();
    assertEquals(
            ALERT_CONFIRM_ACCEPT_TEXT,
            dialogBoxesPage.getConfirmText().getText()
    );
  }

  @Test
  @DisplayName("При отмене Confirm отображается правильный текст")
  void confirmDismissShouldProduceCorrectText() {
    Alert alert = dialogBoxesPage.openConfirm();
    alert.dismiss();
    assertEquals(
            ALERT_CONFIRM_CANCEL_TEXT,
            dialogBoxesPage.getConfirmText().getText()
    );
  }

  @Test
  @DisplayName("Текст Prompt должен быть корректным")
  void promptAlertTextShouldBeCorrect() {
    Alert alert = dialogBoxesPage.openPrompt();
    assertEquals(ALERT_PROMPT_TEXT, alert.getText());
    alert.accept();
  }

  @Test
  @DisplayName("При вводе в Prompt отображается правильный текст подтверждения")
  void promptSendKeysShouldProduceCorrectConfirmationText() {
    Alert alert = dialogBoxesPage.openPrompt();
    alert.sendKeys(LOREM_IPSUM);
    alert.accept();
    assertEquals(
            PROMPT_TEXT_CONFIRMATION + LOREM_IPSUM,
            dialogBoxesPage.getPromptText().getText()
    );
  }

  @Test
  @DisplayName("При отмене Prompt отображается текст подтверждения 'null'")
  void promptDismissShouldProduceNullConfirmationText() {
    Alert alert = dialogBoxesPage.openPrompt();
    alert.sendKeys(LOREM_IPSUM);
    alert.dismiss();
    assertEquals(
            PROMPT_TEXT_CONFIRMATION + "null",
            dialogBoxesPage.getPromptText().getText()
    );
  }

  @Test
  @DisplayName("При подтверждении Prompt без ввода отображается пустой текст подтверждения")
  void promptAcceptWithoutInputShouldProduceEmptyConfirmationText() {
    Alert alert = dialogBoxesPage.openPrompt();
    alert.accept();
    assertEquals(
            PROMPT_TEXT_CONFIRMATION.trim(),
            dialogBoxesPage.getPromptText().getText()
    );
  }

  @Test
  @DisplayName("Проверка тела модального окна: отображается и текст корректен")
  void modalBodyDisplayAndTextShouldBeCorrect() {
    dialogBoxesPage.clickModalTrigger();
    WebElement body = dialogBoxesPage.getModalBody();

    assertAll("Проверка body",
            () -> assertTrue(body.isDisplayed(), "Тело модального окна не отображается"),
            () -> assertEquals(MODAL_BODY, body.getText(), "Текст тела модального окна неверен")
    );
  }

  @Test
  @DisplayName("Проверка заголовка модального окна: отображается и текст корректен")
  void modalTitleDisplayAndTextShouldBeCorrect() {
    dialogBoxesPage.clickModalTrigger();
    WebElement title = dialogBoxesPage.getModalTitle();

    assertAll("Проверка заголовка",
            () -> assertTrue(title.isDisplayed(), "Заголовок модального окна не отображается"),
            () -> assertEquals(MODAL_TITLE, title.getText(), "Текст заголовка модального окна неверен")
    );
  }

  @Test
  @DisplayName("При нажатии 'Save changes' отображается правильный текст подтверждения")
  void modalSaveChangesShouldProduceCorrectConfirmation() {
    dialogBoxesPage.clickModalTrigger();
    dialogBoxesPage.clickModalButtonByText(MODAL_SAVE_CHANGES);
    assertEquals(
            MODAL_CONFIRMATION_TEXT + MODAL_SAVE_CHANGES,
            dialogBoxesPage.getModalConfirmationText()
    );
  }

  @Test
  @DisplayName("При нажатии 'Close' отображается правильный текст подтверждения")
  void modalCloseShouldProduceCorrectConfirmation() {
    dialogBoxesPage.clickModalTrigger();
    dialogBoxesPage.clickModalButtonByText(MODAL_CLOSE);
    assertEquals(
            MODAL_CONFIRMATION_TEXT + MODAL_CLOSE,
            dialogBoxesPage.getModalConfirmationText()
    );
  }
}
