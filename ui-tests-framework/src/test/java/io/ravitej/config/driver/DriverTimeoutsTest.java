package io.ravitej.config.driver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ravitej on 01/10/2015.
 */
public class DriverTimeoutsTest {

    private DriverTimeouts driverTimeouts;

    @Before
    public void setUpClass() throws Exception {
        driverTimeouts = new DriverTimeouts(10,20,30,40);
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