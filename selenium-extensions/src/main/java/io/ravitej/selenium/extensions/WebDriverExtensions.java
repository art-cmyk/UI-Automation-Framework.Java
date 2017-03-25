package io.ravitej.selenium.extensions;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Provides a set of helpful extensions to Selenium's standard RemoteWebDriver.
 * @author Ravitej Aluru
 */
public abstract class WebDriverExtensions {

    /**
     * Waits until the element is visible.
	 
     * @param webDriver The WebDriver object to interact with the web page.
     * @param by The locator mechanism to use to find the element.
     * @param timeoutSeconds The timeout in seconds when expectation is called.
     * @throws TimeoutException if the timeout expires. 
     */
    public static void waitForVisibilityOfElement(WebDriver webDriver, By by, long timeoutSeconds) {
        new WebDriverWait(webDriver, timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits until the element is not visible.
     *
     * @param webDriver The WebDriver object to interact with the web page.
     * @param by The locator mechanism to use to find the element.
     * @param timeoutSeconds The timeout in seconds when expectation is called.
     * @throws TimeoutException if the timeout expires.
     */
    public static void waitForInvisibilityOfElement(WebDriver webDriver, By by, long timeoutSeconds) {
        new WebDriverWait(webDriver, timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Waits until the element is present.
     *
     * @param webDriver The WebDriver object to interact with the web page.
     * @param by The locator mechanism to use to find the element.
     * @param timeoutSeconds The timeout in seconds when expectation is called.
     * @throws TimeoutException if the timeout expires.
     */
    public static void waitForPresenceOfElement(WebDriver webDriver, By by, long timeoutSeconds) {
        new WebDriverWait(webDriver, timeoutSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Wait until the given condition is met.
     *
     * @param webDriver The WebDriver object to interact with the web page.
     * @param timeoutSeconds The timeout in seconds when expectation is called.
     * @param expectedCondition The expected condition to wait for which will eventually be neither null nor false.
     * @throws TimeoutException if the timeout expires.
     */
    public static void waitFor(WebDriver webDriver, long timeoutSeconds, ExpectedCondition expectedCondition) {
        new WebDriverWait(webDriver, timeoutSeconds).until(expectedCondition);
    }

    /**
     * Move the mouse to the centre of the page.
     *
     * @param webDriver The WebDriver object to interact with the web page.
     */
    public static void moveMouseToCentreOfPage(WebDriver webDriver) {
        WebElement bodyElement = WebElementExtensions.findElementSafe(webDriver, By.tagName("body"));
        if (bodyElement == null) {
            //TODO - decide whether to throw the exception or not.
            //throw new WebDriverException("Could not locate body of the HTML page.");
            //TODO - Add a log statement
        } else {
            ((RemoteWebDriver) webDriver).getMouse().mouseMove(((RemoteWebElement) bodyElement).getCoordinates(),
                    bodyElement.getSize().width / 2, bodyElement.getSize().height / 2);
        }
    }

    /**
     * Click a point at the centre of the page.
     *
     * @param webDriver The WebDriver object to interact with the web page.
     */
    public static void clickCentreOfPage(WebDriver webDriver) {
        WebElement bodyElement = WebElementExtensions.findElementSafe(webDriver, By.tagName("body"));
        if (bodyElement == null) {
            //TODO - decide whether to throw the exception or not.
            //throw new WebDriverException("Could not locate body of the HTML page.");
            //TODO - Add a log statement
        } else {
            ((RemoteWebDriver) webDriver).getMouse().mouseMove(((RemoteWebElement) bodyElement).getCoordinates(),
                    bodyElement.getSize().width / 2, bodyElement.getSize().height / 2);
            ((RemoteWebDriver) webDriver).getMouse().mouseDown(((RemoteWebElement) bodyElement).getCoordinates());
            ((RemoteWebDriver) webDriver).getMouse().mouseUp(((RemoteWebElement) bodyElement).getCoordinates());
        }
    }

    /*/// <summary>
    /// Wait for the page to be fully loaded
    /// </summary>
    /// <param name="driver"></param>
    /// <param name="timeout"></param>
    /// <param name="timeTaken"></param>
    /// <param name="pollingInterval"></param>
    /// <returns></returns>
    public static Pair<Boolean, Long> waitForPageFullyLoaded(WebDriver driver, int timeout, int pollingInterval, String innerPageElement)
    {
        String javascript = _GetWaitForPageJavascript("true");
        com.google.common.base.Stopwatch.createStarted();

        Boolean pageLoaded = (Boolean) ((JavascriptExecutor)driver).executeScript(javascript);
        while (pageLoaded == false && com.google.common.base.Stopwatch.Elapsed.TotalMilliseconds <= timeout)
        {
            Thread.Sleep(pollingInterval);
            pageLoaded = (bool)((IJavaScriptExecutor)driver).ExecuteScript(javascript);
        }
        stopwatch.Stop();
        LogX.Info.Category("WebDriverExtensions").Write("Waited for {0} milliseconds for page to finish loading", stopwatch.ElapsedMilliseconds);
        timeTaken = stopwatch.ElapsedMilliseconds;
        return pageLoaded;
    }

    /// <summary>
    /// Wait for the next page to start loading
    /// </summary>
    /// <param name="driver"></param>
    /// <param name="timeout"></param>
    /// <param name="timeTaken"></param>
    /// <param name="pollingInterval"></param>
    /// <returns></returns>
    public static bool WaitForNextPageStartLoading(this IWebDriver driver, int timeout, out long timeTaken, int pollingInterval = 1000, string innerPageElement = null)
    {
        var javascript = innerPageElement == null ? _GetWaitForPageJavascript("false") : _GetWaitForInnerPageJavascript(innerPageElement, "false");
        var stopwatch = new Stopwatch();
        stopwatch.Start();
        var pageLoaded = (bool)((IJavaScriptExecutor)driver).ExecuteScript(javascript);
        while (pageLoaded && stopwatch.Elapsed.TotalMilliseconds <= timeout)
        {
            Thread.Sleep(pollingInterval);
            pageLoaded = (bool)((IJavaScriptExecutor)driver).ExecuteScript(javascript);
        }
        stopwatch.Stop();
        LogX.Info.Category("WebDriverExtensions").Write("Waited for {0} milliseconds for next page to start loading", stopwatch.ElapsedMilliseconds);
        timeTaken = stopwatch.ElapsedMilliseconds;
        return pageLoaded;
    }*/

    private static String _GetWaitForPageJavascript(String pageloadComplete) //bool true send True to the server and JS is case sensitive. Hence, using string and passing "true" and "false"
    {
        String javascript = "var test = function()" +
                "{" +
                "var result = false;" +
                "try" +
                "{" +
                String.format("result = (top.document.getElementsByTagName('body')[0].getAttribute(\"data-automation-pageload-complete\") == \"%s\")", pageloadComplete) +
                "}" +
                "catch(ex)" +
                "{" +
                "}" +
                "return result;" +
                "}; return test();";
        return javascript;
    }

    private static String _GetWaitForInnerPageJavascript(String inner, String loadComplete) //loadComplete value should either be "true" or "false"
    {
        String javascript = "var test = function()" +
                "{" +
                "var result = false;" +
                "try" +
                "{" +
                String.format("result = (top.frames[\"%s\"].contentDocument.getElementsByTagName('body')[0].getAttribute(\"data-automation-pageload-complete\") == \"%s\")", inner, loadComplete) +
                "}" +
                "catch(ex)" +
                "{" +
                "}" +
                "return result;" +
                "}; return test();";
        return javascript;
    }

    /**
     * Attempts to take a screenshot of the browser window. If it fails the first time, it tries again after waiting for given amount of time.
     * @param webDriver The WebDriver object to interact with the web page.
     * @param waitMilliSeconds The amount of time to wait before second attempt.
     * @return Returns a File handle containing the screenshot.
     */
    public static File takeScreenshot(WebDriver webDriver, long waitMilliSeconds) {
        TakesScreenshot screenshotDriver = ((TakesScreenshot) webDriver);

        if (screenshotDriver != null) {
            File screenshot;
            try {
                screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(waitMilliSeconds);
                } catch (InterruptedException ex) {

                }
                screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
            }

            return screenshot;
        }
        return null;
    }

    /**
     * Attempts to take a screenshot of the browser window. If it fails the first time, it tries again after waiting for 500 milliseconds.
     * @param webDriver The WebDriver object to interact with the web page.
     * @return Returns a File handle containing the screenshot.
     */
    public static File takeScreenshot(WebDriver webDriver) {
        return takeScreenshot(webDriver, 500);
    }

    /**
     * Checks if a JavaScript alert box is displayed.
     * @param webDriver The WebDriver object to interact with the web page.
     * @return A Pair<Boolean, String> object which contains the Boolean value of whether the alert is displayed and the alert text, if displayed.
     */
    public static Pair<Boolean, String> isAlertDisplayed(WebDriver webDriver) {
	
        try {
            String alertText = webDriver.switchTo().alert().getText();
             return new ImmutablePair<Boolean, String>(true, alertText);
        }
        catch (NoAlertPresentException e) {
            return new ImmutablePair<Boolean, String>(false, "");
        }
    }
}
