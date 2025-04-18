package tests;

import static org.example.Constants.LOREM_IPSUM;

import Pages.HomePage;
import Pages.IFramesPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

@Epic("IFrames Tests")
class IFramesTests extends BaseTest {

  private IFramesPage iFramesPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    iFramesPage = new HomePage(driver).openIFramesPage();
  }

  @Test
  @DisplayName("Открой страницу и проверь текст в iframe")
  void openIFramesGetText() {
    iFramesPage.switchToIframe();
    Assertions.assertTrue(
            iFramesPage.getFirstParagraphText().contains(LOREM_IPSUM),
        "Текст в iframe не содержит ожидаемого Lorem Ipsum.");
  }

  @Test
  @DisplayName("iFrame существует и текст параграфы доступны только в нем")
  void checkIFrameExists() {

    Assertions.assertThrows(
        TimeoutException.class,
        () -> iFramesPage.getFirstParagraphText(),
        "Параграф с текстом найден вне iframe, но он должен быть доступен только внутри iframe.");

    iFramesPage.switchToIframe();
    Assertions.assertDoesNotThrow(
        () -> iFramesPage.getFirstParagraphText(), "Параграф с текстом не найден внутри iframe.");

    iFramesPage.switchToDefaultContent();
    Assertions.assertThrows(
        TimeoutException.class,
        () -> iFramesPage.getFirstParagraphText(),
        "Параграф с текстом найден вне iframe после возврата в основной контент, но он должен быть доступен только внутри iframe.");
  }
}
