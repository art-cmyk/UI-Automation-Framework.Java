package io.ravitej.core.framework.config;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import com.typesafe.config.Config;

/**
 * @author Ravitej Aluru
 *
 */
public class SuiteSettingsTests {

    @Test
    public void load_should_return_config_object(){
	Config c = SuiteSettings.load();
	SoftAssertions.assertSoftly(softly -> {
	    softly.assertThat(c).isInstanceOf(Config.class);
	    softly.assertThat(c).isNotNull();
	});
    }
}
