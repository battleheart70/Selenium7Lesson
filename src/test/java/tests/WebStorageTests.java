package tests;

import Pages.HomePage;
import Pages.WebStoragePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты Web Storage")
class WebStorageTests extends BaseTest {

  private WebStoragePage webStoragePage;
  private final String testKey   = config.getTestKey();
  private final String testValue = config.getTestValue();

  @BeforeEach
  void prepare() {
    super.prepare();
    webStoragePage = new HomePage(driver).openWebStoragePage();
  }

  @Test
  @DisplayName("Проверка дефолтного Local Storage пуст")
  void defaultLocalStorageEmpty() {
    webStoragePage.clickLocalStorageButton();
    assertNull(webStoragePage.getLocalStorageItem(testKey), "Local storage должно быть пустым!");
  }

  @Test
  @DisplayName("Проверка дефолтного Session Storage содержит правильные значения")
  void defaultSessionStorageValues() {
    webStoragePage.clickSessionStorageButton();
    assertEquals(
            WebStoragePage.DEFAULT_SESSION_STORAGE_VALUE_1,
            webStoragePage.getSessionStorageItem(WebStoragePage.DEFAULT_SESSION_STORAGE_KEY_1),
            "Session storage не содержит правильное значение для 'lastname'"
    );
    assertEquals(
            WebStoragePage.DEFAULT_SESSION_STORAGE_VALUE_2,
            webStoragePage.getSessionStorageItem(WebStoragePage.DEFAULT_SESSION_STORAGE_KEY_2),
            "Session storage не содержит правильное значение для 'name'"
    );
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление в Local Storage и проверка отображения и API")
  void addLocalStorageTest() {
    webStoragePage.addToLocalStorage(testKey, testValue);
    webStoragePage.clickLocalStorageButton();
    assertTrue(
            webStoragePage.getLocalStorageText().contains("\"" + testKey + "\":\"" + testValue + "\""),
            "Local storage не содержит добавленного значения!"
    );
    assertEquals(
            testValue,
            webStoragePage.getLocalStorageItem(testKey),
            "Local storage не содержит добавленного значения через API!"
    );
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Удаление из Local Storage и проверка отображения и API")
  void removeLocalStorageTest() {
    webStoragePage.addToLocalStorage(testKey, testValue);
    webStoragePage.removeFromLocalStorage(testKey);
    webStoragePage.clickLocalStorageButton();
    assertFalse(
            webStoragePage.getLocalStorageText().contains(testKey),
            "Элемент в Local Storage не был удален из UI!"
    );
    assertNull(webStoragePage.getLocalStorageItem(testKey), "Элемент в Local Storage не был удален через API!");
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление в Session Storage и проверка отображения и API")
  void addSessionStorageTest() {
    webStoragePage.addToSessionStorage(testKey, testValue);
    webStoragePage.clickSessionStorageButton();
    assertTrue(
            webStoragePage.getSessionStorageText().contains("\"" + testKey + "\":\"" + testValue + "\""),
            "Session storage не содержит добавленного значения!"
    );
    assertEquals(
            testValue,
            webStoragePage.getSessionStorageItem(testKey),
            "Session storage не содержит добавленного значения через API!"
    );
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Удаление из Session Storage и проверка отображения и API")
  void removeSessionStorageTest() {
    webStoragePage.addToSessionStorage(testKey, testValue);
    webStoragePage.removeFromSessionStorage(testKey);
    webStoragePage.clickSessionStorageButton();
    assertFalse(
            webStoragePage.getSessionStorageText().contains(testKey),
            "Элемент в Session Storage не был удален из UI!"
    );
    assertNull(webStoragePage.getSessionStorageItem(testKey), "Элемент в Session Storage не был удален через API!");
  }
}
