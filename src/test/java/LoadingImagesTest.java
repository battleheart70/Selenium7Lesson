import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Constants.BASE_URL;

class LoadingImagesTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  void prepare() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.get(BASE_URL);
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }

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
