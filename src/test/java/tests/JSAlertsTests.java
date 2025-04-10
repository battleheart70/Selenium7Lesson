package tests;

import static org.example.Constants.LOREM_IPSUM;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

class JSAlertsTests extends BaseTest {
  private static final String ALERT_CONFIRM_TEXT = "Is this correct?";
  private static final String ALERT_HELLO_WORLD_TEXT = "Hello world!";
  private static final String ALERT_CONFIRM_ACCEPT_TEXT = "You chose: true";
  private static final String ALERT_CONFIRM_CANCEL_TEXT = "You chose: false";
  private static final String ALERT_PROMPT_TEXT = "Please enter your name";
  private static final String PROMPT_TEXT_CONFIRMATION = "You typed: ";
  private static final String MODAL_TITLE = "Modal title";
  private static final String MODAL_BODY = "This is the modal body";
  private static final String MODAL_CONFIRMATION_TEXT = "You chose: ";
  private static final String MODAL_SAVE_CHANGES = "Save changes";
  private static final String MODAL_CLOSE = "Close";
  

  @Test
  @DisplayName("Проверить Alert")
  void checkAlertTest() {
    openModalWindowPage();
    Alert alert = clickAndSwitchToAlert("my-alert");
    Assertions.assertEquals(
        ALERT_HELLO_WORLD_TEXT,
        alert.getText(),
        "Текст alert не совпадает с ожидаемым: " + ALERT_HELLO_WORLD_TEXT);
    alert.accept();
  }

  @Test
  @DisplayName("Проверка аксепта Confirm'a")
  void acceptConfirmationTest() {
    openModalWindowPage();
    Alert alert = clickAndSwitchToAlert("my-confirm");
    Assertions.assertEquals(
        ALERT_CONFIRM_TEXT,
        alert.getText(),
        "Текст Confirm не совпадает с ожидаемым: " + ALERT_CONFIRM_TEXT);

    alert.accept();
    Assertions.assertEquals(
        ALERT_CONFIRM_ACCEPT_TEXT,
        driver.findElement(By.id("confirm-text")).getText(),
        "Текст подтверждения после акцепта не совпадает с ожидаемым: " + ALERT_CONFIRM_ACCEPT_TEXT);
  }

  @Test
  @DisplayName("Проверка кансела Confirm'a")
  void cancelConfirmationTest() {
    openModalWindowPage();
    Alert alert = clickAndSwitchToAlert("my-confirm");
    Assertions.assertEquals(
        ALERT_CONFIRM_TEXT,
        alert.getText(),
        "Текст Confirm не совпадает с ожидаемым: " + ALERT_CONFIRM_TEXT);

    alert.dismiss();
    Assertions.assertEquals(
        ALERT_CONFIRM_CANCEL_TEXT,
        driver.findElement(By.id("confirm-text")).getText(),
        "Текст подтверждения после отмены не совпадает с ожидаемым: " + ALERT_CONFIRM_CANCEL_TEXT);
  }

  @Test
  @DisplayName("Проверить текст введенный в  Prompt")
  void promptTest() {
    openModalWindowPage();
    Alert alert = clickAndSwitchToAlert("my-prompt");
    Assertions.assertEquals(
        ALERT_PROMPT_TEXT,
        alert.getText(),
        "Текст Prompt не совпадает с ожидаемым: " + ALERT_PROMPT_TEXT);

    alert.sendKeys(LOREM_IPSUM);
    alert.accept();
    Assertions.assertEquals(
        PROMPT_TEXT_CONFIRMATION + LOREM_IPSUM,
        driver.findElement(By.id("prompt-text")).getText(),
        "Текст после ввода в Prompt не совпадает с ожидаемым: "
            + PROMPT_TEXT_CONFIRMATION
            + LOREM_IPSUM);
  }

