package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Pages.HomePage;
import Pages.ShadowDOMPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

@Epic("Shadow DOM Tests")
class ShadowDOMTests extends BaseTest {
  private ShadowDOMPage shadowDOMPage;

  @BeforeEach
  void prepare() {
    super.prepare();
    shadowDOMPage = new HomePage(driver).openShadowDomPage();
  }

  @Test
  @DisplayName("Shadow DOM Тест")
  void shadowDOMTest() {
    String textInside = shadowDOMPage.getParagraphInsideShadowRootText();
    assertEquals("Hello Shadow DOM", textInside, "Текст внутри Shadow DOM неверен!");
  }

  @Test
  @DisplayName("Исключение при отсутствии getShadowRoot()")
  void shadowDOMThrowsExceptionTest() {
    assertThrows(
            NoSuchElementException.class,
            () -> shadowDOMPage.getParagraphOutsideShadowRootText(),
            "Ожидается исключение при попытке получить элемент вне Shadow DOM"
    );
  }
}
