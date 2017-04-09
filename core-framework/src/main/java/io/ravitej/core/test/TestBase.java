package io.ravitej.core.test;

import com.typesafe.config.Config;
import io.ravitej.core.framework.config.Environments;
import io.ravitej.core.framework.config.SuiteSettings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


/**
 * Created by ravit on 03/02/2017.
 */
public abstract class TestBase {

    protected final Config environments;
    protected final Config suiteSettings;

    public TestBase(String env){
        environments = Environments.load(env);
        suiteSettings = SuiteSettings.load();
    }

    @BeforeClass
    public static void TestBaseBeforeClass(){

    }

    @Before
    public void TestBaseBeforeEachTest(){

    }

    @After
    public void TestBaseAfterEachTest(){

    }

    @AfterClass
    public static void TestBaseAfterClass(){

    }
}
