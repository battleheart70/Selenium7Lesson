package tests;


import Pages.HomePage;
import Pages.InfiniteScrollPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

@Epic("Infinite Scroll Tests")
class InfiniteScrollTests extends BaseTest {

  private InfiniteScrollPage infiniteScrollPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    infiniteScrollPage = new HomePage(driver).openInfiniteScrollPage();
  }

  @Test
  @DisplayName("Проверка бесконечного скролла")
  void infiniteScrollTest() {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    int maxScrolls = 5;

    infiniteScrollPage.waitUntilParagraphsLoad();
    infiniteScrollPage.assertScrollForNTimes(maxScrolls, js);
  }
}
