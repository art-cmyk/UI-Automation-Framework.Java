package io.ravitej.core.config;

import com.typesafe.config.Config;

import java.util.Optional;

/**
 * Created by ravit on 12/12/2016.
 */
public class Environments {

        public final Config config;

        public static Environments load() {
            return new Environments(System.getProperty("environment"));
        }

        public static Environments load(final String environment) {
            return new Environments(environment);
        }

        public Environments(final String environment) {
            Optional param = Optional.ofNullable(environment);
            if (param.isPresent()) {
                this.config = ConfigHelpers.initConfig("environments.conf", environment);
            } else {
                throw new RuntimeException(
                        "Cannot initialise Environments. Please provide environment profile parameter (-Denvironment=staging)");
            }
        }
}
