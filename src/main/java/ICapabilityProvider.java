import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by ravit on 05/12/2016.
 */
interface ICapabilityProvider {

    void SetAdditionalCapability(AdditionalCapability additionalCapability);

    DesiredCapabilities FinalizeCapabilities();
}

