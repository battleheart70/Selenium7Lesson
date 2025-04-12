package tests;

import static Pages.CookiesPage.*;
import Pages.CookiesPage;
import Pages.HomePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

@Epic("Cookies Tests")
class CookiesTests extends BaseTest {

  private CookiesPage cookiesPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    cookiesPage = new HomePage(driver).openCookiesPage();
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление и проверка куки")
  void cookieAddTest() {
    String testKey = config.getTestKey();
    String testValue = config.getTestValue();
    System.out.printf("testKey = %s, testValue = %s", testKey, testValue); // logging
    cookiesPage.addCookie(testKey, testValue);
    cookiesPage.assertCookieExists(testKey, testValue);
    cookiesPage.assertCookiesNumberIncreased();
  }

  @Test
  @DisplayName("Удаление существующих куки с Username")
  void cookieDeleteTest() {
    cookiesPage.deleteCookie(DEFAULT_USERNAME_COOKIES.getName());
    cookiesPage.assertCookieWasDeleted(DEFAULT_USERNAME_COOKIES);
    cookiesPage.assertCookiesNumberDecreased();
  }

  @Test
  @DisplayName("Проверка дефолтных куки Username")
  void cookieUsernameDefaultTest() {
    cookiesPage.pressRefreshCookiesButton();
    cookiesPage.assertCookieExists(DEFAULT_USERNAME_COOKIES);
    cookiesPage.assertCookiesNumberDefault();
  }

  @Test
  @DisplayName("Проверка дефолтных куки Date")
  void cookieDateDefaultTest() {
    cookiesPage.pressRefreshCookiesButton();
    cookiesPage.assertCookieExists(DEFAULT_DATE_COOKIES);
    cookiesPage.assertCookiesNumberDefault();
  }
}
