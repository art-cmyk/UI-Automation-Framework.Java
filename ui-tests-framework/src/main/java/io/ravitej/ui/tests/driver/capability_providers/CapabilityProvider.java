package io.ravitej.ui.tests.driver.capability_providers;

import io.ravitej.ui.tests.config.driver.AdditionalCapability;
import org.openqa.selenium.Capabilities;

/**
 * @author Ravitej Aluru
 */
public interface CapabilityProvider {

    void setAdditionalCapability(AdditionalCapability additionalCapability);

    Capabilities finaliseCapabilities();
}

