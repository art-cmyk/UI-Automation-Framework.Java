/**
 * 
 */
package io.ravitej.selenium.extensions;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Provides a set of helpful extensions to Selenium's SearchContext interface.
 *
 * @author Ravitej Aluru
 */
public abstract class SearchContextExtensions {

    /**
     * Finds the first element on the page using the given mechanism.
     * @param searchContext The context within which to search for the element.
     * @param by The locator mechanism to use to find the element.
     * @return The first matching element within the context or null if none found.
     */
    public static WebElement findElementSafe(SearchContext searchContext, By by){
	List<WebElement> elements = searchContext.findElements(by);
	if(elements.isEmpty()){
	    return null;
	}
	else{
	    return elements.get(0);
	}
    }
    
    /**
     * Finds the first element on the page using the given mechanism.
     * @param searchContext The context within which to search for the element.
     * @param by The locator mechanism to use to find the element.
     * @param exceptionMessage User-friendly message when exception is thrown.
     * @return The first matching element within the context.
     * @throws NotFoundException when element cannot be found.
     */
    public static WebElement findElementOrThrow(SearchContext searchContext, By by, String exceptionMessage){
	WebElement element = findElementSafe(searchContext, by);
	if(element == null){
	    String message = String.format("%s. Element locator: %s", exceptionMessage, by.toString());
	    throw new NotFoundException(message);
	}
	else{
	    return element;
	}
    }

    /**
     * Finds all elements on the page using the given mechanism.
     * @param searchContext The context within which to search for the element.
     * @param by The locator mechanism to use to find the element.
     * @return A list of matching elements within the context or null if none found.
     */
    public static List<WebElement> findElementsSafe(SearchContext searchContext, By by){
	List<WebElement> elements = searchContext.findElements(by);
	if(elements.isEmpty()){
	    return null;
	}
	else{
	    return elements;
	}
    }
    
    /**
     * Finds all elements on the page using the given mechanism.
     * @param searchContext The context within which to search for the element.
     * @param by The locator mechanism to use to find the element.
     * @param exceptionMessage User-friendly message when exception is thrown.
     * @return A list of matching elements within the context.
     * @throws NotFoundException when elements cannot be found.
     */
    public static List<WebElement> findElementsOrThrow(SearchContext searchContext, By by, String exceptionMessage) {
	List<WebElement> elements = findElementsSafe(searchContext, by);
	if(elements == null){
	    String message = String.format("%s. Elements locator: %s", exceptionMessage, by.toString());
	    throw new NotFoundException(message);
	}
	else{
	    return elements;
	}
    }

}
