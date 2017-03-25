package io.ravitej.ui.automation.framework.drivers.factories;

import io.ravitej.ui.tests.config.driver.DriverTimeouts;
import io.ravitej.ui.tests.driver.factories.DriverFactory;
import io.ravitej.ui.tests.driver.factories.RemoteWebDriverFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RemoteWebDriverFactory.class)
public class RemoteWebDriverFactoryTests {

    @Test
    public void given_valid_url_and_capabilities_create_should_return_RemoteWebDriver() throws Exception {
        //Given
        RemoteWebDriver mockRemoteWebDriver = mock(RemoteWebDriver.class);
        PowerMockito.whenNew(RemoteWebDriver.class)
                .withParameterTypes(URL.class, Capabilities.class)
                .withArguments(Mockito.any(URL.class), Mockito.any(Capabilities.class))
                .thenReturn(mockRemoteWebDriver);
        //When
        DriverFactory df = new RemoteWebDriverFactory();
        WebDriver driver = df.create(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        //Then
        PowerMockito.verifyNew(RemoteWebDriver.class)
                .withArguments(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(driver).isNotNull()
                    .isInstanceOf(RemoteWebDriver.class);
        });
    }

    @Test
    public void given_valid_url_and_both_capabilities_create_should_return_RemoteWebDriver() throws Exception {
        //Given
        RemoteWebDriver mockRemoteWebDriver = mock(RemoteWebDriver.class);
        PowerMockito.whenNew(RemoteWebDriver.class)
                .withParameterTypes(URL.class, Capabilities.class, Capabilities.class)
                .withArguments(Mockito.any(URL.class), Mockito.any(Capabilities.class), Mockito.any(Capabilities.class))
                .thenReturn(mockRemoteWebDriver);
        //When
        DriverFactory df = new RemoteWebDriverFactory();
        WebDriver driver = df.create(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome(), DesiredCapabilities.chrome());
        //Then
        PowerMockito.verifyNew(RemoteWebDriver.class)
                .withArguments(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome(), DesiredCapabilities.chrome());
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(driver).isNotNull()
                    .isInstanceOf(RemoteWebDriver.class);
        });
    }

    @Test
    public void given_valid_WebDriver_and_DriverTimeouts_setTimeouts_should_set_appropriate_timeouts_on_WebDriver_and_return_RemoteWebDriver() {
        //Given
        DriverTimeouts driverTimeouts = new DriverTimeouts();
        driverTimeouts.setImplicitWaitSeconds(10);
        driverTimeouts.setPageLoadTimeoutSeconds(60);
        driverTimeouts.setScriptTimeoutSeconds(20);
        RemoteWebDriver mockRemoteWebDriver = mock(RemoteWebDriver.class);
        Options mockOptions = mock(Options.class);
        Timeouts mockTimeouts = mock(Timeouts.class);
        BDDMockito.given(mockRemoteWebDriver.manage()).willReturn(mockOptions);
        BDDMockito.given(mockOptions.timeouts()).willReturn(mockTimeouts);
        BDDMockito.given(mockTimeouts.implicitlyWait(Mockito.any(long.class), Mockito.any(TimeUnit.class))).willReturn(mockTimeouts);
        BDDMockito.given(mockTimeouts.pageLoadTimeout(Mockito.any(long.class), Mockito.any(TimeUnit.class))).willReturn(mockTimeouts);
        BDDMockito.given(mockTimeouts.setScriptTimeout(Mockito.any(long.class), Mockito.any(TimeUnit.class))).willReturn(mockTimeouts);
        //When
        DriverFactory driverFactory = new RemoteWebDriverFactory();
        WebDriver driver = driverFactory.setTimeouts(mockRemoteWebDriver, driverTimeouts);
        //Then
        BDDMockito.then(mockTimeouts).should().implicitlyWait(10, TimeUnit.SECONDS);
        BDDMockito.then(mockTimeouts).should().pageLoadTimeout(60, TimeUnit.SECONDS);
        BDDMockito.then(mockTimeouts).should().setScriptTimeout(20, TimeUnit.SECONDS);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(driver).isNotNull()
                    .isInstanceOf(RemoteWebDriver.class);
        });
    }

    @Test
    public void given_valid_WebDriver_and_empty_DriverTimeouts_setTimeouts_should_set_appropriate_timeouts_on_WebDriver_and_return_RemoteWebDriver() {
        //Given
        DriverTimeouts driverTimeouts = new DriverTimeouts();
        RemoteWebDriver mockRemoteWebDriver = mock(RemoteWebDriver.class);
        Options mockOptions = mock(Options.class);
        Timeouts mockTimeouts = mock(Timeouts.class);
        BDDMockito.given(mockRemoteWebDriver.manage()).willReturn(mockOptions);
        BDDMockito.given(mockOptions.timeouts()).willReturn(mockTimeouts);
        BDDMockito.given(mockTimeouts.implicitlyWait(Mockito.any(long.class), Mockito.any(TimeUnit.class))).willReturn(mockTimeouts);
        BDDMockito.given(mockTimeouts.pageLoadTimeout(Mockito.any(long.class), Mockito.any(TimeUnit.class))).willReturn(mockTimeouts);
        BDDMockito.given(mockTimeouts.setScriptTimeout(Mockito.any(long.class), Mockito.any(TimeUnit.class))).willReturn(mockTimeouts);
        //When
        DriverFactory driverFactory = new RemoteWebDriverFactory();
        WebDriver driver = driverFactory.setTimeouts(mockRemoteWebDriver, driverTimeouts);
        //Then
        BDDMockito.then(mockTimeouts).should().implicitlyWait(0, TimeUnit.SECONDS);
        BDDMockito.then(mockTimeouts).should().pageLoadTimeout(30, TimeUnit.SECONDS);
        BDDMockito.then(mockTimeouts).should().setScriptTimeout(20, TimeUnit.SECONDS);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(driver).isNotNull()
                    .isInstanceOf(RemoteWebDriver.class);
        });
    }
}
