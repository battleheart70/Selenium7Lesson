package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

  @FindBy(linkText = "Web storage")
  private WebElement webStorageButton;

  @FindBy(linkText = "Cookies")
  private WebElement cookiesButton;

  @FindBy(linkText = "IFrames")
  private WebElement iFramesButton;

  @FindBy(linkText = "Infinite scroll")
  private WebElement infiniteScrollButton;

  @FindBy(linkText = "Slow calculator")
  private WebElement slowCalculatorButton;

  @FindBy(linkText = "Shadow DOM")
  private WebElement shadowDomButton;

  @FindBy(linkText = "Dialog boxes")
  private WebElement dialogBoxesButton;

  @FindBy(linkText = "Login form")
  private WebElement loginFormButton;

  @FindBy(linkText = "Loading images")
  private WebElement loadingImagesButton;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @Step("Кликнуть по Web Storage на главной странице")
  public WebStoragePage openWebStoragePage() {
    wait.until(ExpectedConditions.elementToBeClickable(webStorageButton)).click();
    return new WebStoragePage(driver);
  }

  @Step("Кликнуть по Cookies на главной странице")
  public CookiesPage openCookiesPage() {
    wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
    return new CookiesPage(driver);
  }

  @Step("Кликнуть по IFrames на главной странице")
  public IFramesPage openIFramesPage() {
    wait.until(ExpectedConditions.elementToBeClickable(iFramesButton)).click();
    return new IFramesPage(driver);
  }

  @Step("Кликнуть по Infinite Scroll на главной странице")
  public InfiniteScrollPage openInfiniteScrollPage() {
    wait.until(ExpectedConditions.elementToBeClickable(infiniteScrollButton)).click();
    return new InfiniteScrollPage(driver);
  }

  @Step("Кликнуть по Slow Calculator на главной странице")
  public SlowCalculatorPage openSlowCalculatorPage() {
    wait.until(ExpectedConditions.elementToBeClickable(slowCalculatorButton)).click();
    return new SlowCalculatorPage(driver);
  }

  @Step("Кликнуть по Shadow DOM на главной странице")
  public ShadowDOMPage openShadowDomPage() {
    wait.until(ExpectedConditions.elementToBeClickable(shadowDomButton)).click();
    return new ShadowDOMPage(driver);
  }

  @Step("Кликнуть по Dialog Boxes на главной странице")
  public DialogBoxesPage openDialogBoxesPage() {
    wait.until(ExpectedConditions.elementToBeClickable(dialogBoxesButton)).click();
    return new DialogBoxesPage(driver);
  }

  @Step("Кликнуть по Login Form на главной странице")
  public LoginFormPage openLoginFormPage() {
    wait.until(ExpectedConditions.elementToBeClickable(loginFormButton)).click();
    return new LoginFormPage(driver);
  }

  @Step("Кликнуть по Loading Images на главной странице")
  public LoadingImagesPage openLoadingImagesPage() {
    wait.until(ExpectedConditions.elementToBeClickable(loadingImagesButton)).click();
    return new LoadingImagesPage(driver);
  }
}
