package tests;

import java.time.Duration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

class LoadingImagesTest extends BaseTest{

  @Test
  @DisplayName("Открыть страницу и подождать пока все картинки загрузятся")
  void loadingImagesTest() {
    driver.findElement(By.linkText("Loading images")).click();
    wait.pollingEvery(Duration.ofMillis(500))
        .until(ExpectedConditions.textToBe(By.id("text"), "Done!"));

    Assertions.assertTrue(
        isCorrectImageLoaded("compass"), "Изображение с id 'compass' не загружено корректно.");
    Assertions.assertTrue(
        isCorrectImageLoaded("calendar"), "Изображение с id 'calendar' не загружено корректно.");
    Assertions.assertTrue(
        isCorrectImageLoaded("award"), "Изображение с id 'award' не загружено корректно.");
    Assertions.assertTrue(
        isCorrectImageLoaded("landscape"), "Изображение с id 'landscape' не загружено корректно.");
  }

  private boolean isCorrectImageLoaded(String id) {
    WebElement image = driver.findElement(By.id(id));

    return image.isDisplayed() && image.getAttribute("src").contains(id);
  }
}
