package io.ravitej.ui.tests.config.driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the settings for UI automation tool applicable to a suite of tests. For example, Selenium WebDriver.
 * @author Ravitej Aluru
 */
public class DriverSettings {
    private HubType hubType;
    private String hubUrl;
    private Browser browser;
    private String browserVersion;
    private Platform platform;
    private String platformVersion;
    private DriverTimeouts driverTimeouts;
    private Boolean maximiseBrowser;
    private List<AdditionalCapability> additionalCapabilities;
    private String downloadDirectory;
    private UnexpectedAlertBehaviour unexpectedAlertBehaviour;

    public DriverSettings(){
        additionalCapabilities = new ArrayList<AdditionalCapability>();
        driverTimeouts = new DriverTimeouts();
    }

    public HubType getHubType(){
        return hubType;
    }

    /**
     * @param hubType The type of hub being used for the tests.
     * HubType = "Internal" indicates that the browser is started on a remote machine inside the firewall.
     * HubType = "None" indicates that the browser is started on the local machine.
     */
    public void setHubType(HubType hubType){
        this.hubType = hubType;
    }

    public String getHubUrl(){
        return hubUrl;
    }

    /**
     *
     * @param hubUrl The url of the Selenium hub.
     */
    public void setHubUrl(String hubUrl){
        this.hubUrl = hubUrl;
    }

    public Platform getPlatform() {
        return platform;
    }

    /**
     * @param platform The platform to execute a suite of tests on.
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Browser getBrowser() {
        return browser;
    }

    /**
     * @param browser The browser to execute a suite of tests in.
     */
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    /**
     * @param browserVersion The version of the browser to execute a suite of tests in.
     * The string 'Latest' or '*' indicates that the latest version should be used.
     */
    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getDownloadDirectory() {
        return downloadDirectory;
    }

    /**
     * @param downloadDirectory Download directory path to set in browser profile for automatic (dialog-less) file download
     */
    public void setDownloadDirectory(String downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }

    public List<AdditionalCapability> getAdditionalCapabilities() {
        return additionalCapabilities;
    }

    /**
     * @param additionalCapabilities Any additional capabilities that are passed on to the WebDriver instance.
     */
    public void setAdditionalCapabilities(List<AdditionalCapability> additionalCapabilities) {
        this.additionalCapabilities = additionalCapabilities;
    }

    public Boolean isMaximiseBrowser() {
        return maximiseBrowser;
    }

    /**
     * @param maximiseBrowser Indicates whether the browser should be maximised or not.
     */
    public void setMaximiseBrowser(Boolean maximiseBrowser) {
        this.maximiseBrowser = maximiseBrowser;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    /**
     * @param platformVersion The version of the platform (Operating System) to execute a suite of tests on.
     * The string 'Latest' or '*' indicates that the latest version should be used.
     */
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public DriverTimeouts getDriverTimeouts() {
        return driverTimeouts;
    }

    /**
     * @param driverTimeouts The various timeouts to set to the WebDriver instance.
     */
    public void setDriverTimeouts(DriverTimeouts driverTimeouts) {
        this.driverTimeouts = driverTimeouts;
    }

    public UnexpectedAlertBehaviour getUnexpectedAlertBehaviour() {
        return unexpectedAlertBehaviour;
    }

    /**
     * @param unexpectedAlertBehaviour How should the session respond to an unexpected alert.
     */
    public void setUnexpectedAlertBehaviour(UnexpectedAlertBehaviour unexpectedAlertBehaviour) {
        this.unexpectedAlertBehaviour = unexpectedAlertBehaviour;
    }
}
