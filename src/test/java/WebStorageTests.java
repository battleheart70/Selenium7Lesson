import config.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.Storage;
import org.openqa.selenium.html5.WebStorage;

import static org.junit.jupiter.api.Assertions.*;

class WebStorageTests {
  private WebDriver driver;
  private WebStorage webStorage;
  private Storage localStorage;
  private Storage sessionStorage;
  TestPropertiesConfig config =
      ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
  String testKey = config.getTestKey();
  String testValue = config.getTestValue();
  private static final String LOCAL_STORAGE_ID = "local-storage";
  private static final String SESSION_STORAGE_ID = "session-storage";
  private static final String DEFAULT_SESSION_STORAGE_KEY_1 = "lastname";
  private static final String DEFAULT_SESSION_STORAGE_VALUE_1 = "Doe";
  private static final String DEFAULT_SESSION_STORAGE_KEY_2 = "name";
  private static final String DEFAULT_SESSION_STORAGE_VALUE_2 = "John";

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    driver.get(config.getBaseUrl());
    webStorage = (WebStorage) driver;
    localStorage = webStorage.getLocalStorage();
    sessionStorage = webStorage.getSessionStorage();

    System.out.printf("testKey = %s, testValue = %s%n", testKey, testValue);
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }

  @Test
  @DisplayName("Проверка дефолтных Local&Session storage")
  void checkStorageDefaults() {
    openWebStoragePage();
    clickLocalStorageButton();
    assertStorageIsEmpty(localStorage, LOCAL_STORAGE_ID);

    clickSessionStorageButton();
    assertStorageContains(
        sessionStorage,
        DEFAULT_SESSION_STORAGE_KEY_1,
        DEFAULT_SESSION_STORAGE_VALUE_1,
        SESSION_STORAGE_ID);
    assertStorageContains(
        sessionStorage,
        DEFAULT_SESSION_STORAGE_KEY_2,
        DEFAULT_SESSION_STORAGE_VALUE_2,
        SESSION_STORAGE_ID);
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление в LocalStorage и проверка отображения")
  void addLocalStorageTest() {
    openWebStoragePage();
    localStorage.setItem(testKey, testValue);
    clickLocalStorageButton();
    assertStorageContains(localStorage, testKey, testValue, LOCAL_STORAGE_ID);
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Удаление из LocalStorage")
  void removeLocalStorageTest() {
    openWebStoragePage();
    localStorage.setItem(testKey, testValue);
    localStorage.removeItem(testKey);
    clickLocalStorageButton();
    assertStorageDoesNotContain(localStorage, testKey, LOCAL_STORAGE_ID);
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Добавление в SessionStorage и проверка отображения")
  void addSessionStorageTest() {
    openWebStoragePage();
    sessionStorage.setItem(testKey, testValue);
    clickSessionStorageButton();
    assertStorageContains(sessionStorage, testKey, testValue, SESSION_STORAGE_ID);
  }

  @Tag("KeyValueTest")
  @Test
  @DisplayName("Удаление из SessionStorage")
  void removeSessionStorageTest() {
    openWebStoragePage();
    sessionStorage.setItem(testKey, testValue);
    sessionStorage.removeItem(testKey);
    clickSessionStorageButton();
    assertStorageDoesNotContain(sessionStorage, testKey, SESSION_STORAGE_ID);
  }

  private void openWebStoragePage() {
    driver.findElement(By.linkText("Web storage")).click();
  }

  private void clickLocalStorageButton() {
    driver.findElement(By.id("display-local")).click();
  }

  private void clickSessionStorageButton() {
    driver.findElement(By.id("display-session")).click();
  }

  private void assertStorageContains(
      Storage storage, String key, String expectedValue, String storageLocatorId) {
    assertEquals(expectedValue, storage.getItem(key), "Хранилище не содержит ожидаемое значение!");
    assertTrue(
        driver
            .findElement(By.id(storageLocatorId))
            .getText()
            .contains("\"" + key + "\":\"" + expectedValue + "\""),
        storageLocatorId + " не отображается на странице!");
  }

  private void assertStorageDoesNotContain(Storage storage, String key, String storageLocatorId) {
    assertNull(storage.getItem(key), "Элемент хранилища не был удален!");
    assertFalse(
        driver.findElement(By.id(storageLocatorId)).getText().contains(key),
        storageLocatorId + " все еще отображается на странице!");
  }

  private void assertStorageIsEmpty(Storage storage, String storageLocatorId) {
    assertNull(storage.getItem("anyKey"), storageLocatorId + " не должно содержать элементов!");
    assertEquals(
        "{}",
        driver.findElement(By.id(storageLocatorId)).getText(),
        storageLocatorId + " должно быть пустым на странице!");
  }
}
