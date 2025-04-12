package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SlowCalculatorPage extends BasePage {

  private final By delayInput = By.id("delay");
  private final By resultElement = By.className("screen");

  public SlowCalculatorPage(WebDriver driver) {
    super(driver);
  }

  @Step("Вычислить выражение: {expression}")
  public String calculate(String expression) {
    pressButtons(expression);
    return waitAndGetResult();
  }

  @Step("Ожидание смены текста на экране калькулятора и получение результата")
  public String waitAndGetResult() {
    String initialResult = getElementByLocator(resultElement).getText();

    wait.until(
        driver -> {
          String currentResult = getElementByLocator(resultElement).getText();
          return !currentResult.equals(initialResult);
        });

    return getResult();
  }

  @Step("Получить результат из экрана калькулятора - без ожидания того, что текст сменился")
  public String getResult() {
    return getElementByLocator(resultElement).getText();
  }

  @Step("Нажать кнопки для выражения: {expression}")
  public void pressButtons(String expression) {
    for (char ch : expression.toCharArray()) {
      String buttonXPath = "//span[normalize-space()='" + ch + "']";
      getElementByLocator(By.xpath(buttonXPath)).click();
    }
  }

  @Step("Изменить задержку на: {delay} с")
  public void changeDelayTo(String delay) {
    getElementByLocator(delayInput).clear();
    getElementByLocator(delayInput).sendKeys(delay);
  }
}
