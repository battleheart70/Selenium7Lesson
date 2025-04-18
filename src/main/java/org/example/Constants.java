package org.example;

import org.openqa.selenium.Cookie;

public class Constants {

  public static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
  public static final String TEST_KEY = "testKey";
  public static final String TEST_VALUE = "testValue";
  public static final String LOREM_IPSUM = "Lorem ipsum";
  public static final int DEFAULT_COOKIES_SIZE       = 2;
  public static final Cookie DEFAULT_DATE_COOKIES    = new Cookie("date", "10/07/2018");
  public static final Cookie DEFAULT_USERNAME_COOKIES = new Cookie("username", "John Doe");

  private Constants() {
    throw new UnsupportedOperationException("Constants class cannot be instantiated");
  }
}
