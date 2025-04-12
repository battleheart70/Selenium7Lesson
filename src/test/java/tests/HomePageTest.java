package tests;

import Pages.HomePage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Pages.BasePage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("HomePage tests")
class HomePageTest extends BaseTest {
    HomePage homePage;

    @Override
    @BeforeEach
    void prepare(){
        super.prepare();
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Browser Title Test")
    void openHomePageCheckBrowserTitle(){
        String actualTitle = homePage.getTitle();
        assertEquals(BROWSER_TITLE, actualTitle);
    }

    @Test
    @DisplayName("Page Title Test")
    void openHomePageCheckPageTitle(){
        String actualTitle = homePage.getPageTitle();
        assertEquals(PAGE_TITLE, actualTitle);
    }

    @Test
    @DisplayName("Footer Test")
    void checkHomePageFooterText(){
        String actualText = homePage.getFooterText();
        assertTrue(actualText.contains(FOOTER_TEXT));
    }

}
