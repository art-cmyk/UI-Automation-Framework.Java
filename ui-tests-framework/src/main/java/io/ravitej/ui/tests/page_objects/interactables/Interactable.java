/**
 * 
 */
package io.ravitej.ui.tests.page_objects.interactables;

/**
 * Represents any interactable element within the browser's context.
 * @author Ravitej Aluru
 */
public interface Interactable {
    /**
     * Gets the name of the interactable item. For example, name of the page, modal dialog, sliding panel etc.
     */
    String getName();

    /**
     * Gets a value indicating whether expected interactable item is displayed or not.
     * @param throwWhenNotDisplayed Indicate whether {@code NotOnPageException} exception should be thrown if not on expected item.
     * @return True if control is on expected item. False if {@code throwWhenNotDisplayed} is set to false and control is not on expected item.
     * @throws Thrown when control is not on expected item and {@code throwWhenNotDisplayed} is set to true.
     */
     Boolean isDisplayed(Boolean throwWhenNotDisplayed);
}
