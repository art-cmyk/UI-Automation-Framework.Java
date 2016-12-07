/**
 * Created by ravit on 05/12/2016.
 */
public enum HubType {
    /**
     * No hub; browser is started locally
     */
    None,
    /**
     * The internal remote hub
     */
    Internal,

    /**
     * A single or multi node hub which is
     * started within Docker containers
     */
    Docker,

    /**
     * BrowserStack.com cloud hub
     */
    BrowserStack,

    /**
     * SauceLabs on demand cloud hub
     */
    SauceLabs,

    /**
     * CrossBrowserTesting.com cloud hub
     */
    CrossBrowserTesting
}
