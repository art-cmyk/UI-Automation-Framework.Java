package io.ravitej.ui.tests.driver;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author Ravitej Aluru
 */
public interface DriverSession {
    /**
     * Gets the instantiated WebDriver of this session.
     * @return A WebDriver instance.
     */
    WebDriver getWebDriver();
    /**
     * Starts the AUT by navigating to the given url.
     * @param url The url to start the session, typically the AUT's index url.
     */
    void start(String url);
    /**
     * Deletes all cookies from the current session.
     */
    void deleteAllCookies();
    /**
     * Disposes the current session.
     */
    void dispose();
}