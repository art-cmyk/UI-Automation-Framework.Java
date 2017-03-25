package io.ravitej.ui.tests.config.driver;

import org.junit.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

/**
 * Tests for DriverTimeouts.
 * @author Ravitej Aluru
 */
public class DriverTimeoutsTests {

    @Test
    public void default_values_should_be_correct() {
        DriverTimeouts driverTimeouts = new DriverTimeouts();
        assertSoftly(softly -> {
            softly.assertThat(driverTimeouts.getImplicitWaitSeconds()).isEqualTo(0);
            softly.assertThat(driverTimeouts.getScriptTimeoutSeconds()).isEqualTo(20);
            softly.assertThat(driverTimeouts.getPageLoadTimeoutSeconds()).isEqualTo(30);
        });
    }
}