package io.ravitej.selenium.extensions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by ravit on 19/01/2017.
 */
public abstract class WebElementExtensions {

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

    public static Dictionary<String, Object> getAllAttributes(WebElement webElement) {
        WebDriver driver = ((WrapsDriver)webElement).getWrappedDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        final String javascript =
            "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;";
        return ((Dictionary<String, Object>) jsExecutor.executeScript(javascript, webElement));
    }

    public static Boolean hasAttribute(WebElement webElement, String attribute) {
        Dictionary attributes = getAllAttributes(webElement);
        return Arrays.asList(attributes.keys()).contains(attribute);
    }

    public static String getValue(WebElement webElement)
    {
        return webElement.getAttribute("value").trim();
    }

    public static String getText(WebElement webElement)
    {
        return webElement.getText().trim();
    }

    /**
     * Clears existing value and enters value passed in into textbox or text area.
     *
     * @param webElement The web element that the text should be entered into.
     * @param value Value to be entered into textbox or text area.
     */
    public static void enterText(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Sends "CTRL + a" key combination to the web element to select all content. Only works on Windows environments.
     *
     * @param webElement The web element whose content is to be selected.
     * @return Returns the web element that was passed in.
     */
    public static WebElement selectAll(WebElement webElement) {
        webElement.sendKeys(Keys.CONTROL + "a");
        return webElement;
    }

    /**
     * Sends "CTRL + v" key combination to paste current data in the system clipboard into the element. Only works on Windows environments.
     *
     * @param webElement Element into which to paste data.
     */
    public static void pasteData(WebElement webElement)
    {
        selectAll(webElement).sendKeys(Keys.CONTROL + "v");
    }

    /**
     * Gets a value indicating whether or not a checkbox/radio button is ticked. This operation only applies to checkboxes and radio buttons.
     *
     * @param inputElement Element whose ticked status is to be determined.
     * @return True if the checkbox/radio button is ticked/selected and false otherwise.
     */
    public static Boolean isTicked(WebElement inputElement)
    {
        return inputElement.isSelected();
    }

    /**
     * Ticks JQuery checkbox if not already ticked.
     *
     * @param inputElement Hidden input that represents the checkbox
     * @param accompanyingElement Element accompanying the input that interacts on the UI
     */
    public static void tick(WebElement inputElement, WebElement accompanyingElement) {
        if (inputElement.isSelected() == false)
        {
            accompanyingElement.click();
        }
    }

    /**
     * Unticks JQuery checkbox if already ticked.
     *
     * @param inputElement Hidden input that represents the checkbox
     * @param accompanyingElement Element accompanying the input that interacts on the UI
     */
    public static void untick(WebElement inputElement, WebElement accompanyingElement) {
        if (inputElement.isSelected())
        {
            accompanyingElement.click();
        }
    }

    /**
     * Ticks HTML checkbox if not already ticked.
     *
     * @param inputElement Input element that represents the checkbox.
     */
    public static void tick(WebElement inputElement) {
        if (inputElement.isSelected() == false)
        {
            inputElement.click();
        }
    }

    /**
     * Unticks HTML checkbox if already ticked.
     *
     * @param inputElement Input element that represents the checkbox.
     */
    public static void untick(WebElement inputElement) {
        if (inputElement.isSelected())
        {
            inputElement.click();
        }
    }

    /**
     * Performs a drag and drop operation from source element to target element.
     *
     * @param sourceWebElement The element on which the drag operation is started.
     * @param targetWebElement The element on which the drop is performed.
     */
    public static void dragAndDrop(WebElement sourceWebElement, WebElement targetWebElement) {
        WebDriver driver = ((WrapsDriver)sourceWebElement).getWrappedDriver();
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceWebElement, targetWebElement).build().perform();

        //Alternative code if the above code doesn't work.
        //actions.clickAndHold(sourceWebElement)
        //   .moveToElement(targetWebElement)
        //   .release(targetWebElement) //this line may not be needed. check without it first.
        //   .build()
        //   .perform();
    }

    /**
     * Performs a drag and drop operation from source element to a specified offset of the source element.
     *
     * @param sourceWebElement The element on which the drag operation is started.
     * @param offsetX The horizontal offset to which to move the mouse.
     * @param offsetY The vertical offset to which to move the mouse.
     */
    public static void dragAndDrop(WebElement sourceWebElement, int offsetX, int offsetY) {
        WebDriver driver = ((WrapsDriver)sourceWebElement).getWrappedDriver();
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(sourceWebElement, offsetX, offsetY).build().perform();
    }

    /**
     * Performs a drag and drop operation from source element to a specified offset of the top-left corner of target element.
     *
     * @param sourceWebElement The element on which the drag operation is started.
     * @param targetWebElement The element on which the drop is performed.
     * @param offsetX The horizontal offset to which to move the mouse.
     * @param offsetY The vertical offset to which to move the mouse.
     */
    public static void dragAndDrop(WebElement sourceWebElement, WebElement targetWebElement, int offsetX, int offsetY) {
        WebDriver driver = ((WrapsDriver)sourceWebElement).getWrappedDriver();
        Actions actions = new Actions(driver);

        actions.clickAndHold(sourceWebElement)
                .moveToElement(targetWebElement, offsetX, offsetY)
                .release(targetWebElement) //this line may not be needed. check without it first.
                .build()
                .perform();
    }

    /**
     * Moves mouse pointer to the centre of the element.
     *
     * @param webElement Element to move the mouse to the centre of.
     */
    public static void moveMouseToCentre(WebElement webElement) {
        WebDriver driver = ((WrapsDriver)webElement).getWrappedDriver();
        ((RemoteWebDriver)driver).getMouse().mouseMove(((RemoteWebElement)webElement).getCoordinates(), webElement.getSize().width / 2, webElement.getSize().height / 2);
    }

