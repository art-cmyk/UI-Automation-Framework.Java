package io.ravitej.ui.tests;

import com.typesafe.config.ConfigBeanFactory;
import io.ravitej.core.test.TestBase;
import io.ravitej.ui.tests.config.suite.SampleSuiteSettings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Created by ravit on 03/02/2017.
 */
public class UITestBase extends TestBase {

    protected SampleSuiteSettings settings;
    public UITestBase(String env) {
        super(env);
        settings = ConfigBeanFactory.create(super.suiteSettings, SampleSuiteSettings.class);
    }

    @BeforeClass
    public static void UITestBaseBeforeClass(){
    }

    @Before
    public void UITestBaseBeforeEachTest(){

    }

    @After
    public void UITestBaseAfterEachTest(){

    }

    @AfterClass
    public static void UITestBaseAfterClass(){

    }
}
