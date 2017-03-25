package io.ravitej.ui.tests.config.suite;

import io.ravitej.ui.tests.config.aut.AutSettings;
import io.ravitej.ui.tests.config.driver.DriverSettings;

/**
 * Created by ravit on 05/12/2016.
 */
public abstract class AbstractSuiteSettings implements SuiteSettings {
    /**
     * Settings applicable to webdriver such as huburl, browser, platform,
     * timeouts etc. as defined by <see cref="config.driver.DriverSettings"/>.
     */
    private DriverSettings webDriverSettings;

    /**
     * Settings applicable to the application under test (AUT)
     */
    private AutSettings applicationUnderTestSettings;

    public DriverSettings getWebDriverSettings() {return webDriverSettings;}

    public AutSettings getApplicationUnderTestSettings() {return applicationUnderTestSettings;}

    public void setWebDriverSettings(DriverSettings webDriverSettings) {
        this.webDriverSettings = webDriverSettings;
    }

    public void setApplicationUnderTestSettings(AutSettings applicationUnderTestSettings) {
        this.applicationUnderTestSettings = applicationUnderTestSettings;
    }
//
//    public abstract String GetLaunchPage(int targetPage);
//
//    public abstract String GetLaunchPage(int targetPage, ILaunchPageHandler launchPageHandler);
}
