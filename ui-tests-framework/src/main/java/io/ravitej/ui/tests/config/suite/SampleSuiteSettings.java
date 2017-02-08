package io.ravitej.ui.tests.config.suite;

import io.ravitej.ui.tests.config.ILaunchPageHandler;

/**
 * Created by ravit on 03/02/2017.
 */
public class SampleSuiteSettings extends AbstractSuiteSettings {
    private String myCustomSetting;

    public SampleSuiteSettings(){}

    @Override
    public String GetLaunchPage(int targetPage) {
        return null;
    }

    @Override
    public String GetLaunchPage(int targetPage, ILaunchPageHandler launchPageHandler) {
        return null;
    }

    public String getMyCustomSetting() {
        return myCustomSetting;
    }

    public void setMyCustomSetting(String myCustomSetting) {
        this.myCustomSetting = myCustomSetting;
    }
}
