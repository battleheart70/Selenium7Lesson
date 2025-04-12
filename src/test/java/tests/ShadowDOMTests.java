package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Pages.HomePage;
import Pages.ShadowDOMPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Epic("Shadow DOM Tests")
class ShadowDOMTests extends BaseTest {
  private ShadowDOMPage shadowDOMPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    shadowDOMPage = new HomePage(driver).openShadowDomPage();
  }

  @Test
  @DisplayName("Shadow DOM Тест")
  void shadowDOMTest() {
    WebElement shadowElement = shadowDOMPage.getParagraphInsideShadowRoot();
    assertEquals("Hello Shadow DOM", shadowElement.getText(), "Текст внутри Shadow DOM неверен!");
  }

  @Test
  @DisplayName("Исключение при отсутствии getShadowRoot()")
  void shadowDOMThrowsExceptionTest() {
    Assertions.assertThrows(
        NoSuchElementException.class, () -> shadowDOMPage.getParagraphOutsideShadowRoot());
  }
}
