package io.ravitej.ui.automation.framework.drivers;

import io.ravitej.ui.tests.config.ILaunchPageHandler;
import io.ravitej.ui.tests.config.driver.Browser;
import io.ravitej.ui.tests.config.driver.DriverSettings;
import io.ravitej.ui.tests.config.driver.DriverTimeouts;
import io.ravitej.ui.tests.config.driver.Platform;
import io.ravitej.ui.tests.config.suite.AbstractSuiteSettings;
import io.ravitej.ui.tests.driver.DriverSession;
import io.ravitej.ui.tests.driver.WebDriverSession;
import io.ravitej.ui.tests.driver.capability_providers.CapabilityProviderFactory;
import io.ravitej.ui.tests.driver.capability_providers.InternalCapabilityProvider;
import io.ravitej.ui.tests.driver.factories.RemoteWebDriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CapabilityProviderFactory.class)
public class WebDriverSessionTests {

    private final String url = "http://localhost:4200";
    private RemoteWebDriverFactory mockRemoteWebDriverFactory = mock(RemoteWebDriverFactory.class);
    private RemoteWebDriver mockRemoteWebDriver = mock(RemoteWebDriver.class);
    private Navigation mockNavigation = mock(Navigation.class);
    private TargetLocator mockTargetLocator = mock(TargetLocator.class);
    private Alert mockAlert = mock(Alert.class);
    private AbstractSuiteSettings suiteSettings = new AbstractSuiteSettings() {
        @Override
        public String GetLaunchPage(int targetPage) {
            return null;
        }

        @Override
        public String GetLaunchPage(int targetPage, ILaunchPageHandler launchPageHandler) {
            return null;
        }
    };
    //private DriverSettings driverSettings;

    @Before
    public void beforeEachTest() throws Exception {
        DriverSettings driverSettings = new DriverSettings();
        driverSettings.setBrowser(Browser.Chrome);
        driverSettings.setBrowserVersion("*");
        driverSettings.setPlatform(Platform.Linux);
        driverSettings.setHubUrl("http://localhost:4444/wd/hub");
        driverSettings.setMaximiseBrowser(false);

        suiteSettings.setWebDriverSettings(driverSettings);
        PowerMockito.mockStatic(CapabilityProviderFactory.class);
        InternalCapabilityProvider icp = mock(InternalCapabilityProvider.class);
        DesiredCapabilities mockDC = mock(DesiredCapabilities.class);

        BDDMockito.given(CapabilityProviderFactory.create(Mockito.any(DriverSettings.class))).willReturn(icp);
        BDDMockito.given(icp.finaliseCapabilities()).willReturn(mockDC);
        BDDMockito.given(mockRemoteWebDriverFactory.create(Mockito.any(URL.class), Mockito.any(DesiredCapabilities.class))).willReturn(mockRemoteWebDriver);
        BDDMockito.given(mockRemoteWebDriverFactory.setTimeouts(Mockito.any(RemoteWebDriver.class), Mockito.any(DriverTimeouts.class))).willReturn(mockRemoteWebDriver);
    }

    @Test
    public void given_valid_url_start_should_navigate_to_the_url_in_the_browser() throws MalformedURLException {
        //Given
        BDDMockito.given(mockRemoteWebDriver.navigate()).willReturn(mockNavigation);
        //When
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        wds.start(url);
        //Then
        BDDMockito.then(mockRemoteWebDriver).should().navigate();
        BDDMockito.then(mockNavigation).should().to(url);
    }

