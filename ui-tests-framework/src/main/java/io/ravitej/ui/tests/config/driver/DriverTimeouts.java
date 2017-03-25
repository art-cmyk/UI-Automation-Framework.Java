package io.ravitej.ui.tests.config.driver;

/**
 * Created by Ravitej on 01/10/2015.
 */

/**
 * Represents various timeouts applicable to the WebDriver instance driving the browser in which the suite of tests are executed.
 * @author Ravitej Aluru
 */
//[Serializable]
public class DriverTimeouts {

    private long _implicitWaitSeconds;

    private long _scriptTimeoutSeconds;

    private long _pageLoadTimeoutSeconds;

    /**
     * Initialises a new instance of DriverTimeouts with the following default values.
     * ImplicitWait" = 0 Seconds,
     * "ScriptTimeout" = 20 Seconds,
     * "PageLoadTimeout" = 30 Seconds,
     */
    public DriverTimeouts() {
        _implicitWaitSeconds = 0;
        _scriptTimeoutSeconds = 20;
        _pageLoadTimeoutSeconds = 30;
    }

    /**
     * @return Implicit wait timeout to assign to the WebDriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.ImplicitlyWait"/> for more information.
     */
    public long getImplicitWaitSeconds() {
        return _implicitWaitSeconds;
    }

    /**
     * @param implicitWait Implicit wait timeout to assign to the WebDriver instance.
     *                     See <see cref="OpenQA.Selenium.ITimeouts.ImplicitlyWait"/> for more information.
     */
    public void setImplicitWaitSeconds(long implicitWait) {
        this._implicitWaitSeconds = implicitWait;
    }

    /**
     * @return Script timeout to assign to the WebDriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.SetScriptTimeout"/> for more information.
     */
    public long getScriptTimeoutSeconds() {
        return _scriptTimeoutSeconds;
    }

    /**
     * @param scriptTimeout Script timeout to assign to the WebDriver instance.
     *                      See <see cref="OpenQA.Selenium.ITimeouts.SetScriptTimeout"/> for more information.
     */
    public void setScriptTimeoutSeconds(long scriptTimeout) {
        this._scriptTimeoutSeconds = scriptTimeout;
    }

    /**
     * @return Page load timeout to assign to the WebDriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.SetPageLoadTimeout"/> for more information.
     */
    public long getPageLoadTimeoutSeconds() {
        return _pageLoadTimeoutSeconds;
    }

    /**
     * @param pageLoadTimeout Page load timeout to assign to the WebDriver instance.
     *                        See <see cref="OpenQA.Selenium.ITimeouts.SetPageLoadTimeout"/> for more information.
     */
    public void setPageLoadTimeoutSeconds(long pageLoadTimeout) {
        this._pageLoadTimeoutSeconds = pageLoadTimeout;
    }
}
