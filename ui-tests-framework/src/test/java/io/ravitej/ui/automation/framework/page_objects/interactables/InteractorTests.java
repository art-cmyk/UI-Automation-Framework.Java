/**
 *
 */
package io.ravitej.ui.automation.framework.page_objects.interactables;

import io.ravitej.ui.tests.page_objects.interactables.Interactor;
import io.ravitej.ui.tests.page_objects.interactables.SelectBy;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.support.ui.Select;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

/**
 * Tests for the Interactor class
 *
 * @author Ravitej Aluru
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Interactor.class)
public class InteractorTests {

    private WebDriver mockWebDriver;
    private WebElement mockWebElement;
    private SearchContext mockSearchContext;
    private Interactor cut;
    private TargetLocator mockTargetLocator;
    private List<WebElement> elements;
    private Select mockSelect;

    @Before
    public void beforeEachTest() {
        mockWebDriver = mock(WebDriver.class);
        mockWebElement = mock(WebElement.class);
        mockSearchContext = mock(SearchContext.class);
        mockTargetLocator = mock(TargetLocator.class);
        given(mockWebDriver.switchTo()).willReturn(mockTargetLocator);
        given(mockTargetLocator.defaultContent()).willReturn(mockWebDriver);
        cut = new Interactor(mockWebDriver, "Authentication");
        elements = new ArrayList<WebElement>();
        mockSelect = mock(Select.class);
    }

    @Test
    public void isElementDisplayed_should_return_true_if_element_is_found_and_displayed() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(true);
        // when
        Boolean displayed = cut.isElementDisplayed(By.id("id"), "elementDescription");
        // then
        assertThat(displayed).isTrue();
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_found_but_not_displayed() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(false);
        // when
        Boolean displayed = cut.isElementDisplayed(By.id("id"), "elementDescription");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_not_found() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean displayed = cut.isElementDisplayed(By.id("id"), "elementDescription");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void isElementDisplayed_should_return_true_if_element_is_found_and_displayed_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(true);
        // when
        Boolean displayed = cut.isElementDisplayed(By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(displayed).isTrue();
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_found_but_not_displayed_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(false);
        // when
        Boolean displayed = cut.isElementDisplayed(By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_not_found_in_a_frame() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean displayed = cut.isElementDisplayed(By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementDisplayed_should_return_true_if_element_is_found_and_displayed_within_a_context() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(true);
        // when
        Boolean displayed = cut.isElementDisplayed(mockSearchContext, By.id("id"), "elementDescription");
        // then
        assertThat(displayed).isTrue();
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_found_but_not_displayed_within_a_context() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(false);
        // when
        Boolean displayed = cut.isElementDisplayed(mockSearchContext, By.id("id"), "elementDescription");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_not_found_within_a_context() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean displayed = cut.isElementDisplayed(mockSearchContext, By.id("id"), "elementDescription");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void isElementDisplayed_should_return_true_if_element_is_found_and_displayed_within_a_context_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(true);
        // when
        Boolean displayed = cut.isElementDisplayed(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(displayed).isTrue();
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_found_but_not_displayed_within_a_context_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isDisplayed()).willReturn(false);
        // when
        Boolean displayed = cut.isElementDisplayed(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementDisplayed_should_return_false_if_element_is_not_found_within_a_context_in_a_frame() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean displayed = cut.isElementDisplayed(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(displayed).isFalse();
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementPresent_should_return_true_if_element_is_found() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(By.id("id"), "elementDescription");
        // then
        assertThat(elementPresent).isTrue();
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void isElementPresent_should_return_false_if_element_is_not_found() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(By.id("id"), "elementDescription");
        // then
        assertThat(elementPresent).isFalse();
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void isElementPresent_should_return_true_if_element_is_found_within_a_context() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(mockSearchContext, By.id("id"), "elementDescription");
        // then
        assertThat(elementPresent).isTrue();
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void isElementPresent_should_return_false_if_element_is_not_found_within_a_context() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(mockSearchContext, By.id("id"), "elementDescription");
        // then
        assertThat(elementPresent).isFalse();
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void isElementPresent_should_return_true_if_element_is_found_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(By.id("id"))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(elementPresent).isTrue();
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementPresent_should_return_false_if_element_is_not_found_in_a_frame() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(elementPresent).isFalse();
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementPresent_should_return_true_if_element_is_found_within_a_context_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(By.id("id"))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(elementPresent).isTrue();
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void isElementPresent_should_return_false_if_element_is_not_found_within_a_context_in_a_frame() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        Boolean elementPresent = cut.isElementPresent(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        assertThat(elementPresent).isFalse();
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementOrThrow_should_return_element_if_found() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        WebElement myElement = cut.getElementOrThrow(By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(myElement).isNotNull().isInstanceOf(WebElement.class).isEqualTo(mockWebElement);
        });
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void getElementOrThrow_should_throw_an_exception_if_element_is_not_found() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementOrThrow(By.id("id"), "elementDescription");
        }).withMessageContaining(String.format("%s. Element locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void getElementOrThrow_should_return_element_if_found_within_a_context() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        WebElement myElement = cut.getElementOrThrow(mockSearchContext, By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(myElement).isNotNull().isInstanceOf(WebElement.class).isEqualTo(mockWebElement);
        });
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void getElementOrThrow_should_throw_an_exception_if_element_is_not_found_within_a_context() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementOrThrow(mockSearchContext, By.id("id"), "elementDescription");
        }).withMessageContaining(String.format("%s. Element locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void getElementOrThrow_should_return_element_if_found_within_a_context_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        WebElement myElement = cut.getElementOrThrow(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(myElement).isNotNull().isInstanceOf(WebElement.class).isEqualTo(mockWebElement);
        });
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementOrThrow_should_throw_an_exception_if_element_is_not_found_within_a_context_in_a_frame() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementOrThrow(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        }).withMessageContaining(String.format("%s. Element locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementOrThrow_should_return_element_if_found_in_a_frame() {
        // given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        WebElement myElement = cut.getElementOrThrow(By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(myElement).isNotNull().isInstanceOf(WebElement.class).isEqualTo(mockWebElement);
        });
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementOrThrow_should_throw_an_exception_if_element_is_not_found_in_a_frame() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementOrThrow(By.id("id"), "elementDescription", "frameId");
        }).withMessageContaining(String.format("%s. Element locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementsOrThrow_should_return_list_of_all_matching_elements_if_found() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElementsOrThrow(By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void getElementsOrThrow_should_throw_an_exception_if_elements_are_not_found() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementsOrThrow(By.id("id"), "elementDescription");
        }).withMessageContaining(String.format("%s. Elements locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void getElementsOrThrow_should_return_list_of_all_matching_elements_if_found_in_a_frame() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElementsOrThrow(By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementsOrThrow_should_throw_an_exception_if_elements_are_not_found_in_a_frame() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementsOrThrow(By.id("id"), "elementDescription", "frameId");
        }).withMessageContaining(String.format("%s. Elements locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementsOrThrow_should_return_list_of_all_matching_elements_if_found_within_a_context_in_a_frame() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElementsOrThrow(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementsOrThrow_should_throw_an_exception_if_elements_are_not_found_within_a_context_in_a_frame() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementsOrThrow(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        }).withMessageContaining(String.format("%s. Elements locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElementsOrThrow_should_return_list_of_all_matching_elements_if_found_within_a_context() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElementsOrThrow(mockSearchContext, By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void getElementsOrThrow_should_throw_an_exception_if_elements_are_not_found_within_a_context() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        // then
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            cut.getElementsOrThrow(mockSearchContext, By.id("id"), "elementDescription");
        }).withMessageContaining(String.format("%s. Elements locator: By.id: id\n", cut.constructNotFoundErrorMessage("elementDescription")));
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void getElements_should_return_list_of_all_matching_elements_if_found() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void getElements_should_return_an_empty_list_if_elements_are_not_found() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .hasSize(0);
        });
        assertThatFindElementsOnDriverAndNoFrameSwitch();
    }

    @Test
    public void getElements_should_return_list_of_all_matching_elements_if_found_in_a_frame() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElements_should_return_an_empty_list_if_elements_are_not_found_in_a_frame() {
        // given
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .hasSize(0);
        });
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElements_should_return_list_of_all_matching_elements_if_found_within_a_context_in_a_frame() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElements_should_return_an_empty_list_if_elements_are_not_found_within_a_context_in_a_frame() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .hasSize(0);
        });
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
    }

    @Test
    public void getElements_should_return_list_of_all_matching_elements_if_found_within_a_context() {
        // given
        elements.add(mockWebElement);
        WebElement webElement1 = mock(WebElement.class);
        WebElement webElement2 = mock(WebElement.class);
        elements.add(webElement1);
        elements.add(webElement2);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(mockSearchContext, By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .containsOnly(mockWebElement, webElement1, webElement2);
        });
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void getElements_should_return_an_empty_list_if_elements_are_not_found_within_a_context() {
        // given
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        // when
        List<WebElement> actualReturnedElements = cut.getElements(mockSearchContext, By.id("id"), "elementDescription");
        // then
        SoftAssertions.assertSoftly(softly -> {
            assertThat(actualReturnedElements).isNotNull()
                    .isInstanceOf(List.class)
                    .hasSize(0);
        });
        assertThatFindElementsOnContextAndNoFrameSwitch();
    }

    @Test
    public void enterText_should_find_the_element_and_enter_text_into_it() {
        //given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        //when
        cut.enterText(By.id("id"), "elementDescription", "value");
        //then
        assertThatFindElementsOnDriverAndNoFrameSwitch();    // check that switch to frame hasn't been called, element has been searched for on the whole page (mockWebDriver), element hasn't been searched for within a mockSearchContext
        assertThatEnterTextWorks("value");
    }

    @Test
    public void enterText_should_find_the_element_in_a_frame_and_enter_text_into_it() {
        //given
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        //when
        cut.enterText(By.id("id"), "elementDescription", "frameId", "value");
        //then
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");    // check that switch to frame has been called with the correct frameId, element has been searched for on the whole page (mockWebDriver), element hasn't been searched for within a mockSearchContext
        assertThatEnterTextWorks("value");
    }

    @Test
    public void enterText_should_find_the_element_within_a_context_and_enter_text_into_it() {
        //given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        //when
        cut.enterText(mockSearchContext, By.id("id"), "elementDescription", "value");
        //then
        assertThatFindElementsOnContextAndNoFrameSwitch();    // check that switch to frame hasn't been called, element hasn't been searched for on the whole page (mockWebDriver), element has been searched for within the given mockSearchContext
        assertThatEnterTextWorks("value");
    }

    @Test
    public void enterText_should_find_the_element_within_a_context_in_a_frame_and_enter_text_into_it() {
        //given
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        //when
        cut.enterText(mockSearchContext, By.id("id"), "elementDescription", "frameId", "value");
        //then
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
        assertThatEnterTextWorks("value");
    }

    @Test
    public void tick_should_find_the_element_and_tick_if_not_already_ticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnDriverAndNoFrameSwitch();
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void tick_should_find_the_element_and_keep_it_ticked_if_already_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnDriverAndNoFrameSwitch();
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void tick_should_find_the_element_in_a_frame_and_tick_if_not_already_ticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void tick_should_find_the_element_in_a_frame_and_keep_it_ticked_if_already_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void tick_should_find_the_element_within_a_context_in_a_frame_and_tick_if_not_already_ticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void tick_should_find_the_element_within_a_context_in_a_frame_and_keep_it_ticked_if_already_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void tick_should_find_the_element_within_a_context_and_tick_if_not_already_ticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(mockSearchContext, By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnContextAndNoFrameSwitch();
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void tick_should_find_the_element_within_a_context_and_keep_it_ticked_if_already_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.tick(mockSearchContext, By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnContextAndNoFrameSwitch();
        assertThatTickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_and_untick_it_if_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnDriverAndNoFrameSwitch();
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_and_keep_it_unticked_if_already_unticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnDriverAndNoFrameSwitch();
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_in_a_frame_and_untick_it_if_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_in_a_frame_and_keep_it_unticked_if_already_unticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockWebDriver.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnDriverAndStringFrameSwitch("frameId");
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_within_a_context_in_a_frame_and_untick_it_if_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_within_a_context_in_a_frame_and_keep_it_unticked_if_already_unticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(mockSearchContext, By.id("id"), "elementDescription", "frameId");
        //then
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_within_a_context_and_untick_it_if_ticked() {
        //given
        boolean alreadyTicked = true;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(mockSearchContext, By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnContextAndNoFrameSwitch();
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void untick_should_find_the_element_within_a_context_and_keep_it_unticked_if_already_unticked() {
        //given
        boolean alreadyTicked = false;
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        given(mockWebElement.isSelected()).willReturn(alreadyTicked);
        //when
        cut.untick(mockSearchContext, By.id("id"), "elementDescription");
        //then
        assertThatFindElementsOnContextAndNoFrameSwitch();
        assertThatUntickWorks(alreadyTicked);
    }

    @Test
    public void selectOption_by_Index_should_find_the_element_within_a_context_in_a_frame_and_select_the_given_option() throws Exception {
        //given
        SelectBy selectBy = SelectBy.Index;
        String index = "3";
        elements.add(mockWebElement);
        given(mockSearchContext.findElements(any(By.class))).willReturn(elements);
        PowerMockito.whenNew(Select.class).withArguments(mockWebElement).thenReturn(mockSelect);
        //when
        cut.selectOption(mockSearchContext, By.id("id"), "elementDescription", "frameId", selectBy, index);
        //then
        PowerMockito.verifyNew(Select.class).withArguments(mockWebElement);
        assertThatFindElementsOnContextAndStringFrameSwitch("frameId");
        assertThatSelectOptionWorks(selectBy, index);
    }
    
    /*
     * ===================================
     * Private Assertions
     * ===================================
     */

    /**
     * Verifies that clear() and sendKeys() methods were called on the element in the correct order and with correct values
     */
    private void assertThatEnterTextWorks(String enteredText) {
        InOrder inOrder = Mockito.inOrder(mockWebElement);
        then(mockWebElement).should(inOrder).clear();
        then(mockWebElement).should(inOrder).sendKeys(enteredText);
    }

    private void assertThatTickWorks(boolean alreadyTicked) {
        InOrder inOrder = Mockito.inOrder(mockWebElement);
        then(mockWebElement).should(inOrder).isSelected();
        if (!alreadyTicked)
            then(mockWebElement).should(inOrder).click();
    }

    private void assertThatUntickWorks(boolean alreadyTicked) {
        InOrder inOrder = Mockito.inOrder(mockWebElement);
        then(mockWebElement).should(inOrder).isSelected();
        if (alreadyTicked)
            then(mockWebElement).should(inOrder).click();
    }

    private void assertThatSelectOptionWorks(SelectBy selectBy, String toSelect) {
        switch (selectBy) {
            case Index:
                then(mockSelect).should().selectByIndex(Integer.parseInt(toSelect));
            default:
                break;
        }
    }

    private void assertThatFindElementsOnDriverAndNoFrameSwitch() {
        assertDriverContextAndFrame(1, 0, 0, 0, 0);
    }

    private void assertThatFindElementsOnContextAndNoFrameSwitch() {
        assertDriverContextAndFrame(0, 1, 0, 0, 0);
    }

    private void assertThatFindElementsOnDriverAndStringFrameSwitch(String frameIdOrName) {
        assertDriverContextAndFrame(1, 0, 1, 0, 0);
        then(mockTargetLocator).should().frame(frameIdOrName);
    }

    private void assertThatFindElementsOnContextAndStringFrameSwitch(String frameIdOrName) {
        assertDriverContextAndFrame(0, 1, 1, 0, 0);
        then(mockTargetLocator).should().frame(frameIdOrName);
    }

    private void assertDriverContextAndFrame(int driverCount, int contextCount, int frameByStringCount, int frameByIntCount, int frameByWebElementCount) {
        then(mockTargetLocator).should(times(1)).defaultContent();
        then(mockSearchContext).should(times(contextCount)).findElements(any(By.class));
        then(mockWebDriver).should(times(driverCount)).findElements(any(By.class));
        then(mockTargetLocator).should(times(frameByStringCount)).frame(anyString());
        then(mockTargetLocator).should(times(frameByIntCount)).frame(anyInt());
        then(mockTargetLocator).should(times(frameByWebElementCount)).frame(any(WebElement.class));
    }
}
