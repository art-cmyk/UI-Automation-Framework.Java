/**
 * 
 */
package io.ravitej.ui.tests.page_objects.interactables;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;

import uk.gov.hmrc.selenium.extensions.ISelectExtensions;
import uk.gov.hmrc.selenium.extensions.SearchContextExtensions;
import uk.gov.hmrc.selenium.extensions.WebElementExtensions;

/**
 * @author 7831043
 *
 */
public class Interactor {

    private WebDriver driver;
    private String interactableName;
        
    public Interactor(WebDriver driver, String interactableName){
	this.driver = driver;
	this.interactableName = interactableName;
    }

    private SearchContext doTheNecessary(SearchContext context, String frame) {
	switchToDefaultContent();
	if (context == null) {
	    context = driver;
	}
	if (frame != null) {
	    driver.switchTo().frame(frame);
	}
	return context;
    }

    /**
     * Finds and gets the first WebElement within the given context using the given locating mechanism or throws
     * NotFoundException.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return The first matching WebElement in the given context.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public WebElement getElementOrThrow(SearchContext context, By by, String elementDescription, String frame) {
	context = doTheNecessary(context, frame);
	return SearchContextExtensions.findElementOrThrow(context, by, constructNotFoundErrorMessage(elementDescription));
    }

    /**
     * Finds and gets the first {@code WebElement} using the given locating mechanism or throws {@code NotFoundException}.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return The first matching {@code WebElement} in the current context.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public WebElement getElementOrThrow(By by, String elementDescription, String frame) {
	return getElementOrThrow(null, by, elementDescription, frame);
    }

    /**
     * Finds and gets the first WebElement within the given context using the given locating mechanism or throws
     * NotFoundException.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return The first matching WebElement in the given context.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public WebElement getElementOrThrow(SearchContext context, By by, String elementDescription) {
	return getElementOrThrow(context, by, elementDescription, null);
    }

    /**
     * Finds and gets the first {@code WebElement} using the given locating mechanism or throws {@code NotFoundException}.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return The first matching {@code WebElement} in the current context.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public WebElement getElementOrThrow(By by, String elementDescription) {
	return getElementOrThrow(by, elementDescription, null);
    }

    /**
     * Finds and gets all WebElements within the given context using the given locating mechanism or throws NotFoundException.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return A list of all WebElements matching the criteria.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public List<WebElement> getElementsOrThrow(SearchContext context, By by, String elementDescription, String frame) {
	context = doTheNecessary(context, frame);
	return SearchContextExtensions.findElementsOrThrow(context, by, constructNotFoundErrorMessage(elementDescription));
    }

    /**
     * Finds and gets all WebElements using the given locating mechanism or throws NotFoundException.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return A list of all WebElements matching the criteria.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public List<WebElement> getElementsOrThrow(By by, String elementDescription, String frame) {
	return getElementsOrThrow(null, by, elementDescription, frame);
    }

    /**
     * Finds and gets all WebElements using the given locating mechanism or throws NotFoundException.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return A list of all WebElements matching the criteria.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public List<WebElement> getElementsOrThrow(By by, String elementDescription) {
	return getElementsOrThrow(by, elementDescription, null);
    }

    /**
     * Finds and gets all WebElements within the given context using the given locating mechanism or throws NotFoundException.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return A list of all WebElements matching the criteria.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public List<WebElement> getElementsOrThrow(SearchContext context, By by, String elementDescription) {
	return getElementsOrThrow(context, by, elementDescription, null);
    }

    /**
     * Finds and gets all WebElements using the given locating mechanism.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return A List of all WebElements matching the criteria, or an empty list if nothing matches.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public List<WebElement> getElements(SearchContext context, By by, String elementDescription, String frame) {
	List<WebElement> elements = new ArrayList<WebElement>();
	try {
	    elements = getElementsOrThrow(context, by, elementDescription, frame);
	} catch (NotFoundException e) {

	}
	return elements;
    }

    /**
     * Finds and gets all WebElements using the given locating mechanism.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return A list of all WebElements matching the criteria, or an empty list if nothing matches.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public List<WebElement> getElements(By by, String elementDescription, String frame) {
	return getElements(null, by, elementDescription, frame);
    }

    /**
     * Finds and gets all WebElements using the given locating mechanism.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return A list of all WebElements matching the criteria, or an empty list if nothing matches.
     */
    public List<WebElement> getElements(By by, String elementDescription) {
	return getElements(by, elementDescription, null);
    }

