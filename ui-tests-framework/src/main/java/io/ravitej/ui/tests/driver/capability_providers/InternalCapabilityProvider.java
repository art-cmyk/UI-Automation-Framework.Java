package io.ravitej.ui.tests.driver.capability_providers;

import io.ravitej.ui.tests.config.driver.DriverSettings;
import io.ravitej.ui.tests.config.driver.Platform;
import org.openqa.selenium.Capabilities;

/**
 *
 * @author Ravitej Aluru
 */
public class InternalCapabilityProvider extends BaseCapabilityProvider{

    private String targetBrowserVersion;
    private Platform targetPlatform;

    protected InternalCapabilityProvider(DriverSettings driverSettings) {
        super(driverSettings);

        setBrowserDefaults(driverSettings.getBrowser());

        String browserVersion = driverSettings.getBrowserVersion();
        if(browserVersion.toLowerCase() != "latest" && browserVersion != "*") {
            targetBrowserVersion = browserVersion;
        }

        targetPlatform = driverSettings.getPlatform();

        switch(targetPlatform){
            case Windows:{
                desiredCapabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
                break;
            }
            case MacOS:{
                desiredCapabilities.setPlatform(org.openqa.selenium.Platform.MAC);
                break;
            }
            case Linux:{
                desiredCapabilities.setPlatform(org.openqa.selenium.Platform.LINUX);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public Capabilities finaliseCapabilities() {
        return desiredCapabilities;
    }

}
