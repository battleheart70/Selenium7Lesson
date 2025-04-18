package Pages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoadingImagesPage extends BasePage {

  private static final String DONE_TEXT = "Done!";

  @FindBy(id = "compass")
  private WebElement compassImage;

  @FindBy(id = "calendar")
  private WebElement calendarImage;

  @FindBy(id = "award")
  private WebElement awardImage;

  @FindBy(id = "landscape")
  private WebElement landscapeImage;

  @FindBy(id = "text")
  private WebElement doneTextElement;

  public LoadingImagesPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ожидание завершения загрузки изображений")
  public LoadingImagesPage waitForLoadComplete() {
    wait
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .until(ExpectedConditions.textToBePresentInElement(doneTextElement, DONE_TEXT));
    return this;
  }


  private boolean isCorrectImageLoaded(WebElement image) {
    wait.until(ExpectedConditions.visibilityOf(image));
    String src = image.getAttribute("src");
    return image.isDisplayed() && src != null && !src.isEmpty();
  }

  @Step("Проверить, что изображение 'compass' загружено корректно")
  public LoadingImagesPage assertCompassImageLoaded() {
    assertTrue(isCorrectImageLoaded(compassImage),
            "Изображение 'compass' не загружено корректно.");
    return this;
  }

  @Step("Проверить, что изображение 'calendar' загружено корректно")
  public LoadingImagesPage assertCalendarImageLoaded() {
    assertTrue(isCorrectImageLoaded(calendarImage),
            "Изображение 'calendar' не загружено корректно.");
    return this;
  }

  @Step("Проверить, что изображение 'award' загружено корректно")
  public LoadingImagesPage assertAwardImageLoaded() {
    assertTrue(isCorrectImageLoaded(awardImage),
            "Изображение 'award' не загружено корректно.");
    return this;
  }

  @Step("Проверить, что изображение 'landscape' загружено корректно")
  public LoadingImagesPage assertLandscapeImageLoaded() {
    assertTrue(isCorrectImageLoaded(landscapeImage),
            "Изображение 'landscape' не загружено корректно.");
    return this;
  }
}
