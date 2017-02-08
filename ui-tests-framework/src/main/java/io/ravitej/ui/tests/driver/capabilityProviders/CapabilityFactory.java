package io.ravitej.ui.tests.driver.capabilityProviders;

import io.ravitej.ui.tests.config.driver.DriverSettings;
import io.ravitej.ui.tests.config.driver.HubType;

import java.util.Arrays;

public class CapabilityFactory
{
    public static ICapabilityProvider Provider(DriverSettings driverSettings)
    {
        switch (driverSettings.getHubType())
        {
            /*case BrowserStack:
            {
                return new BrowserStackCapabilityProvider(driverSettings);
            }
            case SauceLabs:
            {
                return new SauceLabsCapabilityProvider(driverSettings);
            }
            case CrossBrowserTesting:
            {
                return new CrossBrowserTestingCapabilityProvider(driverSettings);
            }
            case Internal:
            {
                return new InternalCapabilityProvider(driverSettings);
            }*/
            case None:
            {
                return null;
            }
            default:
                throw new IllegalArgumentException(String.format(
                        "Accepted values are: %s", String.join(", ", getNames(HubType.class))));
        }
    }

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}