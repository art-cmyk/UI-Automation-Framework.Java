package io.ravitej.ui.tests.config.driver;

import io.ravitej.ui.tests.UITestBase;
import org.junit.Test;

/**
 * Created by ravit on 03/02/2017.
 */
public class SampleTests extends UITestBase {
    public SampleTests() {
        super("local");
    }

    @Test
    public void test(){
        System.out.println(settings.getWebDriverSettings().getDriverTimeouts().getImplicitWaitSeconds());
        System.out.println(settings.getApplicationUnderTestSettings().getProp());
    }
}
