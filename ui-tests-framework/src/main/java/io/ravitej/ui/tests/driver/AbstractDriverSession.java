package io.ravitej.ui.tests.driver;

import io.ravitej.ui.tests.config.suite.AbstractSuiteSettings;
import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverSession implements DriverSession{
    protected AbstractSuiteSettings suiteSettings;
    protected WebDriver driver;

    public AbstractSuiteSettings getSuiteSettings() {
        return suiteSettings;
    }

    public WebDriver getWebDriver() {
        return driver;
    }
}
