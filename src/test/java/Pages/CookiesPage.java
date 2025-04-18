package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.example.Constants.DEFAULT_COOKIES_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class CookiesPage extends BasePage {

  @FindBy(id = "refresh-cookies")
  private WebElement refreshButton;

  @FindBy(id = "cookies-list")
  private WebElement cookiesList;

  public CookiesPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @Step("Обновить список cookies на UI")
  public CookiesPage refresh() {
    wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
    return this;
  }

  @Step("Добавить cookie: '{key}={value}'")
  public CookiesPage addCookie(String key, String value) {
    driver.manage().addCookie(new Cookie(key, value));
    return refresh();
  }

  @Step("Удалить cookie: '{key}'")
  public CookiesPage deleteCookie(String key) {
    driver.manage().deleteCookieNamed(key);
    return refresh();
  }

  @Step("Проверить, что UI содержит cookie '{name}={value}'")
  public CookiesPage verifyUiContains(String name, String value) {
    String expected = name + "=" + value;
    String actual = wait.until(ExpectedConditions.visibilityOf(cookiesList)).getText();
    assertTrue(
        actual.contains(expected),
        () -> "Ожидали UI строку \"" + expected + "\", но получили \"" + actual + "\"");
    return this;
  }

  @Step("Проверить, что UI не содержит cookie '{name}={value}'")
  public CookiesPage verifyUiDoesNotContain(String name, String value) {
    String expected = name + "=" + value;
    String actual = wait.until(ExpectedConditions.visibilityOf(cookiesList)).getText();
    assertFalse(
        actual.contains(expected),
        () -> "Не ожидали UI строку \"" + expected + "\", но получили \"" + actual + "\"");
    return this;
  }

  @Step("Проверить, что браузер содержит cookie '{name}' со значением '{value}'")
  public CookiesPage verifyBrowserCookie(String name, String value) {
    Cookie cookie = driver.manage().getCookieNamed(name);
    assertAll(
        "Проверка cookie в браузере",
        () -> assertNotNull(cookie, () -> "Cookie '" + name + "' не найдена в браузере"),
        () ->
            assertEquals(
                value,
                cookie.getValue(),
                () ->
                    "Ожидали для '"
                        + name
                        + "' значение \""
                        + value
                        + "\", но было \""
                        + (cookie != null ? cookie.getValue() : "null")
                        + "\""));
    return this;
  }

  @Step("Проверить увеличение количества cookies на 1")
  public CookiesPage checkNumberIncreased() {
    assertEquals(
        DEFAULT_COOKIES_SIZE + 1,
        driver.manage().getCookies().size(),
        "Количество cookies не увеличилось на 1");
    return this;
  }

  @Step("Проверить уменьшение количества cookies на 1")
  public CookiesPage checkNumberDecreased() {
    assertEquals(
        DEFAULT_COOKIES_SIZE - 1,
        driver.manage().getCookies().size(),
        "Количество cookies не уменьшилось на 1");
    return this;
  }

  @Step("Проверить дефолтное количество cookies")
  public CookiesPage checkDefaultNumber() {
    assertEquals(
        DEFAULT_COOKIES_SIZE,
        driver.manage().getCookies().size(),
        "Количество cookies не соответствует дефолтному");
    return this;
  }
}
