import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Arrays;
import java.util.Dictionary;

/**
 * Created by ravit on 19/01/2017.
 */
public abstract class WebElementExtensions extends RemoteWebElement {

    public static Boolean hasClass(WebElement webElement, String className) {
        String aClass = webElement.getAttribute("class");
        if(!aClass.isEmpty()) {
            return Arrays.asList(aClass.split(" ")).contains(className);
        }
        return false;
    }

    public static Boolean isDisplayed(WebElement webElement)
    {
        return webElement.getCssValue("display") != "none";
    }

    public static Dictionary<String, Object> getAllAttributes(WebElement webElement)
    {
        WebDriver driver = ((WrapsDriver)webElement).getWrappedDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        final String javascript =
            "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;";
        return ((Dictionary<String, Object>) jsExecutor.executeScript(javascript, webElement));
    }

    public static Boolean hasAttribute(WebElement webElement, String attribute)
    {
        Dictionary attributes = getAllAttributes(webElement);
        return Arrays.asList(attributes.keys()).contains(attribute);
    }

    public static String GetValue(WebElement webElement)
    {
        return webElement.getAttribute("value").trim();
    }

    public static String getText(WebElement webElement)
    {
        return webElement.getText().trim();
    }

    /**
     * Clears existing value and enters value passed in into textbox or text area
     * @param webElement The web element that the value should be entered into
     * @param value Value to be entered into textbox or text area
     */
    public static void enterValue(WebElement webElement, String value)
    {
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Sends CTRL + a key combination to the web element to select all content
     * @param webElement The web element whose content is to be selected
     * @return Returns the web element that was passed in
     */
    public static WebElement selectAll(WebElement webElement)
    {
        webElement.sendKeys(Keys.CONTROL + "a");
        return webElement;
    }

    /// <summary>
    /// Pastes current data in the system clipboard into the element. Only works on Windows environments
    /// </summary>
    /// <param name="element">Element into which to paste data</param>
    /// <exception cref="InvalidElementStateException">Thrown when the target element is not enabled.</exception>
    /// <exception cref="ElementNotVisibleException">Thrown when the target element is not visible.</exception>
    /// <exception cref="StaleElementReferenceException">Thrown when the target element is no longer valid in the document DOM.</exception>
    public static void pasteData(WebElement webElement)
    {
        selectAll(webElement).sendKeys(Keys.CONTROL + "v");
    }
    /// <summary>
    /// Gets a value indicating whether or not the checkbox is ticked.
    /// This operation only applies to checkboxes and radio buttons.
    /// </summary>
    /// <param name="inputElement"></param>
    /// <returns>True if the checkbox/radio button is ticked/selected and false otherwise.</returns>
    public static Boolean isTicked(WebElement inputElement)
    {
        return inputElement.isSelected();
    }
    /// <summary>
    /// Ticks JQuery checkbox if not already ticked
    /// </summary>
    /// <param name="inputElement">Hidden input that represents the checkbox</param>
    /// <param name="accompanyingElement">Element accompanying the input that interacts on the UI</param>
    /// <exception cref="StaleElementReferenceException">Thrown when the target element is no longer valid in the document DOM.</exception>
    /// <exception cref="ElementNotVisibleException">Thrown when the target element is not visible.</exception>
    public static void tick(WebElement inputElement, WebElement accompanyingElement)
    {
        if (inputElement.isSelected() == false)
        {
            accompanyingElement.click();
        }
    }
    /// <summary>
    /// Unticks JQuery checkbox if already ticked
    /// </summary>
    /// <param name="inputElement">Hidden input that represents the checkbox</param>
    /// <param name="accompanyingElement">Element accompanying the input that interacts on the UI</param>
    /// <exception cref="ElementNotVisibleException">Thrown when the target element is not visible.</exception>
    /// <exception cref="StaleElementReferenceException">Thrown when the target element is no longer valid in the document DOM.</exception>
    public static void untick(WebElement inputElement, WebElement accompanyingElement)
    {
        if (inputElement.isSelected())
        {
            accompanyingElement.click();
        }
    }
    /// <summary>
    /// Ticks HTML checkbox if not already ticked
    /// </summary>
    /// <param name="inputElement">Input that represents the checkbox</param>
    /// <exception cref="ElementNotVisibleException">Thrown when the target element is not visible.</exception>
    /// <exception cref="StaleElementReferenceException">Thrown when the target element is no longer valid in the document DOM.</exception>
    public static void tick(WebElement inputElement)
    {
        if (inputElement.isSelected() == false)
        {
            inputElement.click();
        }
    }

    /// <summary>
    /// Unticks HTML checkbox if already ticked
    /// </summary>
    /// <param name="inputElement">Input that represents the checkbox</param>
    /// <exception cref="ElementNotVisibleException">Thrown when the target element is not visible.</exception>
    /// <exception cref="StaleElementReferenceException">Thrown when the target element is no longer valid in the document DOM.</exception>
    public static void untick(WebElement inputElement)
    {
        if (inputElement.isSelected())
        {
            inputElement.click();
        }
    }
}
