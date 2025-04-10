package tests;

import static org.example.Constants.LOREM_IPSUM;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

class IFramesTests extends BaseTest{


  @Test
  @DisplayName("Открой страницу и проверь текст в iframe")
  void openIFramesGetText() {
    driver.findElement(By.linkText("IFrames")).click();
    driver.switchTo().frame(driver.findElement(By.id("my-iframe")));
    WebElement firstParagraph =
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.lead")));
    Assertions.assertTrue(
        firstParagraph.getText().contains(LOREM_IPSUM),
        "Текст в iframe не содержит ожидаемого Lorem Ipsum.");
  }

  @Test
  @DisplayName("iFrame существует и текст параграфы доступны только в нем")
  void checkIFrameExists() {
    driver.findElement(By.linkText("IFrames")).click();
    Assertions.assertThrows(
        NoSuchElementException.class,
        this::findParagraphInFrame,
        "Параграф с текстом найден вне iframe, но он должен быть доступен только внутри iframe.");

    driver.switchTo().frame(driver.findElement(By.id("my-iframe")));
    Assertions.assertDoesNotThrow(
        this::findParagraphInFrame, "Параграф с текстом не найден внутри iframe.");

    driver.switchTo().defaultContent();
    Assertions.assertThrows(
        NoSuchElementException.class,
        this::findParagraphInFrame,
        "Параграф с текстом найден вне iframe после возврата в основной контент, но он должен быть доступен только внутри iframe.");
  }

  private WebElement findParagraphInFrame() {
    return driver.findElement(By.cssSelector("p.lead"));
  }
}
