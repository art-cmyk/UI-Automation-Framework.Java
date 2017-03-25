package io.ravitej.ui.tests.session;

import io.ravitej.ui.tests.driver.DriverSession;

/**
 * Represents the browser session which is displaying the AUT.
 *
 * @author Ravitej Aluru
 */
public interface Session {

    DriverSession getDriverSession();

    void quit();
}
