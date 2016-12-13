package io.ravitej.config.driver;

/**
 * Created by Ravitej on 01/10/2015.
 */
/// <summary>
/// Represents various timeouts applicable to the WebDriver instance driving the browser in which the suite of tests are executed.
/// </summary>
//[Serializable]
public class DriverTimeouts
{
    /**
     * Implicit wait timeout to assign to the WebDriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.ImplicitlyWait"/> for more information.
     */
    private long _implicitWait;

    /**
    * Script timeout to assign to the WebDriver instance.
    * See <see cref="OpenQA.Selenium.ITimeouts.SetScriptTimeout"/> for more information.
    */
    private long _scriptTimeout;

    /**
     * Page load timeout to assign to the WebDriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.SetPageLoadTimeout"/> for more information.
     */
    private long _pageLoadTimeout;

    /**
     * Command timeout to assign to the WebDriver instance.
     * See <see cref="OpenQA.Selenium.ITimeouts.CommandTimeout"/> for more information.
     */
    private long _commandTimeout;

    /// <summary>
    /// Initialises a new instance of <see cref="config.driver.DriverTimeouts"/> using the
    /// <see cref="TimeSpan"/> values passed in.
    /// </summary>
    /// <param name="implicitWait"></param>
    /// <param name="scriptTimeout"></param>
    /// <param name="pageLoadTimeout"></param>
    /// <param name="commandTimeout"></param>
    public DriverTimeouts(long implicitWait, long scriptTimeout, long pageLoadTimeout, long commandTimeout)
    {
        _implicitWait = implicitWait;
        _scriptTimeout = scriptTimeout;
        _pageLoadTimeout = pageLoadTimeout;
        _commandTimeout = commandTimeout;
    }

    public long getImplicitWait() {
        return _implicitWait;
    }

    public long getScriptTimeout() {
        return _scriptTimeout;
    }

    public long getPageLoadTimeout() {
        return _pageLoadTimeout;
    }

    public long getCommandTimeout() {
        return _commandTimeout;
    }
}
