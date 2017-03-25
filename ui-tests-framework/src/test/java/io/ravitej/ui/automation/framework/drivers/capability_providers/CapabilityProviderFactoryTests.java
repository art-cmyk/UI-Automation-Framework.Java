package io.ravitej.ui.automation.framework.drivers.capability_providers;

import io.ravitej.ui.tests.config.driver.Browser;
import io.ravitej.ui.tests.config.driver.DriverSettings;
import io.ravitej.ui.tests.config.driver.HubType;
import io.ravitej.ui.tests.config.driver.Platform;
import io.ravitej.ui.tests.driver.capability_providers.CapabilityProvider;
import io.ravitej.ui.tests.driver.capability_providers.CapabilityProviderFactory;
import io.ravitej.ui.tests.driver.capability_providers.InternalCapabilityProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(PowerMockRunner.class)
public class CapabilityProviderFactoryTests {

    @Test
    public void given_valid_HubType_create_should_return_appropriate_provider() {
        //Given
        DriverSettings ds = new DriverSettings();
        ds.setHubType(HubType.Internal);
        ds.setBrowser(Browser.Chrome);
        ds.setBrowserVersion("");
        ds.setPlatform(Platform.Linux);
        //When
        CapabilityProvider cp = CapabilityProviderFactory.create(ds);
        //Then
        assertThat(cp).isInstanceOf(InternalCapabilityProvider.class);
    }

    @Test
    @PrepareForTest(HubType.class)
    public void given_invalid_HubType_create_should_throw_appropriate_error() {
        //Mock a new value for the enum
        HubType unknownHubType = PowerMockito.mock(HubType.class);
        Whitebox.setInternalState(unknownHubType, "name", "Unknown");
        Whitebox.setInternalState(unknownHubType, "ordinal", HubType.values().length);
        List<HubType> actualHubTypeValues = Arrays.asList(HubType.values());
        List<HubType> newList = new ArrayList<HubType>(actualHubTypeValues);
        newList.add(unknownHubType);

        PowerMockito.mockStatic(HubType.class);
        //TODO - Figure out how to use newList in the below line. Getting ClassCastException. So, temporarily used hardcoded array.
        PowerMockito.when(HubType.values()).thenReturn(new HubType[]{HubType.Internal, HubType.None, unknownHubType});
        PowerMockito.when(HubType.valueOf("Unknown")).thenReturn(unknownHubType);
        PowerMockito.when(HubType.valueOf("Internal")).thenReturn(HubType.Internal);
        //Given
        DriverSettings ds = new DriverSettings();
        ds.setHubType(HubType.valueOf("Unknown"));
        ds.setBrowser(Browser.Chrome);
        ds.setBrowserVersion("");
        ds.setPlatform(Platform.Linux);
        //When
        //Then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            CapabilityProvider cp = CapabilityProviderFactory.create(ds);
        }).withMessageContaining("Please provide a valid HubType. Accepted values are: ");
    }

}
