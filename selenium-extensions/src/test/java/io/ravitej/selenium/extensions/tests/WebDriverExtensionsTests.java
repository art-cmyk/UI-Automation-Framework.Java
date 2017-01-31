package io.ravitej.selenium.extensions.tests;

import io.ravitej.selenium.extensions.WebDriverExtensions;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.TargetLocator;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Ravitej Aluru
 * Tests for WebDriverExtensions
 */
public class WebDriverExtensionsTests {

    private WebDriver webDriver = mock(WebDriver.class, Mockito.withSettings().extraInterfaces(TakesScreenshot.class));
    private static TargetLocator targetLocator = mock(TargetLocator.class);
    private static Alert alert = mock(Alert.class);
    private static File file = mock(File.class);

    @Before
    public void beforeTest(){
        when(webDriver.switchTo()).thenReturn(targetLocator);
        when(targetLocator.alert()).thenReturn(alert);
    }

    @Test
    public void is_alert_displayed_should_return_true_and_alert_text_if_alert_displayed(){
        final String alertText = "some alert text";
        when(alert.getText()).thenReturn(alertText);
        Pair<Boolean, String> p = WebDriverExtensions.isAlertDisplayed(webDriver);
        assertThat(p).extracting("key", "value").containsExactly(true, alertText);
    }

    @Test
    public void is_alert_displayed_should_return_false_and_empty_string_if_alert_is_not_displayed(){
        when(alert.getText()).thenThrow(NoAlertPresentException.class);
        Pair<Boolean, String> p = WebDriverExtensions.isAlertDisplayed(webDriver);
        assertThat(p).extracting("key", "value").containsExactly(false, "");
    }

    @Test
    public void take_screenshot_should_return_file_if_successful_on_first_attempt(){
        when(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE)).thenReturn(file);
        File screenshot = WebDriverExtensions.takeScreenshot(webDriver);
        assertThat(screenshot)
                .isInstanceOf(File.class)
                .isNotNull();
    }

    @Test
    public void take_screenshot_should_return_file_if_successful_on_second_attempt(){
        when(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE))
                .thenThrow(new WebDriverException())
                .thenReturn(file);
        File screenshot = WebDriverExtensions.takeScreenshot(webDriver);
        assertThat(screenshot)
                .isInstanceOf(File.class)
                .isNotNull();
    }

    @Test(expected = WebDriverException.class)
    public void take_screenshot_should_throw_webdriverexception_if_not_successful_on_second_attempt(){
        when(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE))
                .thenThrow(new WebDriverException());
        WebDriverExtensions.takeScreenshot(webDriver);
    }

    @Test
    public void take_screenshot_should_wait_for_given_amount_of_time_if_not_successful_on_first_attempt(){
        when(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE))
                .thenThrow(new WebDriverException())
                .thenReturn(file);
        final long waitTime = 1000;
        long before = System.currentTimeMillis();
        WebDriverExtensions.takeScreenshot(webDriver, waitTime);
        long after = System.currentTimeMillis();
        //checking that the method took at least 1 second to execute since the waitTime is 1 second.
        assertThat(after-before).isGreaterThanOrEqualTo(waitTime);
    }
}