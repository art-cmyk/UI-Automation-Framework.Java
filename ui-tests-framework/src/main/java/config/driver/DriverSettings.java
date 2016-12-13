package config.driver;

import config.driver.Browser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravit on 05/12/2016.
 */
public class DriverSettings {
    /**
     * Initialises a new instance of <see cref="config.driver.DriverSettings"/> with the following default values.
     * <see cref="CommandTimeoutSeconds"/> = 120,
     * <see cref="ImplicitWaitSeconds"/> = 0,
     * <see cref="ScriptTimeoutSeconds"/> = 10,
     * <see cref="PageLoadTimeoutSeconds"/> = 60,
     * <see cref="MaximiseBrowser"/> = 10,
     * and initialises a new empty list of <see cref="AdditionalCapabilities"/>.
     */
    public DriverSettings()
    {
        commandTimeoutSeconds = 120;
        implicitWaitSeconds = 0;
        scriptTimeoutSeconds = 10;
        pageLoadTimeoutSeconds = 60;
        maximiseBrowser = true;
        additionalCapabilities = new ArrayList<AdditionalCapability>();
    }

    /**
     * The type of hub being used for the tests.
     * <see cref="Drivers.CapabilityProviders.config.driver.HubType"/> = "Internal" indicates that the browser is started on a remote machine inside the firewall.
     * <see cref="Drivers.CapabilityProviders.config.driver.HubType"/> = "None" indicates that the browser is started on the local machine.
     */
    private HubType hubType;

    public HubType getHubType(){
        return hubType;
    }

    public void setHubType(HubType hubType){
        this.hubType = hubType;
    }

    /**
     * The url of the Selenium hub.
     */
    private String hubUrl;

    public String getHubUrl(){
        return hubUrl;
    }

    public void setHubUrl(String hubUrl){
        this.hubUrl = hubUrl;
    }
    /**
     * The browser to execute a suite of tests in.
     */
    private Browser browser;

    /**
     * The version of the browser to execute a suite of tests in.
     * The string 'Latest' or '*' indicates that the latest version should be used.
     */
    private String browserVersion;


    /**
     * The platform to execute a suite of tests on.
     */
    private Platform platform;

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * The version of the platform (Operating System) to execute a suite of tests on.
     * The string 'Latest' or '*' indicates that the latest version should be used.
     */
    private String platformVersion;

    /**
     * The command timeout value in seconds to assign to the webdriver instance.
     * CommandTimeout is the maximum amount of time to wait for each command sent to the browser.
     */
    private int commandTimeoutSeconds;

    /**
     * The implicit wait timeout value in seconds to assign to the webdriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.ImplicitlyWait"/> for more information.
     */
    private int implicitWaitSeconds;

    /**
     * The script timeout value in seconds to assign to the webdriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.SetScriptTimeout"/> for more information.
     */
    private int scriptTimeoutSeconds;

    /**
     * The page load timeout value in seconds to assign to the webdriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.SetPageLoadTimeout"/> for more information.
     */
    private int pageLoadTimeoutSeconds;

    /**
     * Indicates whether the browser should be maximised or not.
     */
    private boolean maximiseBrowser;

    /**
     * Any additional capabilities that are passed on to the webdriver instance.
     */
    private List<AdditionalCapability> additionalCapabilities;

    /**
     * Download directory path to set in browser profile for automatic (dialog-less) file download
     */
    private String downloadDirectory;

    /// <summary>
    /// Assigns following default values to a new instance of <see cref="config.driver.DriverSettings"/>.
    /// <see cref="config.driver.Browser"/> = Chrome,
    /// <see cref="BrowserVersion"/> = * (latest),
    /// <see cref="config.driver.Platform"/> = Windows,
    /// <see cref="PlatformVersion"/> = 7 (Windows 7),
    /// <see cref="config.driver.HubType"/> = "Ravitej" (tests are directed to a hub inside the firewall - a hub running on the ADP network).
    /// </summary>
    /// <returns></returns>
    /*public config.driver.DriverSettings SelfHydrate()
    {
        var retVal = new config.driver.DriverSettings
        {
            config.driver.Browser = new PermittedSettingsValidatingItem<config.driver.Browser>("Chrome"),
                    BrowserVersion = "*",
                    config.driver.HubType = new PermittedSettingsValidatingItem<config.driver.HubType>("Internal"),
                    HubUrl = "http://localhost:4444/wd/hub",
                    config.driver.Platform = new PermittedSettingsValidatingItem<config.driver.Platform>("Windows"),
                    PlatformVersion = "7",
                    DownloadDirectory = @"C:\SeleniumDownloads"
        };

        retVal.AdditionalCapabilities.Add(new config.driver.AdditionalCapability
        {
            Id = "Sample",
                    Value = "Sample Value"
        });

        return retVal;
    }*/


    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public int getCommandTimeoutSeconds() {
        return commandTimeoutSeconds;
    }

    public void setCommandTimeoutSeconds(int commandTimeoutSeconds) {
        this.commandTimeoutSeconds = commandTimeoutSeconds;
    }

    public int getImplicitWaitSeconds() {
        return implicitWaitSeconds;
    }

    public void setImplicitWaitSeconds(int implicitWaitSeconds) {
        this.implicitWaitSeconds = implicitWaitSeconds;
    }

    public int getScriptTimeoutSeconds() {
        return scriptTimeoutSeconds;
    }

    public void setScriptTimeoutSeconds(int scriptTimeoutSeconds) {
        this.scriptTimeoutSeconds = scriptTimeoutSeconds;
    }

    public int getPageLoadTimeoutSeconds() {
        return pageLoadTimeoutSeconds;
    }

    public void setPageLoadTimeoutSeconds(int pageLoadTimeoutSeconds) {
        this.pageLoadTimeoutSeconds = pageLoadTimeoutSeconds;
    }

    public boolean getMaximiseBrowser() {
        return maximiseBrowser;
    }

    public void setMaximiseBrowser(boolean maximiseBrowser) {
        this.maximiseBrowser = maximiseBrowser;
    }

    public List<AdditionalCapability> getAdditionalCapabilities() {
        return additionalCapabilities;
    }

    public void setAdditionalCapabilities(List<AdditionalCapability> additionalCapabilities) {
        this.additionalCapabilities = additionalCapabilities;
    }

    public String getDownloadDirectory() {
        return downloadDirectory;
    }

    public void setDownloadDirectory(String downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }
}