    /**
     * Finds and gets all WebElements using the given locating mechanism.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return A list of all WebElements matching the criteria, or an empty list if nothing matches.
     */
    public List<WebElement> getElements(SearchContext context, By by, String elementDescription) {
	return getElements(context, by, elementDescription, null);
    }

    private void TryClickOrScroll(WebElement element) {
	try {
	    element.click();
	} catch (Exception e) {
	    final String js = "arguments[0].scrollIntoView(true);";
	    ((JavascriptExecutor) driver).executeScript(js, element);
	    try {
		Thread.sleep(1000); // Don't know why, but need this line for the js to work!
	    } catch (InterruptedException e1) {
		// TODO Auto-generated catch block
	    }
	    element.click();
	}
    }

    /**
     * Clicks the element and logs the action. If the element is not in view, it is scrolled into view and then clicked.
     * 
     * @param element
     *            Element to click on.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     */
    public void click(WebElement element, String elementDescription) {
	TryClickOrScroll(element);
	// WriteInfoLogEntry(constructClickedMessage(elementDescription));
    }

    /**
     * Finds element using specified location strategy within given context, clicks on it and logs the action. If the element is
     * not in view, it is scrolled into view and then clicked.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public void click(SearchContext context, By by, String elementDescription, String frame) {
	WebElement element = getElementOrThrow(context, by, elementDescription, frame);
	TryClickOrScroll(element);
	// WriteInfoLogEntry(constructClickedMessage(elementDescription));
    }

    /**
     * Finds element using specified location strategy within given context, clicks on it and logs the action. If the element is
     * not in view, it is scrolled into view and then clicked.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public void click(By by, String elementDescription, String frame) {
	click(null, by, elementDescription, frame);
    }

    /**
     * Finds element using specified location strategy within given context, clicks on it and logs the action. If the element is
     * not in view, it is scrolled into view and then clicked.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public void click(By by, String elementDescription) {
	click(by, elementDescription, null);
    }

    /**
     * Finds element using specified location strategy within given context, clicks on it and logs the action. If the element is
     * not in view, it is scrolled into view and then clicked.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public void click(SearchContext context, By by, String elementDescription) {
	click(context, by, elementDescription, null);
    }

    /**
     * Gets a value indicating whether element is present in DOM within the given context.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return True if element is present, otherwise false.
     */
    public Boolean isElementPresent(SearchContext context, By by, String elementDescription, String frame) {
	try {
	    getElementOrThrow(context, by, elementDescription, frame);
	    return true;
	} catch (NotFoundException e) {
	    return false;
	}
    }

    /**
     * Gets a value indicating whether element is present in DOM.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return True if element is present, otherwise false.
     */
    public Boolean isElementPresent(By by, String elementDescription, String frame) {
	return isElementPresent(null, by, elementDescription, frame);
    }

    /**
     * Gets a value indicating whether element is present in DOM.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return True if element is present, otherwise false.
     */
    public Boolean isElementPresent(By by, String elementDescription) {
	return isElementPresent(by, elementDescription, null);
    }

    /**
     * Gets a value indicating whether element is present in DOM within the given context.
     * 
     * @param context
     *            Context within which to search for the elements.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return True if element is present, otherwise false.
     */
    public Boolean isElementPresent(SearchContext context, By by, String elementDescription) {
	return isElementPresent(context, by, elementDescription, null);
    }

