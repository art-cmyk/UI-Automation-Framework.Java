package io.ravitej.core.framework.config;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import com.typesafe.config.Config;

/**
 * @author Ravitej Aluru
 *
 */
public class EnvironmentsTests {

    @Test
    public void load_should_throw_an_exception_when_argument_is_null(){
	assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> { 
	    	Config c = Environments.load(null); 
	    }).withMessage("Cannot initialise Environments. Please provide environment profile parameter (-Denvironment=staging)");
    }
    
    @Test
    public void load_should_return_config_object_when_valid_argument_is_passed_in(){
	Config c = Environments.load("env1");
	SoftAssertions.assertSoftly(softly -> {
	    softly.assertThat(c).isInstanceOf(Config.class);
	    softly.assertThat(c).isNotNull();
	    softly.assertThat(c.getString("key1")).isEqualTo("value1");
	});
    }
    
    @Test
    public void load_should_return_config_object_when_valid_argument_is_set_in_system_properties(){
	System.setProperty("environment", "env2");
	Config c = Environments.load();
	SoftAssertions.assertSoftly(softly -> {
	    softly.assertThat(c).isInstanceOf(Config.class);
	    softly.assertThat(c).isNotNull();
	    softly.assertThat(c.getString("key1")).isEqualTo("value11");
	});
    }
}
