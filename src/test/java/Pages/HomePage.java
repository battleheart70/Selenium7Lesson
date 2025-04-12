package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

  private final By webStorageButton = By.linkText("Web storage");
  private final By cookiesButton = By.linkText("Cookies");
  private final By iFramesButton = By.linkText("IFrames");
  private final By infiniteScrollButton = By.linkText("Infinite scroll");
  private final By slowCalculatorButton = By.linkText("Slow calculator");
  private final By shadowDomButton = By.linkText("Shadow DOM");
  private final By dialogBoxesButton = By.linkText("Dialog boxes");
  private final By loginFormButton = By.linkText("Login form");
  private final By loadingImagesButton = By.linkText("Loading images");

  public HomePage(WebDriver driver) {
    super(driver);
    driver.get(config.getBaseUrl());
  }

  @Step("Кликнуть по Webstorage на главной странице")
  public WebStoragePage openWebStoragePage() {
    getElementByLocator(webStorageButton).click();
    return new WebStoragePage(driver);
  }

  @Step("Кликнуть по Cookies на главной странице")
  public CookiesPage openCookiesPage() {
    getElementByLocator(cookiesButton).click();
    return new CookiesPage(driver);
  }

  @Step("Кликнуть по Iframes на главной странице")
  public IFramesPage openIFramesPage() {
    getElementByLocator(iFramesButton).click();
    return new IFramesPage(driver);
  }

  @Step("Кликнуть по InfiniteScroll на главной странице")
  public InfiniteScrollPage openInfiniteScrollPage() {
    getElementByLocator(infiniteScrollButton).click();
    return new InfiniteScrollPage(driver);
  }

  @Step("Кликнуть по Slow Calculator на главной странице")
  public SlowCalculatorPage openSlowCalculatorPage() {
    getElementByLocator(slowCalculatorButton).click();
    return new SlowCalculatorPage(driver);
  }

  @Step("Кликнуть по Slow Calculator на главной странице")
  public ShadowDOMPage openShadowDomPage() {
    getElementByLocator(shadowDomButton).click();
    return new ShadowDOMPage(driver);
  }

  @Step("Кликнуть по Dialog boxes на главной странице")
  public DialogBoxesPage openDialogBoxPage() {
    getElementByLocator(dialogBoxesButton).click();
    return new DialogBoxesPage(driver);
  }

  @Step("Кликнуть по Login Form на главной странице")
  public LoginFormPage openLoginFormPage() {
    getElementByLocator(loginFormButton).click();
    return new LoginFormPage(driver);
  }

  @Step("Кликнуть по Loading Images на главной странице")
  public LoadingImagesPage openLoadingImagesPage() {
    getElementByLocator(loadingImagesButton).click();
    return new LoadingImagesPage(driver);
  }
}
