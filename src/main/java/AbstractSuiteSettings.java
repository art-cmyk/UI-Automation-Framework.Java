/**
 * Created by ravit on 05/12/2016.
 */
public abstract class AbstractSuiteSettings implements ISuiteSettings {
    /**
     * Settings applicable to webdriver such as huburl, browser, platform,
     * timeouts etc. as defined by <see cref="DriverSettings"/>.
     */
    protected DriverSettings webDriverSettings;

    /**
     * Settings applicable to the application under test (AUT)
     */
    protected AutSettings applicationUnderTestSettings;

    public DriverSettings getWebDriverSettings() {return webDriverSettings;}

    public AutSettings getApplicationUnderTestSettings() {return applicationUnderTestSettings;}
//
//    public abstract String GetLaunchPage(int targetPage);
//
//    public abstract String GetLaunchPage(int targetPage, ILaunchPageHandler launchPageHandler);
}
