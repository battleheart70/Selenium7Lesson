package tests;

import Pages.HomePage;
import Pages.LoadingImagesPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("Loading images tests")
class LoadingImagesTest extends BaseTest {

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
  }

  @Test
  @DisplayName("Открыть страницу и подождать пока все картинки загрузятся (soft assertions)")
  void loadingImagesSoftAssertTest() {
    LoadingImagesPage page = new HomePage(driver)
            .openLoadingImagesPage()
            .waitForLoadComplete();


    assertAll("Проверка загрузки всех изображений",
            page::assertCompassImageLoaded,
            page::assertCalendarImageLoaded,
            page::assertAwardImageLoaded,
            page::assertLandscapeImageLoaded
    );
  }
}
