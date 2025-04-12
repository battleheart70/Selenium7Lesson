package tests;

import Pages.HomePage;
import Pages.WebStoragePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Web Storage Tests")
class WebStorageTests extends BaseTest {

  String testKey = config.getTestKey();
  String testValue = config.getTestValue();
  private WebStoragePage webStoragePage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    HomePage homePage = new HomePage(driver);
    webStoragePage = homePage.openWebStoragePage();
    System.out.printf("testKey = %s, testValue = %s%n", testKey, testValue);
  }

  @Test
  @DisplayName("Проверка дефолтных Local&Session storage")
  void checkStorageDefaults() {

    webStoragePage.clickLocalStorageButton();
    assertNull(webStoragePage.getLocalStorageItem(testKey), "Local storage должно быть пустым!");


    webStoragePage.clickSessionStorageButton();
    assertEquals(
            WebStoragePage.DEFAULT_SESSION_STORAGE_VALUE_1,
            webStoragePage.getSessionStorageItem(WebStoragePage.DEFAULT_SESSION_STORAGE_KEY_1),
            "Session storage не содержит правильное значение для \"lastname\""
    );
    assertEquals(
            WebStoragePage.DEFAULT_SESSION_STORAGE_VALUE_2,
            webStoragePage.getSessionStorageItem(WebStoragePage.DEFAULT_SESSION_STORAGE_KEY_2),
            "Session storage не содержит правильное значение для \"name\""
    );
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление в LocalStorage и проверка отображения и API")
  void addLocalStorageTest() {

    webStoragePage.addToLocalStorage(testKey, testValue);
    webStoragePage.clickLocalStorageButton();


    assertTrue(
            webStoragePage.getLocalStorageText().contains("\"" + testKey + "\":\"" + testValue + "\""),
            "Local storage не содержит добавленного значения!");


    assertEquals(testValue, webStoragePage.getLocalStorageItem(testKey), "Local storage не содержит добавленного значения через API!");
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Удаление из LocalStorage и проверка отображения и API")
  void removeLocalStorageTest() {

    webStoragePage.addToLocalStorage(testKey, testValue);

    webStoragePage.removeFromLocalStorage(testKey);
    webStoragePage.clickLocalStorageButton();


    assertFalse(
            webStoragePage.getLocalStorageText().contains(testKey),
            "Элемент в LocalStorage не был удален из UI!");


    assertNull(webStoragePage.getLocalStorageItem(testKey), "Элемент в LocalStorage не был удален через API!");
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление в SessionStorage и проверка отображения и API")
  void addSessionStorageTest() {

    webStoragePage.addToSessionStorage(testKey, testValue);
    webStoragePage.clickSessionStorageButton();


    assertTrue(
            webStoragePage.getSessionStorageText().contains("\"" + testKey + "\":\"" + testValue + "\""),
            "Session storage не содержит добавленного значения!");


    assertEquals(testValue, webStoragePage.getSessionStorageItem(testKey), "Session storage не содержит добавленного значения через API!");
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Удаление из SessionStorage и проверка отображения и API")
  void removeSessionStorageTest() {

    webStoragePage.addToSessionStorage(testKey, testValue);

    webStoragePage.removeFromSessionStorage(testKey);
    webStoragePage.clickSessionStorageButton();


    assertFalse(webStoragePage.getSessionStorageText().contains(testKey),"Элемент в SessionStorage не был удален из UI!");

    assertNull(webStoragePage.getSessionStorageItem(testKey), "Элемент в SessionStorage не был удален через API!");
  }
}
