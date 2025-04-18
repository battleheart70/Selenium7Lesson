package tests;

import Pages.HomePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("HomePage tests")
class HomePageTest extends BaseTest {

    private static final String PAGE_TITLE = "Hands-On Selenium WebDriver with Java";
    private static final String BROWSER_TITLE = "Hands-On Selenium WebDriver with Java";
    private static final String FOOTER_TEXT = "Copyright © 2021-2025";
    private static final String HEADER_TITLE = "Hands-On Selenium WebDriver with Java";
    private static final String HEADER_SUBTITLE = "Practice site";
    private static final String LOGO_URL = "https://github.com/bonigarcia/selenium-webdriver-java";

    private HomePage homePage;

    @Override
    @BeforeEach
    void prepare() {
        super.prepare();
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Проверка Browser Title")
    void openHomePageCheckBrowserTitle() {
        String actual = homePage.getTitle();
        assertEquals(BROWSER_TITLE, actual, "Заголовок браузера неверен");
    }

    @Test
    @DisplayName("Проверка Page Title")
    void openHomePageCheckPageTitle() {
        String actual = homePage.getPageTitle();
        assertEquals(PAGE_TITLE, actual, "Заголовок страницы неверен");
    }

    @Test
    @DisplayName("Проверка заголовка HeaderComponent")
    void checkHeaderTitle() {
        String actual = homePage.header().getTitleText();
        assertEquals(HEADER_TITLE, actual, "Текст заголовка в шапке неверен");
    }

    @Test
    @DisplayName("Проверка подзаголовка HeaderComponent")
    void checkHeaderSubtitle() {
        String actual = homePage.header().getSubTitleText();
        assertEquals(HEADER_SUBTITLE, actual, "Текст подзаголовка в шапке неверен");
    }

    @Test
    @DisplayName("Проверка FooterComponent")
    void checkFooterText() {
        String actual = homePage.footer().getFooterText();
        assertTrue(actual.contains(FOOTER_TEXT), "Текст в подвале неверен");
    }

    @Test
    @DisplayName("Клик по лого переходит на гитхаб автора")
    void checkLogoClick(){
        homePage.header().clickLogo();
        assertEquals(LOGO_URL, homePage.getCurrentUrl(),"Неправильный URL после клика по лого!" );
    }
}