    /**
     * Moves mouse pointer to the specified offset of the element's top-left corner and clicks.
     *
     * @param webElement Element to click
     * @param offsetX The horizontal offset to which to move the mouse
     * @param offsetY The vertical offset to which to move the mouse
     */
    public static void moveMouseAndClick(WebElement webElement, int offsetX, int offsetY) {
        WebDriver driver = ((WrapsDriver)webElement).getWrappedDriver();
        Actions actions = new Actions(driver);
        actions
                .moveToElement(webElement, offsetX, offsetY)
                .click()
                .build()
                .perform();
    }

    /**
     * Finds all elements within the current context using the given mechanism.
     *
     * @param searchContext
     * @param by
     * @return Returns a collection of IWebElement if found or null if nothing found
     */
    public static List<WebElement> findElementsSafe(SearchContext searchContext, By by) {
        List<WebElement> elements = searchContext.findElements(by);
        return elements.isEmpty() ? null : elements;
    }
    /*/// <summary>
    /// Finds all elements within the current context using the given mechanism and matching the given condition
    /// </summary>
    /// <param name="searchContext"></param>
    /// <param name="by"></param>
    /// <param name="fnCondition"></param>
    /// <returns>Returns a collection of IWebElement if found and condition satisfied or null if nothing found or condition not satisfied</returns>
    public static List<WebElement> findElementsSafe(SearchContext searchContext, By by, Func<WebElement, Boolean> fnCondition)
    {
        var elements = searchContext.FindElements(@by).Where(fnCondition).ToList();
        return elements.Any() ? elements : null;
    }*/

    /**
     *
     * @param searchContext
     * @param by
     * @param exceptionMessage
     * @return
     * @throws NotFoundException
     */
    public static List<WebElement> findElementsOrThrow(SearchContext searchContext, By by, String exceptionMessage) throws NotFoundException {
        List<WebElement> elements = findElementsSafe(searchContext, by);
        if (elements == null)
        {
            String message = String.format("%s. Elements locator: %s", exceptionMessage, by);
            //LogX.Warning.Category(Type).Write(message);
            throw new NotFoundException(message);
        }
        //LogX.Info.Category(Type).Write("Elements found: {0}", @by.ToString());
        return elements;
    }
    /*/// <summary>
    ///
    /// </summary>
    /// <param name="searchContext"></param>
    /// <param name="by"></param>
    /// <param name="fnCondition"></param>
    /// <param name="exceptionMessage"></param>
    /// <returns></returns>
    /// <exception cref="NotFoundException">Thrown when element cannot be found.</exception>
    public static List<WebElement> FindElementsOrThrow(this ISearchContext searchContext, By @by, Func<IWebElement, bool> fnCondition, String exceptionMessage)
    {
        var elements = searchContext.FindElementsSafe(@by, fnCondition);
        if (elements == null)
        {
            var message = $"{exceptionMessage}. Elements locator: {@by}";
            LogX.Warning.Category(Type).Write(message);
            throw new NotFoundException(message);
        }
        LogX.Info.Category(Type).Write("Elements found: {0}", @by.ToString());
        return elements;
    }*/

    /**
     *
     * @param searchContext
     * @param by
     * @return
     */
    public static WebElement findElementSafe(SearchContext searchContext, By by) {
        List<WebElement> elements = searchContext.findElements(by);
        if (elements.isEmpty()) {
            return null;
        }
        else {
            return elements.get(0);
        }
    }
    /*/// <summary>
    ///
    /// </summary>
    /// <param name="oElement"></param>
    /// <param name="oBy"></param>
    /// <param name="fnCondition"></param>
    /// <returns></returns>
    public static IWebElement FindElementSafe(this ISearchContext oElement, By oBy, Func<IWebElement, bool> fnCondition)
    {
        return oElement.FindElements(oBy).FirstOrDefault(fnCondition);
    }*/

    /**
     *
     * @param searchContext
     * @param by
     * @param exceptionMessage
     * @return
     */
    public static WebElement findElementOrThrow(SearchContext searchContext, By by, String exceptionMessage) {
        WebElement webElement = findElementSafe(searchContext, by);
        if (webElement == null)
        {
            String message = String.format("%s. Element locator: %s", exceptionMessage, by);
            //LogX.Warning.Category(Type).Write(message);
            throw new NotFoundException(message);
        }
        //LogX.Info.Category(Type).Write("Element found: {0}", oBy.ToString());
        return webElement;
    }
    /*/// <summary>
    ///
    /// </summary>
    /// <param name="oDriver"></param>
    /// <param name="oBy"></param>
    /// <param name="fnCondition"></param>
    /// <param name="sNotFoundExceptionText"></param>
    /// <returns></returns>
    /// <exception cref="NotFoundException">Thrown when element cannot be found. </exception>
    public static IWebElement FindElementOrThrow(this ISearchContext oDriver, By oBy, Func<IWebElement, bool> fnCondition, string sNotFoundExceptionText)
    {
        IWebElement oElement = oDriver.FindElementSafe(oBy, fnCondition);
        if (oElement == null)
        {
            var message = $"{sNotFoundExceptionText}. Element locator: {oBy}";
            LogX.Warning.Category(Type).Write(message);
            throw new NotFoundException(message);
        }
        LogX.Info.Category(Type).Write("Element found: {0}", oBy.ToString());
        return oElement;
    }*/
}
