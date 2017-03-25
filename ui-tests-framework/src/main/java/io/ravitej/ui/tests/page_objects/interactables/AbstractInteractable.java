/**
 * 
 */
package io.ravitej.ui.tests.page_objects.interactables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import uk.gov.hmrc.ui.automation.framework.page.objects.session.Session;

/**
 * 
 * @author Ravitej Aluru
 */
public abstract class AbstractInteractable implements Interactable {

    /**
     * The {@code org.openqa.selenium.WebDriver} instance used to drive interaction with this item.
     */
    private final WebDriver driver;

    /**
     * Instance of Session
     */
    private final Session session;

    /**
     * The name of the interactable item. For example, name of the page, modal dialog, sliding panel etc.
     */
    private final String name;

    /**
     * The {@code org.openqa.selenium.WebElement} which contains the main content of the interactable item.
     */
    private WebElement contentContainer;
    
    /**
     * An instance of Interactor class which provides various methods to easily interact with the browser.
     */
    protected final Interactor interactor; 

    /**
     * 
     * @param session
     *            The {@code uk.gov.hmrc.ui.automation.framework.page.objects.session.Session} instance which contains this item.
     * @param name
     *            The name of the interactable item. For example, name of the page, modal dialog, sliding panel etc.
     */
    protected AbstractInteractable(Session session, String name) {
	this.session = session;
	this.driver = session.getDriverSession().getWebDriver();
	this.name = name;
	this.contentContainer = null;
	this.interactor = new Interactor(driver, name);
    }

    /**
     * Gets the name of the interactable item.
     */
    public String getName() {
	return name;
    }

    /**
     * @return The {@code org.openqa.selenium.WebElement} which contains the main content of this interactable item. This is null
     *         by default unless set.
     */
    protected WebElement getContentContainer() {
	return contentContainer;
    }

    /**
     * @param contentContainer
     *            The {@code org.openqa.selenium.WebElement} which contains the main content of this interactable item.
     */
    protected void setContentContainer(WebElement contentContainer) {
	this.contentContainer = contentContainer;
    }

    /**
     * @return the driver
     */
    protected WebDriver getDriver() {
	return driver;
    }

    /**
     * @return the session
     */
    protected Session getSession() {
	return session;
    }

    
}
