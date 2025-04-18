package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Storage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class WebStoragePage extends BasePage {

  private final WebStorage webStorage;
  private final Storage localStorage;
  private final Storage sessionStorage;

  @FindBy(id = "display-local")
  private WebElement localStorageButton;

  @FindBy(id = "display-session")
  private WebElement sessionStorageButton;

  @FindBy(id = "local-storage")
  private WebElement localStorageText;

  @FindBy(id = "session-storage")
  private WebElement sessionStorageText;

  public static final String DEFAULT_SESSION_STORAGE_KEY_1   = "lastname";
  public static final String DEFAULT_SESSION_STORAGE_VALUE_1 = "Doe";
  public static final String DEFAULT_SESSION_STORAGE_KEY_2   = "name";
  public static final String DEFAULT_SESSION_STORAGE_VALUE_2 = "John";

  public WebStoragePage(WebDriver driver) {
    super(driver);
    this.webStorage     = (WebStorage) driver;
    this.localStorage   = webStorage.getLocalStorage();
    this.sessionStorage = webStorage.getSessionStorage();
  }

  @Step("Кликнуть на кнопку Local Storage на UI")
  public void clickLocalStorageButton() {
    wait.until(ExpectedConditions.elementToBeClickable(localStorageButton)).click();
  }

  @Step("Кликнуть на кнопку Session Storage на UI")
  public void clickSessionStorageButton() {
    wait.until(ExpectedConditions.elementToBeClickable(sessionStorageButton)).click();
  }

  @Step("Вернуть LocalStorage Item по ключу: {key}")
  public String getLocalStorageItem(String key) {
    return localStorage.getItem(key);
  }

  @Step("Вернуть SessionStorage Item по ключу: {key}")
  public String getSessionStorageItem(String key) {
    return sessionStorage.getItem(key);
  }

  @Step("Текст Local Storage на UI")
  public String getLocalStorageText() {
    return wait.until(ExpectedConditions.visibilityOf(localStorageText)).getText();
  }

  @Step("Текст Session Storage на UI")
  public String getSessionStorageText() {
    return wait.until(ExpectedConditions.visibilityOf(sessionStorageText)).getText();
  }

  @Step("Добавить в Local Storage по ключу: {key} и значению: {value}")
  public void addToLocalStorage(String key, String value) {
    localStorage.setItem(key, value);
  }

  @Step("Удалить из Local Storage по ключу: {key}")
  public void removeFromLocalStorage(String key) {
    localStorage.removeItem(key);
  }

  @Step("Добавить в Session Storage по ключу: {key} и значению: {value}")
  public void addToSessionStorage(String key, String value) {
    sessionStorage.setItem(key, value);
  }

  @Step("Удалить из Session Storage по ключу: {key}")
  public void removeFromSessionStorage(String key) {
    sessionStorage.removeItem(key);
  }
}
