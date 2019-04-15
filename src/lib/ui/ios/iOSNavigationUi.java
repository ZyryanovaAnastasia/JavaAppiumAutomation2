package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUi extends NavigationUi {

    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public iOSNavigationUi(RemoteWebDriver driver)
    {
        super(driver);
    }
}
