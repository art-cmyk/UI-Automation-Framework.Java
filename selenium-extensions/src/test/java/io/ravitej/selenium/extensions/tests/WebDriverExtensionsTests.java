package io.ravitej.selenium.extensions.tests;

import io.ravitej.selenium.extensions.WebDriverExtensions;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.TargetLocator;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for WebDriverExtensions.
 *
 * @author Ravitej Aluru
 */
public class WebDriverExtensionsTests {

    private WebDriver mockWebDriver = mock(WebDriver.class, Mockito.withSettings().extraInterfaces(TakesScreenshot.class));
    private TargetLocator mockTargetLocator = mock(TargetLocator.class);
    private Alert mockAlert = mock(Alert.class);
    private File mockFile = mock(File.class);

    @Before
    public void beforeTest() {
        when(mockWebDriver.switchTo()).thenReturn(mockTargetLocator);
        when(mockTargetLocator.alert()).thenReturn(mockAlert);
    }

    @Test
    public void is_alert_displayed_should_return_true_and_alert_text_if_alert_displayed() {
        final String alertText = "some mockAlert text";
        when(mockAlert.getText()).thenReturn(alertText);
        Pair<Boolean, String> p = WebDriverExtensions.isAlertDisplayed(mockWebDriver);
        assertThat(p).extracting("key", "value").containsExactly(true, alertText);
    }

    @Test
    public void is_alert_displayed_should_return_false_and_empty_string_if_alert_is_not_displayed() {
        when(mockAlert.getText()).thenThrow(NoAlertPresentException.class);
        Pair<Boolean, String> p = WebDriverExtensions.isAlertDisplayed(mockWebDriver);
        assertThat(p).extracting("key", "value").containsExactly(false, "");
    }

    @Test
    public void take_screenshot_should_return_file_if_successful_on_first_attempt() {
        when(((TakesScreenshot) mockWebDriver).getScreenshotAs(OutputType.FILE)).thenReturn(mockFile);
        File screenshot = WebDriverExtensions.takeScreenshot(mockWebDriver);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(screenshot)
                    .isInstanceOf(File.class)
                    .isNotNull();
        });
    }

    @Test
    public void take_screenshot_should_return_file_if_successful_on_second_attempt() {
        when(((TakesScreenshot) mockWebDriver).getScreenshotAs(OutputType.FILE))
                .thenThrow(new WebDriverException())
                .thenReturn(mockFile);
        File screenshot = WebDriverExtensions.takeScreenshot(mockWebDriver);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(screenshot)
                    .isInstanceOf(File.class)
                    .isNotNull();
        });

    }

    @Test
    public void take_screenshot_should_throw_webdriverexception_if_not_successful_on_second_attempt() {
        when(((TakesScreenshot) mockWebDriver).getScreenshotAs(OutputType.FILE))
                .thenThrow(new WebDriverException());
        assertThatExceptionOfType(WebDriverException.class).isThrownBy(() -> {
            WebDriverExtensions.takeScreenshot(mockWebDriver);
        });

    }

    @Test
    public void take_screenshot_should_wait_for_given_amount_of_time_if_not_successful_on_first_attempt() {
        when(((TakesScreenshot) mockWebDriver).getScreenshotAs(OutputType.FILE))
                .thenThrow(new WebDriverException())
                .thenReturn(mockFile);
        final long waitTime = 1000;
        long before = System.currentTimeMillis();
        WebDriverExtensions.takeScreenshot(mockWebDriver, waitTime);
        long after = System.currentTimeMillis();
        //checking that the method took at least 1 second to execute since the waitTime is 1 second.
        assertThat(after - before).isGreaterThanOrEqualTo(waitTime);
    }
}