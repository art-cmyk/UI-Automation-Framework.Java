package config;

import com.typesafe.config.Config;

/**
 * Created by ravit on 12/12/2016.
 */
public class SuiteSettings {

        public final Config config;

        public static SuiteSettings suiteSettings(){ return new SuiteSettings(); }

        public SuiteSettings(){
            this.config = ConfigHelpers.initConfig("suitesettings.conf");
        }
}
