package Pages;

import static org.junit.jupiter.api.Assertions.*;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CookiesPage extends BasePage {

  public static final int DEFAULT_COOKIES_SIZE = 2;
  public static final Cookie DEFAULT_DATE_COOKIES = new Cookie("date", "10/07/2018");
  public static final Cookie DEFAULT_USERNAME_COOKIES = new Cookie("username", "John Doe");

  private final By refreshButton = By.id("refresh-cookies");
  private final By cookiesList = By.id("cookies-list");

  public CookiesPage(WebDriver driver) {
    super(driver);
  }

  @Step("Нажать кнопку обновления списка cookies на UI")
  public void pressRefreshCookiesButton() {
    getElementByLocator(refreshButton).click();
  }

  @Step("Получить текст элемента в котором на UI перечислены cookies")
  public String getCookiesTextOnPage() {
    WebElement cookiesListElement = getElementByLocator(cookiesList);
    return cookiesListElement.getText();
  }

  @Step("Проверить наличие cookie с именем '{name}' и значением '{value}'")
  public void assertCookieExists(String name, String value) {
    assertTrue(getCookiesTextOnPage().contains(name + "=" + value), "Cookie отсутствует!");
    Cookie cookieOnPage = driver.manage().getCookieNamed(name);
    assertNotNull(cookieOnPage, "Cookie не найден");
    assertEquals(value, cookieOnPage.getValue());
  }

  @Step("Проверить наличие cookie: {cookie}")
  public void assertCookieExists(Cookie cookie) {
    assertTrue(
        getCookiesTextOnPage().contains(cookie.getName() + "=" + cookie.getValue()),
        "Cookie отсутствует!");
    Cookie cookieOnPage = driver.manage().getCookieNamed(cookie.getName());
    assertNotNull(cookieOnPage, "Cookie не найден");
    assertEquals(cookie.getValue(), cookieOnPage.getValue());
  }

  @Step("Добавить cookie: ключ '{key}' со значением '{value}'")
  public void addCookie(String key, String value) {
    driver.manage().addCookie(new Cookie(key, value));
    pressRefreshCookiesButton();
  }

  @Step("Удалить cookie с ключом '{key}'")
  public void deleteCookie(String key) {
    driver.manage().deleteCookieNamed(key);
    pressRefreshCookiesButton();
  }

  @Step("Проверить удаление cookie: {cookie}")
  public void assertCookieWasDeleted(Cookie cookie) {
    assertFalse(
        getCookiesTextOnPage().contains(cookie.getName() + "=" + cookie.getValue()),
        "Cookie не удален");
  }

  @Step("Проверить увеличение количества cookies на 1")
  public void assertCookiesNumberIncreased() {
    assertEquals(DEFAULT_COOKIES_SIZE + 1, driver.manage().getCookies().size());
  }

  @Step("Проверить уменьшение количества cookies на 1")
  public void assertCookiesNumberDecreased() {
    assertEquals(DEFAULT_COOKIES_SIZE - 1, driver.manage().getCookies().size());
  }

  @Step("Проверить дефолтное количество cookies")
  public void assertCookiesNumberDefault() {
    assertEquals(DEFAULT_COOKIES_SIZE, driver.manage().getCookies().size());
  }
}
