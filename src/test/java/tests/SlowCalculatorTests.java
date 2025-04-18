package tests;

import Pages.HomePage;
import Pages.SlowCalculatorPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты медленного калькулятора")
class SlowCalculatorTests extends BaseTest {
  private SlowCalculatorPage slowCalculatorPage;

  @BeforeEach
  void prepare() {
    super.prepare();
    slowCalculatorPage = new HomePage(driver).openSlowCalculatorPage();
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/calc_test_data.csv", numLinesToSkip = 1)
  @DisplayName("Проверить разные операции калькулятора")
  void slowCalculatorOperationsTest(String expression, String expectedResult) {
    assertEquals(
            expectedResult,
            slowCalculatorPage.calculate(expression),
            "Неверный расчет!"
    );
  }

  @Test
  @DisplayName("Проверить выражение со всеми операциями")
  void testExpressionWithAllOperations() {
    assertEquals(
            "9.5",
            slowCalculatorPage.calculate("4.0x2.5÷1.25+3.2-1.7="),
            "Неверный расчет!"
    );
  }

  @Test
  @DisplayName("Изменить задержку и ожидать TimeoutException")
  void timeOutTest() {
    slowCalculatorPage.changeDelayTo("6");
    slowCalculatorPage.pressButtons("7+8=");
    assertThrows(
            TimeoutException.class,
            slowCalculatorPage::waitAndGetResult,
            "Ожидалось TimeoutException, но его не произошло."
    );
  }

  @Test
  @DisplayName("Убрать задержку расчета")
  void noDelayTest() {
    slowCalculatorPage.changeDelayTo("0");
    slowCalculatorPage.pressButtons("7+8=");
    assertEquals(
            "15",
            slowCalculatorPage.getResult(),
            "Неверный расчет!"
    );
  }

  @Test
  @DisplayName("Кнопка C стирает ввод")
  void clearButtonTest() {
    slowCalculatorPage.pressButtons("1234C");
    assertEquals(
            "",
            slowCalculatorPage.getResult(),
            "Кнопка 'C' не очистила поле ввода"
    );
  }

  @Test
  @DisplayName("Игнорирование нескольких точек подряд")
  void multipleDotsInput() {
    assertEquals(
            "4.6",
            slowCalculatorPage.calculate("1....2+3....4="),
            "Результат неверный!"
    );
  }

  @Test
  @DisplayName("Игнорирование нескольких операторов подряд")
  void multipleOperatorsInput() {
    assertEquals(
            "6",
            slowCalculatorPage.calculate("2+-÷x3="),
            "Результат неверный!"
    );
  }

  @Test
  @DisplayName("Отрицательные числа")
  void negativeNumbersTest() {
    assertEquals(
            "-9",
            slowCalculatorPage.calculate("-3-6="),
            "Результат неверный!"
    );
  }
}
