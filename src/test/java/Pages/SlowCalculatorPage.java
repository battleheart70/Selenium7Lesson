package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SlowCalculatorPage extends BasePage {

  @FindBy(id = "delay")
  private WebElement delayInput;

  @FindBy(className = "screen")
  private WebElement resultElement;

  public SlowCalculatorPage(WebDriver driver) {
    super(driver);
  }

  @Step("Изменить задержку на: {delay} с")
  public void changeDelayTo(String delay) {
    wait.until(ExpectedConditions.visibilityOf(delayInput)).clear();
    delayInput.sendKeys(delay);
  }

  @Step("Нажать кнопки для выражения: {expression}")
  public void pressButtons(String expression) {
    for (char ch : expression.toCharArray()) {
      String xpath = "//span[normalize-space()='" + ch + "']";
      WebElement button = wait.until(
              ExpectedConditions.elementToBeClickable(
                      driver.findElement(By.xpath(xpath))
              )
      );
      button.click();
    }
  }

  @Step("Получить результат из экрана калькулятора (без ожидания)")
  public String getResult() {
    return wait.until(ExpectedConditions.visibilityOf(resultElement)).getText();
  }

  @Step("Ожидание смены текста на экране калькулятора и получение результата")
  public String waitAndGetResult() {
    String initial = getResult();
    wait.until(driver -> !resultElement.getText().equals(initial));
    return getResult();
  }

  @Step("Вычислить выражение: {expression}")
  public String calculate(String expression) {
    pressButtons(expression);
    return waitAndGetResult();
  }
}
