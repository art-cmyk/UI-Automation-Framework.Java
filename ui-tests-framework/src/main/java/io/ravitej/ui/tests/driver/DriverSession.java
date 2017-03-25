package io.ravitej.ui.tests.driver;

import org.openqa.selenium.WebDriver;

/**
 * Created by ravit on 05/12/2016.
 */
public interface DriverSession {
    void start(String url);

    void deleteAllCookies();

    void dispose();

    WebDriver getWebDriver();
}