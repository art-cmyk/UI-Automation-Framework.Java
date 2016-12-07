package driver;

import com.sun.jndi.toolkit.url.Uri;
import config.driver.AdditionalCapability;
import config.driver.DriverTimeouts;
import config.suite.AbstractSuiteSettings;
import driver.capabilityProviders.CapabilityFactory;
import driver.capabilityProviders.ICapabilityProvider;
import driver.factories.IDriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

/**
 * Created by ravit on 05/12/2016.
 */
public class WebDriverSession extends AbstractDriverSession {

    public WebDriverSession(IDriverFactory driverFactory, AbstractSuiteSettings suiteSettings) throws MalformedURLException {
        this.suiteSettings = suiteSettings;

        ICapabilityProvider capabilityProvider = CapabilityFactory.Provider(this.suiteSettings.getWebDriverSettings());
        Uri hubUrl = null;
        try {
            hubUrl = new Uri(this.suiteSettings.getWebDriverSettings().getHubUrl());
        } catch (MalformedURLException e) {
            throw new MalformedURLException(String.format("The provided hub url is not a valid url. Please check and try again. Internal Exception message: %s", e.getMessage()));
        }
        for (AdditionalCapability ac : this.suiteSettings.getWebDriverSettings().getAdditionalCapabilities()){
            if (ac.getId() != "Sample"){
                capabilityProvider.SetAdditionalCapability(ac);
            }
        }
        
        DesiredCapabilities finalCapabilities = capabilityProvider.FinalizeCapabilities();

        DriverTimeouts driverTimeouts = new DriverTimeouts(
                this.suiteSettings.getWebDriverSettings().getImplicitWaitSeconds(),
                this.suiteSettings.getWebDriverSettings().getScriptTimeoutSeconds(),
                this.suiteSettings.getWebDriverSettings().getPageLoadTimeoutSeconds(),
                this.suiteSettings.getWebDriverSettings().getCommandTimeoutSeconds());

        driver = driverFactory.create(hubUrl, finalCapabilities, driverTimeouts.getCommandTimeout());
        driver = driverFactory.setTimeouts(driver, driverTimeouts);

        if (this.suiteSettings.getWebDriverSettings().getMaximiseBrowser())
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

    }
}
