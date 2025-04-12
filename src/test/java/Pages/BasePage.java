package Pages;

import config.TestPropertiesConfig;
import io.qameta.allure.Step;
import java.time.Duration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected TestPropertiesConfig config =
      ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

  public static final String PAGE_TITLE = "Hands-On Selenium WebDriver with Java";
  public static final String BROWSER_TITLE = "Hands-On Selenium WebDriver with Java";
  public static final String FOOTER_TEXT = "Copyright © 2021-2025";


  private final By title = By.cssSelector("h1.display-4");
  private final By footer = By.cssSelector("footer.footer");
  private final By footerText = By.cssSelector("span.text-muted");

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  @Step("Получить текст Заголовка на самой странице")
  public String getPageTitle() {
    return getElementByLocator(title).getText();
  }

  @Step("Получить заголовок страницы(отображаемый на вкладке браузера)")
  public String getTitle() {
    return driver.getTitle();
  }

  @Step("Получить элемент по локатору '{locator}' (с ожиданием)")
  public WebElement getElementByLocator(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  @Step("Получить Alert по локатору '{locator}'")
  public Alert getAlert(By locator) {
    getElementByLocator(locator).click();
    return driver.switchTo().alert();
  }

  @Step("Получить footer и вернуть его текст")
  public String getFooterText() {
    return getElementByLocator(footer).findElement(footerText).getText();
  }
}
