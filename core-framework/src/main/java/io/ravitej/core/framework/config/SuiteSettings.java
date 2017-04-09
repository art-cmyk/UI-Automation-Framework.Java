package io.ravitej.core.framework.config;

import com.typesafe.config.Config;
/**
 * Provides an interface to get hold of execution settings loaded from config files.
 * @author Ravitej Aluru
 */
public class SuiteSettings {

    private final Config config;

    /**
     * Loads data from "suitesettings.conf" file.
     * @return An instance of {@code com.typesafe.config.Config} with all the data in the file loaded into it.
     */
    public static Config load(){ return new SuiteSettings().config; }

    private SuiteSettings(){
        this.config = ConfigHelpers.initConfig("suitesettings.conf");
    }
}
