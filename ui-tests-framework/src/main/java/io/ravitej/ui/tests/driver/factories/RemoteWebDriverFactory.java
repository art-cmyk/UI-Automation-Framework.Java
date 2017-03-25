package io.ravitej.ui.tests.driver.factories;

import io.ravitej.ui.tests.config.driver.DriverTimeouts;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Implements DriverFactory to produce RemoteWebDriver instances.
 *
 * @author Ravitej Aluru
 */
public class RemoteWebDriverFactory implements DriverFactory {

    @Override
    public WebDriver create(URL hubUrl, Capabilities desiredCapabilities) {
        return new RemoteWebDriver(hubUrl, desiredCapabilities);
    }

    @Override
    public WebDriver create(URL hubUrl, Capabilities desiredCapabilities, Capabilities requiredCapabilities) {
        return new RemoteWebDriver(hubUrl, desiredCapabilities, requiredCapabilities);
    }

    @Override
    public WebDriver setTimeouts(WebDriver webDriver, DriverTimeouts timeouts) {
        webDriver.manage().timeouts().implicitlyWait(timeouts.getImplicitWaitSeconds(), TimeUnit.SECONDS)
                                     .pageLoadTimeout(timeouts.getPageLoadTimeoutSeconds(), TimeUnit.SECONDS)
                                     .setScriptTimeout(timeouts.getScriptTimeoutSeconds(), TimeUnit.SECONDS);
        return webDriver;
    }
}