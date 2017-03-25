package io.ravitej.ui.tests.driver.capability_providers;


import io.ravitej.ui.tests.config.driver.AdditionalCapability;
import io.ravitej.ui.tests.config.driver.Browser;
import io.ravitej.ui.tests.config.driver.DriverSettings;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Ravitej Aluru
 */
abstract class BaseCapabilityProvider implements CapabilityProvider {

    private final DriverSettings driverSettings;
    protected DesiredCapabilities desiredCapabilities;

    protected BaseCapabilityProvider(DriverSettings driverSettings){
        this.driverSettings = driverSettings;
    }

    protected void setBrowserDefaults(Browser browser){
        switch(browser){
            case Firefox:{
                desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, driverSettings.getUnexpectedAlertBehaviour());
                //TODO: add capability to create custom profiles and set download directory to avoid the download file dialogs.
                break;
            }
            case Chrome:{
                desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, driverSettings.getUnexpectedAlertBehaviour());
                //TODO: add capability to create ChromeOptions and set prefs including download directory to avoid the download file dialogs.
                break;
            }
//	case Unspecified:{
//	    desiredCapabilities = new DesiredCapabilities();
//	    break;
//	}
            default:
                throw new IllegalArgumentException("No browser specified or unexpected browser specified.");
        }
    }

    @Override
    public void setAdditionalCapability(AdditionalCapability additionalCapability) {
        desiredCapabilities.setCapability(additionalCapability.getName(), additionalCapability.getValue());
    }

    public abstract Capabilities finaliseCapabilities();

}

