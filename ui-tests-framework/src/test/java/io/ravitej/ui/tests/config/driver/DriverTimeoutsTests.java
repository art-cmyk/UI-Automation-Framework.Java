package io.ravitej.ui.tests.config.driver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ravitej on 01/10/2015.
 */
public class DriverTimeoutsTests {

    private DriverTimeouts driverTimeouts;

    @Before
    public void setUpClass() throws Exception {
        driverTimeouts = new DriverTimeouts();
        driverTimeouts.setImplicitWait(10);
        driverTimeouts.setCommandTimeout(40);
        driverTimeouts.setPageLoadTimeout(30);
        driverTimeouts.setScriptTimeout(20);
    }

    @Test
    public void testGetImplicitWait() throws Exception {
        Assert.assertEquals(10, driverTimeouts.getImplicitWait());
    }

    @Test
    public void testGetScriptTimeout() throws Exception {
        Assert.assertEquals(20, driverTimeouts.getScriptTimeout());
    }

    @Test
    public void testGetPageLoadTimeout() throws Exception {
        Assert.assertEquals(30, driverTimeouts.getPageLoadTimeout());
    }

    @Test
    public void testGetCommandTimeout() throws Exception {
        Assert.assertEquals(40, driverTimeouts.getCommandTimeout());
    }
}