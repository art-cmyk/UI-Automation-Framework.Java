package driver;

import config.suite.AbstractSuiteSettings;
import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverSession implements IDriverSession{
    protected AbstractSuiteSettings suiteSettings;
    protected WebDriver driver;

    public AbstractSuiteSettings getSuiteSettings() {
        return suiteSettings;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
