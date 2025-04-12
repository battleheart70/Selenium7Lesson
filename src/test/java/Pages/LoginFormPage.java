package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {
  public static final String INVALID_LOGIN = "invalidLogin";
  public static final String INVALID_PASSWORD = "invalidPassword";

  private final By usernameInput = By.id("username");
  private final By passwordInput = By.id("password");
  private final By submitButton = By.cssSelector("button[type='submit']");
  private final By dangerAlert = By.id("invalid");
  private final By successAlert = By.id("success");

  public LoginFormPage(WebDriver driver) {
    super(driver);
  }

  @Step("Заполнить поле с именем пользователя '{username}'")
  public void populateUsername(String username) {
    driver.findElement(usernameInput).sendKeys(username);
  }

  @Step("Заполнить поле с паролем '{password}'")
  public void populatePassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  @Step("Нажать кнопку отправки формы")
  public void pressSubmitButton() {
    driver.findElement(submitButton).click();
  }

  @Step("Войти с логином '{login}' и паролем '{password}'")
  public void loginWithCredentials(String login, String password) {
    populateUsername(login);
    populatePassword(password);
    pressSubmitButton();
  }

  @Step("Получить текст сообщения об ошибке: '{dangerAlert}'")
  public String getDangerAlertText() {
    return getElementByLocator(dangerAlert).getText();
  }

  @Step("Получить текст сообщения об успешном входе: '{successAlert}'")
  public String getSuccessAlertText() {
    return getElementByLocator(successAlert).getText();
  }
}
