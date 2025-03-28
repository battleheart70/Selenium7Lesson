package org.example;

import org.openqa.selenium.Cookie;

public class Constants {

    public static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    public static final String TEST_KEY = "testKey";
    public static final String TEST_VALUE = "testValue";
    public static final String LOREM_IPSUM = "Lorem ipsum";
    public static final String ALERT_HELLO_WORLD_TEXT = "Hello world!";
    public static final int DEFAULT_COOKIES_SIZE = 2;
    public static final Cookie DEFAULT_DATE_COOKIES = new Cookie("date", "10/07/2018");
    public static final Cookie DEFAULT_USERNAME_COOKIES = new Cookie("username", "John Doe");
    public static final String LOCAL_STORAGE_ID = "local-storage";
    public static final String SESSION_STORAGE_ID = "session-storage";
    public static final String DEFAULT_SESSION_STORAGE_KEY_1 = "lastname";
    public static final String DEFAULT_SESSION_STORAGE_VALUE_1 = "Doe";
    public static final String DEFAULT_SESSION_STORAGE_KEY_2 = "name";
    public static final String DEFAULT_SESSION_STORAGE_VALUE_2 = "John";
    public static final String ALERT_CONFIRM_TEXT = "Is this correct?";
    public static final String ALERT_CONFIRM_ACCEPT_TEXT = "You chose: true";
    public static final String ALERT_CONFIRM_CANCEL_TEXT = "You chose: false";
    public static final String ALERT_PROMPT_TEXT = "Please enter your name";
    public static final String PROMPT_TEXT_CONFIRMATION = "You typed: ";
    public static final String MODAL_TITLE = "Modal title";
    public static final String MODAL_BODY = "This is the modal body";
    public static final String MODAL_CONFIRMATION_TEXT = "You chose: ";
    public static final String MODAL_SAVE_CHANGES = "Save changes";
    public static final String MODAL_CLOSE = "Close";


    private Constants() {
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }
}
