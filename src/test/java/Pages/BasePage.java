package Pages;

import components.FooterComponent;
import components.HeaderComponent;
import config.TestPropertiesConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected TestPropertiesConfig config =
          ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

  protected HeaderComponent header;
  protected FooterComponent footer;

  @FindBy(css = "h1.display-4")
  private WebElement pageTitle;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    PageFactory.initElements(driver, this);
    this.header = new HeaderComponent(driver);
    this.footer = new FooterComponent(driver);
  }

  @Step("Получить текст заголовка на странице")
  public String getPageTitle() {
    wait.until(ExpectedConditions.visibilityOf(pageTitle));
    return pageTitle.getText();
  }

  @Step("Получить заголовок страницы (title в браузере)")
  public String getTitle() {
    return driver.getTitle();
  }

  @Step("Получить URL страницы")
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  @Step("Перейти в header")
  public HeaderComponent header() {
    return header;
  }

  @Step("Перейти в footer")
  public FooterComponent footer() {
    return footer;
  }

  @Step("Кликнуть по элементу и получить Alert")
  public Alert clickAndGetAlert(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    return driver.switchTo().alert();
  }
}