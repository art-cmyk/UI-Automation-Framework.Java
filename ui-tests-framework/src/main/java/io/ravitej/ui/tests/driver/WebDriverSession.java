package io.ravitej.ui.tests.driver;

import io.ravitej.ui.tests.config.driver.AdditionalCapability;
import io.ravitej.ui.tests.config.suite.AbstractSuiteSettings;
import io.ravitej.ui.tests.driver.capabilityProviders.CapabilityFactory;
import io.ravitej.ui.tests.driver.capabilityProviders.ICapabilityProvider;
import io.ravitej.ui.tests.driver.factories.DriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ravitej Aluru
 */
public class WebDriverSession extends AbstractDriverSession {

    public WebDriverSession(DriverFactory driverFactory, AbstractSuiteSettings suiteSettings) throws MalformedURLException {
        this.suiteSettings = suiteSettings;

        ICapabilityProvider capabilityProvider = CapabilityFactory.Provider(this.suiteSettings.getWebDriverSettings());
        URL hubUrl = null;
        try {
            hubUrl = new URL(this.suiteSettings.getWebDriverSettings().getHubUrl());
        } catch (MalformedURLException e) {
            throw new MalformedURLException(String.format("The provided hub url is not a valid url. Please check and try again. Internal Exception message: %s", e.getMessage()));
        }
        for (AdditionalCapability ac : this.suiteSettings.getWebDriverSettings().getAdditionalCapabilities()){
            if (ac.getId() != "Sample"){
                capabilityProvider.SetAdditionalCapability(ac);
            }
        }
        
        DesiredCapabilities finalCapabilities = capabilityProvider.FinalizeCapabilities();

        driver = driverFactory.create(hubUrl, finalCapabilities);
        driver = driverFactory.setTimeouts(driver, this.suiteSettings.getWebDriverSettings().getDriverTimeouts());

        if (this.suiteSettings.getWebDriverSettings().isMaximiseBrowser())
        {
            driver.manage().window().maximize();
        }
    }

    public void start(String url) {
        if (url != null && !StringUtils.isEmpty(url))
        {
            driver.navigate().to(url);
            //Logs.WriteInfoLogEntry(GetType().ToString(), "Started new driver session with url: {0}", url);
        }
        else
        {
            //Logs.WriteErrorLogEntry(GetType().ToString(), "Attempt to start the driver session failed. The url cannot be null or empty string. Please specify a valid url.");
            //throw new ArgumentException("The url cannot be null or empty string. Please specify a valid url.", nameof(url));
        }
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
        //Logs.WriteInfoLogEntry(GetType().ToString(), "Deleted all cookies from the current driver session.");
    }

    public void dispose() {
        try{
            driver.close();
        }
        catch(Exception e){
            //ignore
        }

        try{
            driver.switchTo().alert().accept();
            driver.quit();
        }
        catch(Exception ex){
            driver.quit();
        }
    }
}
