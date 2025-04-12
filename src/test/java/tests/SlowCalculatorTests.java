package tests;

import Pages.HomePage;
import Pages.SlowCalculatorPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.TimeoutException;

@Epic("Slow Calculator Tests")
class SlowCalculatorTests extends BaseTest {
  private SlowCalculatorPage slowCalculatorPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    slowCalculatorPage = new HomePage(driver).openSlowCalculatorPage();
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/calc_test_data.csv", numLinesToSkip = 1)
  @DisplayName("Проверить медленный калькулятор с различными операциями")
  void slowCalculatorOperationsTest(String expression, String expectedResult) {
    Assertions.assertEquals(expectedResult, slowCalculatorPage.calculate(expression), "Неверный расчет!");
  }

  @Test
  @DisplayName("Проверить выражение с умножением, делением, сложением и вычитанием")
  void testExpressionWithAllOperations() {
    Assertions.assertEquals("9.5", slowCalculatorPage.calculate("4.0x2.5÷1.25+3.2-1.7="), "Неверный расчет!");
  }

  @Test
  @DisplayName("Изменить время ожидания и словить TimeOutException")
  void timeOutTest() {
    slowCalculatorPage.changeDelayTo("6");
    slowCalculatorPage.pressButtons("7+8=");
    Assertions.assertThrows(TimeoutException.class, slowCalculatorPage::waitAndGetResult,
            "Ожидалось выбрасывание исключения TimeoutException, но его не произошло.");
  }
  @Test
  @DisplayName("Убрать задержку расчета")
  void noDelayTest() {
    slowCalculatorPage.changeDelayTo("0");
    slowCalculatorPage.pressButtons("7+8=");
    Assertions.assertEquals("15", slowCalculatorPage.getResult(), "Неверный расчет!");
  }

  @Test
  @DisplayName("Проверить что кнопка C стирает ввод")
  void clearButtonTest() {
    slowCalculatorPage.pressButtons("1234C");
    Assertions.assertEquals("", slowCalculatorPage.getResult(), "Кнопка 'C' не очистила поле ввода");
  }

  @Test
  @DisplayName("Ввод нескольких '.' подряд игнорируется")
  void multipleDotsInput() {
    Assertions.assertEquals("4.6", slowCalculatorPage.calculate("1....2+3....4="), "Результат неверный!");
  }

  @Test
  @DisplayName("Ввод нескольких операторов подряд")
  void multipleOperatorsInput() {
    Assertions.assertEquals("6", slowCalculatorPage.calculate("2+-÷x3="), "Результат неверный!");
  }

  @Test
  @DisplayName("Отрицательные числа")
  void negativeNumbersTest() {
    Assertions.assertEquals("-9", slowCalculatorPage.calculate("-3-6="), "Результат неверный!");
  }
}