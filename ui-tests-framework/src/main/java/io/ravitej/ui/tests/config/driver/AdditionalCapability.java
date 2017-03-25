package io.ravitej.ui.tests.config.driver;

/**
 * Created by ravit on 07/12/2016.
 */
public class AdditionalCapability {
    /**
     * The name of the capability to set
     */
    private String name;

    /**
     * The value to assign to the capability
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
