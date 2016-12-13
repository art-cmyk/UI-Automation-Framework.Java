package driver.factories;

/**
 * Created by Ravitej on 01/10/2015.
 */
import com.sun.jndi.toolkit.url.Uri;
import config.driver.DriverTimeouts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

;

public interface IDriverFactory {
    /**
    /// <summary>
    /// Create a new instance of an IWebDriver using the specified hub url, capabilities and command timeout
    /// </summary>
    /// <param name="hubUrl"></param>
    /// <param name="capabilities"></param>
    /// <param name="commandTimeout"></param>
    /// <returns></returns>
     */
    WebDriver create(Uri hubUrl, DesiredCapabilities capabilities, long commandTimeout);
    /**
    /// <summary>
    /// Creates a new instance of IWebDriver using the specified hub url and capabilities
    /// </summary>
    /// <param name="hubUrl"></param>
    /// <param name="capabilities"></param>
    /// <returns></returns>
     */
    WebDriver create(Uri hubUrl, DesiredCapabilities capabilities);
    /**
    /// <summary>
    /// Sets the given implicit wait, script timeout and page load timeout values to the given instance
    /// </summary>
    /// <param name="driver"></param>
    /// <param name="timeouts"></param>
    /// <returns></returns>
     */
    WebDriver setTimeouts(WebDriver driver, DriverTimeouts timeouts);
}
