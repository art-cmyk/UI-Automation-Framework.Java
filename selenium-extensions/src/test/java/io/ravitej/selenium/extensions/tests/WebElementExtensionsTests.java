package io.ravitej.selenium.extensions.tests;

import io.ravitej.selenium.extensions.WebElementExtensions;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Tests for WebElementExtensions.
 *
 * @author Ravitej Aluru
 */
public class WebElementExtensionsTests {

    private WebElement mockWebElement = mock(WebElement.class);

    @Test
    public void getText_should_get_the_inner_text_and_trim_trailing_and_leading_whitespace() {
        String innerText = "   some inner text   ";
        when(mockWebElement.getText()).thenReturn(innerText);
        assertThat(WebElementExtensions.getText(mockWebElement)).isEqualTo(innerText.trim());
    }

    @Test
    public void tick_should_call_click_once_if_checkbox_is_not_ticked() {
        when(mockWebElement.isSelected()).thenReturn(false);
        WebElementExtensions.tick(mockWebElement);
        verify(mockWebElement, times(1)).click();
    }

    @Test
    public void tick_should_not_call_click_if_checkbox_is_already_ticked() {
        when(mockWebElement.isSelected()).thenReturn(true);
        WebElementExtensions.tick(mockWebElement);
        verify(mockWebElement, times(0)).click();
    }

    @Test
    public void untick_should_call_click_once_if_checkbox_is_already_ticked() {
        when(mockWebElement.isSelected()).thenReturn(true);
        WebElementExtensions.untick(mockWebElement);
        verify(mockWebElement, times(1)).click();
    }

    @Test
    public void untick_should_not_call_click_if_checkbox_is_not_ticked() {
        when(mockWebElement.isSelected()).thenReturn(false);
        WebElementExtensions.untick(mockWebElement);
        verify(mockWebElement, times(0)).click();
    }

    @Test
    public void enterText_should_call_clear_and_sendKeys_with_the_given_text() {
        WebElementExtensions.enterText(mockWebElement, "some text");
        verify(mockWebElement, times(1)).clear();
        verify(mockWebElement, times(1)).sendKeys("some text");
    }
}
