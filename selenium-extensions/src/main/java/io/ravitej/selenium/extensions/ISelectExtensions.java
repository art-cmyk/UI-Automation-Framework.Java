/**
 *
 */
package io.ravitej.selenium.extensions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ISelect;

import java.util.List;

/**
 * Provides a set of helpful extensions to Selenium's ISelect interface.
 *
 * @author Ravitej Aluru
 */
public class ISelectExtensions {
    /**
     * Selects the first option that display text containing the argument.
     *
     * @param select The ISelect from which to select the option.
     * @param text   The substring to search for in the options of the select element.
     */
    public static void selectByVisibleTextContains(ISelect select, String text) {
        Boolean selected = null;
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            final String optionText = WebElementExtensions.getText(option);
            if (optionText.contains(text)) {
                select.selectByVisibleText(optionText);
                selected = true;
                break;
            }
        }
        if (!selected) {
            throw new NoSuchElementException(String.format("Could not find option with visible text containing '%s'.", text));
        }
    }
}
