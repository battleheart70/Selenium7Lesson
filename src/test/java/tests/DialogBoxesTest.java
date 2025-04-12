package tests;

import static Pages.DialogBoxesPage.*;
import static org.example.Constants.LOREM_IPSUM;

import Pages.DialogBoxesPage;
import Pages.HomePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

@Epic("Dialog Boxes Tests")
class DialogBoxesTest extends BaseTest {

  DialogBoxesPage dialogBoxesPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    dialogBoxesPage = new HomePage(driver).openDialogBoxPage();
  }

  @Test
  @DisplayName("Проверить Alert")
  void checkAlertTest() {
    Alert alert = dialogBoxesPage.openAlert();
    Assertions.assertEquals(ALERT_HELLO_WORLD_TEXT, alert.getText());
    alert.accept();
  }

  @Test
  @DisplayName("Проверка аксепта Confirm'a")
  void acceptConfirmationTest() {
    Alert alert = dialogBoxesPage.openConfirm();
    Assertions.assertEquals(ALERT_CONFIRM_TEXT, alert.getText());
    alert.accept();
    Assertions.assertEquals(ALERT_CONFIRM_ACCEPT_TEXT, dialogBoxesPage.getConfirmText().getText());
  }

  @Test
  @DisplayName("Проверка кансела Confirm'a")
  void cancelConfirmationTest() {
    Alert alert = dialogBoxesPage.openConfirm();
    Assertions.assertEquals(ALERT_CONFIRM_TEXT, alert.getText());
    alert.dismiss();
    Assertions.assertEquals(ALERT_CONFIRM_CANCEL_TEXT, dialogBoxesPage.getConfirmText().getText());
  }

  @Test
  @DisplayName("Проверить текст введенный в Prompt")
  void promptTest() {
    Alert alert = dialogBoxesPage.openPrompt();
    Assertions.assertEquals(ALERT_PROMPT_TEXT, alert.getText());
    alert.sendKeys(LOREM_IPSUM);
    alert.accept();
    Assertions.assertEquals(PROMPT_TEXT_CONFIRMATION + LOREM_IPSUM, dialogBoxesPage.getPromptText().getText());
  }

  @Test
  @DisplayName("Проверить отмену ввода в Prompt")
  void cancelPromptTest() {
    Alert alert = dialogBoxesPage.openPrompt();
    Assertions.assertEquals(ALERT_PROMPT_TEXT, alert.getText());
    alert.sendKeys(LOREM_IPSUM);
    alert.dismiss();
    Assertions.assertEquals(PROMPT_TEXT_CONFIRMATION + "null", dialogBoxesPage.getPromptText().getText());
  }

  @Test
  @DisplayName("Проверить пустой ввод в Prompt")
  void nullPromptTest() {
    Alert alert = dialogBoxesPage.openPrompt();
    Assertions.assertEquals(ALERT_PROMPT_TEXT, alert.getText());
    alert.accept();
    Assertions.assertEquals(PROMPT_TEXT_CONFIRMATION.trim(), dialogBoxesPage.getPromptText().getText());
  }

  @Test
  @DisplayName("Проверить обычную модалку (не Алерт)")
  void modalWindowTest() {
    dialogBoxesPage.clickModalTrigger();
    WebElement body = dialogBoxesPage.getModalBody();
    WebElement title = dialogBoxesPage.getModalTitle();
    Assertions.assertTrue(body.isDisplayed());
    Assertions.assertTrue(title.isDisplayed());
    Assertions.assertEquals(MODAL_BODY, body.getText());
    Assertions.assertEquals(MODAL_TITLE, title.getText());
  }

  @Test
  @DisplayName("Проверить SaveChanges обычной модалки (не Алерт)")
  void modalWindowSaveChangesTest() {
    dialogBoxesPage.clickModalTrigger();
    dialogBoxesPage.clickModalButtonByText(MODAL_SAVE_CHANGES);
    Assertions.assertEquals(MODAL_CONFIRMATION_TEXT + MODAL_SAVE_CHANGES,
            dialogBoxesPage.getModalConfirmationText());
  }

  @Test
  @DisplayName("Проверить Close обычной модалки (не Алерт)")
  void modalWindowCloseTest() {
    dialogBoxesPage.clickModalTrigger();
    dialogBoxesPage.clickModalButtonByText(MODAL_CLOSE);
    Assertions.assertEquals(MODAL_CONFIRMATION_TEXT + MODAL_CLOSE,
            dialogBoxesPage.getModalConfirmationText());
  }
}
