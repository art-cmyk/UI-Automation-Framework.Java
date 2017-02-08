package io.ravitej.ui.tests.config.driver;

/**
 * Created by ravit on 07/12/2016.
 */
public class AdditionalCapability {
    /**
     * The Id of the capability to set
     */
    private String id;

    /**
     * The value to assign to the capability
     */
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
