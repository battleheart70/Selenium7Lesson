package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

class ShadowDOMTests extends BaseTest {

  @Test
  @DisplayName("Shadow DOM Тест")
  void shadowDOMTest() {
    driver.findElement(By.linkText("Shadow DOM")).click();
    WebElement shadowElement = getContentElement().getShadowRoot().findElement(By.cssSelector("p"));
    assertEquals("Hello Shadow DOM", shadowElement.getText(), "Текст внутри Shadow DOM неверен!");
  }

  @Test
  @DisplayName("Исключение при отсутствии getShadowRoot()")
  void shadowDOMThrowsExceptionTest() {
    driver.findElement(By.linkText("Shadow DOM")).click();
    Assertions.assertThrows(
        NoSuchElementException.class, () -> getContentElement().findElement(By.cssSelector("p")));
  }

  private WebElement getContentElement() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
  }
}
