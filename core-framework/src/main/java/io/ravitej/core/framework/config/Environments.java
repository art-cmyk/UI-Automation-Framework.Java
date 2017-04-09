package io.ravitej.core.framework.config;

import com.typesafe.config.Config;

import java.util.Optional;

/**
 * Provides an interface to get hold of test environments loaded from the config files.
 * @author Ravitej Aluru
 */
public class Environments {

    private final Config config;

    /**
     * Loads data from "environments.conf" file and gets config section specified in the "environment" system property.
     * @return An instance of Config with the requested config section loaded into it.
     */
    public static Config load() {
        return new Environments(System.getProperty("environment")).config;
    }

    /**
     * Loads data from "environments.conf" file and gets config section specified in the {@code environment} parameter.
     * @param environment Name of the config section to load from the file.
     * @return An instance of Config with the requested config section loaded into it.
     */
    public static Config load(final String environment) {
        return new Environments(environment).config;
    }

    private Environments(final String environment) {
        Optional<String> param = Optional.ofNullable(environment);
        if (param.isPresent()) {
            this.config = ConfigHelpers.initConfig("environments.conf", environment);
        } else {
            throw new RuntimeException(
                    "Cannot initialise Environments. Please provide environment profile parameter (-Denvironment=staging)");
        }
    }
}