    @Test
    public void given_empty_url_start_should_throw_an_exception() throws MalformedURLException {
        //Given
        BDDMockito.given(mockRemoteWebDriver.navigate()).willReturn(mockNavigation);
        //When
        //Then
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            wds.start(StringUtils.EMPTY);
        }).withMessage("The url cannot be null, blank (\"\") or empty (\"  \") string. Please specify a valid url.");
    }

    @Test
    public void given_blank_url_start_should_throw_an_exception() throws MalformedURLException {
        //Given
        BDDMockito.given(mockRemoteWebDriver.navigate()).willReturn(mockNavigation);
        //When
        //Then
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            wds.start("   ");
        }).withMessage("The url cannot be null, blank (\"\") or empty (\"  \") string. Please specify a valid url.");
    }

    @Test
    public void given_null_url_start_should_throw_an_exception() throws MalformedURLException {
        //Given
        BDDMockito.given(mockRemoteWebDriver.navigate()).willReturn(mockNavigation);
        //When
        //Then
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            wds.start(null);
        }).withMessage("The url cannot be null, blank (\"\") or empty (\"  \") string. Please specify a valid url.");
    }

    @Test
    public void deleteAllCookies_should_delete_all_cookies() throws MalformedURLException {
        //Given
        Options mockOptions = mock(Options.class);
        BDDMockito.given(mockRemoteWebDriver.manage()).willReturn(mockOptions);
        //When
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        wds.deleteAllCookies();
        //Then
        BDDMockito.then(mockOptions).should().deleteAllCookies();
    }

    @Test
    public void dispose_should_close_the_driver() throws MalformedURLException {
        //Given
        BDDMockito.willDoNothing().given(mockRemoteWebDriver).close();
        //When
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        wds.dispose();
        //Then
        BDDMockito.then(mockRemoteWebDriver).should().close();
    }

    @Test
    public void dispose_should_accept_alert_and_quit_if_close_throws_an_exception() throws MalformedURLException {
        //Given
        BDDMockito.willThrow(Exception.class).given(mockRemoteWebDriver).close();
        BDDMockito.given(mockRemoteWebDriver.switchTo()).willReturn(mockTargetLocator);
        BDDMockito.given(mockTargetLocator.alert()).willReturn(mockAlert);
        BDDMockito.willDoNothing().given(mockAlert).accept();
        //When
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        wds.dispose();
        //Then
        BDDMockito.then(mockRemoteWebDriver).should().close();
        BDDMockito.then(mockRemoteWebDriver).should().switchTo();
        BDDMockito.then(mockTargetLocator).should().alert();
        BDDMockito.then(mockAlert).should().accept();
        BDDMockito.then(mockRemoteWebDriver).should().quit();
    }

    @Test
    public void dispose_should_quit_if_close_and_alert_throw_exceptions() throws MalformedURLException {
        //Given
        BDDMockito.willThrow(Exception.class).given(mockRemoteWebDriver).close();
        BDDMockito.given(mockRemoteWebDriver.switchTo()).willReturn(mockTargetLocator);
        BDDMockito.given(mockTargetLocator.alert()).willThrow(Exception.class);
        //When
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        wds.dispose();
        //Then
        BDDMockito.then(mockRemoteWebDriver).should().close();
        BDDMockito.then(mockRemoteWebDriver).should().switchTo();
        BDDMockito.then(mockTargetLocator).should().alert();
        BDDMockito.then(mockRemoteWebDriver).should().quit();
    }

    @Test
    public void dispose_should_quit_if_close_and_accept_alert_throw_exceptions() throws MalformedURLException {
        //Given
        BDDMockito.willThrow(Exception.class).given(mockRemoteWebDriver).close();
        BDDMockito.given(mockRemoteWebDriver.switchTo()).willReturn(mockTargetLocator);
        BDDMockito.given(mockTargetLocator.alert()).willReturn(mockAlert);
        BDDMockito.willThrow(Exception.class).given(mockAlert).accept();
        //When
        DriverSession wds = new WebDriverSession(mockRemoteWebDriverFactory, suiteSettings);
        wds.dispose();
        //Then
        BDDMockito.then(mockRemoteWebDriver).should().close();
        BDDMockito.then(mockRemoteWebDriver).should().switchTo();
        BDDMockito.then(mockTargetLocator).should().alert();
        BDDMockito.then(mockAlert).should().accept();
        BDDMockito.then(mockRemoteWebDriver).should().quit();
    }
}
