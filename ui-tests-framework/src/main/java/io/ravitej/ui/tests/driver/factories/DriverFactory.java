package io.ravitej.ui.tests.driver.factories;

import io.ravitej.ui.tests.config.driver.DriverTimeouts;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * @author Ravitej Aluru
 */
public interface DriverFactory {

    /**
     * Create a new instance of WebDriver using the specified hub url, desired capabilities.
     *
     * @param hubUrl
     * @param desiredCapabilities
     * @return
     */
    WebDriver create(URL hubUrl, Capabilities desiredCapabilities);

    /**
     * Creates a new instance of WebDriver using the specified hub url, desired capabilities and required capabilities.
     *
     * @param hubUrl
     * @param desiredCapabilities
     * @param requiredCapabilities
     * @return
     */
    WebDriver create(URL hubUrl, Capabilities desiredCapabilities, Capabilities requiredCapabilities);

    /**
     * Sets the given implicit wait, script timeout and page load timeout values to the given instance.
     *
     * @param driver
     * @param timeouts
     * @return
     */
    WebDriver setTimeouts(WebDriver driver, DriverTimeouts timeouts);
}
