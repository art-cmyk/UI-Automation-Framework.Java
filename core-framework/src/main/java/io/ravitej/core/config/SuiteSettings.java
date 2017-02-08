package io.ravitej.core.config;

import com.typesafe.config.Config;

/**
 * Created by ravit on 12/12/2016.
 */
public class SuiteSettings {

        public final Config config;

        public static SuiteSettings load(){ return new SuiteSettings(); }

        public SuiteSettings(){
            this.config = ConfigHelpers.initConfig("suitesettings.conf");
        }
}