  @Test
  @DisplayName("Проверить отмену ввода в  Prompt")
  void cancelPromptTest() {
    openModalWindowPage();
    Alert alert = clickAndSwitchToAlert("my-prompt");
    Assertions.assertEquals(
        ALERT_PROMPT_TEXT,
        alert.getText(),
        "Текст Prompt не совпадает с ожидаемым: " + ALERT_PROMPT_TEXT);

    alert.sendKeys(LOREM_IPSUM);
    alert.dismiss();
    Assertions.assertEquals(
        PROMPT_TEXT_CONFIRMATION + "null",
        driver.findElement(By.id("prompt-text")).getText(),
        "Текст после отмены ввода в Prompt не совпадает с ожидаемым: "
            + PROMPT_TEXT_CONFIRMATION
            + "null");
  }

  @Test
  @DisplayName("Проверить пустой ввод в  Prompt")
  void nullPromptTest() {
    openModalWindowPage();
    Alert alert = clickAndSwitchToAlert("my-prompt");
    Assertions.assertEquals(
        ALERT_PROMPT_TEXT,
        alert.getText(),
        "Текст Prompt не совпадает с ожидаемым: " + ALERT_PROMPT_TEXT);

    alert.accept();
    Assertions.assertEquals(
        PROMPT_TEXT_CONFIRMATION.trim(),
        driver.findElement(By.id("prompt-text")).getText(),
        "Текст после пустого ввода в Prompt не совпадает с ожидаемым: "
            + PROMPT_TEXT_CONFIRMATION.trim());
  }

  @Test
  @DisplayName("Проверить обычную модалку (не Алерт)")
  void modalWindowTest() {
    openModalWindowPage();
    driver.findElement(By.id("my-modal")).click();
    WebElement modalBody =
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-body")));
    WebElement modalTitle =
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-header")));
    Assertions.assertTrue(modalBody.isDisplayed(), "Тело модалки не отображается");
    Assertions.assertTrue(modalTitle.isDisplayed(), "Заголовок модалки не отображается");
    Assertions.assertEquals(
        MODAL_BODY,
        modalBody.getText(),
        "Текст в теле модалки не совпадает с ожидаемым: " + MODAL_BODY);
    Assertions.assertEquals(
        MODAL_TITLE,
        modalTitle.getText(),
        "Заголовок модалки не совпадает с ожидаемым: " + MODAL_TITLE);
  }

  @Test
  @DisplayName("Проверить SaveChanges обычной модалки (не Алерт)")
  void modalWindowSaveChangesTest() {
    openModalWindowPage();
    driver.findElement(By.id("my-modal")).click();
    clickModalButton(MODAL_SAVE_CHANGES);
    checkConfirmationModalText(MODAL_SAVE_CHANGES);
  }

  @Test
  @DisplayName("Проверить Close обычной модалки (не Алерт)")
  void modalWindowCloseTest() {
    openModalWindowPage();
    driver.findElement(By.id("my-modal")).click();
    clickModalButton(MODAL_CLOSE);
    checkConfirmationModalText(MODAL_CLOSE);
  }

  private void openModalWindowPage() {
    driver.findElement(By.linkText("Dialog boxes")).click();
  }

  private Alert clickAndSwitchToAlert(String alertId) {
    driver.findElement(By.id(alertId)).click();
    return driver.switchTo().alert();
  }

  private void clickModalButton(String buttonText) {
    WebElement clickButton =
        driver.findElement(By.xpath("//button[normalize-space(text()) = '" + buttonText + "']"));
    clickButton.click();
  }

  private void checkConfirmationModalText(String buttonText) {
    WebElement confirmationText = driver.findElement(By.id("modal-text"));
    Assertions.assertEquals(
        MODAL_CONFIRMATION_TEXT + buttonText,
        confirmationText.getText(),
        "Текст в модалке с подтверждением не совпадает с ожидаемым: "
            + MODAL_CONFIRMATION_TEXT
            + buttonText);
  }
}
