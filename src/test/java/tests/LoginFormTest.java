package tests;

import Pages.HomePage;
import Pages.LoginFormPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

@Epic("Login Form Tests")
class LoginFormTest extends BaseTest {

  LoginFormPage loginFormPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    loginFormPage = new HomePage(driver).openLoginFormPage();
  }

  @Test
  @DisplayName("Удачный логин")
  void successLoginTest() {
    loginFormPage.loginWithCredentials(config.getUsername(), config.getPassword());
    Assertions.assertEquals("Login successful", loginFormPage.getSuccessAlertText());
  }

  @Test
  @DisplayName("Логин с неправильными данными")
  void invalidCredentialsTest() {
    loginFormPage.loginWithCredentials(LoginFormPage.INVALID_LOGIN, LoginFormPage.INVALID_PASSWORD);
    Assertions.assertEquals("Invalid credentials", loginFormPage.getDangerAlertText());
  }
}
