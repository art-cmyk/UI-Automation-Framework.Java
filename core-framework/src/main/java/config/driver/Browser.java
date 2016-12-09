package config.driver; /**
 * Created by ravit on 06/12/2016.
 */

/**
 * Determines the browser to execute a suite of tests in.
 */
public enum Browser
{
    /**
     * Mozilla Firefox
     */
    Firefox,

    /**
     * Google Chrome
     */
    Chrome,

    /**
     * Microsoft Internet Explorer
     */
    InternetExplorer,

    /**
     * Apple Safari
     */
    Safari,

    /**
     * Opera
     */
    Opera,

    /**
     * Microsoft Edge
     */
    Edge,

    /**
     * Not specified - used the desired capabilities to handle any target settings
     */
    Unspecified
}
