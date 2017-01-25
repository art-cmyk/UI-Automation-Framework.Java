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
 * Created by ravit on 21/01/2017.
 */
public class RemoteWebDriverExtensions extends RemoteWebDriver {

    /**
     * Waits until the element is visible
     * @param driver
     * @param by
     * @param timeoutSeconds
     */
    public static void waitForVisibilityOfElement(WebDriver driver, By by, long timeoutSeconds)
    {
        new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits for the element to not be visible
     * @param driver
     * @param by
     * @param timeoutSeconds
     */
    public static void waitForInvisibilityOfElement(WebDriver driver, By by, long timeoutSeconds)
    {
        new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Waits until the element is present
     * @param driver
     * @param by
     * @param timeoutSeconds
     */
    public static void waitForPresenceOfElement(WebDriver driver, By by, long timeoutSeconds)
    {
        new WebDriverWait(driver, timeoutSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Wait until the expected condition is met
     * @param driver
     * @param timeoutSeconds
     * @param expectedCondition
     */
    public static void waitFor(WebDriver driver, long timeoutSeconds, ExpectedCondition expectedCondition)
    {
        new WebDriverWait(driver, timeoutSeconds).until(expectedCondition);
    }

    /*/// <summary>
    /// Wait for a period of time
    /// </summary>
    /// <param name="driver"></param>
    /// <param name="condition"></param>
    /// <param name="timeoutSeconds"></param>
    public static void WaitFor(this IWebDriver driver, Func<IWebDriver, bool> condition, int timeoutSeconds)
    {
        var timeout = TimeSpan.FromSeconds(timeoutSeconds);
        var waitFor = new WebDriverWait(driver, timeout);
        waitFor.IgnoreExceptionTypes(typeof(ElementNotVisibleException),
                typeof(NoSuchElementException));
        waitFor.Until(condition);
    }

    /// <summary>
    /// Wait for a period of time
    /// </summary>
    /// <param name="driver"></param>
    /// <param name="condition"></param>
    /// <param name="timeoutSeconds"></param>
    public static void WaitFor(this IWebDriver driver, Func<IWebDriver, IWebElement> condition, int timeoutSeconds)
    {
        var timeout = TimeSpan.FromSeconds(timeoutSeconds);
        var waitFor = new WebDriverWait(driver, timeout);
        waitFor.IgnoreExceptionTypes(typeof(ElementNotVisibleException),
                typeof(NoSuchElementException));
        waitFor.Until(condition);
    }*/

    /**
     * Move the mouse to the centre of the page
     * @param webDriver
     */
    public static void moveMouseToCentreOfPage(WebDriver webDriver)
    {
        WebElement bodyElement = RemoteWebElementExtensions.findElementSafe(webDriver, By.tagName("body"));
        if (bodyElement == null) {
            //TODO - decide whether to throw the exception or not.
            //throw new WebDriverException("Could not locate body of the HTML page.");
            //TODO - Add a log statement
        }
        else {
            ((RemoteWebDriver) webDriver).getMouse().mouseMove(((RemoteWebElement) bodyElement).getCoordinates(),
                    bodyElement.getSize().width / 2, bodyElement.getSize().height / 2);
        }
    }

    /**
     * Click a point at the centre of the page
     * @param webDriver
     */
    public static void clickCentreOfPage(WebDriver webDriver)
    {
        WebElement bodyElement = RemoteWebElementExtensions.findElementSafe(webDriver, By.tagName("body"));
        if (bodyElement == null) {
            //TODO - decide whether to throw the exception or not.
            //throw new WebDriverException("Could not locate body of the HTML page.");
            //TODO - Add a log statement
        }
        else {
            ((RemoteWebDriver) webDriver).getMouse().mouseMove(((RemoteWebElement) bodyElement).getCoordinates(),
                    bodyElement.getSize().width / 2, bodyElement.getSize().height / 2);
            ((RemoteWebDriver)webDriver).getMouse().mouseDown(((RemoteWebElement)bodyElement).getCoordinates());
            ((RemoteWebDriver)webDriver).getMouse().mouseUp(((RemoteWebElement)bodyElement).getCoordinates());
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
     * Attempts to take a screenshot of the browser window.
     * @param driver Driver object for interacting with the browser.
     * @return  Returns a File handle to the screenshot.
     */
    public static File takeScreenshot(WebDriver driver)
    {
        TakesScreenshot screenshotDriver = ((TakesScreenshot)driver);

        if (screenshotDriver != null)
        {
            File screenshot;
            try
            {
                screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
            }
            catch (WebDriverException e)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {

                }
                screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
            }

            return screenshot;
        }
        return null;
    }

    /**
     * Checks if a javascript alert box is displayed.
     * @param driver Driver object for interacting with the browser
     * @return A Pair<Boolean, String> object which contains the Boolean value of whether the alert is displayed and the alert text, if so.
     */
    public static Pair<Boolean, String> isAlertDisplayed(WebDriver driver)
    {
        try
        {
            String alertText = driver.switchTo().alert().getText();
            return new ImmutablePair<>(true, alertText);
        }
        catch (NoAlertPresentException e)
        {
            return new ImmutablePair<>(false, "");
        }
    }
}