    /**
     * Gets a value indicating whether element is present in DOM and whether it is displayed.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return True if the element is found AND displayed, otherwise false
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public Boolean isElementDisplayed(SearchContext context, By by, String elementDescription, String frame) {
	try {
	    Boolean elementDisplayed = getElementOrThrow(context, by, elementDescription, frame).isDisplayed();
	    /*
	     * WriteInfoLogEntry( elementDisplayed ? "{0} ({1}) found within given context and is displayed" :
	     * "{0} ({1}) found within given context, but is not displayed", elementDescription, by.toString());
	     */
	    return elementDisplayed;
	} catch (NotFoundException e) {
	    return false;
	}
    }

    /**
     * Gets a value indicating whether element is present in DOM and whether it is displayed.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @return True if the element is found AND displayed, otherwise false
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public Boolean isElementDisplayed(By by, String elementDescription, String frame) {
	return isElementDisplayed(null, by, elementDescription, frame);
    }

    /**
     * Gets a value indicating whether element is present in DOM and whether it is displayed.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return True if the element is found AND displayed, otherwise false
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public Boolean isElementDisplayed(By by, String elementDescription) {
	return isElementDisplayed(by, elementDescription, null);
    }

    /**
     * Gets a value indicating whether element is present in DOM and whether it is displayed.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @return True if the element is found AND displayed, otherwise false
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public Boolean isElementDisplayed(SearchContext context, By by, String elementDescription) {
	return isElementDisplayed(context, by, elementDescription, null);
    }

    /**
     * Finds checkbox element using specified location strategy, ticks if it is not already ticked and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     */
    public void tick(SearchContext context, By by, String elementDescription, String frame) {
	WebElement element = getElementOrThrow(context, by, elementDescription, frame);
	WebElementExtensions.tick(element);
    }

    /**
     * Finds checkbox element using specified location strategy, ticks if it is not already ticked and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     */
    public void tick(By by, String elementDescription, String frame) {
	tick(null, by, elementDescription, frame);
    }

    /**
     * Finds checkbox element using specified location strategy, ticks if it is not already ticked and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     */
    public void tick(By by, String elementDescription) {
	tick(by, elementDescription, null);
    }

    /**
     * Finds checkbox element using specified location strategy, ticks if it is not already ticked and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     */
    public void tick(SearchContext context, By by, String elementDescription) {
	tick(context, by, elementDescription, null);
    }

    /**
     * Finds checkbox element using specified location strategy, unticks if it is not already ticked and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     */
    public void untick(SearchContext context, By by, String elementDescription, String frame) {
	WebElement element = getElementOrThrow(context, by, elementDescription, frame);
	WebElementExtensions.untick(element);
    }

    /**
     * Finds checkbox element using specified location strategy, ticks if it is not already ticked and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     */
    public void untick(By by, String elementDescription, String frame) {
	untick(null, by, elementDescription, frame);
    }

    /**
     * Finds checkbox element using specified location strategy, ticks if it is not already ticked and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     */
    public void untick(By by, String elementDescription) {
	untick(by, elementDescription, null);
    }

    /**
     * Finds checkbox element using specified location strategy, unticks if it is not already ticked and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     */
    public void untick(SearchContext context, By by, String elementDescription) {
	untick(context, by, elementDescription, null);
    }

    /**
     * Finds the textbox or textarea element using the specified location strategy, enters the value into it and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @param value
     *            The text to enter into the textbox or textarea.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     * @throws InvalidElementStateException
     *             Thrown when the target element is not enabled.
     * @throws ElementNotVisibleException
     *             Thrown when the target element is not visible.
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public void enterText(SearchContext context, By by, String elementDescription, String frame, String value) {
	WebElement element = getElementOrThrow(context, by, elementDescription, frame);
	WebElementExtensions.enterText(element, value);
	// WriteInfoLogEntry(constructDataEnteredMessage(value, elementDescription));
    }

    /**
     * Finds the textbox or textarea element using the specified location strategy, enters the value into it and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param value
     *            The text to enter into the textbox or textarea.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws InvalidElementStateException
     *             Thrown when the target element is not enabled.
     * @throws ElementNotVisibleException
     *             Thrown when the target element is not visible.
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public void enterText(SearchContext context, By by, String elementDescription, String value) {
	enterText(context, by, elementDescription, null, value);
    }

    /**
     * Finds the textbox or textarea element using the specified location strategy, enters the value into it and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @param value
     *            The text to enter into the textbox or textarea.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     * @throws InvalidElementStateException
     *             Thrown when the target element is not enabled.
     * @throws ElementNotVisibleException
     *             Thrown when the target element is not visible.
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public void enterText(By by, String elementDescription, String frame, String value) {
	enterText(null, by, elementDescription, frame, value);
    }

    /**
     * Finds the textbox or textarea element using the specified location strategy, enters the value into it and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param value
     *            The text to enter into the textbox or textarea.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws InvalidElementStateException
     *             Thrown when the target element is not enabled.
     * @throws ElementNotVisibleException
     *             Thrown when the target element is not visible.
     * @throws StaleElementReferenceException
     *             Thrown when the target element is no longer valid in the document DOM.
     */
    public void enterText(By by, String elementDescription, String value) {
	enterText(by, elementDescription, null, value);
    }

    /**
     * Find the select element using the specified location strategy, selects the option using the specified selection strategy
     * and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @param selectBy
     *            The strategy to use to locate and select an option.
     * @param toSelect
     *            The text or substring or value or index of the option to select. If the value of {@code selectBy} is
     *            SelectBy.Index, then the value of {@code toSelect} must be parsable into an integer.
     * @throws NumberFormatException
     *             Thrown when the value of {@code toSelect} is not a valid integer.
     * @throws NoSuchElementException
     *             Thrown when no matching option elements are found.
     * @throws UnexpectedTagNameException
     *             Thrown when the element wrapped is not a {@code <select>} element.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public void selectOption(SearchContext context, By by, String elementDescription, String frame, SelectBy selectBy,
            String toSelect) {
	WebElement element = getElementOrThrow(context, by, elementDescription, frame);
	ISelect select = new Select(element);
	switch (selectBy) {
        	case Index:
        	    int index;
        	    try {
        		index = Integer.parseInt(toSelect);
        	    } catch (NumberFormatException e) {
        		throw new NumberFormatException(
        		        String.format("The text (%s) supplied in parameter \"toSelect\" is not an acceptable integer. %s", toSelect,
        		                e.getMessage()));
        	    }
        	    select.selectByIndex(index);
        	    break;
        	case Value:
        	    select.selectByValue(toSelect);
        	    break;
        	case VisibleText:
        	    select.selectByVisibleText(toSelect);
        	    break;
        	case VisibleTextContains:
        	    try {
        		ISelectExtensions.selectByVisibleTextContains(select, toSelect);
        	    } catch (NoSuchElementException e) {
        		String message = String.format("%s in %s combobox.", e.getMessage().replace(".", ""), elementDescription);
        		// WriteInfoLogEntry(message);
        		throw new NoSuchElementException(message);
        	    }
        	    break;
        	default:
        	    break;
	}
	// WriteInfoLogEntry(constructDataSelectedMessage(value, elementDescription));
    }

    /**
     * Find the select element using the specified location strategy, selects the option using the specified selection strategy
     * and logs the action.
     * 
     * @param context
     *            Context within which to search for the element.
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param selectBy
     *            The strategy to use to locate and select an option.
     * @param toSelect
     *            The text or substring or value or index of the option to select. If the value of {@code selectBy} is
     *            SelectBy.Index, then the value of {@code toSelect} must be parsable into an integer.
     * @throws NumberFormatException
     *             Thrown when the value of {@code toSelect} is not a valid integer.
     * @throws NoSuchElementException
     *             Thrown when no matching option elements are found.
     * @throws UnexpectedTagNameException
     *             Thrown when the element wrapped is not a {@code <select>} element.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public void selectOption(SearchContext context, By by, String elementDescription, SelectBy selectBy, String toSelect) {
	selectOption(context, by, elementDescription, null, selectBy, toSelect);
    }

    /**
     * Find the select element using the specified location strategy, selects the option using the specified selection strategy
     * and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param frame
     *            Id or name of the frame in which to find the element.
     * @param selectBy
     *            The strategy to use to locate and select an option.
     * @param toSelect
     *            The text or substring or value or index of the option to select. If the value of {@code selectBy} is
     *            SelectBy.Index, then the value of {@code toSelect} must be parsable into an integer.
     * @throws NumberFormatException
     *             Thrown when the value of {@code toSelect} is not a valid integer.
     * @throws NoSuchElementException
     *             Thrown when no matching option elements are found.
     * @throws UnexpectedTagNameException
     *             Thrown when the element wrapped is not a {@code <select>} element.
     * @throws NotFoundException
     *             If no element matches the criteria.
     * @throws NoSuchFrameException
     *             If the frame with Id or name specified in {@code frame} cannot be found.
     */
    public void selectOption(By by, String elementDescription, String frame, SelectBy selectBy, String toSelect) {
	selectOption(null, by, elementDescription, frame, selectBy, toSelect);
    }

    /**
     * Find the select element using the specified location strategy, selects the option using the specified selection strategy
     * and logs the action.
     * 
     * @param by
     *            The locating mechanism to use.
     * @param elementDescription
     *            User-friendly description of the element to use in logs and exception messages.
     * @param selectBy
     *            The strategy to use to locate and select an option.
     * @param toSelect
     *            The text or substring or value or index of the option to select. If the value of {@code selectBy} is
     *            SelectBy.Index, then the value of {@code toSelect} must be parsable into an integer.
     * @throws NumberFormatException
     *             Thrown when the value of {@code toSelect} is not a valid integer.
     * @throws NoSuchElementException
     *             Thrown when no matching option elements are found.
     * @throws UnexpectedTagNameException
     *             Thrown when the element wrapped is not a {@code <select>} element.
     * @throws NotFoundException
     *             If no element matches the criteria.
     */
    public void selectOption(By by, String elementDescription, SelectBy selectBy, String toSelect) {
	selectOption(null, by, elementDescription, null, selectBy, toSelect);
    }

    // ========================================================================================================================================
    // ========================================================================================================================================

    public String constructNotFoundErrorMessage(String elementDescription) {
	return String.format("Could not find %s on %s page", elementDescription, interactableName);
    }

    public String constructDataEnteredMessage(String enteredValue, String elementDescription) {
	return String.format("Entered '%s' into %s on %s page", enteredValue, elementDescription, interactableName);
    }

    public String constructDataFetchedMessage(String fetchedValue, String elementDescription) {
	return String.format("Fetched value '%s' from %s on %s page", fetchedValue, elementDescription, interactableName);
    }

    public String constructDataClearedMessage(String clearedValue, String elementDescription) {
	return String.format("Cleared text '%s' from %s on %s page", clearedValue, elementDescription, interactableName);
    }

    public String constructDataFetchedMessage(List<String> fetchedValue, String elementDescription) {
	return String.format("Fetched values '%s' from %s on %s page", String.join(", ", fetchedValue), elementDescription, interactableName);
    }

    public String constructClickedMessage(String elementDescription) {
	return String.format("Clicked %s on %s page", elementDescription, interactableName);
    }

    public String constructCheckboxClickedMessage(String elementDescription, Boolean ticked) {
	return String.format(ticked ? "Ticked {0} on {1} page" : "Unticked {0} on {1} page", elementDescription, interactableName);
    }

    public String constructDataSelectedMessage(String enteredValue, String elementDescription) {
	return String.format("Selected '%s' from %s on %s page", enteredValue, elementDescription, interactableName);
    }

    public String constructDataValueSelectedMessage(String enteredValue, String elementDescription) {
	return String.format("Selected option with value '%s' from %s on %s page", enteredValue, elementDescription, interactableName);
    }

    public String constructDataPastedMessage(String pastedText, String elementDescription) {
	return String.format("Pasted '%s' from system clipboard into %s on %s page", pastedText, elementDescription, interactableName);
    }

    /**
     * Selects either the first frame on the page or the main document when a page contains iFrames.
     */
    public void switchToDefaultContent() {
	driver.switchTo().defaultContent();
    }
}
