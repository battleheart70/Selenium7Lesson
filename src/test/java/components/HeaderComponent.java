package components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    private final WebDriver driver;

    @FindBy(className = "display-4")
    @CacheLookup
    private WebElement title;

    @FindBy(xpath = "//h5[text()='Practice site']")
    @CacheLookup
    private WebElement subTitle;

    @FindBy(className = "img-fluid")
    @CacheLookup
    private WebElement logo;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Получить заголовок в Header")
    public String getTitleText() {
        return title.getText();
    }

    @Step("Получить подзаголовок в Header")
    public String getSubTitleText() {
        return subTitle.getText();
    }

    @Step("Клик по логотипу в Header")
    public void clickLogo() {
        logo.click();
    }
}
