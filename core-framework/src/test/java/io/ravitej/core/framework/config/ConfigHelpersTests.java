/**
 * 
 */
package io.ravitej.core.framework.config;

import com.typesafe.config.Config;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ravitej Aluru
 *
 */
public class ConfigHelpersTests {

    @Test
    public void an_error_with_expected_message_should_be_thrown_if_the_file_doesnt_exist(){
	final String filename = "my-file-with-an-odd-name.txt";
	assertThatExceptionOfType(Error.class).isThrownBy(() -> {
	    Config c = ConfigHelpers.initConfig(filename);
	}).withMessage("Config file with name [%s] could not be found in your classpath.", filename);
    }
    
    @Test @Ignore("Not sure how to create a file that is not a proper file!")
    public void an_error_with_expected_message_should_be_thrown_if_the_file_is_not_a_proper_file(){
	final String filename = "not-a-proper-file";
	assertThatExceptionOfType(Error.class).isThrownBy(() -> {
	    Config c = ConfigHelpers.initConfig(filename);
	}).withMessage("The file [%s] is not a normal file.", filename);
    }
    
    @Test
    public void a_config_object_should_be_returned_if_the_file_exists(){
	final String filename = "sample-config.conf";	
	Config c = ConfigHelpers.initConfig(filename);
	assertThat(c).isInstanceOf(Config.class);
    }
}
