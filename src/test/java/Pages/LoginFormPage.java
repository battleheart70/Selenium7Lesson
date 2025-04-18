package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginFormPage extends BasePage {

  @FindBy(id = "username")
  private WebElement usernameInput;

  @FindBy(id = "password")
  private WebElement passwordInput;

  @FindBy(css = "button[type='submit']")
  private WebElement submitButton;

  @FindBy(id = "invalid")
  private WebElement dangerAlert;

  @FindBy(id = "success")
  private WebElement successAlert;

  public LoginFormPage(WebDriver driver) {
    super(driver);
  }

  @Step("Заполнить username: {username}")
  public LoginFormPage username(String username) {
    wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
    usernameInput.sendKeys(username);
    return this;
  }

  @Step("Заполнить password: {password}")
  public LoginFormPage password(String password) {
    wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
    passwordInput.sendKeys(password);
    return this;
  }

  @Step("Нажать Submit")
  public LoginFormPage submit() {
    wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    return this;
  }

  @Step("Логин с {username} и {password}")
  public LoginFormPage loginWith(String username, String password) {
    return username(username).password(password).submit();
  }

  @Step("Проверить сообщение об успехе: {expected}")
  public LoginFormPage assertSuccess(String expected) {
    String actual = wait.until(ExpectedConditions.visibilityOf(successAlert)).getText();
    assertEquals(expected, actual, "Текст success alert не совпадает");
    return this;
  }

  @Step("Проверить сообщение об ошибке: {expected}")
  public LoginFormPage assertDanger(String expected) {
    String actual = wait.until(ExpectedConditions.visibilityOf(dangerAlert)).getText();
    assertEquals(expected, actual, "Текст danger alert не совпадает");
    return this;
  }
}
