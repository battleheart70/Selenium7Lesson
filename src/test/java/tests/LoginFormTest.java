package tests;

import Pages.HomePage;
import Pages.LoginFormPage;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import patterns.builder.Credentials;

@Epic("Login Form Tests")
class LoginFormTest extends BaseTest {

  private LoginFormPage loginFormPage;

  @Override
  @BeforeEach
  void prepare() {
    super.prepare();
    loginFormPage = new HomePage(driver).openLoginFormPage();
  }

  @Test
  @DisplayName("Правильные креды")
  void successLoginTest() {
    Credentials creds = Credentials.builder()
            .username(config.getUsername())
            .password(config.getPassword())
            .build();

    loginFormPage
            .loginWith(creds.getUsername(), creds.getPassword())
            .assertSuccess("Login successful");
  }

  @Test
  @DisplayName("Правильный логин, неправильный пароль")
  void wrongPasswordTest() {
    Credentials creds = Credentials.builder()
            .username(config.getUsername())
            .password("wrongPassword")
            .build();

    loginFormPage
            .loginWith(creds.getUsername(), creds.getPassword())
            .assertDanger("Invalid credentials");
  }

  @Test
  @DisplayName("Неправильный логин, правильный пароль")
  void wrongLoginTest() {
    Credentials creds = Credentials.builder()
            .username("wrongUser")
            .password(config.getPassword())
            .build();

    loginFormPage
            .loginWith(creds.getUsername(), creds.getPassword())
            .assertDanger("Invalid credentials");
  }

  @Test
  @DisplayName("Неправильный логин и неправильный пароль")
  void wrongLoginAndPasswordTest() {
    Credentials creds = Credentials.builder()
            .username("wrongUser")
            .password("wrongPassword")
            .build();

    loginFormPage
            .loginWith(creds.getUsername(), creds.getPassword())
            .assertDanger("Invalid credentials");
  }
}
