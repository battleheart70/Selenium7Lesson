import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.Storage;
import org.openqa.selenium.html5.WebStorage;
import static org.example.Constants.*;


import static org.junit.jupiter.api.Assertions.*;

class WebStorageTests {
  private WebDriver driver;
  private WebStorage webStorage;
  private Storage localStorage;
  private Storage sessionStorage;

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    driver.get(BASE_URL);
    webStorage = (WebStorage) driver;
    localStorage = webStorage.getLocalStorage();
    sessionStorage = webStorage.getSessionStorage();
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
    assertStorageContains(sessionStorage, DEFAULT_SESSION_STORAGE_KEY_1, DEFAULT_SESSION_STORAGE_VALUE_1, SESSION_STORAGE_ID);
    assertStorageContains(sessionStorage, DEFAULT_SESSION_STORAGE_KEY_2, DEFAULT_SESSION_STORAGE_VALUE_2, SESSION_STORAGE_ID);
  }

  @Test
  @DisplayName("Добавление в LocalStorage и проверка отображения")
  void addLocalStorageTest() {
    openWebStoragePage();
    localStorage.setItem(TEST_KEY, TEST_VALUE);
    clickLocalStorageButton();
    assertStorageContains(localStorage, TEST_KEY, TEST_VALUE, LOCAL_STORAGE_ID);
  }

  @Test
  @DisplayName("Удаление из LocalStorage")
  void removeLocalStorageTest() {
    openWebStoragePage();
    localStorage.setItem(TEST_KEY, TEST_VALUE);
    localStorage.removeItem(TEST_KEY);
    clickLocalStorageButton();
    assertStorageDoesNotContain(localStorage, TEST_KEY, LOCAL_STORAGE_ID);
  }

  @Test
  @DisplayName("Добавление в SessionStorage и проверка отображения")
  void addSessionStorageTest() {
    openWebStoragePage();
    sessionStorage.setItem(TEST_KEY, TEST_VALUE);
    clickSessionStorageButton();
    assertStorageContains(sessionStorage, TEST_KEY, TEST_VALUE, SESSION_STORAGE_ID);
  }

  @Test
  @DisplayName("Удаление из SessionStorage")
  void removeSessionStorageTest() {
    openWebStoragePage();
    sessionStorage.setItem(TEST_KEY, TEST_VALUE);
    sessionStorage.removeItem(TEST_KEY);
    clickSessionStorageButton();
    assertStorageDoesNotContain(sessionStorage, TEST_KEY, SESSION_STORAGE_ID);
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

  private void assertStorageContains(Storage storage, String key, String expectedValue, String storageLocatorId) {
    assertEquals(expectedValue, storage.getItem(key), "Хранилище не содержит ожидаемое значение!");
    assertTrue(driver.findElement(By.id(storageLocatorId)).getText().contains("\"" + key + "\":\"" + expectedValue + "\""),
            storageLocatorId + " не отображается на странице!");
  }

  private void assertStorageDoesNotContain(Storage storage, String key, String storageLocatorId) {
    assertNull(storage.getItem(key), "Элемент хранилища не был удален!");
    assertFalse(driver.findElement(By.id(storageLocatorId)).getText().contains(key), storageLocatorId + " все еще отображается на странице!");
  }

  private void assertStorageIsEmpty(Storage storage, String storageLocatorId) {
    assertNull(storage.getItem("anyKey"), storageLocatorId + " не должно содержать элементов!");
    assertEquals("{}", driver.findElement(By.id(storageLocatorId)).getText(), storageLocatorId + " должно быть пустым на странице!");
  }
}
