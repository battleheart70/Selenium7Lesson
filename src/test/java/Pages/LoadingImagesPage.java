package Pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;

public class LoadingImagesPage extends BasePage {

  public static final String DONE_TEXT = "Done!";

  private final By compassImage = By.id("compass");
  private final By calendarImage = By.id("calendar");
  private final By awardImage = By.id("award");
  private final By landscapeImage = By.id("landscape");
  private final By doneTextElement = By.id("text");

  public LoadingImagesPage(WebDriver driver) {
    super(driver);
  }

  @Step("Ожидание завершения загрузки изображений")
  public void waitForLoadComplete() {
    wait.withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .until(ExpectedConditions.textToBe(doneTextElement, DONE_TEXT));
  }

  @Step("Проверка, что изображение с локатором {0} загружено и отображается корректно")
  public boolean isCorrectImageLoaded(By imageLocator) {
    WebElement image = driver.findElement(imageLocator);
    String src = image.getAttribute("src");
    return image.isDisplayed() && src.contains(imageLocator.toString().split(":")[1].trim());
  }

  @Step("Получить локатор изображения компаса")
  public By getCompassImage() {
    return compassImage;
  }

  @Step("Получить локатор изображения календаря")
  public By getCalendarImage() {
    return calendarImage;
  }

  @Step("Получить локатор изображения награды")
  public By getAwardImage() {
    return awardImage;
  }

  @Step("Получить локатор изображения пейзажа")
  public By getLandscapeImage() {
    return landscapeImage;
  }
}
