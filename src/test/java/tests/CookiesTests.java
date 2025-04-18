package tests;

import Pages.CookiesPage;
import Pages.HomePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Cookies Tests")
class CookiesTests extends BaseTest {

  private CookiesPage cookiesPage;

  @BeforeEach
  void prepare() {
    super.prepare();
    cookiesPage = new HomePage(driver).openCookiesPage();
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление cookie и проверка")
  void cookieAddTest() {
    String key   = config.getTestKey();
    String value = config.getTestValue();

    cookiesPage
            .addCookie(key, value)
            .verifyUiContains(key, value)
            .verifyBrowserCookie(key, value)
            .checkNumberIncreased();
  }

  @Test
  @DisplayName("Удаление cookie и проверка отсутствия")
  void cookieDeleteTest() {
    String key   = CookiesPage.DEFAULT_USERNAME_COOKIES.getName();
    String value = CookiesPage.DEFAULT_USERNAME_COOKIES.getValue();

    cookiesPage
            .deleteCookie(key)
            .verifyUiDoesNotContain(key, value)
            .checkNumberDecreased();
  }

  @Test
  @DisplayName("Дефолтные cookie на странице и в API")
  void defaultCookiesTest() {
    cookiesPage
            .refresh()
            .verifyUiContains(
                    CookiesPage.DEFAULT_USERNAME_COOKIES.getName(),
                    CookiesPage.DEFAULT_USERNAME_COOKIES.getValue()
            )
            .verifyBrowserCookie(
                    CookiesPage.DEFAULT_USERNAME_COOKIES.getName(),
                    CookiesPage.DEFAULT_USERNAME_COOKIES.getValue()
            )
            .verifyUiContains(
                    CookiesPage.DEFAULT_DATE_COOKIES.getName(),
                    CookiesPage.DEFAULT_DATE_COOKIES.getValue()
            )
            .verifyBrowserCookie(
                    CookiesPage.DEFAULT_DATE_COOKIES.getName(),
                    CookiesPage.DEFAULT_DATE_COOKIES.getValue()
            )
            .checkDefaultNumber();
  }
}
