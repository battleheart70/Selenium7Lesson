package tests;

import static Pages.LoadingImagesPage.*;

import Pages.HomePage;
import Pages.LoadingImagesPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

@Epic("Loading images tests")
class LoadingImagesTest extends BaseTest {
  LoadingImagesPage loadingImagesPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    loadingImagesPage = new HomePage(driver).openLoadingImagesPage();
  }

  @Test
  @DisplayName("Открыть страницу и подождать пока все картинки загрузятся")
  void loadingImagesTest() {
    loadingImagesPage.waitForLoadComplete();

    Assertions.assertTrue(
        loadingImagesPage.isCorrectImageLoaded(loadingImagesPage.getCompassImage()),
        "Изображение с id 'compass' не загружено корректно.");
    Assertions.assertTrue(
        loadingImagesPage.isCorrectImageLoaded(loadingImagesPage.getCalendarImage()),
        "Изображение с id 'calendar' не загружено корректно.");
    Assertions.assertTrue(
        loadingImagesPage.isCorrectImageLoaded(loadingImagesPage.getAwardImage()),
        "Изображение с id 'award' не загружено корректно.");
    Assertions.assertTrue(
        loadingImagesPage.isCorrectImageLoaded(loadingImagesPage.getLandscapeImage()),
        "Изображение с id 'landscape' не загружено корректно.");
  }
}
