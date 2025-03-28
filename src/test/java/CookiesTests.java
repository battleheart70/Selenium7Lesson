import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.example.Constants.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CookiesTests {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    driver.get(BASE_URL);
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }

  @Test
  @DisplayName("Добавление и проверка куки")
  void cookieAddTest() {

    openCookiesPage();
    driver.manage().addCookie(new Cookie(TEST_KEY, TEST_VALUE));
    pressRefreshCookiesButton();

    assertCookieExists(TEST_KEY, TEST_VALUE);
    assertEquals(DEFAULT_COOKIES_SIZE + 1, driver.manage().getCookies().size());
  }

  @Test
  @DisplayName("Удаление существующих куки с Username")
  void cookieDeleteTest() {
    openCookiesPage();
    driver.manage().deleteCookieNamed(DEFAULT_USERNAME_COOKIES.getName());
    pressRefreshCookiesButton();

    assertFalse(
        getCookiesTextOnPage()
            .contains(
                DEFAULT_USERNAME_COOKIES.getName() + "=" + DEFAULT_USERNAME_COOKIES.getValue()),
        "Cookie не удален");
    assertEquals(DEFAULT_COOKIES_SIZE - 1, driver.manage().getCookies().size());
  }

  @Test
  @DisplayName("Проверка дефолтных куки Username")
  void cookieUsernameDefaultTest() {
    openCookiesPage();
    pressRefreshCookiesButton();
    assertCookieExists(DEFAULT_USERNAME_COOKIES.getName(), DEFAULT_USERNAME_COOKIES.getValue());
    assertEquals(DEFAULT_COOKIES_SIZE, driver.manage().getCookies().size());
  }

  @Test
  @DisplayName("Проверка дефолтных куки Date")
  void cookieDateDefaultTest() {
    openCookiesPage();
    pressRefreshCookiesButton();
    assertCookieExists(DEFAULT_DATE_COOKIES.getName(), DEFAULT_DATE_COOKIES.getValue());
    assertEquals(DEFAULT_COOKIES_SIZE, driver.manage().getCookies().size());
  }

  private void openCookiesPage() {
    driver.findElement(By.linkText("Cookies")).click();
  }

  private void pressRefreshCookiesButton() {
    driver.findElement(By.id("refresh-cookies")).click();
  }

  private String getCookiesTextOnPage() {
    WebElement cookiesListElement =
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookies-list")));
    return cookiesListElement.getText();
  }

  private void assertCookieExists(String name, String value) {
    assertTrue(getCookiesTextOnPage().contains(name + "=" + value), "Cookie отсутствует!");
    Cookie cookie = driver.manage().getCookieNamed(name);
    assertNotNull(cookie, "Cookie не найден");
    assertEquals(value, cookie.getValue());
  }
}
