/**
 *
 */
package io.ravitej.selenium.extensions.tests;

import io.ravitej.selenium.extensions.SearchContextExtensions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for SearchContextExtensions.
 *
 * @author Ravitej Aluru
 */
public class SearchContextExtensionsTests {

    private SearchContext mockSearchContext = mock(SearchContext.class);
    private WebElement mockWebElement = mock(WebElement.class);

    @Test
    public void find_element_safe_should_return_null_when_elements_are_not_found() {
        when(mockSearchContext.findElements(By.id("elementid"))).thenReturn(new ArrayList<WebElement>());
        WebElement returnValue = SearchContextExtensions.findElementSafe(mockSearchContext, By.id("elementid"));
        assertThat(returnValue).isNull();
    }

    @Test
    public void find_element_safe_should_return_WebElement_object_when_elements_are_found() {
        List<WebElement> elements = new ArrayList<WebElement>();
        elements.add(mockWebElement);
        when(mockSearchContext.findElements(By.id("elementid"))).thenReturn(elements);
        WebElement returnValue = SearchContextExtensions.findElementSafe(mockSearchContext, By.id("elementid"));
        assertThat(returnValue).isInstanceOf(WebElement.class);
    }

    @Test
    public void find_element_safe_should_return_first_element_from_the_list_when_elements_are_found() {
        List<WebElement> elements = new ArrayList<WebElement>();
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(mockWebElement);
        elements.add(webElement1);
        elements.add(webElement2);
        when(mockSearchContext.findElements(By.id("elementid"))).thenReturn(elements);
        WebElement returnValue = SearchContextExtensions.findElementSafe(mockSearchContext, By.id("elementid"));
        assertThat(returnValue).isEqualTo(mockWebElement);
    }

    @Test
    public void find_element_or_throw_should_return_throw_exception_when_element_not_found() {
        final String exceptionMessage = "Some exception message";
        when(mockSearchContext.findElements(By.id("elementid"))).thenReturn(new ArrayList<WebElement>());
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            SearchContextExtensions.findElementOrThrow(mockSearchContext, By.id("elementid"), exceptionMessage);
        }).withMessageContaining(String.format("%s. Element locator: By.id: elementid\n", exceptionMessage));
    }
}
