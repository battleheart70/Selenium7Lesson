package components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterComponent {
    private final WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "footer.footer")
    @CacheLookup
    private WebElement footerContainer;

    @FindBy(css = "span.text-muted")
    @CacheLookup
    private WebElement footerText;

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Получить текст footer")
    public String getFooterText() {
        wait.until(ExpectedConditions.visibilityOf(footerContainer));
        return footerText.getText();
    }

    @Step("Кликнуть по элементу в footer: {element}")
    public void clickFooterElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}