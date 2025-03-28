import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Constants.BASE_URL;

class SlowCalculatorTests {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    driver.get(BASE_URL);
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }

  @ParameterizedTest
  @DisplayName("Проверить медленный калькулятор с различными операциями")
  @CsvFileSource(resources = "/calc_test_data.csv", numLinesToSkip = 1)
  void slowCalculatorOperationsTest(String expression, String expectedResult) {
    openSlowCalculatorPage();
    pressButtons(expression);
    waitAndAssertResult(expectedResult, "Неверный расчет!");
  }

  @Test
  @DisplayName("Проверить выражение с умножением, делением, сложением и вычитанием")
  void testExpressionWithAllOperations() {
    openSlowCalculatorPage();
    pressButtons("4.0x2.5÷1.25+3.2-1.7=");
    waitAndAssertResult("9.5", "Неверный расчет!");
  }

  @Test
  @DisplayName("Изменить время ожидания и словить TimeOutException")
  void timeOutTest() {
    openSlowCalculatorPage();
    changeDelay("6");
    System.out.println(driver.findElement(By.id("delay")).getAttribute("value"));
    pressButtons("7+8=");
    Assertions.assertThrows(
        TimeoutException.class,
        () -> {
          wait.until(
              ExpectedConditions.textToBePresentInElementLocated(By.className("screen"), "15"));
        },
        "Ожидалось выбрасывание исключения TimeoutException, но его не произошло.");
  }

  @Test
  @DisplayName("Проверить что кнопка C стирает ввод")
  void clearButtonTest() {
    openSlowCalculatorPage();
    pressButtons("1234");
    waitAndAssertResult("1234", "Введенная информация не отображается");

    pressButtons("C");
    waitAndAssertResult("", "Кнопка 'C' не очистила поле ввода");
  }

  @Test
  @DisplayName("Ввод нескольких '.' подряд игнорируется")
  void multipleDotsInput(){
    openSlowCalculatorPage();
    pressButtons("1....2+3....4=");
    waitAndAssertResult("4.6", "Результат неверный!");
  }

  @Test
  @DisplayName("Ввод нескольких операторов подряд")
  void multipleOperatorsInput(){
    openSlowCalculatorPage();
    pressButtons("2+-x÷3=");
    waitAndAssertResult("6", "Результат неверный!");
  }
  @Test
  @DisplayName("Отрицательные числа")
  void negativeNumbersTest(){
    openSlowCalculatorPage();
    pressButtons("-3-6=");
    waitAndAssertResult("-9", "Результат неверный!");
  }

  private void pressButtons(String expression) {
    for (char ch : expression.toCharArray()) {
      String buttonXPath = "//span[normalize-space()='" + ch + "']";
      driver.findElement(By.xpath(buttonXPath)).click();
    }
  }

  private void openSlowCalculatorPage() {
    driver.findElement(By.linkText("Slow calculator")).click();
  }

  private void changeDelay(String delay) {
    driver.findElement(By.id("delay")).clear();
    driver.findElement(By.id("delay")).sendKeys(delay);
  }

  private void waitAndAssertResult(String expectedResult, String message) {
    wait.until(
        ExpectedConditions.textToBePresentInElementLocated(By.className("screen"), expectedResult));
    WebElement actualResult = driver.findElement(By.className("screen"));
    Assertions.assertEquals(expectedResult, actualResult.getText(), message);
  }
}
