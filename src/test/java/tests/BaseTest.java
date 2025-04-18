package tests;

import config.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static patterns.WebDriverFactory.configProperties;
import static patterns.WebDriverFactory.createWebDriver;

class BaseTest {
  WebDriver driver;
  WebDriverWait wait;
  TestPropertiesConfig config =
      ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

  @BeforeEach
  void prepare() {
    driver = createWebDriver(configProperties.browser());
    wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.get(config.getBaseUrl());
  }

  @AfterEach
  void cleanUp() {
    driver.quit();
  }
}
